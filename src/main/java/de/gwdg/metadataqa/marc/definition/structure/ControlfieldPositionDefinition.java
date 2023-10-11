package de.gwdg.metadataqa.marc.definition.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import de.gwdg.metadataqa.marc.EncodedValue;
import de.gwdg.metadataqa.marc.definition.FRBRFunction;
import de.gwdg.metadataqa.marc.definition.general.codelist.CodeList;
import de.gwdg.metadataqa.marc.definition.general.parser.SubfieldContentParser;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Péter Király <peter.kiraly at gwdg.de>
 */
public class ControlfieldPositionDefinition implements Serializable {

  protected static final Pattern TRIMMABLE = Pattern.compile("^[^ ]+ +$");

  protected String id;
  protected String label;
  protected String bibframeTag;
  protected String mqTag;
  protected int positionStart;
  protected int positionEnd;
  protected List<EncodedValue> codes;
  protected List<EncodedValue> historicalCodes;
  protected CodeList codeList;

  protected List<String> validCodes = new ArrayList<>();
  protected int unitLength = -1;
  protected boolean repeatableContent = false;
  protected String defaultCode;
  protected String descriptionUrl;
  protected SubfieldContentParser parser;
  protected List<FRBRFunction> functions;

  public ControlfieldPositionDefinition() {}

  public ControlfieldPositionDefinition(String label, int positionStart, int positionEnd) {
    this.label = label;
    this.positionStart = positionStart;
    this.positionEnd = positionEnd;
    validCodes = new ArrayList<>();
  }

  public ControlfieldPositionDefinition(String label, int positionStart, int positionEnd,
                                        List<EncodedValue> codes) {
    this(label, positionStart, positionEnd);
    this.codes = codes;
    extractValidCodes();
  }

  public ControlfieldPositionDefinition setCodes(List<EncodedValue> codes) {
    this.codes = codes;
    extractValidCodes();
    return this;
  }

  public String getLabel() {
    return label;
  }

  public String getBibframeTag() {
    return bibframeTag;
  }

  public ControlfieldPositionDefinition setBibframeTag(String bibframeTag) {
    this.bibframeTag = bibframeTag;
    return this;
  }

  public String getMqTag() {
    return mqTag;
  }

  public ControlfieldPositionDefinition setMqTag(String mqTag) {
    this.mqTag = mqTag;
    return this;
  }

  public int getPositionStart() {
    return positionStart;
  }

  public int getPositionEnd() {
    return positionEnd;
  }

  public List<EncodedValue> getCodes() {
    return codes;
  }

  public int getUnitLength() {
    return unitLength;
  }

  public String getId() {
    return id;
  }

  public ControlfieldPositionDefinition setId(String id) {
    this.id = id;
    return this;
  }

  public ControlfieldPositionDefinition setUnitLength(int unitLength) {
    this.unitLength = unitLength;
    return this;
  }

  public boolean isRepeatableContent() {
    return repeatableContent;
  }

  public ControlfieldPositionDefinition setRepeatableContent(boolean repeatableContent) {
    this.repeatableContent = repeatableContent;
    return this;
  }

  public String getDefaultCode() {
    return defaultCode;
  }

  public ControlfieldPositionDefinition setCodeList(CodeList codeList) {
    this.codeList = codeList;
    return this;
  }

  public CodeList getCodeList() {
    return codeList;
  }


  public ControlfieldPositionDefinition setDefaultCode(String defaultCode) {
    this.defaultCode = defaultCode;
    return this;
  }

  public String getDescriptionUrl() {
    return descriptionUrl;
  }

  public boolean validate(String code) {
    if (isRepeatableContent())
      return validateRepeatable(code);
    else
      return validCodes.contains(code);
  }

  private boolean validateRepeatable(String code) {
    for (int i=0; i < code.length(); i += unitLength) {
      String unit = code.substring(i, i+unitLength);
      if (!validCodes.contains(unit))
        return false;
    }
    return true;
  }

  public String resolve(String inputCode) {
    if (codes != null || codeList != null)
      if (repeatableContent)
        inputCode = resolveRepeatable(inputCode);
      else
        inputCode = resolveSingleCode(inputCode);

    return inputCode;
  }

  private String resolveRepeatable(String inputCode) {
    List<String> units = new ArrayList<>();
    for (int i=0; i < inputCode.length(); i += unitLength) {
      String unit = inputCode.substring(i, i+unitLength);
      if (!units.contains(unit))
        units.add(unit);
    }
    List<String> resolved = new ArrayList<>();
    for (String unit : units)
      resolved.add(resolveSingleCode(unit));

    inputCode = StringUtils.join(resolved, ", ");
    return inputCode;
  }

  private String resolveSingleCode(String inputCode) {
    if (codeList != null) {
      if (inputCode.length() > 1 && TRIMMABLE.matcher(inputCode).matches()) {
        String trimmed = inputCode.trim();
        if (codeList.isValid(trimmed))
          return codeList.getCode(trimmed).getLabel();
      }
      if (codeList.isValid(inputCode.trim()))
        return codeList.getCode(inputCode.trim()).getLabel();
    }

    if (codes != null) {
      for (EncodedValue code : codes)
        if (code.getCode().equals(inputCode))
          return code.getLabel();
    }

    return inputCode;
  }

  protected void extractValidCodes() {
    if (codes == null)
      return;
    for (EncodedValue code : codes)
      validCodes.add(code.getCode());
  }

  public List<String> getValidCodes() {
    return validCodes;
  }

  public String formatPositon() {
    return (positionStart == positionEnd - 1)
      ? String.format("%02d", positionStart)
      : String.format("%02d-%02d", positionStart, positionEnd-1);
  }

  public String getControlField() {
    String className = this.getClass().getSimpleName();
    if (className.startsWith("Leader"))
      return "Leader";
    return this.getClass().getSimpleName().substring(3, 6);
  }

  public EncodedValue getCode(String otherCode) {
    for (EncodedValue code : codes)
      if (code.getCode().equals(otherCode))
        return code;
      else if (code.isRange() && code.getRange().isValid(otherCode))
        return code;

    return null;
  }

  public boolean isHistoricalCode(String inputCode) {
    if (historicalCodes != null && !historicalCodes.isEmpty())
      for (EncodedValue historicalCode : historicalCodes)
        if (historicalCode.getCode().equals(inputCode))
          return true;
    return false;
  }

  public String getPath() {
    return getPath(true);
  }

  public String getPath(boolean showId) {
    if (showId)
      return String.format("%s/%s (%s)", getControlField(), formatPositon(), getId());
    else
      return String.format("%s/%s", getControlField(), formatPositon());
  }

  public boolean hasParser() {
    return parser != null;
  }

  public SubfieldContentParser getParser() {
    return parser;
  }

  public List<EncodedValue> getHistoricalCodes() {
    return historicalCodes;
  }

  public List<FRBRFunction> getFrbrFunctions() {
    return functions;
  }

  @Override
  public String toString() {
    return "ControlfieldPositionDefinition{" +
        "label='" + label + '\'' +
        ", positionStart=" + positionStart +
        ", positionEnd=" + positionEnd +
        ", codes=" + codes +
        ", validCodes=" + validCodes +
        ", unitLength=" + unitLength +
        ", repeatableContent=" + repeatableContent +
        ", mqTag=" + mqTag +
        ", id=" + id +
        '}';
  }
}
