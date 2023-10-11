package de.gwdg.metadataqa.marc.definition.controlpositions.tag008;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;

/**
 * Type of cartographic material
 * https://www.loc.gov/marc/bibliographic/bd008p.html
 */
public class Tag008map25 extends ControlfieldPositionDefinition {
  private static Tag008map25 uniqueInstance;

  private Tag008map25() {
    initialize();
    extractValidCodes();
  }

  public static Tag008map25 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag008map25();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Type of cartographic material";
    id = "008map25";
    mqTag = "typeOfCartographicMaterial";
    positionStart = 25;
    positionEnd = 26;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd008p.html";
    codes = Utils.generateCodes(
      "a", "Single map",
      "b", "Map series",
      "c", "Map serial",
      "d", "Globe",
      "e", "Atlas",
      "f", "Separate supplement to another work",
      "g", "Bound as part of another work",
      "u", "Unknown",
      "z", "Other",
      "|", "No attempt to code"
    );
  }
}