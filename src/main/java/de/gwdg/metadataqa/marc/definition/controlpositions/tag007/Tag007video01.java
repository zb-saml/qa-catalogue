package de.gwdg.metadataqa.marc.definition.controlpositions.tag007;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Specific material designation
 * https://www.loc.gov/marc/bibliographic/bd007v.html
 */
public class Tag007video01 extends ControlfieldPositionDefinition {
  private static Tag007video01 uniqueInstance;

  private Tag007video01() {
    initialize();
    extractValidCodes();
  }

  public static Tag007video01 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag007video01();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Specific material designation";
    id = "007video01";
    mqTag = "specificMaterialDesignation";
    positionStart = 1;
    positionEnd = 2;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007v.html";
    codes = Utils.generateCodes(
      "c", "Videocartridge",
      "d", "Videodisc",
      "f", "Videocassette",
      "r", "Videoreel",
      "u", "Unspecified",
      "z", "Other",
      "|", "No attempt to code"
    );
    functions = Arrays.asList(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain, UseManage, UseOperate);
    historicalCodes = Utils.generateCodes(
      " ", "Not applicable or no attempt to code [OBSOLETE, 1980]",
      "n", "Not applicable [OBSOLETE, 1981]"
    );
  }
}