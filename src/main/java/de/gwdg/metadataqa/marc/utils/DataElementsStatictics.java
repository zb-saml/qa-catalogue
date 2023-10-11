package de.gwdg.metadataqa.marc.utils;

import de.gwdg.metadataqa.marc.Utils;
import de.gwdg.metadataqa.marc.definition.structure.ControlFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.ControlfieldPositionDefinition;
import de.gwdg.metadataqa.marc.definition.structure.DataFieldDefinition;
import de.gwdg.metadataqa.marc.definition.structure.Indicator;
import de.gwdg.metadataqa.marc.definition.structure.MarcDefinition;
import de.gwdg.metadataqa.marc.definition.MarcVersion;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DataElementsStatictics {

  private static final Logger logger = Logger.getLogger(DataElementsStatictics.class.getCanonicalName());

  public static Counter<DataElementType> count() {
    Counter<DataElementType> counter = new Counter<>();

    counter.add(DataElementType.controlFieldPositions, MarcDefinition.getLeaderPositions().size());
    counter.add(DataElementType.controlFields, MarcDefinition.getSimpleControlFields().size());

    for (ControlFieldDefinition controlField : MarcDefinition.getComplexControlFields()) {
      counter.count(DataElementType.controlFields);

      for (List<ControlfieldPositionDefinition> controlFieldPositions : controlField.getControlfieldPositions().values())
        counter.add(DataElementType.controlFieldPositions, controlFieldPositions.size());
    }

    for (Class<? extends DataFieldDefinition> tagClass : MarcTagLister.listTags()) {
      MarcVersion version = Utils.getVersion(tagClass);
      Method getInstance;
      DataFieldDefinition fieldTag;
      try {
        getInstance = tagClass.getMethod("getInstance");
        fieldTag = (DataFieldDefinition) getInstance.invoke(tagClass);
        boolean isCore = (version == MarcVersion.MARC21);
        if (isCore)
          counter.count(DataElementType.coreFields);
        else
          counter.count(DataElementType.localFields);

        for (Indicator indicator : fieldTag.getIndicators())
          if (indicator != null && StringUtils.isNotBlank(indicator.getLabel()))
            if (isCore)
              counter.count(DataElementType.coreIndicators);
            else
              counter.count(DataElementType.localIndicators);

        if (fieldTag.getSubfields() != null)
          if (isCore)
            counter.add(DataElementType.coreSubfields, fieldTag.getSubfields().size());
          else
            counter.add(DataElementType.localSubfields, fieldTag.getSubfields().size());

        if (isCore && fieldTag.getVersionSpecificSubfields() != null)
          for (MarcVersion localVersion : fieldTag.getVersionSpecificSubfields().keySet())
            counter.add(DataElementType.localSubfields, fieldTag.getVersionSpecificSubfields().get(localVersion).size());

      } catch (NoSuchMethodException
              | IllegalAccessException
              | InvocationTargetException e) {
        logger.log(Level.WARNING, "error in count()", e);
      }
    }

    return counter;
  }
}
