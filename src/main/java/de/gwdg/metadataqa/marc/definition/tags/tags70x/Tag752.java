package de.gwdg.metadataqa.marc.definition.tags.tags70x;

import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.Indicator;
import de.gwdg.metadataqa.marc.definition.SourceSpecificationType;
import de.gwdg.metadataqa.marc.definition.general.codelist.NameAndTitleAuthoritySourceCodes;
import de.gwdg.metadataqa.marc.definition.general.codelist.RelatorCodes;
import de.gwdg.metadataqa.marc.definition.general.parser.LinkageParser;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

/**
 * Added Entry - Hierarchical Place Name
 * http://www.loc.gov/marc/bibliographic/bd752.html
 */
public class Tag752 extends DataFieldDefinition {

  private static Tag752 uniqueInstance;

  private Tag752() {
    initialize();
    postCreation();
  }

  public static Tag752 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag752();
    return uniqueInstance;
  }

  private void initialize() {

    tag = "752";
    label = "Added Entry - Hierarchical Place Name";
    bibframeTag = "HierarchicalGeographic";
    cardinality = Cardinality.Repeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd752.html";

    ind1 = new Indicator();
    ind2 = new Indicator();

    setSubfieldsWithCardinality(
      "a", "Country or larger entity", "R",
      "b", "First-order political jurisdiction", "NR",
      "c", "Intermediate political jurisdiction", "R",
      "d", "City", "NR",
      "e", "Relator term", "R",
      "f", "City subsection", "R",
      "g", "Other nonjurisdictional geographic region and feature", "R",
      "h", "Extraterrestrial area", "R",
      "0", "Authority record control number or standard number", "R",
      "2", "Source of heading or term", "NR",
      "4", "Relationship", "R",
      "6", "Linkage", "NR",
      "8", "Field link and sequence number", "R"
    );

    getSubfield("2").setCodeList(NameAndTitleAuthoritySourceCodes.getInstance());
    getSubfield("4").setCodeList(RelatorCodes.getInstance());

    getSubfield("6").setContentParser(LinkageParser.getInstance());

    getSubfield("a").setBibframeTag("country")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("b").setBibframeTag("country").setMqTag("firstOrderJurisdiction")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("c").setBibframeTag("state")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("d").setBibframeTag("city")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("e").setBibframeTag("relatorTerm");
    getSubfield("f").setBibframeTag("citySection")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("g").setBibframeTag("region")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("h").setBibframeTag("extraterrestrialArea")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("0").setMqTag("authorityRecordControlNumber");
    getSubfield("2").setMqTag("source")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess);
    getSubfield("4").setMqTag("relationship");
    getSubfield("6").setBibframeTag("linkage")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess);
    getSubfield("8").setMqTag("fieldLink")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess);

    sourceSpecificationType = SourceSpecificationType.Subfield2;
  }
}
