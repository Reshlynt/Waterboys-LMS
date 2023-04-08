package src;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A Test class developed to test the methods behind the UserList class
 * @author Pushpendra Shekhawat/shekhawp
 * 
 */

class UserListTest {
    
  @BeforeClass
	public void oneTimeSetup() {}
	
	@AfterClass
	public void oneTimeTearDown() {}
	
	@BeforeEach
	public void setup() {}
	
	@AfterEach
	public void tearDown() {}

    /**
     * TestAddTeacher is designed to test if we can successfully add a Teacher object to our UserList
     * @assert We are checking to see if a newly created Teacher object is added to our UserList
     */
    @Test
    public void TestAddTeacher() {
      UserList userList = UserList.getInstance();
      User new_user = new Teacher(UUID.randomUUID(), "username", "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      boolean tester = false;
      for (User user : userList.getUserList()) {
        if (user == new_user)
          tester = true;
      }
      assertTrue(tester);   
    }

    /**
     * TestAddStudent is designed to test if we can successfully add a Student object to our UserList
     * @assert We are checking to see if a newly created Student object is added to our UserList
     */
    @Test
    public void TestAddStudent() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), "username", "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      boolean tester = false;
      for (User user : userList.getUserList()) {
        if (user == new_user)
          tester = true;
      }
      assertTrue(tester);   
    }

    /**
     * TestAddNullUser is designed to test if our program does not add a Null User to our UserList
     * @assert We are checking to see if a Null User object is not added to our UserList
     */
    @Test
    public void TestAddNullUser() {
      UserList userList = UserList.getInstance();
      User new_user = null;
      userList.addUser(new_user);
      boolean tester = false;
      for (User user : userList.getUserList()) {
        if (user == new_user) {
          tester = true;
        }
      }
      assertFalse(tester);
    }

    /**
     * TestAddExistingUser is desgined to test if our program does not add an existing user to our UserList
     * @assert We are checking to see if a duplicate User object is not added to our UserList
     */
    @Test
    public void TestAddExistingUser() {
      UserList userList = UserList.getInstance();
      User new_user = userList.getUser("UziThaGoat13");
      userList.addUser(new_user);
      assertSame(true, !userList.getUserList().contains(new_user));   
    }

    /**
     * TestDeleteNullUser is designed to test if our program will delete a Null User object from our UserList
     * @assert We are checking to see if a Null User is in our UserList once we both add and delete that Null User
     */
    @Test
    public void TestDeleteNullUser() {
      UserList userList = UserList.getInstance();
      User new_user = null;
      userList.addUser(new_user);
      userList.deleteUser(new_user);
      assertSame(false, userList.getUserList().contains(new_user));
      
    }

    /**
     * TestDeleteExistingUser is designed to test if we can delete an existing User object from the UserList
     * @assert We are checking to see if an existing User is deleted from our UserList
     */
    @Test
    public void TestDeleteExistingUser() {
      UserList userList = UserList.getInstance();
      User new_user = userList.getUser("UziThaGoat13");
      userList.deleteUser(new_user);
      assertSame(true, !userList.getUserList().contains(new_user));   
    }

    /**
     * TestGetUserByUUID is designed to test if we can retrieve a User object by their UUID from the UserList
     * @assert We are checking to see if we can retrieve a User object by their UUID from the UserList
     */
    @Test
    public void TestGetUserByUUID() {
      UserList userList = UserList.getInstance();
      assertNotNull(userList.getUserByUUID(userList.getUser("UziThaGoat13").getID()));   
    }

    /**
     * TestFoundUserInList is designed to test if we found a User object from our UserList
     * @assert We are checking to see if we have a User object that should already exist in our UserList
     */
    @Test
    public void TestFoundUserInList() {
      UserList userList = UserList.getInstance();
      assertTrue(userList.foundUser("UziThaGoat13"));   
    }

    /**
     * TestFoundUserNotInList is designed to test if we did not find a User object from our UserList
     * @assert we are checking to see that we do not have a non-existing User object in our UserList
     */
    @Test
    public void TestFoundUserNotInList() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), "username1", "John", "Doe", "JD@JD.com", "password", null);
      assertFalse(userList.foundUser(new_user.getUsername()));   
    }

    /**
     * TestFoundUserNullUserName is designed to test if we have a User object in our UserList with a null username
     * @assert we are checking to see if our UserList contains a user with a null username
     */
    @Test
    public void TestFoundUserNullUsername() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), null, "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      assertFalse(userList.foundUser(new_user.getUsername()));   
    }

    /**
     * TestGetUserNullUsername is designed to test if we can retrieve a User object with a null username from our UserList
     * @assert we are checking to see if we can retrieve a User object with a null username from our UserList
     */
    @Test
    public void TestGetUserNullUsername() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), null, "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      assertNull(userList.getUser(new_user.getUsername()));   
    }

    /**
     * TestGetUserByUsernameNotInList is designed to test if do not get a User object from our UserList by the username
     * @assert we are checking to see if we do not get some type of User object by trying to get a User object from the UserList by a username
     */
    @Test
    public void TestGetUserByUsernameNotInList() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), "I<3testing", "John", "Doe", "JD@JD.com", "password", null);
      assertNull(userList.getUser(new_user.getUsername()));   
    }

    /**
     * TestGetUserByUsernameInList is designed to test if we get a User object from our UserList by the username
     * @assert we are checking to see if we get a User object from our UserList with a username
     */
    @Test
    public void TestGetUserByUsernameInList() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), "I<3testing", "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      assertNotNull(userList.getUser(new_user.getUsername()));   
    }
}