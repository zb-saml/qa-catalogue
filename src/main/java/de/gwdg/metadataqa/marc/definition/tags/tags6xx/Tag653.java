package de.gwdg.metadataqa.marc.definition.tags.tags6xx;

import de.gwdg.metadataqa.marc.definition.*;
import de.gwdg.metadataqa.marc.definition.general.parser.LinkageParser;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.Indicator;
import de.gwdg.metadataqa.marc.definition.structure.SubfieldDefinition;

import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Index Term - Uncontrolled
 * http://www.loc.gov/marc/bibliographic/bd653.html
 */
public class Tag653 extends DataFieldDefinition {

  private static Tag653 uniqueInstance;

  private Tag653() {
    initialize();
    postCreation();
  }

  public static Tag653 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag653();
    return uniqueInstance;
  }

  private void initialize() {

    tag = "653";
    label = "Index Term - Uncontrolled";
    bibframeTag = "Subject";
    mqTag = "UncontrolledIndexTerm";
    cardinality = Cardinality.Repeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd653.html";
    setCompilanceLevels("A");

    ind1 = new Indicator("Level of index term")
      .setCodes(
        " ", "No information provided",
        "0", "No level specified",
        "1", "Primary",
        "2", "Secondary"
      )
      .setMqTag("level");

    ind2 = new Indicator("Type of term or name")
      .setCodes(
        " ", "No information provided",
        "0", "Topical term",
        "1", "Personal name",
        "2", "Corporate name",
        "3", "Meeting name",
        "4", "Chronological term",
        "5", "Geographic name",
        "6", "Genre/form term"
      )
      .setMqTag("type");

    setSubfieldsWithCardinality(
      "a", "Uncontrolled term", "R",
      "6", "Linkage", "NR",
      "8", "Field link and sequence number", "R"
    );

    getSubfield("6").setContentParser(LinkageParser.getInstance());

    getSubfield("a")
      .setMqTag("rdf:value")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("M");

    getSubfield("6")
      .setMqTag("linkage")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess)
      .setCompilanceLevels("A");

    getSubfield("8")
      .setMqTag("fieldLink")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess)
      .setCompilanceLevels("O");

    putVersionSpecificSubfields(MarcVersion.FENNICA, Arrays.asList(
      new SubfieldDefinition("9", "Sanastokoodi", "NR")
    ));

    putVersionSpecificSubfields(MarcVersion.MARC21NO, Arrays.asList(
      new SubfieldDefinition("9", "Language code", "NR")
    ));

    putVersionSpecificSubfields(MarcVersion.KBR, Arrays.asList(
      new SubfieldDefinition("*", "Link with identifier", "NR").setMqTag("link"),
      new SubfieldDefinition("@", "Language of field", "NR").setMqTag("language"),
      new SubfieldDefinition("#", "number/occurrence of field", "NR").setMqTag("number")
    ));

    sourceSpecificationType = SourceSpecificationType.Indicator2;
  }
}
