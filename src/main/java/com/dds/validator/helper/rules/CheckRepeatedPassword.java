package com.dds.validator.helper.rules;

import com.dds.validator.helper.IPassword;
import com.dds.validator.helper.IValidationRule;

public class CheckRepeatedPassword implements IValidationRule {

    @Override
    public Boolean validate(IPassword user) {

        if (user.getOldPasswords() == null || user.getOldPasswords().size() == 0) {
            return true;
        }

        return !user.getOldPasswords().contains(user.getPassword());

    }
}
