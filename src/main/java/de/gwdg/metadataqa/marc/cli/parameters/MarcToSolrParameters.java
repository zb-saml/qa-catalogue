package de.gwdg.metadataqa.marc.cli.parameters;

import de.gwdg.metadataqa.marc.model.SolrFieldType;
import org.apache.commons.cli.ParseException;
import org.apache.solr.client.solrj.SolrClient;

public class MarcToSolrParameters extends CommonParameters {

  private boolean useEmbedded = false;
  private String solrUrl = null;
  private boolean doCommit = false;
  private SolrFieldType solrFieldType = SolrFieldType.MARC;
  private SolrClient mainClient = null;
  private SolrClient validationClient = null;
  private boolean indexWithTokenizedField = false;

  private boolean isOptionSet = false;

  protected void setOptions() {
    if (!isOptionSet) {
      super.setOptions();
      options.addOption("s", "solrUrl", true, "the URL of Solr server");
      options.addOption("c", "doCommit", false, "send commits to Solr regularly");
      options.addOption("t", "solrFieldType", true,
        "type of Solr fields, could be one of 'marc-tags', 'human-readable', or 'mixed'");
      options.addOption("B", "useEmbedded", false, "use embedded Solr server (used in tests only)");
      options.addOption("C", "indexWithTokenizedField", false, "index data elements as tokenized field as well");
      isOptionSet = true;
    }
  }

  public MarcToSolrParameters(String[] arguments) throws ParseException {
    super(arguments);

    if (cmd.hasOption("solrUrl"))
      solrUrl = cmd.getOptionValue("solrUrl");

    if (cmd.hasOption("doCommit"))
      doCommit = true;

    if (cmd.hasOption("solrFieldType"))
      solrFieldType = SolrFieldType.byCode(cmd.getOptionValue("solrFieldType"));

    if (cmd.hasOption("useEmbedded"))
      useEmbedded = true;

    if (cmd.hasOption("indexWithTokenizedField"))
      indexWithTokenizedField = true;
  }

  public String getSolrUrl() {
    return solrUrl;
  }

  public boolean doCommit() {
    return doCommit;
  }

  public SolrFieldType getSolrFieldType() {
    return solrFieldType;
  }

  public SolrClient getMainClient() {
    return mainClient;
  }

  public void setMainClient(SolrClient mainClient) {
    this.mainClient = mainClient;
  }

  public SolrClient getValidationClient() {
    return validationClient;
  }

  public void setValidationClient(SolrClient validationClient) {
    this.validationClient = validationClient;
  }

  public boolean useEmbedded() {
    return useEmbedded;
  }

  public boolean indexWithTokenizedField() {
    return indexWithTokenizedField;
  }

  @Override
  public String formatParameters() {
    String text = super.formatParameters();
    text += String.format("solrUrl: %s%n", solrUrl);
    text += String.format("doCommit: %s%n", doCommit);
    text += String.format("solrFieldType: %s%n", solrFieldType);
    text += String.format("indexWithTokenizedField: %s%n", indexWithTokenizedField);
    return text;
  }
}
