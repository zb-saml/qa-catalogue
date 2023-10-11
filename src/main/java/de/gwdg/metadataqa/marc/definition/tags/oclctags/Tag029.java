package de.gwdg.metadataqa.marc.definition.tags.oclctags;

import de.gwdg.metadataqa.marc.EncodedValue;
import de.gwdg.metadataqa.marc.definition.Cardinality;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.Indicator;
import de.gwdg.metadataqa.marc.definition.MarcVersion;

import java.util.Arrays;

/**
 * Other System Control Number
 * http://www.oclc.org/bibformats/en/0xx/029.html
 */
public class Tag029 extends DataFieldDefinition {

  private static Tag029 uniqueInstance;

  private Tag029() {
    initialize();
    postCreation();
  }

  public static Tag029 getInstance() {
    if (uniqueInstance == null)
      uniqueInstance = new Tag029();
    return uniqueInstance;
  }

  private void initialize() {
    tag = "029";
    label = "Other System Control Number";
    bibframeTag = "IdentifiedBy/Lccn";
    cardinality = Cardinality.Repeatable;
    descriptionUrl = "http://www.oclc.org/bibformats/en/0xx/029.html";

    ind1 = new Indicator("The type of system control number")
      .setCodes(
        "0", "Primary control number",
        "1", "Secondary control number"
      )
      .putVersionSpecificCodes(MarcVersion.DNB, Arrays.asList(
        new EncodedValue("a", "ISSN formal richtig"),
        new EncodedValue("b", "ISSN formal falsch")
      ))
      .setMqTag("type");

    ind2 = new Indicator()
      .putVersionSpecificCodes(MarcVersion.DNB, Arrays.asList(
        new EncodedValue(" ", "Nicht spezifiziert (bei fehlerhaften ISSN)"),
        new EncodedValue("a", "Autorisierte ISSN"),
        new EncodedValue("b", "ISSN der Ausgabe auf anderem Datenträger"),
        new EncodedValue("c", "ISSN der Internet-Ausgabe"),
        new EncodedValue("d", "ISSN der Druck-Ausgabe")
      ));

    setSubfieldsWithCardinality(
      "a", "OCLC library identifier", "NR",
      "b", "System control number", "NR",
      "c", "OAI set name", "NR",
      "t", "Content type identifier", "NR"
    );

    getSubfield("a").setMqTag("oclcLibraryIdentifier");
    getSubfield("b").setMqTag("otherSystemControlNumber");
    getSubfield("c").setMqTag("oaiSet");
    getSubfield("t").setMqTag("contentTypeIdentifier");
  }
}
