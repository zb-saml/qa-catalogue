package de.gwdg.metadataqa.marc.definition.tags.tags84x;

import de.gwdg.metadataqa.marc.definition.*;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.Indicator;
import de.gwdg.metadataqa.marc.definition.structure.SubfieldDefinition;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Alternate Graphic Representation
 * http://www.loc.gov/marc/bibliographic/bd880.html
 */
public class Tag880 extends DataFieldDefinition {

  private static Tag880 uniqueInstance;

  private Tag880() {
    initialize();
    postCreation();
  }

  public static Tag880 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag880();
    return uniqueInstance;
  }

  private void initialize() {
    tag = "880";
    label = "Alternate Graphic Representation";
    mqTag = "AlternateGraphicRepresentation";
    cardinality = Cardinality.Repeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd880.html";
    setCompilanceLevels("A", "A");

    ind1 = new Indicator();
    ind2 = new Indicator();

    /*
    setSubfieldsWithCardinality(
      "a-z", "Same as associated field", "R",
      "0-5", "Same as associated field", "R",
      "6", "Linkage", "NR",
      "7-9", "Same as associated field", "R"
    );
    */
    // getSubfield("a-z")
    //getSubfieldList()
    // List<String> subfieldCodes = new ArrayList<>();
    subfields = new ArrayList<>();
    subfields.add(new SubfieldDefinition("6", "Linkage", "NR").setParent(this));
    for (char c = 'a'; c <= 'z'; c++)
      subfields.add(
        new SubfieldDefinition(String.valueOf(c), "Same as associated field", "R")
          .setParent(this)
          .setCompilanceLevels("M", "M")
      );
    for (int c = 0; c <= 5; c++)
      subfields.add(
        new SubfieldDefinition(String.valueOf(c), "Same as associated field", "R")
          .setParent(this)
          .setCompilanceLevels("M", "M")
      );
    for (int c = 7; c <= 9; c++)
      subfields.add(
        new SubfieldDefinition(String.valueOf(c), "Same as associated field", "R")
          .setParent(this)
          .setCompilanceLevels("M", "M")
      );
    indexSubfields();

    putVersionSpecificSubfields(MarcVersion.KBR, Arrays.asList(
      new SubfieldDefinition("*", "Link with identifier", "NR").setMqTag("link"),
      new SubfieldDefinition("@", "Language of field", "NR").setMqTag("language"),
      new SubfieldDefinition("#", "number/occurrence of field", "NR").setMqTag("number")
    ));
  }

  public void validate(String tag, MarcVersion marcVersion) {
    DataFieldDefinition definition = TagDefinitionLoader.load(tag, marcVersion);
  }
}
