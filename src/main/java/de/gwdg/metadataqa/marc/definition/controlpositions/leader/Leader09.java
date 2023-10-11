package de.gwdg.metadataqa.marc.definition.controlpositions.leader;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;

import java.util.Arrays;

import static de.gwdg.metadataqa.marc.definition.FRBRFunction.ManagementProcess;

/**
 * Character coding scheme
 * https://www.loc.gov/marc/bibliographic/bdleader.html
 */
public class Leader09 extends ControlfieldPositionDefinition {
  private static Leader09 uniqueInstance;

  private Leader09() {
    initialize();
    extractValidCodes();
  }

  public static Leader09 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Leader09();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Character coding scheme";
    id = "leader09";
    mqTag = "characterCodingScheme";
    positionStart = 9;
    positionEnd = 10;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bdleader.html";
    codes = Utils.generateCodes(
      " ", "MARC-8",
      "a", "UCS/Unicode"
    );
    functions = Arrays.asList(ManagementProcess);
  }
}