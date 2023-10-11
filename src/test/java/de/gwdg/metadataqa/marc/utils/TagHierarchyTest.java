package de.gwdg.metadataqa.marc.utils;

import de.gwdg.metadataqa.marc.definition.MarcVersion;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class TagHierarchyTest {

  @Test
  public void testTagHierarchyExisting() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("245$a");
    assertNotNull(tagHierarchy);
    assertEquals("Title", tagHierarchy.getPackageLabel());
    assertEquals("Title Statement", tagHierarchy.getTagLabel());
    assertEquals("Title", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void datafield() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("245");
    assertNotNull(tagHierarchy);
    assertEquals("Title", tagHierarchy.getPackageLabel());
    assertEquals("Title Statement", tagHierarchy.getTagLabel());
    assertEquals(null, tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchyNonExistingSubfield() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("245$x");
    assertNotNull(tagHierarchy);
    assertEquals("Title", tagHierarchy.getPackageLabel());
    assertEquals("Title Statement", tagHierarchy.getTagLabel());
    assertEquals("", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testIssueWith366() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("366$2", MarcVersion.MARC21);
    assertNotNull(tagHierarchy);
    assertEquals("Physical Description", tagHierarchy.getPackageLabel());
    assertEquals("Trade Availability Information", tagHierarchy.getTagLabel());
    assertEquals("Source of availability status code", tagHierarchy.getSubfieldLabel());

    tagHierarchy = TagHierarchy.createFromPath("366$c");
    assertNotNull(tagHierarchy);
    assertEquals("Physical Description", tagHierarchy.getPackageLabel());
    assertEquals("Trade Availability Information", tagHierarchy.getTagLabel());
    assertEquals("Availability status code", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void createFromPath_withGent() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("591$a", MarcVersion.GENT);
    assertNotNull(tagHierarchy);
    assertEquals("Locally defined tags of Gent", tagHierarchy.getPackageLabel());
    assertEquals("Locally defined field in Gent", tagHierarchy.getTagLabel());
    assertEquals("Value", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchySimpleControlField_001() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("001");
    assertNotNull(tagHierarchy);
    assertEquals("Control Fields", tagHierarchy.getPackageLabel());
    assertEquals("Control Number", tagHierarchy.getTagLabel());
    assertEquals("", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchySimpleControlField_003() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("003");
    assertNotNull(tagHierarchy);
    assertEquals("Control Fields", tagHierarchy.getPackageLabel());
    assertEquals("Control Number Identifier", tagHierarchy.getTagLabel());
    assertEquals("", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchySimpleControlField_005() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("005");
    assertNotNull(tagHierarchy);
    assertEquals("Control Fields", tagHierarchy.getPackageLabel());
    assertEquals("Date and Time of Latest Transaction", tagHierarchy.getTagLabel());
    assertEquals("", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchySimpleControlField_006() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("006/16-17");
    assertNotNull(tagHierarchy);
    assertEquals("Control Fields", tagHierarchy.getPackageLabel());
    assertEquals("Additional Material Characteristics", tagHierarchy.getTagLabel());
    assertEquals("16-17", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchySimpleControlField_008() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("008/00-05");
    assertNotNull(tagHierarchy);
    assertEquals("Control Fields", tagHierarchy.getPackageLabel());
    assertEquals("General Information", tagHierarchy.getTagLabel());
    assertEquals("00-05", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchySimpleControlField_id_006() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("006book16");
    assertNotNull(tagHierarchy);
    assertEquals("Control Fields", tagHierarchy.getPackageLabel());
    assertEquals("Additional Material Characteristics", tagHierarchy.getTagLabel());
    assertEquals("Literary form", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchySimpleControlField_id_007() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("007electro01");
    assertNotNull(tagHierarchy);
    assertEquals("Control Fields", tagHierarchy.getPackageLabel());
    assertEquals("Physical Description", tagHierarchy.getTagLabel());
    assertEquals("Specific material designation", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchySimpleControlField_id_007_withUppercase() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("007motionPicture06");
    assertNotNull(tagHierarchy);
    assertEquals("Control Fields", tagHierarchy.getPackageLabel());
    assertEquals("Physical Description", tagHierarchy.getTagLabel());
    assertEquals("Medium for sound", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchySimpleControlField_id_008() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("008all07");
    assertNotNull(tagHierarchy);
    assertEquals("Control Fields", tagHierarchy.getPackageLabel());
    assertEquals("General Information", tagHierarchy.getTagLabel());
    assertEquals("Date 1", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchyLeader_00() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("leader00");
    assertNotNull(tagHierarchy);
    assertEquals("Control Fields", tagHierarchy.getPackageLabel());
    assertEquals("Leader", tagHierarchy.getTagLabel());
    assertEquals("Record length", tagHierarchy.getSubfieldLabel());
  }

  @Test
  public void testTagHierarchyLeader_18() {
    TagHierarchy tagHierarchy = TagHierarchy.createFromPath("leader18");
    assertNotNull(tagHierarchy);
    assertEquals("Control Fields", tagHierarchy.getPackageLabel());
    assertEquals("Leader", tagHierarchy.getTagLabel());
    assertEquals("Descriptive cataloging form", tagHierarchy.getSubfieldLabel());
  }
}
