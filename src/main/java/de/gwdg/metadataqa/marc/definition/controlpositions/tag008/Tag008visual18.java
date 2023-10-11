package de.gwdg.metadataqa.marc.definition.controlpositions.tag008;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;

/**
 * Running time for motion pictures and videorecordings
 * https://www.loc.gov/marc/bibliographic/bd008v.html
 */
public class Tag008visual18 extends ControlfieldPositionDefinition {
  private static Tag008visual18 uniqueInstance;

  private Tag008visual18() {
    initialize();
    extractValidCodes();
  }

  public static Tag008visual18 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag008visual18();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Running time for motion pictures and videorecordings";
    id = "008visual18";
    mqTag = "runningTime";
    positionStart = 18;
    positionEnd = 21;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd008v.html";
    codes = Utils.generateCodes(
      "000", "Running time exceeds three characters",
      "001-999", "Running time",
      "nnn", "Not applicable",
      "---", "Unknown",
      "|||", "No attempt to code"
    );
    getCode("001-999").setRange(true);
  }
}