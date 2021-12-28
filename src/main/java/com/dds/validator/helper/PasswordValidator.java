package com.dds.validator.helper;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {

    private List<IValidationRule> rules;

    public PasswordValidator() {
        rules = new ArrayList<>();
    }

    public PasswordValidator addRule(IValidationRule rule) {
        this.rules.add(rule);
        return this;
    }

    public Boolean isValidPassword(IPassword user)  {
        for (IValidationRule rule: rules
             ) {
            if (!rule.validate(user)) {
                return false;
            }
        }
        return true;
    }



}
