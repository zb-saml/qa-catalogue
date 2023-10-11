package de.gwdg.metadataqa.marc.definition.tags.tags76x;

import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.MarcVersion;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.Indicator;
import de.gwdg.metadataqa.marc.definition.general.Tag76xSubfield7PositionsGenerator;
import de.gwdg.metadataqa.marc.definition.general.codelist.RelatorCodes;
import de.gwdg.metadataqa.marc.definition.general.parser.LinkageParser;
import de.gwdg.metadataqa.marc.definition.general.validator.ISSNValidator;
import de.gwdg.metadataqa.marc.definition.structure.SubfieldDefinition;

import java.util.Arrays;

import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

/**
 * Main Series Entry
 * http://www.loc.gov/marc/bibliographic/bd760.html
 */
public class Tag760 extends DataFieldDefinition {

  private static Tag760 uniqueInstance;

  private Tag760() {
    initialize();
    postCreation();
  }

  public static Tag760 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag760();
    return uniqueInstance;
  }

  private void initialize() {

    tag = "760";
    label = "Main Series Entry";
    mqTag = "Series";
    cardinality = Cardinality.Repeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd760.html";
    setCompilanceLevels("O");

    ind1 = new Indicator("Note controller")
      .setCodes(
        "0", "Display note",
        "1", "Do not display note"
      )
      .setMqTag("noteController")
      .setFrbrFunctions(ManagementProcess, ManagementDisplay);

    ind2 = new Indicator("Display constant controller")
      .setCodes(
        " ", "Main series",
        "8", "No display constant generated"
      )
      .setMqTag("displayConstant")
      .setFrbrFunctions(ManagementDisplay);

    setSubfieldsWithCardinality(
      "a", "Main entry heading", "NR",
      "b", "Edition", "NR",
      "c", "Qualifying information", "NR",
      "d", "Place, publisher, and date of publication", "NR",
      "g", "Related parts", "R",
      "h", "Physical description", "NR",
      "i", "Relationship information", "R",
      "m", "Material-specific details", "NR",
      "n", "Note", "R",
      "o", "Other item identifier", "R",
      "s", "Uniform title", "NR",
      "t", "Title", "NR",
      "w", "Record control number", "R",
      "x", "International Standard Serial Number", "NR",
      "y", "CODEN designation", "NR",
      "4", "Relationship", "R",
      "6", "Linkage", "NR",
      "7", "Control subfield", "NR",
      "8", "Field link and sequence number", "R"
    );

    // TODO: this requires position parser! see e.g. Tag830
    // see http://www.loc.gov/marc/bibliographic/bd76x78x.html
    getSubfield("7").setPositions(Tag76xSubfield7PositionsGenerator.getPositions());

    getSubfield("6").setContentParser(LinkageParser.getInstance());
    getSubfield("x").setValidator(ISSNValidator.getInstance());

    getSubfield("a")
      .setBibframeTag("rdfs:label").setMqTag("rdf:value")
      .setCompilanceLevels("A");

    getSubfield("b")
      .setBibframeTag("editionStatement")
      .setFrbrFunctions(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain)
      .setCompilanceLevels("A");

    getSubfield("c")
      .setBibframeTag("qualifier")
      .setCompilanceLevels("A");

    getSubfield("d")
      .setBibframeTag("provisionActivityStatement")
      .setFrbrFunctions(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain)
      .setCompilanceLevels("O");

    getSubfield("g")
      .setBibframeTag("part")
      .setCompilanceLevels("A");

    getSubfield("h")
      .setBibframeTag("extent")
      .setFrbrFunctions(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain)
      .setCompilanceLevels("A");

    getSubfield("i")
      .setBibframeTag("relation")
      .setFrbrFunctions(DiscoveryIdentify)
      .setCompilanceLevels("O");

    getSubfield("m")
      .setBibframeTag("note").setMqTag("materialSpecificDetails")
      .setCompilanceLevels("A");

    getSubfield("n")
      .setBibframeTag("note")
      .setCompilanceLevels("A");

    getSubfield("o")
      .setMqTag("otherItemIdentifier")
      .setFrbrFunctions(DiscoveryIdentify, DiscoveryObtain)
      .setCompilanceLevels("O");

    getSubfield("s")
      .setBibframeTag("title").setMqTag("uniformTitle")
      .setFrbrFunctions(DiscoveryIdentify)
      .setCompilanceLevels("A");

    getSubfield("t")
      .setBibframeTag("title")
      .setFrbrFunctions(DiscoveryIdentify, DiscoveryObtain)
      .setCompilanceLevels("A");

    getSubfield("w")
      .setMqTag("recordControlNumber")
      .setFrbrFunctions(ManagementIdentify)
      .setCompilanceLevels("A");

    getSubfield("x")
      .setBibframeTag("issn")
      .setFrbrFunctions(DiscoveryIdentify, DiscoveryObtain)
      .setCompilanceLevels("A");

    getSubfield("y")
      .setBibframeTag("coden")
      .setFrbrFunctions(DiscoveryIdentify, DiscoveryObtain)
      .setCompilanceLevels("O");

    getSubfield("4")
      .setMqTag("relationship")
      .setCodeList(RelatorCodes.getInstance())
      .setCompilanceLevels("O");

    getSubfield("6")
      .setBibframeTag("linkage")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess)
      .setCompilanceLevels("A");

    // TODO check FRBR functions
    /*
     7/00  .setFrbrFunctions(ManagementIdentify, ManagementProcess, ManagementSort)
     7/01  .setFrbrFunctions(ManagementIdentify, ManagementProcess, ManagementSort)
     7/02  .setFrbrFunctions(ManagementProcess)
     7/03  .setFrbrFunctions(ManagementProcess)
    */
    getSubfield("7")
      .setMqTag("controlSubfield")
      .setCompilanceLevels("O");

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
