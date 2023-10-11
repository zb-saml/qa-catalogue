package de.gwdg.metadataqa.marc.definition.general.codelist;

import de.gwdg.metadataqa.marc.Utils;

/**
 * Access Restriction Term Source Codes
 * https://www.loc.gov/standards/sourcelist/access-restriction.html
 */
public class AccessRestrictionTermSourceCodes extends CodeList {

  private void initialize() {
    name = "Access Restriction Term Source Codes";
    url = "https://www.loc.gov/standards/sourcelist/access-restriction.html";
    codes = Utils.generateCodes(
      "cc", "Creative Commons",
      "rs", "Rights Statements",
      "star", "Standardized terminology for access restriction (DLF/OCLC Registry of Digital Masters Working Group)"
    );
    indexCodes();
  }

  private static AccessRestrictionTermSourceCodes uniqueInstance;

  private AccessRestrictionTermSourceCodes() {
    initialize();
  }

  public static AccessRestrictionTermSourceCodes getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new AccessRestrictionTermSourceCodes();
    return uniqueInstance;
  }
}
