package de.gwdg.metadataqa.marc.cli.utils.ignorablerecords;

import de.gwdg.metadataqa.marc.dao.DataField;
import de.gwdg.metadataqa.marc.dao.record.Marc21Record;
import de.gwdg.metadataqa.marc.dao.record.BibliographicRecord;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecordIgnoratorMarc21Test {

  RecordIgnoratorMarc21 conditions;

  @Test
  public void testParse() {
    conditions = new RecordIgnoratorMarc21("STA$s=SUPPRESSED");
    assertEquals(1, conditions.getConditions().size());
  }

  @Test
  public void isIgnorable() {
    conditions = new RecordIgnoratorMarc21("STA$s=SUPPRESSED");

    BibliographicRecord marcRecord = new Marc21Record("test");
    DataField field = new DataField("STA", "  $sSUPPRESSED");
    field.setMarcRecord(marcRecord);
    marcRecord.addDataField(field);

    assertTrue(conditions.isIgnorable(marcRecord));
  }
}