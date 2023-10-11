package de.gwdg.metadataqa.marc.definition.general.indexer.subject;

import de.gwdg.metadataqa.marc.dao.DataField;
import de.gwdg.metadataqa.marc.definition.general.indexer.FieldIndexer;
import de.gwdg.metadataqa.marc.model.SolrFieldType;

import java.util.List;
import java.util.Map;

public class SubjectIndexerTest {

  protected Map<String, List<String>> getIndexEntries(DataField field) {
    FieldIndexer indexer = field.getFieldIndexers().get(0);
    return indexer.index(field, field.getKeyGenerator(SolrFieldType.MIXED));
  }

}
