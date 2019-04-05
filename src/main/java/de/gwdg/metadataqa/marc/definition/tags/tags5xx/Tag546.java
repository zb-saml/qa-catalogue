package de.gwdg.metadataqa.marc.definition.tags.tags5xx;

import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.Indicator;
import de.gwdg.metadataqa.marc.definition.general.parser.LinkageParser;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

/**
 * Language Note
 * http://www.loc.gov/marc/bibliographic/bd546.html
 */
public class Tag546 extends DataFieldDefinition {

  private static Tag546 uniqueInstance;

  private Tag546() {
    initialize();
    postCreation();
  }

  public static Tag546 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag546();
    return uniqueInstance;
  }

  private void initialize() {

    tag = "546";
    label = "Language Note";
    bibframeTag = "Language";
    cardinality = Cardinality.Repeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd546.html";

    ind1 = new Indicator();
    ind2 = new Indicator();

    setSubfieldsWithCardinality(
      "a", "Language note", "NR",
      "b", "Information code or alphabet", "R",
      "3", "Materials specified", "NR",
      "6", "Linkage", "NR",
      "8", "Field link and sequence number", "R"
    );

    getSubfield("6").setContentParser(LinkageParser.getInstance());

    getSubfield("a").setBibframeTag("rdfs:label").setMqTag("rdf:value")
      .setFrbrFunctions(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain, UseInterpret);
    getSubfield("b").setBibframeTag("notation").setMqTag("informationCodeOrAlphabet")
      .setFrbrFunctions(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain, UseInterpret);
    getSubfield("3").setMqTag("materialsSpecified")
      .setFrbrFunctions(DiscoveryIdentify);
    getSubfield("6").setBibframeTag("linkage")
      .setFrbrFunctions(UseIdentify, ManagementProcess);
    getSubfield("8").setMqTag("fieldLink")
      .setFrbrFunctions(UseIdentify, ManagementProcess);

    setHistoricalSubfields(
      "z", "Source of note information (SE) [OBSOLETE, 1990]"
    );
  }
}
