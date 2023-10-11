package de.gwdg.metadataqa.marc.definition.controlpositions.leader;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;

import java.util.Arrays;

import static de.gwdg.metadataqa.marc.definition.FRBRFunction.ManagementProcess;

/**
 * Type of control
 * https://www.loc.gov/marc/bibliographic/bdleader.html
 */
public class Leader08 extends ControlfieldPositionDefinition {
  private static Leader08 uniqueInstance;

  private Leader08() {
    initialize();
    extractValidCodes();
  }

  public static Leader08 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Leader08();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Type of control";
    id = "leader08";
    mqTag = "typeOfControl";
    positionStart = 8;
    positionEnd = 9;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bdleader.html";
    codes = Utils.generateCodes(
      " ", "No specified type",
      "a", "Archival"
    );
    functions = Arrays.asList(ManagementProcess);
  }
}