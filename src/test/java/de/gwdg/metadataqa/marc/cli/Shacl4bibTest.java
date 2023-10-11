package de.gwdg.metadataqa.marc.cli;

import de.gwdg.metadataqa.api.calculator.output.MetricCollector;
import de.gwdg.metadataqa.api.calculator.output.OutputCollector;
import de.gwdg.metadataqa.api.configuration.ConfigurationReader;
import de.gwdg.metadataqa.api.configuration.SchemaConfiguration;
import de.gwdg.metadataqa.api.configuration.schema.Rule;
import de.gwdg.metadataqa.api.interfaces.MetricResult;
import de.gwdg.metadataqa.api.json.DataElement;
import de.gwdg.metadataqa.api.rule.RuleCatalog;
import de.gwdg.metadataqa.api.rule.RuleCheckingOutputType;
import de.gwdg.metadataqa.api.schema.BaseSchema;
import de.gwdg.metadataqa.api.schema.Schema;
import de.gwdg.metadataqa.api.util.CompressionLevel;
import de.gwdg.metadataqa.api.util.FileUtils;
import de.gwdg.metadataqa.marc.CsvUtils;
import de.gwdg.metadataqa.marc.RuleCatalogUtils;
import de.gwdg.metadataqa.marc.cli.utils.BibSelector;
import de.gwdg.metadataqa.marc.cli.utils.MarcSpecSelector;
import de.gwdg.metadataqa.marc.cli.utils.RecordIterator;
import de.gwdg.metadataqa.marc.dao.DataField;
import de.gwdg.metadataqa.marc.dao.record.Marc21Record;
import de.gwdg.metadataqa.marc.definition.tags.tags20x.Tag245;
import de.gwdg.metadataqa.marc.definition.tags.tags3xx.Tag300;
import org.apache.commons.cli.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class Shacl4bibTest extends CliTestUtils {

  private String inputFile;
  private String outputDir;
  private List<String> outputFiles;

  @Before
  public void setUp() throws Exception {
    inputFile = getPath("src/test/resources/alephseq/alephseq-example3.txt");
    outputDir = getPath("src/test/resources/output");
    outputFiles = Arrays.asList(
      "shacl.csv"
    );
  }

  @Test
  public void contructor_kbr() throws ParseException, IOException {
    clearOutput(outputDir, outputFiles);

    Shacl4bib processor = new Shacl4bib(new String[]{
      "--defaultRecordType", "BOOKS",
      "--marcVersion", "GENT",
      "--alephseq",
      "--outputDir", outputDir,
      "--shaclConfigurationFile", getPath("src/test/resources/shacl/kbr-small.yaml"),
      "--shaclOutputFile", "shacl.csv",
      inputFile
    });
    RecordIterator iterator = new RecordIterator(processor);
    iterator.start();

    for (String outputFile : outputFiles) {
      File output = new File(outputDir, outputFile);
      assertTrue(outputFile + " should exist", output.exists());
      List<String> lines = FileUtils.readLinesFromFile(output.getAbsolutePath());
      if (outputFile.equals("shacl.csv")) {
        assertEquals(2, lines.size());
        assertEquals("id,040$a.minCount,040$a.pattern", lines.get(0).trim());
        assertEquals("000000002,1,0", lines.get(1).trim());
      }
    }
  }

  @Test
  public void main() throws ParseException, IOException {
    clearOutput(outputDir, outputFiles);

    Shacl4bib.main(new String[]{
      "--defaultRecordType", "BOOKS",
      "--marcVersion", "GENT",
      "--alephseq",
      "--outputDir", outputDir,
      "--shaclConfigurationFile", getPath("src/test/resources/shacl/kbr-small.yaml"),
      "--shaclOutputFile", "shacl.csv",
      inputFile
    });

    for (String outputFile : outputFiles) {
      File output = new File(outputDir, outputFile);
      assertTrue(outputFile + " should exist", output.exists());
      List<String> lines = FileUtils.readLinesFromFile(output.getAbsolutePath());
      if (outputFile.equals("shacl.csv")) {
        assertEquals(2, lines.size());
        assertEquals("id,040$a.minCount,040$a.pattern", lines.get(0).trim());
        assertEquals("000000002,1,0", lines.get(1).trim());
      }
    }
  }

  @Test
  public void main_json() throws ParseException, IOException {
    clearOutput(outputDir, outputFiles);

    Shacl4bib.main(new String[]{
      "--defaultRecordType", "BOOKS",
      "--marcVersion", "GENT",
      "--alephseq",
      "--outputDir", outputDir,
      "--shaclConfigurationFile", getPath("src/test/resources/shacl/kbr-small.json"),
      "--shaclOutputFile", "shacl.csv",
      inputFile
    });

    for (String outputFile : outputFiles) {
      File output = new File(outputDir, outputFile);
      assertTrue(outputFile + " should exist", output.exists());
      List<String> lines = FileUtils.readLinesFromFile(output.getAbsolutePath());
      if (outputFile.equals("shacl.csv")) {
        assertEquals(2, lines.size());
        assertEquals("id,040$a.1,040$a.2", lines.get(0).trim());
        assertEquals("000000002,1,0", lines.get(1).trim());
      }
    }
  }

  @Test
  public void regex_1() {
    String text = "116 p.";
    Pattern pattern = Pattern.compile("^.*\\d+ p\\.?\\s*$");
    assertTrue("should fit", pattern.matcher(text).matches());
  }

  @Test
  public void regex_2() {
    String text = "116 € p.";
    Pattern pattern = Pattern.compile("^.*€.*$");
    assertTrue("should fit", pattern.matcher(text).matches());
  }

  @Test
  public void testField_without_subfield() {
    Marc21Record marcRecord = new Marc21Record("u2407796");
    marcRecord.addDataField(new DataField(Tag245.getInstance(),"0", "0",
      "6", "880-01",
      "a", "iPhone the Bible wan jia sheng jing."
    ));

    Schema schema = new BaseSchema().addField(new DataElement("245", "245")
      .setRule(List.of(new Rule().withId("245").withMinCount(1))));
    RuleCatalog ruleCatalog = RuleCatalogUtils.create(schema);

    MarcSpecSelector selector = new MarcSpecSelector(marcRecord);
    assertEquals("880-01 iPhone the Bible wan jia sheng jing.", selector.extract("245").get(0));
    List<Object> values = RuleCatalogUtils.extract(ruleCatalog, ruleCatalog.measure(selector));
    assertEquals("1", CsvUtils.createCsvFromObjects(values).trim());
  }

  @Test
  public void testField_depends_on_existing_fiels() {
    Marc21Record marcRecord = new Marc21Record("u2407796");
    marcRecord.addDataField(new DataField(Tag245.getInstance(),"0", "0",
            "6", "880-01",
            "a", "iPhone the Bible wan jia sheng jing."
    ));
    marcRecord.addDataField(new DataField(Tag300.getInstance()," ", " ",
            "a", "tIII, 83 Bl.",
            "b", "graph. Darst."
    ));

    Schema schema = new BaseSchema()
      .addField(new DataElement("245", "245").setRule(List.of(new Rule().withId("245").withMinCount(1).withHidden(true))))
      .addField(new DataElement("300a", "300$a").setRule(List.of(new Rule().withId("300$a").withAnd(
              List.of(new Rule().withDependencies(List.of("245")), new Rule().withMinCount(1))
      ))))
    ;
    RuleCatalog ruleCatalog = RuleCatalogUtils.create(schema);

    MarcSpecSelector selector = new MarcSpecSelector(marcRecord);
    assertEquals("880-01 iPhone the Bible wan jia sheng jing.", selector.extract("245").get(0));
    List<Object> values = RuleCatalogUtils.extract(ruleCatalog, ruleCatalog.measure(selector));
    System.err.println(ruleCatalog.getHeader());
    assertEquals("1", CsvUtils.createCsvFromObjects(values).trim());
  }

  @Test
  public void testField_fail_if_depends_on_nonexisting_field() {
    Marc21Record marcRecord = new Marc21Record("u2407796");
    marcRecord.addDataField(new DataField(Tag245.getInstance(),"0", "0", "6", "880-01", "a", "iPhone the Bible wan jia sheng jing."));
    marcRecord.addDataField(new DataField(Tag300.getInstance()," ", " ", "a", "tIII, 83 Bl.", "b", "graph. Darst."));

    Schema schema = new BaseSchema()
            .addField(new DataElement("245", "245").setRule(List.of(new Rule().withId("245").withMinCount(1).withHidden(true))))
            .addField(new DataElement("100", "100").setRule(List.of(new Rule().withId("100").withMinCount(1).withHidden(true))))
            .addField(new DataElement("300a", "300$a").setRule(List.of(new Rule().withId("300$a").withAnd(
                    List.of(new Rule().withDependencies(List.of("100")), new Rule().withMinCount(1))
            ))))
            ;
    RuleCatalog ruleCatalog = RuleCatalogUtils.create(schema);

    MarcSpecSelector selector = new MarcSpecSelector(marcRecord);
    assertEquals("880-01 iPhone the Bible wan jia sheng jing.", selector.extract("245").get(0));
    List<Object> values = RuleCatalogUtils.extract(ruleCatalog, ruleCatalog.measure(selector));
    System.err.println(ruleCatalog.getHeader());
    assertEquals("0", CsvUtils.createCsvFromObjects(values).trim());
  }
}