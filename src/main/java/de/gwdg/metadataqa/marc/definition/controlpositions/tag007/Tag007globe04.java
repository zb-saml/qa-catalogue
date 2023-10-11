package de.gwdg.metadataqa.marc.definition.controlpositions.tag007;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Physical medium
 * https://www.loc.gov/marc/bibliographic/bd007d.html
 */
public class Tag007globe04 extends ControlfieldPositionDefinition {
  private static Tag007globe04 uniqueInstance;

  private Tag007globe04() {
    initialize();
    extractValidCodes();
  }

  public static Tag007globe04 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag007globe04();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Physical medium";
    id = "007globe04";
    mqTag = "physicalMedium";
    positionStart = 4;
    positionEnd = 5;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007d.html";
    codes = Utils.generateCodes(
      "a", "Paper",
      "b", "Wood",
      "c", "Stone",
      "d", "Metal",
      "e", "Synthetic",
      "f", "Skin",
      "g", "Textile",
      "i", "Plastic",
      "l", "Vinyl",
      "n", "Vellum",
      "p", "Plaster",
      "u", "Unknown",
      "v", "Leather",
      "w", "Parchment",
      "z", "Other",
      "|", "No attempt to code"
    );
    functions = Arrays.asList(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain, UseManage);
  }
}