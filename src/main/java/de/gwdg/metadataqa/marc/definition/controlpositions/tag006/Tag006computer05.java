package de.gwdg.metadataqa.marc.definition.controlpositions.tag006;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Target audience
 * https://www.loc.gov/marc/bibliographic/bd006.html
 */
public class Tag006computer05 extends ControlfieldPositionDefinition {
  private static Tag006computer05 uniqueInstance;

  private Tag006computer05() {
    initialize();
    extractValidCodes();
  }

  public static Tag006computer05 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag006computer05();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Target audience";
    id = "006computer05";
    mqTag = "targetAudience";
    positionStart = 5;
    positionEnd = 6;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd006.html";
    codes = Utils.generateCodes(
      " ", "Unknown or not specified",
      "a", "Preschool",
      "b", "Primary",
      "c", "Pre-adolescent",
      "d", "Adolescent",
      "e", "Adult",
      "f", "Specialized",
      "g", "General",
      "j", "Juvenile",
      "|", "No attempt to code"
    );
    functions = Arrays.asList(DiscoverySelect);
  }
}