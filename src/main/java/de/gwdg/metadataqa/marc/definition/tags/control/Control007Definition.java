package de.gwdg.metadataqa.marc.definition.tags.control;

import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.structure.ControlFieldDefinition;
import de.gwdg.metadataqa.marc.definition.controlpositions.Control007Positions;

/**
 * Control Number Identifier
 * https://www.loc.gov/marc/bibliographic/bd007.html
 */
public class Control007Definition extends ControlFieldDefinition {

  private static Control007Definition uniqueInstance;

  private Control007Definition() {
    initialize();
    postCreation();
  }

  public static Control007Definition getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Control007Definition();
    return uniqueInstance;
  }

  private void initialize() {
    tag = "007";
    label = "Physical Description";
    mqTag = "PhysicalDescription";
    cardinality = Cardinality.Repeatable;
    descriptionUrl = "https://www.loc.gov/marc/bibliographic/bd007.html";
    controlfieldPositions = Control007Positions.getInstance();
  }
}
