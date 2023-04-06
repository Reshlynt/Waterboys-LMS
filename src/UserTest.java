package src;
import static org.junit.jupiter.api.Assertions.*;
import static org.juint.jupiter.api.Test;
import static java.util.UUID;

import java.util.file;


public class UserTest {
    public UserList userList = UserList.getInstance();
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
        User.setUser("UziThaGoat13");
    }
    @Test
    public void testSetUserOnlySpecials() {
        User.setUser("!!!!!!!!!!!!");
    }
    @Test
    public void testSetUserTooShort() {
        User.setUser("a");
    }
    @Test
    public void testSetUserTooLong() {
        User.setUser("!asdfghjkl1234567890asdfghjkl1234567890");
    }

    // equals tests
    User uzi = userList.getUser("UziThaGoat13");

    @Test
    public void testEqualsNull() {
        uzi.User.equals(null);
    }
    @Test
    public void testEqualsEqual() {
        uzi.User.equals("e58ed763-928c-4155-bee9-fdbaaadc15f3");
    }
    @Test
    public void testEqualsInequal() {
        uzi.User.equals(UUID.randomUUID());
    }

}