package de.gwdg.metadataqa.marc.analysis.validator;

import de.gwdg.metadataqa.marc.MarcFactory;
import de.gwdg.metadataqa.marc.cli.CliTestUtils;
import de.gwdg.metadataqa.marc.dao.DataField;
import de.gwdg.metadataqa.marc.dao.record.BibliographicRecord;
import de.gwdg.metadataqa.marc.definition.MarcFormat;
import de.gwdg.metadataqa.marc.definition.bibliographic.SchemaType;
import de.gwdg.metadataqa.marc.utils.QAMarcReaderFactory;
import de.gwdg.metadataqa.marc.utils.pica.PicaFieldDefinition;
import de.gwdg.metadataqa.marc.utils.pica.PicaSchemaManager;
import de.gwdg.metadataqa.marc.utils.pica.PicaSchemaReader;
import org.junit.Test;
import org.marc4j.MarcReader;
import org.marc4j.marc.Record;

import static org.junit.Assert.*;

public class DataFieldValidatorTest {

  @Test
  public void validate() {
    DataField field = getDatafield();
    DataFieldValidator validator = new DataFieldValidator(new ValidatorConfiguration().withSchemaType(SchemaType.PICA));
    boolean valid = validator.validate(field);
    assertTrue(valid);
    assertTrue(validator.getValidationErrors().isEmpty());
  }

  private DataField getDatafield() {
    PicaSchemaManager schema = PicaSchemaReader.createSchema(CliTestUtils.getTestResource("pica/k10plus.json"));
    MarcReader reader;
    try {
      reader = QAMarcReaderFactory.getFileReader(MarcFormat.PICA_NORMALIZED, CliTestUtils.getTestResource("pica/pica-with-holdings-info.dat"), null);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    assertTrue(reader.hasNext());
    Record record = reader.next();
    BibliographicRecord marcRecord = MarcFactory.createPicaFromMarc4j(record, schema);

    assertNotNull(marcRecord.getDatafield("036F").get(0).getDefinition());
    assertNull(marcRecord.getDatafield("036F").get(1).getDefinition());

    DataField selected = null;
    for (DataField field : marcRecord.getDatafield("036E")) {
      if (field.getOccurrence() != null) {
        selected = field;
        break;
      }
    }
    assertEquals(PicaFieldDefinition.class, selected.getDefinition().getClass());
    return selected;
  }
}