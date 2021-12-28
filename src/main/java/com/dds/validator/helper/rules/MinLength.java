package com.dds.validator.helper.rules;

import com.dds.validator.helper.IPassword;
import com.dds.validator.helper.IValidationRule;

public class MinLength implements IValidationRule {

    private Integer minLength;

    public MinLength(Integer minLength) {
        this.minLength = minLength;
    }

    @Override
    public Boolean validate(IPassword user) {
        if (user.getPassword().length() < minLength) {
            return false;
        }
        return true;
    }
}
