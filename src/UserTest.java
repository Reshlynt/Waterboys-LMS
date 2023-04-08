package src;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.UUID;



public class UserTest {
    public UserList userList = UserList.getInstance();
    User uzi = userList.getUser("UziThaGoat13");

    // Password tests

    @Test
    public void testSetPasswordNull() {
        boolean testBool = uzi.setPassword(null);
        assertEquals(false, testBool, "Cannot set password to null");
    }
    @Test
    public void testSetPasswordTooLong() {
        boolean testBool = uzi.setPassword("!password12345678901234567890123456789012345678901234567890");
        assertEquals(false, testBool);
    }
    @Test
    public void testSetPasswordTooShort() {
        uzi.setPassword(".a1");
    }
    @Test
    public void testSetPasswordOnlySpecials() {
        uzi.setPassword("*!/$@#/!_/!");
    }
    @Test
    public void testSetPasswordNoNumbersSpecials() {
        uzi.setPassword("!password")
    }
    @Test
    public void testSetPasswordNoNumbersNoSpecials() {
        uzi.setPassword("password");
    }
    @Test
    public void testSetPasswordNumbersNoSpecials() {
        uzi.setPassword("password11");
    }
    @Test
    public void testPasswordNoLetters() {
        uzi.setPassword("!!!!!12345");
    }
    @Test
    public void testSetPasswordValidPass() {
        uzi.setPassword("!password123"));
    }
    @Test
    public void testSetPasswordSpace() {
        uzi.setPassword("!pass word123");
    }

    // Username tests

    @Test
    public void testSetUserNull() {
        uzi.setUser(null);
    }
    @Test
    public void testSetUserSpace() {
        uzi.setUser("UziTha Goat13");
    }
    @Test
    public void testSetUserNameTaken() {
        uzi.setUser("UziThaGoat13");
    }
    @Test
    public void testSetUserOnlySpecials() {
        uzi.setUser("!!!!!!!!!!!!");
    }
    @Test
    public void testSetUserTooShort() {
        uzi.setUser("a");
    }
    @Test
    public void testSetUserTooLong() {
        uzi.setUser("!asdfghjkl1234567890asdfghjkl1234567890");
    }

    // equals tests

    @Test
    public void testEqualsNull() {
        boolean testBool = uzi.equals(null);
    }
    @Test
    public void testEqualsEqual() {
        boolean testBool = uzi.equals("e58ed763-928c-4155-bee9-fdbaaadc15f3");
    }
    @Test
    public void testEqualsInequal() {
        boolean testBool = uzi.equals(UUID.randomUUID());
    }

}