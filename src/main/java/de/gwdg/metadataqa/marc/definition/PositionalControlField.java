package de.gwdg.metadataqa.marc.definition;

import de.gwdg.metadataqa.marc.Extractable;
import de.gwdg.metadataqa.marc.dao.record.BibliographicRecord;
import de.gwdg.metadataqa.marc.Validatable;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import de.gwdg.metadataqa.marc.model.SolrFieldType;
import de.gwdg.metadataqa.marc.model.validation.ValidationError;
import de.gwdg.metadataqa.marc.utils.keygenerator.PositionalControlFieldKeyGenerator;

import java.util.*;

public abstract class PositionalControlField extends ControlField implements Extractable, Validatable {

  protected BibliographicRecord marcRecord;
  protected Map<ControlfieldPositionDefinition, String> valuesMap;
  protected List<ControlValue> valuesList;
  protected List<ValidationError> validationErrors;

  public void setMarcRecord(BibliographicRecord marcRecord) {
    this.marcRecord = marcRecord;
    for (ControlValue value : valuesList) {
      value.setMarcRecord(marcRecord);
    }
  }

  @Override
  public Map<String, List<String>> getKeyValuePairs() {
    return getKeyValuePairs(SolrFieldType.MARC);
  }

  public Map<String, List<String>> getKeyValuePairs(
      String tag, String mqTag, SolrFieldType type) {
    Map<String, List<String>> map = new LinkedHashMap<>();
    PositionalControlFieldKeyGenerator keyGenerator = new PositionalControlFieldKeyGenerator(
      tag, mqTag, type);
    if (content != null) {
      map.put(keyGenerator.forTag(), Arrays.asList(content));
      for (Map.Entry<ControlfieldPositionDefinition, String> entry : valuesMap.entrySet()) {
        ControlfieldPositionDefinition position = entry.getKey();
        String value = position.resolve(entry.getValue());
        map.put(keyGenerator.forSubfield(position), Arrays.asList(value));
      }
    }
    return map;
  }

  public Map<ControlfieldPositionDefinition, String> getMap() {
    return valuesMap;
  }
}
