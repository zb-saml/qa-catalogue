package de.gwdg.metadataqa.marc.cli;

import org.junit.Test;

import static org.junit.Assert.*;

public class VersionTest {

  private static final String VERSION = "0.7.0-rc2";

  @Test
  public void getVersion() {
    assertEquals(VERSION, Version.getVersion());
  }

  @Test
  public void readVersionFromPropertyFile() {
    assertEquals(VERSION, Version.readVersionFromPropertyFile());
  }
}