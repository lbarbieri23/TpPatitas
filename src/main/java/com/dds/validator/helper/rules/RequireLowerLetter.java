package com.dds.validator.helper.rules;

import com.dds.validator.helper.IPassword;
import com.dds.validator.helper.IValidationRule;

public class RequireLowerLetter implements IValidationRule {

    @Override
    public Boolean validate(IPassword user) {
        if (!user.getPassword().matches(".*[a-z].*")) {
            return false;
        }
        return true;
    }

}
