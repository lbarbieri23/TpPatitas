package com.dds.validator.helper.rules;


import com.dds.validator.helper.IPassword;
import com.dds.validator.helper.IValidationRule;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CheckInListPassword implements IValidationRule {

    private List<String> passwords;

    public CheckInListPassword(List<String> passwords) {
        this.passwords = passwords;
    }

    @Override
    public Boolean validate(IPassword user) {
        if (passwords.stream().anyMatch(x -> x.equals(user.getPassword().toLowerCase()))) {
            return false;
        }

        return true;
    }

}
