package de.gwdg.metadataqa.marc.cli;

import de.gwdg.metadataqa.api.model.XmlFieldInstance;
import de.gwdg.metadataqa.marc.dao.DataField;
import de.gwdg.metadataqa.marc.dao.record.BibliographicRecord;
import de.gwdg.metadataqa.marc.dao.record.Marc21Record;
import de.gwdg.metadataqa.marc.definition.tags.tags20x.Tag245;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class BibPathCacheTest {

  BibliographicRecord marcRecord;

  @Before
  public void setUp() throws Exception {
    marcRecord = new Marc21Record("u2407796");

    marcRecord.addDataField(
      new DataField(
        Tag245.getInstance(),
        "0", "0",
        "6", "880-01",
        "a", "iPhone the Bible wan jia sheng jing."
      )
    );

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullArgument() {
    new BibPathCache(null);
  }

  @Test
  public void get_existing() {
    BibPathCache pathCache = new BibPathCache(marcRecord);
    List<XmlFieldInstance> results = pathCache.get("245$a");
    assertEquals(1, results.size());
    assertEquals("iPhone the Bible wan jia sheng jing.", results.get(0).getValue());
  }

  @Test
  public void get_nonexistentField() {
    BibPathCache pathCache = new BibPathCache(marcRecord);
    List<XmlFieldInstance> results = pathCache.get("100");
    assertTrue(results.isEmpty());
  }

  @Test
  public void read() {
    BibPathCache pathCache = new BibPathCache(marcRecord);
    assertNull(pathCache.read("245$a", null));
  }

  @Test
  public void testGet1() {
  }

  @Test
  public void testGet2() {
  }

  @Test
  public void getFragment_path() {
    BibPathCache pathCache = new BibPathCache(marcRecord);
    assertNull(pathCache.getFragment("245$a"));
  }

  @Test
  public void getFragment_3arguments() {
    BibPathCache pathCache = new BibPathCache(marcRecord);
    assertNull(pathCache.getFragment("245$a", "245$a", null));
  }

  @Test
  public void getRecordId() {
    BibPathCache pathCache = new BibPathCache(marcRecord);
    assertEquals("u2407796", pathCache.getRecordId());
  }

  @Test
  public void setRecordId() {
    BibPathCache pathCache = new BibPathCache(marcRecord);
    pathCache.setRecordId("xyz");
    assertEquals("u2407796", pathCache.getRecordId());
  }

  @Test
  public void getCache() {
    BibPathCache pathCache = new BibPathCache(marcRecord);
    assertNull(pathCache.getCache());
  }

  @Test
  public void getFragmentCache() {
    BibPathCache pathCache = new BibPathCache(marcRecord);
    assertNull(pathCache.getFragmentCache());
  }

  @Test
  public void getContent() {
    BibPathCache pathCache = new BibPathCache(marcRecord);
    assertNull(pathCache.getContent());
  }
}
