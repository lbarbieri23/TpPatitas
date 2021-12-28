package com.dds.validator.helper.rules;

import com.dds.validator.helper.IPassword;
import com.dds.validator.helper.IValidationRule;

public class MaxLength implements IValidationRule {

    private Integer maxLength;

    public MaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Boolean validate(IPassword user) {
        if (user.getPassword().length() > maxLength) {
            return false;
        }
        return true;
    }
}
