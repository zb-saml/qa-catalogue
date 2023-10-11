package de.gwdg.metadataqa.marc.definition.controlpositions.tag006;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Biography
 * same as 008/34
 * https://www.loc.gov/marc/bibliographic/bd006.html
 * https://www.loc.gov/marc/bibliographic/bd008b.html
 */
public class Tag006book17 extends ControlfieldPositionDefinition {
  private static Tag006book17 uniqueInstance;

  private Tag006book17() {
    initialize();
    extractValidCodes();
  }

  public static Tag006book17 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag006book17();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Biography";
    id = "006book17";
    mqTag = "biography";
    positionStart = 17;
    positionEnd = 18;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd006.html";
    codes = Utils.generateCodes(
      " ", "No biographical material",
      "a", "Autobiography",
      "b", "Individual biography",
      "c", "Collective biography",
      "d", "Contains biographical information",
      "|", "No attempt to code"
    );
    functions = Arrays.asList(DiscoveryIdentify, DiscoverySelect);
  }
}