package com.dds.helper;

import com.dds.validator.helper.IPassword;
import com.dds.validator.helper.rules.*;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PasswordValidatorTest {

    private class MockUser implements IPassword {
        private String password;
        private LocalDateTime lastFailedLogin;
        private Integer failedLogin;

        public MockUser(String password) {
            this.password = password;
            this.failedLogin = 0;
        }

        public MockUser(String password, LocalDateTime lastFailedLogin, Integer failedLogin) {
            this.password = password;
            this.lastFailedLogin = lastFailedLogin;
            this.failedLogin = failedLogin;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public LocalDateTime getLastFailedLogin() {
            return lastFailedLogin;
        }

        @Override
        public List<String> getOldPasswords() {
            List<String> passwords = new ArrayList<>();
            passwords.add("%rTy90Mnb");
            return passwords;
        }

        public void setLastFailedLogin(LocalDateTime lastFailedLogin) {
            this.lastFailedLogin = lastFailedLogin;
        }

        public Integer getFailedLogin() {
            return failedLogin;
        }

        public void setFailedLogin(Integer failedLogin) {
            this.failedLogin = failedLogin;
        }
    }

    private com.dds.validator.helper.PasswordValidator passwordValidator = new com.dds.validator.helper.PasswordValidator();

    @Before
    public void createValidator() throws Exception{

        List<String> passwords = Files.lines(Paths.get(ClassLoader.getSystemResource("top-list-passwords.txt").toURI())).collect(Collectors.toList());
        this.passwordValidator
                .addRule(new MinLength(8))
                .addRule(new MaxLength(64))
                .addRule(new RequireLowerLetter())
                .addRule(new RequireUpperLetter())
                .addRule(new RequireDigit())
                .addRule(new AvoidSequenceChar())
                .addRule(new AvoidRepeatedChars())
                .addRule(new CheckInListPassword(passwords))
                .addRule(new CheckRepeatedPassword());
    }

    @Test
    public void testValidPassword() throws  Exception {
        MockUser user = new MockUser("%rTy90Mna");
        assertEquals(this.passwordValidator.isValidPassword(user), true);

        user = new MockUser("A90mna3qwe");
        assertEquals(this.passwordValidator.isValidPassword(user), true);

        user = new MockUser("aAPO7J97C");
        assertEquals(this.passwordValidator.isValidPassword(user), true);
    }

    @Test
    public void testPasswordIsShort() throws  Exception {
        MockUser user = new MockUser("Astyy1");
        assertEquals(this.passwordValidator.isValidPassword(user), false);
    }

    @Test
    public void testPasswordIsLong() throws  Exception {
        MockUser user = new MockUser("Astyy1182j89ad98asjd9asjdh9a8sdh89ashd9a8sdh9a8sdhas89dhas89dh98av");
        assertEquals(this.passwordValidator.isValidPassword(user), false);
    }

    @Test
    public void testPasswordLowerCaseError() throws  Exception {
        MockUser user = new MockUser("QWERTYUI1");
        assertEquals(this.passwordValidator.isValidPassword(user), false);
    }

    @Test
    public void testPasswordUpperCaseError() throws  Exception {
        MockUser user = new MockUser("qwerty$51");
        assertEquals(this.passwordValidator.isValidPassword(user), false);
    }

    @Test
    public void testPasswordDigitError() throws  Exception {
        MockUser user = new MockUser("qwertyuiAR");
        assertEquals(this.passwordValidator.isValidPassword(user), false);
    }

    @Test
    public void testPasswordSequenceError() throws  Exception {
        MockUser user = new MockUser("rTy90Mna123");
        assertEquals(this.passwordValidator.isValidPassword(user), false);

        user = new MockUser("rTy90Mnabc");
        assertEquals(this.passwordValidator.isValidPassword(user), false);
    }

    @Test
    public void testPasswordRepeatedCharsError() throws  Exception {
        MockUser user = new MockUser("rTy90Mna111");
        assertEquals(this.passwordValidator.isValidPassword(user), false);

        user = new MockUser("rTy90Mnaaa");
        assertEquals(this.passwordValidator.isValidPassword(user), false);
    }

    @Test
    public void testPasswordInList() throws  Exception {
        MockUser user = new MockUser("Bubbles1");
        assertEquals(this.passwordValidator.isValidPassword(user), false);
    }

    @Test
    public void testRepeatedPassword() throws  Exception {
        MockUser user = new MockUser("%rTy90Mnb");
        assertEquals(this.passwordValidator.isValidPassword(user), false);
    }


}