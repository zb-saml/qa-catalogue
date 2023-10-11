package de.gwdg.metadataqa.marc.utils.keygenerator;

import de.gwdg.metadataqa.marc.MarcSubfield;
import de.gwdg.metadataqa.marc.definition.MarcVersion;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.tags.tags20x.Tag245;
import de.gwdg.metadataqa.marc.model.SolrFieldType;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class DataFieldKeyGeneratorTest {
  DataFieldDefinition definition = Tag245.getInstance();
  MarcSubfield subfield = new MarcSubfield(definition.getSubfield("a"), "a", "test");

  @Test
  public void testMarc() {
    DataFieldKeyGenerator generator = new DataFieldKeyGenerator(definition, SolrFieldType.MARC);
    assertNotNull(generator.forInd1());
    assertEquals("245ind1", generator.forInd1());
    assertEquals("245ind2", generator.forInd2());
    assertEquals("245a", generator.forSubfield(subfield));
  }

  @Test
  public void testHuman() {
    DataFieldKeyGenerator generator = new DataFieldKeyGenerator(definition, SolrFieldType.HUMAN);
    assertNotNull(generator.forInd1());
    assertEquals("Title_titleAddedEntry", generator.forInd1());
    assertEquals("Title_nonfilingCharacters", generator.forInd2());
    assertEquals("Title_mainTitle", generator.forSubfield(subfield));
  }

  @Test
  public void testMixed() {
    DataFieldKeyGenerator generator = new DataFieldKeyGenerator(definition, SolrFieldType.MIXED);
    assertNotNull(generator.forInd1());
    assertEquals("245ind1_Title_titleAddedEntry", generator.forInd1());
    assertEquals("245ind2_Title_nonfilingCharacters", generator.forInd2());
    assertEquals("245a_Title_mainTitle", generator.forSubfield(subfield));
  }

  @Test
  public void testRegex() {
    Pattern nonValidSubfieldCode = Pattern.compile("[^0-9a-zA-Z]");
    assertTrue(nonValidSubfieldCode.matcher("@").matches());
  }

  @Test
  public void testAt() {
    DataFieldKeyGenerator generator = new DataFieldKeyGenerator(definition, SolrFieldType.MIXED);
    assertEquals("245x40_Title_language_KBR", generator.forSubfield(definition.getVersionSpecificSubfield(MarcVersion.KBR, "@")));
  }

  @Test
  public void getTag() {
    DataFieldKeyGenerator generator = new DataFieldKeyGenerator(definition, SolrFieldType.MIXED);
    assertEquals("245", generator.getTag());
  }

  @Test
  public void getIndexTag() {
    DataFieldKeyGenerator generator = new DataFieldKeyGenerator(definition, SolrFieldType.MIXED);
    assertEquals("Title", generator.getIndexTag());
  }
}
