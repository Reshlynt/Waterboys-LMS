package src;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;


/**
 * Test cases for the User class
 * @author Jagger Tanner
 */
public class UserTest {
    public UserList userList = UserList.getInstance();
    public Student uzi = new Student("UziThaGoat13", "Grichael", "Crach", "gCrach@gmail.com", "password", "11011999");

    
    @BeforeClass
	public void oneTimeSetup() {}
	
	@AfterClass
	public void oneTimeTearDown() {}
	
	@BeforeEach
	public void setup() {}
	
	@AfterEach
	public void tearDown() {}

    // Password tests

    @Test
    public void Test() {
      assertTrue(true);
    }
    @Test
    public void testSetPasswordNull() {
        boolean testBool = uzi.setPassword(null);
        assertEquals(false, testBool, "Cannot set password to null");
    }
    @Test
    public void testSetPasswordTooLong() {
        boolean testBool = uzi.setPassword("!password12345678901234567890123456789012345678901234567890");
        assertFalse(testBool, "Password should not be set with excessive length");
    }
    @Test
    public void testSetPasswordTooShort() {
       boolean testBool = uzi.setPassword(".a1");
        assertEquals(false, testBool);
    }
    @Test
    public void testSetPasswordOnlySpecials() {
        boolean testBool = uzi.setPassword("*!/$@#/!_/!");
        assertEquals(false, testBool);
    }
    @Test
    public void testSetPasswordNoNumbersSpecials() {
        boolean testBool = uzi.setPassword("!password");
        assertEquals(false, testBool);
    }
    @Test
    public void testSetPasswordNoNumbersNoSpecials() {
        boolean testBool = uzi.setPassword("password");
        assertEquals(false, testBool);
    }
    @Test
    public void testSetPasswordNumbersNoSpecials() {
        boolean testBool = uzi.setPassword("password11");
        assertEquals(false, testBool);
    }
    @Test
    public void testPasswordNoLetters() {
        boolean testBool = uzi.setPassword("!!!!!12345");
        assertEquals(false, testBool);
    }
    @Test
    public void testSetPasswordValidPass() {
        boolean testBool = uzi.setPassword("!password123");
        assertEquals(true, testBool);
    }
    @Test
    public void testSetPasswordSpace() {
        boolean testBool = uzi.setPassword("!pass word123");
        assertEquals(false, testBool);
    }

    // Username tests

    @Test
    public void testSetUserNull() {
        boolean testBool = uzi.setUser(null);
        assertEquals(false, testBool);
    }
    @Test
    public void testSetUserSpace() {
        boolean testBool = uzi.setUser("UziTha Goat13");
        assertEquals(false, testBool);
    }
    @Test
    public void testSetUserNameTaken() {
        boolean testBool = uzi.setUser("UziThaGoat13");
        assertEquals(false, testBool);
    }
    @Test
    public void testSetUserOnlySpecials() {
        boolean testBool = uzi.setUser("!!!!!!!!!!!!");
        assertEquals(false, testBool);
    }
    @Test
    public void testSetUserTooShort() {
        boolean testBool = uzi.setUser("a");
        assertEquals(false, testBool);
    }
    @Test
    public void testSetUserTooLong() {
        boolean testBool = uzi.setUser("!asdfghjkl1234567890asdfghjkl1234567890");
        assertEquals(false, testBool);
    }
    @Test
    public void testSetUserValid() {
        boolean testBool = uzi.setUser("UziTheGoat13");
        assertEquals(true, testBool);
    }

    // equals tests

    @Test
    public void testEqualsNull() {
        boolean testBool = uzi.equals(null);
        assertEquals(false, testBool);
    }
    @Test
    public void testEqualsEqual() {
        UUID uziID = uzi.getID();
        boolean testBool = uzi.equals(uziID);
        assertEquals(true, testBool);
    }
    @Test
    public void testEqualsInequal() {
        boolean testBool = uzi.equals(UUID.randomUUID());
        assertEquals(false, testBool);
    }

}