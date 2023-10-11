package de.gwdg.metadataqa.marc.definition.controlpositions.tag007;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Reduction ratio range
 * https://www.loc.gov/marc/bibliographic/bd007h.html
 */
public class Tag007microform05 extends ControlfieldPositionDefinition {
  private static Tag007microform05 uniqueInstance;

  private Tag007microform05() {
    initialize();
    extractValidCodes();
  }

  public static Tag007microform05 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag007microform05();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Reduction ratio range";
    id = "007microform05";
    mqTag = "reductionRatioRange";
    positionStart = 5;
    positionEnd = 6;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007h.html";
    codes = Utils.generateCodes(
      "a", "Low reduction ratio",
      "b", "Normal reduction",
      "c", "High reduction",
      "d", "Very high reduction",
      "e", "Ultra high reduction",
      "u", "Unknown",
      "v", "Reduction rate varies",
      "|", "No attempt to code"
    );
    functions = Arrays.asList(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain, UseOperate);
  }
}