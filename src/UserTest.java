package src;
import static org.junit.jupiter.api.Assertions.*;
import static org.juint.jupiter.api.Test;

import java.util.file;


public class UserTest {
    // Password tests

    @Test
    public void testSetPasswordNull() {
        User.setPassword(null);
    }
    @Test
    public void testSetPasswordTooLong() {
        User.setPassword("!password12345678901234567890123456789012345678901234567890");
    }
    @Test
    public void testSetPasswordTooShort() {
        User.setPassword(".a1");
    }
    @Test
    public void testSetPasswordOnlySpecials() {
        User.setPassword("*!/$@#/!_/!");
    }
    @Test
    public void testSetPasswordNoNumbersSpecials() {
        User.setPassword("!password")
    }
    @Test
    public void testSetPasswordNoNumbersNoSpecials() {
        User.setPassword("password");
    }
    @Test
    public void testSetPasswordNumbersNoSpecials() {
        User.setPassword("password11");
    }
    @Test
    public void testPasswordNoLetters() {
        User.setPassword("!!!!!12345");
    }
    @Test
    public void testSetPasswordValidPass() {
        User.setPassword("!password123"));
    }
    @Test
    public void testSetPasswordSpace() {
        User.setPassword("!pass word123");
    }

    // Username tests

    @Test
    public void testSetUserNull() {
        User.setUser(null);
    }
    @Test
    public void testSetUserSpace() {
        User.setUser("UziTha Goat13");
    }
    @Test
    public void testSetUserNameTaken() {
        return null;
    }
    @Test
    public void testSetUserOnlySpecials() {
        return null;
    }
    @Test
    public void testSetUserTooShort() {
        return null;
    }
    @Test
    public void testSetUserTooLong() {
        return null;
    }

    // equals tests

    @Test
    public void testEqualsNull() {
        return null;
    }
    @Test
    public void testEqualsEqual() {
        return null;
    }
    @Test
    public void testEqualsInequal() {
        return null;
    }

}