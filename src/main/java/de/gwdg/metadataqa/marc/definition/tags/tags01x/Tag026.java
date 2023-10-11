package de.gwdg.metadataqa.marc.definition.tags.tags01x;

import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.MarcVersion;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.Indicator;
import de.gwdg.metadataqa.marc.definition.general.codelist.FingerprintSchemeSourceCodes;
import de.gwdg.metadataqa.marc.definition.general.codelist.OrganizationCodes;
import de.gwdg.metadataqa.marc.definition.general.parser.LinkageParser;
import de.gwdg.metadataqa.marc.definition.structure.SubfieldDefinition;

import java.util.Arrays;

import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

/**
 * Fingerprint Identifier
 * http://www.loc.gov/marc/bibliographic/bd026.html
 */
public class Tag026 extends DataFieldDefinition {

  private static Tag026 uniqueInstance;

  private Tag026() {
    initialize();
    postCreation();
  }

  public static Tag026 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag026();
    return uniqueInstance;
  }

  private void initialize() {

    tag = "026";
    label = "Fingerprint Identifier";
    bibframeTag = "Fingerprint";
    cardinality = Cardinality.Repeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd026.html";
    setCompilanceLevels("O");

    ind1 = new Indicator();
    ind2 = new Indicator();

    setSubfieldsWithCardinality(
      "a", "First and second groups of characters", "NR",
      "b", "Third and fourth groups of characters", "NR",
      "c", "Date", "NR",
      "d", "Number of volume or part", "R",
      "e", "Unparsed fingerprint", "NR",
      "2", "Source", "NR",
      "5", "Institution to which field applies", "R",
      "6", "Linkage", "NR",
      "8", "Field link and sequence number", "R"
    );

    getSubfield("2").setCodeList(FingerprintSchemeSourceCodes.getInstance());
    getSubfield("5").setCodeList(OrganizationCodes.getInstance());
    getSubfield("6").setContentParser(LinkageParser.getInstance());

    getSubfield("a")
      .setBibframeTag("rdf:value").setMqTag("firstAndSecondGroups")
      .setFrbrFunctions(DiscoveryIdentify)
      .setCompilanceLevels("A");

    getSubfield("b")
      .setBibframeTag("rdf:value").setMqTag("thirdAndFourthGroups")
      .setFrbrFunctions(DiscoveryIdentify)
      .setCompilanceLevels("A");

    getSubfield("c")
      .setBibframeTag("rdf:value").setMqTag("date")
      .setCompilanceLevels("A");

    getSubfield("d")
      .setBibframeTag("rdf:value").setMqTag("volume")
      .setFrbrFunctions(DiscoveryIdentify, DiscoverySelect)
      .setCompilanceLevels("A");

    getSubfield("e")
      .setBibframeTag("rdf:value").setMqTag("unparsed")
      .setFrbrFunctions(DiscoveryIdentify)
      .setCompilanceLevels("A");

    getSubfield("2")
      .setBibframeTag("source")
      .setCompilanceLevels("A");

    getSubfield("5")
      .setMqTag("institutionToWhichFieldApplies")
      .setFrbrFunctions(ManagementProcess, ManagementDisplay)
      .setCompilanceLevels("A");

    getSubfield("6")
      .setBibframeTag("linkage")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess)
      .setCompilanceLevels("A");

    getSubfield("8")
      .setMqTag("fieldLink")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess)
      .setCompilanceLevels("O");

    putVersionSpecificSubfields(MarcVersion.KBR, Arrays.asList(
      new SubfieldDefinition("*", "Link with identifier", "NR").setMqTag("link"),
      new SubfieldDefinition("@", "Language of field", "NR").setMqTag("language"),
      new SubfieldDefinition("#", "number/occurrence of field", "NR").setMqTag("number")
    ));
  }
}
