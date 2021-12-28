package com.dds.validator.helper.rules;

import com.dds.validator.helper.IPassword;
import com.dds.validator.helper.IValidationRule;

public class RequireUpperLetter implements IValidationRule {

    @Override
    public Boolean validate(IPassword user)  {
        if (!user.getPassword().matches(".*[A-Z].*")) {
            return false;
        }
        return true;
    }

}
