package de.gwdg.metadataqa.marc.analysis.validator;

import de.gwdg.metadataqa.marc.dao.Leader;
import de.gwdg.metadataqa.marc.definition.ControlValue;

import java.util.ArrayList;

public class LeaderValidator extends AbstractValidator {

  public LeaderValidator() {
    super(new ValidatorConfiguration());
  }

  public LeaderValidator(ValidatorConfiguration configuration) {
    super(configuration);
  }

  public boolean validate(Leader leader) {
    var isValid = true;
    ControlValueValidator controlValueValidator = new ControlValueValidator(configuration);
    validationErrors = new ArrayList<>();
    if (!leader.getInitializationErrors().isEmpty())
      validationErrors.addAll(filterErrors(leader.getInitializationErrors()));

    for (ControlValue controlValue : leader.getValuesList()) {
      if (!controlValueValidator.validate(controlValue)) {
        validationErrors.addAll(filterErrors(controlValueValidator.getValidationErrors()));
        isValid = false;
      }
    }

    return isValid;
  }
}
