package de.gwdg.metadataqa.marc.definition.controlpositions.tag007;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Category of material
 * https://www.loc.gov/marc/bibliographic/bd007o.html
 */
public class Tag007kit00 extends ControlfieldPositionDefinition {
  private static Tag007kit00 uniqueInstance;

  private Tag007kit00() {
    initialize();
    extractValidCodes();
  }

  public static Tag007kit00 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag007kit00();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Category of material";
    id = "007kit00";
    mqTag = "categoryOfMaterial";
    positionStart = 0;
    positionEnd = 1;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007o.html";
    codes = Utils.generateCodes(
      "o", "Kit"
    );
    functions = Arrays.asList(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain);
  }
}