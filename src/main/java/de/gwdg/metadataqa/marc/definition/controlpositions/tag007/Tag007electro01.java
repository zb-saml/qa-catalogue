package de.gwdg.metadataqa.marc.definition.controlpositions.tag007;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Specific material designation
 * https://www.loc.gov/marc/bibliographic/bd007c.html
 */
public class Tag007electro01 extends ControlfieldPositionDefinition {
  private static Tag007electro01 uniqueInstance;

  private Tag007electro01() {
    initialize();
    extractValidCodes();
  }

  public static Tag007electro01 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag007electro01();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Specific material designation";
    id = "007electro01";
    mqTag = "specificMaterialDesignation";
    positionStart = 1;
    positionEnd = 2;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007c.html";
    codes = Utils.generateCodes(
      "a", "Tape cartridge",
      "b", "Chip cartridge",
      "c", "Computer optical disc cartridge",
      "d", "Computer disc, type unspecified",
      "e", "Computer disc cartridge, type unspecified",
      "f", "Tape cassette",
      "h", "Tape reel",
      "j", "Magnetic disk",
      "k", "Computer card",
      "m", "Magneto-optical disc",
      "o", "Optical disc",
      "r", "Remote",
      "s", "Standalone device",
      "u", "Unspecified",
      "z", "Other",
      "|", "No attempt to code"
    );
    functions = Arrays.asList(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain, UseManage, UseOperate);
  }
}