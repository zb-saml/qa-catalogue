package de.gwdg.metadataqa.marc.definition.tags.tags01x;

import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.general.parser.RecordControlNumberParser;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.Indicator;
import de.gwdg.metadataqa.marc.definition.MarcVersion;
import de.gwdg.metadataqa.marc.definition.structure.SubfieldDefinition;
import de.gwdg.metadataqa.marc.definition.general.codelist.GeographicAreaSourceCodes;
import de.gwdg.metadataqa.marc.definition.general.codelist.GeographicAreaCodes;
import de.gwdg.metadataqa.marc.definition.general.parser.LinkageParser;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Geographic Area Code
 * http://www.loc.gov/marc/bibliographic/bd043.html
 */
public class Tag043 extends DataFieldDefinition {

  private static Tag043 uniqueInstance;

  private Tag043() {
    initialize();
    postCreation();
  }

  public static Tag043 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag043();
    return uniqueInstance;
  }

  private void initialize() {
    tag = "043";
    label = "Geographic Area Code";
    bibframeTag = "GeographicCoverage";
    cardinality = Cardinality.Repeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd043.html";
    setCompilanceLevels("A");

    ind1 = new Indicator();
    ind2 = new Indicator();

    setSubfieldsWithCardinality(
      "a", "Geographic area code", "R",
      "b", "Local GAC code", "R",
      "c", "ISO code", "R",
      "0", "Authority record control number or standard number", "R",
      "1", "Real World Object URI", "R",
      "2", "Source of local code", "R",
      "6", "Linkage", "NR",
      "8", "Field link and sequence number", "R"
    );

    getSubfield("a").setCodeList(GeographicAreaCodes.getInstance());
    getSubfield("2").setCodeList(GeographicAreaSourceCodes.getInstance());

    getSubfield("6").setContentParser(LinkageParser.getInstance());

    getSubfield("a")
      .setMqTag("code")
      .setFrbrFunctions(DiscoverySearch, DiscoverySelect)
      .setCompilanceLevels("M");

    getSubfield("b")
      .setMqTag("localGACcode")
      .setFrbrFunctions(DiscoverySearch, DiscoverySelect)
      .setCompilanceLevels("O");

    getSubfield("c")
      .setMqTag("ISOcode")
      .setFrbrFunctions(DiscoverySearch, DiscoverySelect);

    getSubfield("0")
      .setMqTag("authorityRecordControlNumber")
      .setContentParser(RecordControlNumberParser.getInstance());

    getSubfield("1")
      .setMqTag("uri");

    getSubfield("2")
      .setMqTag("source")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess)
      .setCompilanceLevels("A");

    getSubfield("6")
      .setBibframeTag("linkage")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess)
      .setCompilanceLevels("A");

    getSubfield("8")
      .setMqTag("fieldLink")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess)
      .setCompilanceLevels("O");

    putVersionSpecificSubfields(MarcVersion.NKCR, Arrays.asList(
      new SubfieldDefinition("7", "NKCR Authority ID", "NR")
    ));

    putVersionSpecificSubfields(MarcVersion.KBR, Arrays.asList(
      new SubfieldDefinition("*", "Link with identifier", "NR").setMqTag("link"),
      new SubfieldDefinition("@", "Language of field", "NR").setMqTag("language"),
      new SubfieldDefinition("#", "number/occurrence of field", "NR").setMqTag("number")
    ));
  }
}
