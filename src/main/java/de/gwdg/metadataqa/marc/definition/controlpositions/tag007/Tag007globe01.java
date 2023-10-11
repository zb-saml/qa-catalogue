package de.gwdg.metadataqa.marc.definition.controlpositions.tag007;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Specific material designation
 * https://www.loc.gov/marc/bibliographic/bd007d.html
 */
public class Tag007globe01 extends ControlfieldPositionDefinition {
  private static Tag007globe01 uniqueInstance;

  private Tag007globe01() {
    initialize();
    extractValidCodes();
  }

  public static Tag007globe01 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag007globe01();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Specific material designation";
    id = "007globe01";
    mqTag = "specificMaterialDesignation";
    positionStart = 1;
    positionEnd = 2;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007d.html";
    codes = Utils.generateCodes(
      "a", "Celestial globe",
      "b", "Planetary or lunar globe",
      "c", "Terrestrial globe",
      "e", "Earth moon globe",
      "u", "Unspecified",
      "z", "Other",
      "|", "No attempt to code"
    );
    functions = Arrays.asList(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain, UseManage, UseOperate);
    historicalCodes = Utils.generateCodes(
      "d", "Satellite globe (of our solar system), excluding the earth moon [OBSOLETE, 1997] [CAN/MARC only]"
    );
  }
}