package de.gwdg.metadataqa.marc.definition.tags.tags1xx;

import de.gwdg.metadataqa.marc.Code;
import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.Indicator;
import de.gwdg.metadataqa.marc.definition.MarcVersion;
import de.gwdg.metadataqa.marc.definition.general.codelist.RelatorCodes;
import de.gwdg.metadataqa.marc.definition.general.parser.LinkageParser;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Main Entry - Personal Name
 * https://www.loc.gov/marc/bibliographic/bd100.html
 */
public class Tag100 extends DataFieldDefinition {

  private static Tag100 uniqueInstance;

  private Tag100() {
    initialize();
    postCreation();
  }

  public static Tag100 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag100();
    return uniqueInstance;
  }

  private void initialize() {

    tag = "100";
    label = "Main Entry - Personal Name";
    mqTag = "MainPersonalName";
    cardinality = Cardinality.Nonrepeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd100.html";

    ind1 = new Indicator("Type of personal name entry element")
      .setCodes(
        "0", "Forename",
        "1", "Surname",
        "3", "Family name"
      )
      .putVersionSpecificCodes(MarcVersion.SZTE, Arrays.asList(
        new Code("2", "Multiple surname")
      ))
      .setMqTag("type")
      .setFrbrFunctions(UseIdentify, ManagementProcess, ManagementSort);

    ind2 = new Indicator();

    setSubfieldsWithCardinality(
      "a", "Personal name", "NR",
      "b", "Numeration", "NR",
      "c", "Titles and words associated with a name", "R",
      "d", "Dates associated with a name", "NR",
      "e", "Relator term", "R",
      "f", "Date of a work", "NR",
      "g", "Miscellaneous information", "R",
      "j", "Attribution qualifier", "R",
      "k", "Form subheading", "R",
      "l", "Language of a work", "NR",
      "n", "Number of part/section of a work", "R",
      "p", "Name of part/section of a work", "R",
      "q", "Fuller form of name", "NR",
      "t", "Title of a work", "NR",
      "u", "Affiliation", "NR",
      "0", "Authority record control number or standard number", "R",
      "4", "Relator code", "R",
      "6", "Linkage", "NR",
      "8", "Field link and sequence number", "R"
    );

    getSubfield("4").setCodeList(RelatorCodes.getInstance());

    getSubfield("6").setContentParser(LinkageParser.getInstance());

    getSubfield("a").setMqTag("personalName")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("b").setMqTag("numeration")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("c").setMqTag("titlesAndWords")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("d").setMqTag("dates")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("e").setMqTag("relatorTerm")
      .setFrbrFunctions(DiscoveryIdentify);
    getSubfield("f").setMqTag("dateOfAWork")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("g").setMqTag("miscellaneous");
    getSubfield("j").setMqTag("attributionQualifier")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("k").setMqTag("formSubheading")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("l").setMqTag("language")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("n").setMqTag("numberOfPart")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("p").setMqTag("nameOfPart")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("q").setMqTag("fullerForm")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("t").setMqTag("titleOfAWork")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify);
    getSubfield("u").setMqTag("affiliation")
      .setFrbrFunctions(DiscoveryIdentify);
    getSubfield("0").setMqTag("authorityRecordControlNumber");
    getSubfield("4").setMqTag("relatorCode")
      .setFrbrFunctions(DiscoveryIdentify);
    getSubfield("6").setMqTag("linkage")
      .setFrbrFunctions(UseIdentify, ManagementProcess);
    getSubfield("8").setMqTag("fieldLink")
      .setFrbrFunctions(UseIdentify, ManagementProcess);
  }
}
