package de.gwdg.metadataqa.marc.definition.tags.tags4xx;

import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.MarcVersion;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.Indicator;
import de.gwdg.metadataqa.marc.definition.general.codelist.RelatorCodes;
import de.gwdg.metadataqa.marc.definition.general.parser.LinkageParser;
import de.gwdg.metadataqa.marc.definition.general.validator.ISSNValidator;
import de.gwdg.metadataqa.marc.definition.structure.SubfieldDefinition;

import java.util.Arrays;

/**
 * Series Statement/Added Entry Meeting Name
 * http://www.loc.gov/marc/bibliographic/bd411.html
 */
public class Tag411 extends DataFieldDefinition {

  private static Tag411 uniqueInstance;

  private Tag411() {
    initialize();
    postCreation();
  }

  public static Tag411 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag411();
    return uniqueInstance;
  }

  private void initialize() {

    tag = "411";
    label = "Series Statement/Added Entry Meeting Name";
    mqTag = "SeriesStatementAddedEntryMeetingName";
    cardinality = Cardinality.Repeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd411.html";

    ind1 = new Indicator("Type of meeting name entry element")
      .setCodes(
        "0", "Inverted name",
        "1", "Jurisdiction name",
        "2", "Name in direct order"
      )
      .setMqTag("type");
    ind2 = new Indicator("Pronoun represents main entry")
      .setCodes(
        "0", "Main entry not represented by pronoun",
        "9", "Main entry represented by pronoun"
      )
      .setMqTag("pronounRepresentsMainEntry");

    setSubfieldsWithCardinality(
      "a", "Meeting name or jurisdiction name as entry element", "NR",
      "c", "Location of meeting", "NR",
      "d", "Date of meeting", "NR",
      "e", "Subordinate unit", "R",
      "f", "Date of a work", "NR",
      "g", "Miscellaneous information", "NR",
      "k", "Form subheading", "R",
      "l", "Language of a work", "NR",
      "n", "Number of part/section/meeting", "R",
      "p", "Name of part/section of a work", "R",
      "q", "Name of meeting following jurisdiction name entry element", "NR",
      "t", "Title of a work", "NR",
      "u", "Affiliation", "NR",
      "v", "Volume/sequential designation", "NR",
      "x", "International Standard Serial Number", "NR",
      "4", "Relator code", "R",
      "6", "Linkage", "NR",
      "8", "Field link and sequence number", "R"
    );

    getSubfield("6").setContentParser(LinkageParser.getInstance());
    getSubfield("x").setValidator(ISSNValidator.getInstance());

    getSubfield("a")
      .setMqTag("rdf:value");

    getSubfield("c")
      .setMqTag("locationOfMeeting");

    getSubfield("d")
      .setMqTag("date");

    getSubfield("e")
      .setMqTag("subordinateUnit");

    getSubfield("f")
      .setMqTag("dateOfAWork");

    getSubfield("g")
      .setMqTag("miscellaneous");

    getSubfield("k")
      .setMqTag("formSubheading");

    getSubfield("l")
      .setMqTag("languageOfAWork");

    getSubfield("n")
      .setMqTag("numberOfPart");

    getSubfield("p")
      .setMqTag("nameOfPart");

    getSubfield("q")
      .setMqTag("nameOfMeeting");

    getSubfield("t")
      .setMqTag("title");

    getSubfield("u")
      .setMqTag("affiliation");

    getSubfield("v")
      .setMqTag("volume");

    getSubfield("x")
      .setMqTag("issn");

    getSubfield("4")
      .setMqTag("relationship")
      .setCodeList(RelatorCodes.getInstance());

    getSubfield("6")
      .setBibframeTag("linkage");

    getSubfield("8")
      .setMqTag("fieldLink");

    putVersionSpecificSubfields(MarcVersion.KBR, Arrays.asList(
      new SubfieldDefinition("*", "Link with identifier", "NR").setMqTag("link"),
      new SubfieldDefinition("@", "Language of field", "NR").setMqTag("language"),
      new SubfieldDefinition("#", "number/occurrence of field", "NR").setMqTag("number")
    ));
  }
}
