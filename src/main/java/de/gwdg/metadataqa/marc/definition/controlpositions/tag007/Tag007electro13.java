package de.gwdg.metadataqa.marc.definition.controlpositions.tag007;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Reformatting quality
 * https://www.loc.gov/marc/bibliographic/bd007c.html
 */
public class Tag007electro13 extends ControlfieldPositionDefinition {
  private static Tag007electro13 uniqueInstance;

  private Tag007electro13() {
    initialize();
    extractValidCodes();
  }

  public static Tag007electro13 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag007electro13();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Reformatting quality";
    id = "007electro13";
    mqTag = "reformattingQuality";
    positionStart = 13;
    positionEnd = 14;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007c.html";
    codes = Utils.generateCodes(
      "a", "Access",
      "n", "Not applicable",
      "p", "Preservation",
      "r", "Replacement",
      "u", "Unknown",
      "|", "No attempt to code"
    );
    defaultCode = "|";
    functions = Arrays.asList(DiscoverySelect, UseManage);
  }
}