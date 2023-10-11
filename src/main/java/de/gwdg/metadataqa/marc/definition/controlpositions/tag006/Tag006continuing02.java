package de.gwdg.metadataqa.marc.definition.controlpositions.tag006;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Regularity
 * https://www.loc.gov/marc/bibliographic/bd006.html
 */
public class Tag006continuing02 extends ControlfieldPositionDefinition {
  private static Tag006continuing02 uniqueInstance;

  private Tag006continuing02() {
    initialize();
    extractValidCodes();
  }

  public static Tag006continuing02 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag006continuing02();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Regularity";
    id = "006continuing02";
    mqTag = "regularity";
    positionStart = 2;
    positionEnd = 3;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd006.html";
    codes = Utils.generateCodes(
      "n", "Normalized irregular",
      "r", "Regular",
      "u", "Unknown",
      "x", "Completely irregular",
      "|", "No attempt to code"
    );
    functions = Arrays.asList(DiscoveryIdentify, DiscoverySelect, UseManage);
  }
}