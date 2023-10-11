package de.gwdg.metadataqa.marc.definition.tags.bltags;

import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.Indicator;

/**
 * Informal Notes (ESTC)
 */
public class Tag509 extends DataFieldDefinition {

  private static Tag509 uniqueInstance;

  private Tag509() {
    initialize();
    postCreation();
  }

  public static Tag509 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag509();
    return uniqueInstance;
  }

  private void initialize() {

    tag = "509";
    label = "Informal Notes (ESTC)";
    mqTag = "InformalNotesESTC";
    cardinality = Cardinality.Repeatable;
    // descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd037.html";
    // setCompilanceLevels("O");

    ind1 = new Indicator();
    ind2 = new Indicator();

    setSubfieldsWithCardinality(
      "a", "Informal notes", "NR"
    );

    getSubfield("a")
      .setMqTag("informalNotes");
  }
}
