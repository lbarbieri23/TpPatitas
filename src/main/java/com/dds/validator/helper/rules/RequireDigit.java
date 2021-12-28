package com.dds.validator.helper.rules;

import com.dds.validator.helper.IPassword;
import com.dds.validator.helper.IValidationRule;

public class RequireDigit implements IValidationRule {

    @Override
    public Boolean validate(IPassword user)  {
        if (!user.getPassword().matches(".*[0-9].*")) {
            return false;
        }
        return true;
    }

}
