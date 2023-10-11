package de.gwdg.metadataqa.marc.analysis.completeness;

import de.gwdg.metadataqa.marc.cli.QACli;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;

import java.util.HashMap;
import java.util.Map;

public class CompletenessDAO {

  private Map<String, Map<String, Integer>> packageCounter = new HashMap<>();
  private Map<String, Map<String, Map<String, Integer>>> groupedPackageCounter = new HashMap<>();
  private Map<String, Map<String, Integer>> elementCardinality = new HashMap<>();
  private Map<String, Map<String, Map<String, Integer>>> groupedElementCardinality = new HashMap<>();
  private Map<String, Map<String, Integer>> elementFrequency = new HashMap<>();
  private Map<String, Map<String, Map<String, Integer>>> groupedElementFrequency = new HashMap<>();
  private Map<String, Integer> groupCounter = new HashMap<>();
  private Map<String, Integer> library003Counter = new HashMap<>();
  private Map<String, Integer> libraryCounter = new HashMap<>();
  private Map<DataFieldDefinition, String> packageNameCache = new HashMap<>();
  private Map<String, Map<Integer, Integer>> fieldHistogram = new HashMap<>();
  private Map<String, Map<String, Map<Integer, Integer>>> groupedFieldHistogram = new HashMap<>();

  public void initialize() {
    packageCounter.put(QACli.ALL, new HashMap<>());
    elementCardinality.put(QACli.ALL, new HashMap<>());
    elementFrequency.put(QACli.ALL, new HashMap<>());
  }

  public Map<String, Map<String, Integer>> getPackageCounter() {
    return packageCounter;
  }

  public Map<String, Map<String, Map<String, Integer>>> getGroupedPackageCounter() {
    return groupedPackageCounter;
  }

  public Map<String, Map<String, Integer>> getElementCardinality() {
    return elementCardinality;
  }

  public Map<String, Map<String, Map<String, Integer>>> getGroupedElementCardinality() {
    return groupedElementCardinality;
  }

  public Map<String, Map<String, Integer>> getElementFrequency() {
    return elementFrequency;
  }

  public Map<String, Integer> getGroupCounter() {
    return groupCounter;
  }

  public Map<String, Integer> getLibrary003Counter() {
    return library003Counter;
  }

  public Map<String, Integer> getLibraryCounter() {
    return libraryCounter;
  }

  public Map<DataFieldDefinition, String> getPackageNameCache() {
    return packageNameCache;
  }

  public Map<String, Map<String, Map<String, Integer>>> getGroupedElementFrequency() {
    return groupedElementFrequency;
  }

  public Map<String, Map<Integer, Integer>> getFieldHistogram() {
    return fieldHistogram;
  }

  public Map<String, Map<String, Map<Integer, Integer>>> getGroupedFieldHistogram() {
    return groupedFieldHistogram;
  }
}
