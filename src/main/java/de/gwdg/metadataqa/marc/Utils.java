package de.gwdg.metadataqa.marc;

import de.gwdg.metadataqa.marc.dao.DataField;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;

import de.gwdg.metadataqa.marc.definition.MarcVersion;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Péter Király <peter.kiraly at gwdg.de>
 */
public class Utils {

  private Utils() {
  }

  public static List<EncodedValue> generateCodes(String... input) {
    if (input.length % 2 != 0) {
      throw new IllegalArgumentException("Number of input should be even");
    }
    List<EncodedValue> codes = new ArrayList<>();
    for (int i = 0, len = input.length; i < len; i += 2) {
      codes.add(new EncodedValue(input[i], input[i + 1]));
    }
    return codes;
  }

  public static List<ControlfieldPositionDefinition> generateControlPositionList(ControlfieldPositionDefinition... input) {
    List<ControlfieldPositionDefinition> list = new ArrayList<>();
    list.addAll(Arrays.asList(input));
    return list;
  }

  public static String extractPackageName(DataField field) {
    return extractPackageName(field.getDefinition());
  }

  public static String extractPackageName(DataFieldDefinition field) {
    return field.getClass().getPackage().getName()
      .replace("de.gwdg.metadataqa.marc.definition.tags.", "")
      .replace("de.gwdg.metadataqa.marc.utils.", "")
      ;
  }

  public static String extractPackageName(Class<? extends DataFieldDefinition> field) {
    return field.getPackage().getName()
      .replace("de.gwdg.metadataqa.marc.definition.tags.", "")
      .replace("de.gwdg.metadataqa.marc.utils.", "")
      ;
  }

  public static MarcVersion getVersion(DataFieldDefinition field) {
    return package2version(extractPackageName(field));
  }

  public static MarcVersion getVersion(Class<? extends DataFieldDefinition> field) {
    return package2version(extractPackageName(field));
  }

  public static MarcVersion package2version(String packageName) {
    MarcVersion version;
    switch (packageName) {
      case "bltags":      version = MarcVersion.BL;      break;
      case "dnbtags":     version = MarcVersion.DNB;     break;
      case "fennicatags": version = MarcVersion.FENNICA; break;
      case "genttags":    version = MarcVersion.GENT;    break;
      case "oclctags":    version = MarcVersion.OCLC;    break;
      case "sztetags":    version = MarcVersion.SZTE;    break;
      case "nkcrtags":    version = MarcVersion.NKCR;    break;
      case "uvatags":     version = MarcVersion.UVA;     break;
      case "b3kattags":   version = MarcVersion.B3KAT;   break;
      case "kbrtags":     version = MarcVersion.KBR;     break;
      default:            version = MarcVersion.MARC21;  break;
    }
    return version;
  }

  public static List<Object> quote(List<? extends Serializable> values) {
    List<Object> quoted = new ArrayList<>();
    for (Serializable value : values) {
      quoted.add(Utils.quote(value));
    }
    return quoted;
  }

  public static Object quote(Object value) {
    if (value instanceof String) {
      return '"' + ((String) value).replace("\\", "\\\\")
                                   .replace("\"", "\\\"")
                                   .replace("\n", "\\n") + '"';
    }
    return value;
  }

  /**
   * Increment a counter with 1. The counter is a key in a map.
   * @param key (any kind of object)
   * @param counter (a map where the key type equals to the key parameter)
   * @param <T>
   */
  public static <T extends Object> void count(T key, Map<T, Integer> counter) {
    if (!counter.containsKey(key))
      counter.put(key, 1);
    else
      counter.put(key, counter.get(key) + 1);
  }

  public static <T extends Object> void add(T key, Map<T, Integer> counter, int i) {
    counter.computeIfAbsent(key, s -> 0);
    counter.put(key, counter.get(key) + i);
  }

  public static List<String> counterToList(Map<Integer, Integer> counter) {
    return counterToList(':', counter);
  }

  public static List<String> counterToList(char separator, Map<Integer, Integer> counter) {
    return counter.entrySet().stream()
      .sorted(Map.Entry.comparingByKey())
      .map(item -> item.getKey().toString() + separator + item.getValue())
      .collect(Collectors.toList());
  }

  public static String solarize(String abbreviation) {
    abbreviation = StringUtils.stripAccents(abbreviation);
    abbreviation = abbreviation.replaceAll("\\W", "_").toLowerCase();
    return abbreviation;
  }

  public static String createRow(Object... fields) {
    var separator = ',';
    if (fields[0].getClass() == Character.class) {
      separator = (char) fields[0];
      fields = Arrays.copyOfRange(fields, 1, fields.length);
    }
    return createRowWithSep(separator, fields);
  }

  public static String createRowWithSep(char separator, Object... fields) {
    if (fields.length == 1 && fields[0] instanceof List) {
      return StringUtils.join(((List)fields[0]), separator) + "\n";
    } else {
      return StringUtils.join(fields, separator) + "\n";
    }
  }

  /**
   * Transforms a positive integer to a base36 encoded string
   * @param id a positive integer either in normal or in scientific notation (e.g. 12 or 10E+3)
   * @return
   */
  public static String base36Encode(String id) {
    return Integer.toString(parseId(id), Character.MAX_RADIX);
  }

  public static int parseId(String id) {
    return (id.contains("+"))
        ? Utils.scientificNotationToInt(id)
        : Integer.parseInt(id);
  }

  public static String base36Encode(int i) {
    return Integer.toString(i, Character.MAX_RADIX);
  }

  public static void mergeMap(Map<String, List<String>> base, Map<String, List<String>> extra) {
    for (Map.Entry<String, List<String>> entry : extra.entrySet()) {
      if (base.containsKey(entry.getKey()))
        base.get(entry.getKey()).addAll(entry.getValue());
      else
        base.put(entry.getKey(), entry.getValue());
    }
  }

  public static int scientificNotationToInt(String scientificNotation) {
    var value = new BigDecimal(scientificNotation);
    return value.toBigInteger().intValue();
  }

  public static String substring(String value, int start, int end) {
    if (start < value.length()) {
      if (end <= value.length()) {
        return value.substring(start, end);
      } else {
        return value.substring(start);
      }
    }
    throw new StringIndexOutOfBoundsException(String.format(
      "Character position range %d-%d is not available in string '%s'", start, end, value));
  }

  public static Map<String, Boolean> listToMap(List<String> list) {
    Map<String, Boolean> map = new HashMap<>();
    for (String tag : list)
      map.put(tag, true);
    return map;
  }

  public static String base64decode(String raw) {
    Base64.Decoder dec = Base64.getDecoder();
    return new String(dec.decode(raw.replaceAll("^base64:", "")));
  }
}
