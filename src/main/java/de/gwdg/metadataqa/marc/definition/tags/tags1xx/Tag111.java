package de.gwdg.metadataqa.marc.definition.tags.tags1xx;

import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.Indicator;
import de.gwdg.metadataqa.marc.definition.MarcVersion;
import de.gwdg.metadataqa.marc.definition.structure.SubfieldDefinition;
import de.gwdg.metadataqa.marc.definition.SourceSpecificationType;
import de.gwdg.metadataqa.marc.definition.general.codelist.NameAndTitleAuthoritySourceCodes;
import de.gwdg.metadataqa.marc.definition.general.codelist.RelatorCodes;
import de.gwdg.metadataqa.marc.definition.general.parser.LinkageParser;
import de.gwdg.metadataqa.marc.definition.general.parser.RecordControlNumberParser;

import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Main Entry - Meeting Name
 * https://www.loc.gov/marc/bibliographic/bd111.html
 */
public class Tag111 extends DataFieldDefinition {

  private static Tag111 uniqueInstance;

  private Tag111() {
    initialize();
    postCreation();
  }

  public static Tag111 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag111();
    return uniqueInstance;
  }

  private void initialize() {

    tag = "111";
    label = "Main Entry - Meeting Name";
    mqTag = "MainMeetingName";
    cardinality = Cardinality.Nonrepeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd111.html";
    setCompilanceLevels("A", "A");

    ind1 = new Indicator("Type of meeting name entry element")
      .setCodes(
        "0", "Inverted name",
        "1", "Jurisdiction name",
        "2", "Name in direct order"
      )
      .setMqTag("type")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess, ManagementSort);

    ind2 = new Indicator();

    setSubfieldsWithCardinality(
      "a", "Meeting name or jurisdiction name as entry element", "NR",
      "c", "Location of meeting", "R",
      "d", "Date of meeting or treaty signing", "NR",
      "e", "Subordinate unit", "R",
      "f", "Date of a work", "NR",
      "g", "Miscellaneous information", "R",
      "j", "Relator term", "R",
      "k", "Form subheading", "R",
      "l", "Language of a work", "NR",
      "n", "Number of part/section/meeting", "R",
      "p", "Name of part/section of a work", "R",
      "q", "Name of meeting following jurisdiction name entry element", "NR",
      "t", "Title of a work", "NR",
      "u", "Affiliation", "NR",
      "0", "Authority record control number or standard number", "R",
      "1", "Real World Object URI", "R",
      "2", "Source of heading or term", "NR",
      "4", "Relationship", "R",
      "6", "Linkage", "NR",
      "8", "Field link and sequence number", "R"
    );

    getSubfield("6").setContentParser(LinkageParser.getInstance());

    getSubfield("a")
      .setMqTag("rdf:value")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("M", "M");

    getSubfield("c")
      .setMqTag("locationOfMeeting")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("A", "A");

    getSubfield("d")
      .setMqTag("dates")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("A", "A");

    getSubfield("e")
      .setMqTag("subordinateUnit")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("A", "A");

    getSubfield("f")
      .setMqTag("dateOfAWork")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("A");

    getSubfield("g")
      .setMqTag("miscellaneous")
      .setCompilanceLevels("A");

    getSubfield("j")
      .setMqTag("relatorTerm")
      .setCompilanceLevels("O");

    getSubfield("k")
      .setMqTag("formSubheading")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("A");

    getSubfield("l")
      .setMqTag("language")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("A");

    getSubfield("n")
      .setMqTag("numberOfPart")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("A", "A");

    getSubfield("p")
      .setMqTag("nameOfPart")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("A");

    getSubfield("q")
      .setMqTag("followingName")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("A");

    getSubfield("t")
      .setMqTag("titleOfAWork")
      .setFrbrFunctions(DiscoverySearch, DiscoveryIdentify)
      .setCompilanceLevels("A");

    getSubfield("u")
      .setMqTag("affiliation")
      .setFrbrFunctions(DiscoveryIdentify)
      .setCompilanceLevels("O");

    getSubfield("0")
      .setMqTag("authorityRecordControlNumber")
      .setContentParser(RecordControlNumberParser.getInstance())
      .setCompilanceLevels("O");

    getSubfield("1")
      .setMqTag("uri");

    getSubfield("2")
      .setMqTag("source")
      .setCodeList(NameAndTitleAuthoritySourceCodes.getInstance());

    getSubfield("4")
      .setMqTag("relationship")
      .setCodeList(RelatorCodes.getInstance())
      .setFrbrFunctions(DiscoveryIdentify)
      .setCompilanceLevels("O", "A");

    getSubfield("6")
      .setMqTag("linkage")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess)
      .setCompilanceLevels("A", "A");

    getSubfield("8")
      .setMqTag("fieldLink")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess)
      .setCompilanceLevels("O");

    putVersionSpecificSubfields(MarcVersion.NKCR, Arrays.asList(
      new SubfieldDefinition("7", "NKCR Authority ID", "NR"),
      new SubfieldDefinition("9", "NKCR Authority field - tracing form", "NR")
    ));

    putVersionSpecificSubfields(MarcVersion.KBR, Arrays.asList(
      new SubfieldDefinition("*", "Link with identifier", "NR").setMqTag("link"),
      new SubfieldDefinition("@", "Language of field", "NR").setMqTag("language"),
      new SubfieldDefinition("#", "number/occurrence of field", "NR").setMqTag("number")
    ));

    sourceSpecificationType = SourceSpecificationType.Subfield2;
  }
}
