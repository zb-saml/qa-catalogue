package de.gwdg.metadataqa.marc.definition.controlpositions.tag007;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Specific material designation
 * https://www.loc.gov/marc/bibliographic/bd007f.html
 */
public class Tag007tactile01 extends ControlfieldPositionDefinition {
  private static Tag007tactile01 uniqueInstance;

  private Tag007tactile01() {
    initialize();
    extractValidCodes();
  }

  public static Tag007tactile01 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag007tactile01();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Specific material designation";
    id = "007tactile01";
    mqTag = "specificMaterialDesignation";
    positionStart = 1;
    positionEnd = 2;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007f.html";
    codes = Utils.generateCodes(
      "a", "Moon",
      "b", "Braille",
      "c", "Combination",
      "d", "Tactile, with no writing system",
      "u", "Unspecified",
      "z", "Other",
      "|", "No attempt to code"
    );
    functions = Arrays.asList(DiscoverySelect);
  }
}