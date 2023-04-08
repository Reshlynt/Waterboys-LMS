package src;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataWriterTest {

    private UserList userList = UserList.getInstance(); // Instance of a UserList
    private ArrayList<User> Users = userList.getUserList(); // List of users

    // Before each is doing pre-test things before testing.
    // Test is actual testing

    /**
     * Setup method for testing. Clears the UserList.
     */
    @BeforeEach
    public void setup() {
        UserList.getInstance().getUserList().clear();

        DataWriter.saveUsers();
    }

    /*
     * Cleaning after testing method. Cleans the userList.
     */
    @AfterEach
    public void tearDown() {
        UserList.getInstance().getUserList().clear();
        DataWriter.saveUsers();
    }

    /*
     * Test 1: Determine whether DataWriter can save a user.
     */
    @Test
    void testWritingOneUser() {
        String username = "john_smith";
        String firstName = "John";
        String lastName = "Smith";
        String email = "john.smith@example.com";
        String password = "password123";
        String DOB = "01052000";

        User user1 = new Student(username, firstName, lastName, email, password, DOB);

        Users.add(user1);
        DataWriter.saveUsers();
        assertEquals(user1.getID(), DataLoader.getUsers().get(0).getID());
    }

    /**
     * Test 2: Test several users. 5 users will be added, and this test will find if the third user
     * appears. This user will be Lucatiel of Mirrah.
     */
    @Test
    void testSeveralUsers() {
        User samepleUser = new Student("lucatiel22", "Lucatiel", "of Mirrah",
                "lucatiel22@gmail.com", "password123", "04121991"); // User to find

        Users.add(new Teacher("King_In_TheNorth69", "Jon", "Snow", "crackweedcane@aol.com",
                "password", "06251995"));
        Users.add(new Teacher("Jorumganer_Ahsoka", "Kirby", "Kirbo",
                "schlitzmaltliqour@example.com", "password123", "04252002"));
        Users.add(samepleUser);
        Users.add(new Student("arthurmorgan1899", "Arthur", "Morgan", "arthurmorgan1899@gmail.com",
                "password123", "07181972"));
        Users.add(new Student("ikorarey1", "Ikora", "Rey", "ikorarey1@gmail.com", "password123",
                "09091988"));

        DataWriter.saveUsers();
        assertEquals(samepleUser.getID(), DataLoader.getUsers().get(2).getID());

    }

    /**
     * Test 3: Write an empty user.
     * Determine if DataWriter can write to a JSON file if the user has empty strings as a constructor.
     */
    @Test
    void testWritingEmptyUser() {
        Users.add(new Teacher("", "", "", "", "", ""));
        DataWriter.saveUsers();
        assertEquals("", DataLoader.getUsers().get(0).getFirstName());
    }

}
