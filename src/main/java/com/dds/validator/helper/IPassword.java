package com.dds.validator.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IPassword {

    String getPassword();
    List<String> getOldPasswords();

}
