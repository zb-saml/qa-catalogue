package de.gwdg.metadataqa.marc.definition.controlpositions.tag007;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Quality assurance targets
 * https://www.loc.gov/marc/bibliographic/bd007c.html
 */
public class Tag007electro10 extends ControlfieldPositionDefinition {
  private static Tag007electro10 uniqueInstance;

  private Tag007electro10() {
    initialize();
    extractValidCodes();
  }

  public static Tag007electro10 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag007electro10();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Quality assurance targets";
    id = "007electro10";
    mqTag = "qualityAssuranceTargets";
    positionStart = 10;
    positionEnd = 11;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007c.html";
    codes = Utils.generateCodes(
      "a", "Absent",
      "n", "Not applicable",
      "p", "Present",
      "u", "Unknown",
      "|", "No attempt to code"
    );
    defaultCode = "|";
    functions = Arrays.asList(DiscoverySelect, UseManage);
  }
}