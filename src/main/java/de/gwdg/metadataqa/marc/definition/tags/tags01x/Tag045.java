package de.gwdg.metadataqa.marc.definition.tags.tags01x;

import de.gwdg.metadataqa.marc.definition.*;
import de.gwdg.metadataqa.marc.definition.general.parser.LinkageParser;
import de.gwdg.metadataqa.marc.definition.general.validator.Tag054AValidator;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.Indicator;
import de.gwdg.metadataqa.marc.definition.structure.SubfieldDefinition;

import java.util.Arrays;

import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

/**
 * Time Period of Content
 * http://www.loc.gov/marc/bibliographic/bd045.html
 */
public class Tag045 extends DataFieldDefinition {

  private static Tag045 uniqueInstance;

  private Tag045() {
    initialize();
    postCreation();
  }

  public static Tag045 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag045();
    return uniqueInstance;
  }

  private void initialize() {

    tag = "045";
    label = "Time Period of Content";
    bibframeTag = "TemporalCoverage";
    cardinality = Cardinality.Nonrepeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd045.html";
    setCompilanceLevels("O");

    ind1 = new Indicator("Type of time period in subfield $b or $c")
      .setCodes(
        " ", "Subfield $b or $c not present",
        "0", "Single date/time",
        "1", "Multiple single dates/times",
        "2", "Range of dates/times"
      )
      .setMqTag("type")
      .setFrbrFunctions(ManagementIdentify, ManagementProcess);

    ind2 = new Indicator();

    setSubfieldsWithCardinality(
      "a", "Time period code", "R",
      "b", "Formatted 9999 B.C. through C.E. time period", "R",
      "c", "Formatted pre-9999 B.C. time period", "R",
      "6", "Linkage", "NR",
      "8", "Field link and sequence number", "R"
    );

    // TODO: set a parser, move the codes there
    getSubfield("a").setCodes(
      "a0", "before 2999",
      "b0", "2999-2900",
      "b1", "2899-2800",
      "b2", "2799-2700",
      "b3", "2699-2600",
      "b4", "2599-2500",
      "b5", "2499-2400",
      "b6", "2399-2300",
      "b7", "2299-2200",
      "b8", "2199-2100",
      "b9", "2099-2000",
      "c0", "1999-1900",
      "c1", "1899-1800",
      "c2", "1799-1700",
      "c3", "1699-1600",
      "c4", "1599-1500",
      "c5", "1499-1400",
      "c6", "1399-1300",
      "c7", "1299-1200",
      "c8", "1199-1100",
      "c9", "1099-1000",
      "d0", "999-900",
      "d1", "899-800",
      "d2", "799-700",
      "d3", "699-600",
      "d4", "599-500",
      "d5", "499-400",
      "d6", "399-300",
      "d7", "299-200",
      "d8", "199-100",
      "d9", "99-1",
      "e", "1-99",
      "f", "100-199",
      "g", "200-299",
      "h", "300-399",
      "i", "400-499",
      "j", "500-599",
      "k", "600-699",
      "l", "700-799",
      "m", "800-899",
      "n", "900-999",
      "o", "1000-1099",
      "p", "1100-1199",
      "q", "1200-1299",
      "r", "1300-1399",
      "s", "1400-1499",
      "t", "1500-1599",
      "u", "1600-1699",
      "v", "1700-1799",
      "w", "1800-1899",
      "x", "1900-1999",
      "y", "2000-2099"
    );
    getSubfield("a").setValidator(new Tag054AValidator());

    getSubfield("6").setContentParser(LinkageParser.getInstance());

    getSubfield("a")
      .setMqTag("rdf:value")
      .setFrbrFunctions(DiscoverySearch, DiscoverySelect)
      .setCompilanceLevels("A");

    getSubfield("b")
      .setMqTag("timePeriod")
      .setFrbrFunctions(DiscoverySearch, DiscoverySelect)
      .setCompilanceLevels("A");

    getSubfield("c")
      .setMqTag("preBC9999TimePeriod")
      .setFrbrFunctions(DiscoverySearch, DiscoverySelect)
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
