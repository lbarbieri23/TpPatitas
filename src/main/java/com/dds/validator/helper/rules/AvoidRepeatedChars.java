package com.dds.validator.helper.rules;

import com.dds.validator.helper.IPassword;
import com.dds.validator.helper.IValidationRule;

public class AvoidRepeatedChars implements IValidationRule {


    @Override
    public Boolean validate(IPassword user) {
        String password = user.getPassword().toLowerCase();
        for (int i = 2; i < password.length(); ++i)
        {
            if (Character.isLetterOrDigit((password.charAt(i))) && Character.isLetterOrDigit((password.charAt(i-1))) && Character.isLetterOrDigit((password.charAt(i-2))))
            {
                if (password.charAt(i) == password.charAt(i-1) && password.charAt(i-1) == password.charAt(i-2))
                {
                    return false;
                }

            }
        }

        return true;
    }
}
