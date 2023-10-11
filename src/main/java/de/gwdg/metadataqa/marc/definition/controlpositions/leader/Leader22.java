package de.gwdg.metadataqa.marc.definition.controlpositions.leader;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;

import java.util.Arrays;

import static de.gwdg.metadataqa.marc.definition.FRBRFunction.ManagementProcess;

/**
 * Length of the implementation-defined portion
 * https://www.loc.gov/marc/bibliographic/bdleader.html
 */
public class Leader22 extends ControlfieldPositionDefinition {
  private static Leader22 uniqueInstance;

  private Leader22() {
    initialize();
    extractValidCodes();
  }

  public static Leader22 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Leader22();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Length of the implementation-defined portion";
    id = "leader22";
    mqTag = "lengthOfTheImplementationDefinedPortion";
    positionStart = 22;
    positionEnd = 23;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bdleader.html";
    functions = Arrays.asList(ManagementProcess);
    codes = Utils.generateCodes(
      "0", "Number of characters in the implementation-defined portion of a Directory entry"
    );
  }
}