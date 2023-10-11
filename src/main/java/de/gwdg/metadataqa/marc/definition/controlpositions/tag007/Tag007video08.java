package de.gwdg.metadataqa.marc.definition.controlpositions.tag007;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import static de.gwdg.metadataqa.marc.definition.FRBRFunction.*;

import java.util.Arrays;

/**
 * Configuration of playback channels
 * https://www.loc.gov/marc/bibliographic/bd007v.html
 */
public class Tag007video08 extends ControlfieldPositionDefinition {
  private static Tag007video08 uniqueInstance;

  private Tag007video08() {
    initialize();
    extractValidCodes();
  }

  public static Tag007video08 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag007video08();
    return uniqueInstance;
  }

  private void initialize() {
    label = "Configuration of playback channels";
    id = "007video08";
    mqTag = "configurationOfPlaybackChannels";
    positionStart = 8;
    positionEnd = 9;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007v.html";
    codes = Utils.generateCodes(
      "k", "Mixed",
      "m", "Monaural",
      "n", "Not applicable",
      "q", "Quadraphonic, multichannel, or surround",
      "s", "Stereophonic",
      "u", "Unknown",
      "z", "Other",
      "|", "No attempt to code"
    );
    functions = Arrays.asList(DiscoveryIdentify, DiscoverySelect, DiscoveryObtain, UseOperate);
  }
}