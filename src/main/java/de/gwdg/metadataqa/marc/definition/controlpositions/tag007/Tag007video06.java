package de.gwdg.metadataqa.marc.definition.controlpositions.tag007;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Medium for sound
 * https://www.loc.gov/marc/bibliographic/bd007v.html
 */
public class Tag007video06 extends ControlfieldPositionDefinition {
  private static Tag007video06 uniqueInstance;

  private Tag007video06() {
    initialize();
    extractValidCodes();
  }

  public static Tag007video06 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag007video06();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Medium for sound";
    id = "007video06";
    mqTag = "mediumForSound";
    positionStart = 6;
    positionEnd = 7;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007v.html";
    codes = Utils.generateCodes(
      " ", "No sound (silent)",
      "a", "Optical sound track on motion picture film",
      "b", "Magnetic sound track on motion picture film",
      "c", "Magnetic audio tape in cartridge",
      "d", "Sound disc",
      "e", "Magnetic audio tape on reel",
      "f", "Magnetic audio tape in cassette",
      "g", "Optical and magnetic sound track on motion picture film",
      "h", "Videotape",
      "i", "Videodisc",
      "u", "Unknown",
      "z", "Other",
      "|", "No attempt to code"
    );
    functions = Arrays.asList(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain, UseManage, UseOperate);
    historicalCodes = Utils.generateCodes(
      "g", "Other [OBSOLETE, 1980]"
    );
  }
}