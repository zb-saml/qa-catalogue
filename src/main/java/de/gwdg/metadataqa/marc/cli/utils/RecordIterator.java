package de.gwdg.metadataqa.marc.cli.utils;

import de.gwdg.metadataqa.marc.cli.parameters.CommonParameters;
import de.gwdg.metadataqa.marc.dao.Leader;
import de.gwdg.metadataqa.marc.MarcFactory;
import de.gwdg.metadataqa.marc.dao.record.BibliographicRecord;
import de.gwdg.metadataqa.marc.cli.processor.BibliographicInputProcessor;
import de.gwdg.metadataqa.marc.definition.DataSource;
import de.gwdg.metadataqa.marc.definition.MarcVersion;
import de.gwdg.metadataqa.marc.definition.bibliographic.SchemaType;
import de.gwdg.metadataqa.marc.utils.QAMarcReaderFactory;
import de.gwdg.metadataqa.marc.utils.marcreader.AlephseqMarcReader;
import de.gwdg.metadataqa.marc.utils.pica.PicaSchemaManager;
import de.gwdg.metadataqa.marc.utils.pica.PicaSchemaReader;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.marc4j.MarcException;
import org.marc4j.MarcReader;
import org.marc4j.marc.Record;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

/**
 * usage:
 * java -cp target/qa-catalogue-0.1-SNAPSHOT-jar-with-dependencies.jar de.gwdg.metadataqa.marc.cli.Validator [MARC21 file]
 * @author Péter Király <peter.kiraly at gwdg.de>
 */
public class RecordIterator {

  private static final Logger logger = Logger.getLogger(RecordIterator.class.getCanonicalName());
  private final BibliographicInputProcessor processor;
  private int i = 0;
  private String lastKnownId = "";
  private CommonParameters parameters;
  private String replacementInControlFields;
  private MarcVersion marcVersion;
  private Leader.Type defaultRecordType;
  private DecimalFormat decimalFormat;
  private PicaSchemaManager picaSchema;
  private String status = "waits";

  public RecordIterator(BibliographicInputProcessor processor) {
    this.processor = processor;
    status = "initialized";
  }

  public void start() {

    long start = System.currentTimeMillis();
    processor.beforeIteration();
    parameters = processor.getParameters();

    marcVersion = parameters.getMarcVersion();
    defaultRecordType = parameters.getDefaultRecordType();
    replacementInControlFields = parameters.getReplacementInControlFields();
    decimalFormat = new DecimalFormat();
    if (parameters.isPica()) {
      picaSchema = PicaSchemaReader.createSchemaManager(parameters.getPicaSchemaFile());
    }

    if (processor.getParameters().doLog())
      logger.info("marcVersion: " + marcVersion.getCode() + ", " + marcVersion.getLabel());

    if (parameters.getDataSource().equals(DataSource.FILE)) {
      String[] inputFileNames = processor.getParameters().getArgs();
      for (String inputFileName : inputFileNames) {
        if (!processor.readyToProcess())
          break;
        processFile(inputFileName);
      }
    } else if (parameters.getDataSource().equals(DataSource.STREAM)) {
      try {
        MarcReader reader = getMarcStreamReader(processor.getParameters());
        processContent(reader, "stream");
      } catch (Exception e) {
        logger.severe(e.getLocalizedMessage());
      }
    }

    processor.afterIteration(i);

    long end = System.currentTimeMillis();
    long duration = (end - start) / 1000;
    if (parameters.doLog())
      logger.log(Level.INFO, "Bye! It took: " + DurationFormatUtils.formatDuration(end - start, "d HH:mm:ss", true));
      // logger.log(Level.INFO, "Bye! It took: " + LocalTime.MIN.plusSeconds(duration).format(DateTimeFormatter.ofPattern("d HH:mm:ss")));

    status = "done";
  }

  private void processFile(String inputFileName) {
    var path = Paths.get(inputFileName);
    String fileName = path.getFileName().toString();

    if (processor.getParameters().doLog())
      logger.info("processing: " + fileName);

    try {
      processor.fileOpened(path);
      MarcReader reader = getMarcFileReader(processor.getParameters(), path);
      processContent(reader, fileName);
      if (processor.getParameters().doLog())
        logger.log(Level.INFO, "Finished processing file. Processed {0} records.", new Object[]{decimalFormat.format(i)});

    } catch (SolrServerException ex) {
      if (processor.getParameters().doLog())
        logger.severe(ex.toString());
      System.exit(0);
    } catch (Exception ex) {
      if (processor.getParameters().doLog()) {
        logger.severe("Other exception: " + ex.toString());

        for (StackTraceElement element : ex.getStackTrace()) {
          System.err.println(element.toString());
        }
        Throwable exa = ex;
        while (exa.getCause() != null) {
          System.err.println("cause");
          exa = exa.getCause();
          for (StackTraceElement element : exa.getStackTrace()) {
            System.err.println(element.toString());
          }
        }
      }
      logger.log(Level.SEVERE, "start", ex);
      System.exit(0);
    }
  }

  private void processContent(MarcReader reader, String fileName) {
    while (reader.hasNext()) {
      if (!processor.readyToProcess())
        break;

      Record marc4jRecord = getNextMarc4jRecord(i, lastKnownId, reader);
      i++;
      if (marc4jRecord == null)
        continue;

      if (isUnderOffset(processor.getParameters().getOffset(), i))
        continue;

      if (isOverLimit(processor.getParameters().getLimit(), i))
        break;

      if (marc4jRecord.getControlNumber() == null) {
        logger.severe("No record number at " + i + ", last known ID: " + lastKnownId);
        System.err.println(marc4jRecord);
        continue;
      } else {
        lastKnownId = marc4jRecord.getControlNumber();
      }

      if (skipRecord(marc4jRecord))
        continue;

      try {
        processor.processRecord(marc4jRecord, i);

        BibliographicRecord marcRecord = transformMarcRecord(marc4jRecord);
        try {
          processor.processRecord(marcRecord, i);
        } catch(Exception e) {
          logger.log(Level.SEVERE, "start", e);
        }

        if (i % 100000 == 0 && processor.getParameters().doLog())
          logger.log(Level.INFO, "{0}/{1} ({2})", new Object[]{fileName, decimalFormat.format(i), marcRecord.getId()});
      } catch (IllegalArgumentException e) {
        extracted(i, marc4jRecord, e, "Error (illegal argument) with record '%s'. %s");
      } catch (Exception e) {
        extracted(i, marc4jRecord, e, "Error (general) with record '%s'. %s");
      }
    }
  }

  private BibliographicRecord transformMarcRecord(Record marc4jRecord) {
    if (parameters.getSchemaType().equals(SchemaType.MARC21))
      return MarcFactory.createFromMarc4j(marc4jRecord, defaultRecordType, marcVersion, replacementInControlFields);
    else
      return MarcFactory.createPicaFromMarc4j(marc4jRecord, picaSchema);
  }

  private MarcReader getMarcFileReader(CommonParameters parameters, Path path) throws Exception {
    MarcReader marcReader;
    if (path.toString().endsWith(".gz")) {
      marcReader = QAMarcReaderFactory.getStreamReader(
        parameters.getMarcFormat(),
        new GZIPInputStream(new FileInputStream(path.toFile())),
        parameters);
    } else {
      marcReader = QAMarcReaderFactory.getFileReader(parameters.getMarcFormat(), path.toString(), parameters);
    }
    if (parameters.getAlephseqLineType() != null && marcReader instanceof AlephseqMarcReader) {
      ((AlephseqMarcReader) marcReader).setLineType(parameters.getAlephseqLineType());
    }
    return marcReader;
  }

  private MarcReader getMarcStreamReader(CommonParameters parameters) throws Exception {
    return QAMarcReaderFactory.getStreamReader(parameters.getMarcFormat(), parameters.getStream(), parameters);
  }

  private Record getNextMarc4jRecord(int i, String lastKnownId, MarcReader reader) {
    Record marc4jRecord = null;
    try {
      marc4jRecord = reader.next();
    } catch (MarcException | NegativeArraySizeException | NumberFormatException e) {
      logger.severe(
        String.format(
          "MARC record parsing problem at record #%d (last known ID: %s): %s",
          (i + 1), lastKnownId, e.getLocalizedMessage()));
    } catch (Exception e) {
      logger.log(Level.SEVERE, "start", e);
    }
    return marc4jRecord;
  }

  private boolean skipRecord(Record marc4jRecord) {
    return processor.getParameters().hasId()
      && !marc4jRecord.getControlNumber().trim().equals(processor.getParameters().getId());
  }

  private void extracted(int i, Record marc4jRecord, Exception e, String message) {
    if (marc4jRecord.getControlNumber() == null)
      logger.severe("No record number at " + i);
    if (processor.getParameters().doLog())
      logger.severe(String.format(message, marc4jRecord.getControlNumber(), e.getMessage()));
    logger.log(Level.SEVERE, "start", e);
  }

  private static boolean isOverLimit(int limit, int i) {
    return limit > -1 && i > limit;
  }

  private static boolean isUnderOffset(int offset, int i) {
    return offset > -1 && i < offset;
  }

  private static void printHelp(Options opions) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("java -cp qa-catalogue.jar de.gwdg.metadataqa.marc.cli.Validator [options] [file]",
      opions);
  }

  public String getStatus() {
    return status;
  }
}