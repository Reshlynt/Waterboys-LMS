package src;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserListTest {
    
    @BeforeClass
	public void oneTimeSetup() {
		
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public void setup() {
		//runs before each test
	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
	}
	
	//assertEquals(val1,val2)
	//assertFalse(val)
	//assertTrue(val)
	//assertSame(val1,val2)
	//assertNotSame(val1,val2)
	//assertNull(val)
	//assertNotNull(val)

    @Test
    public void TestAddTeacher() {
      UserList userList = UserList.getInstance();
      User new_user = new Teacher(UUID.randomUUID(), "username", "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      assertEquals(userList.foundUser("username"), true);   
    }

    @Test
    public void TestAddStudent() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), "username", "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      assertEquals(userList.foundUser("username"), true);     
    }

    @Test
    public void TestAddNullUser() {
      UserList userList = UserList.getInstance();
      User new_user = null;
      userList.addUser(new_user);
      User tester = new Student(UUID.randomUUID(), "username", "John", "Doe", "JD@JD.com", "password", null);
      for (User user : userList.getUserList()) {
        if (user == new_user) {
          tester = user;
        }
      }
      assertNotNull(tester);
    }

    @Test
    public void TestAddExistingUser() {
      UserList userList = UserList.getInstance();
      User new_user = userList.getUser("UziThaGoat13");
      userList.addUser(new_user);
      assertSame(true, !userList.getUserList().contains(new_user));   
    }

    @Test
    public void TestDeleteNullUser() {
      UserList userList = UserList.getInstance();
      User new_user = null;
      userList.addUser(new_user);
      userList.deleteUser(new_user);
      for (User user : userList.getUserList()) {
        if (user == new_user) {
          userList.deleteUser(new_user);
          assertSame(false, userList.getUserList().contains(new_user));
        }
      }
    }

    @Test
    public void TestDeleteExistingUser() {
      UserList userList = UserList.getInstance();
      User new_user = userList.getUser("UziThaGoat13");
      userList.deleteUser(new_user);
      assertSame(true, !userList.getUserList().contains(new_user));   
    }

    @Test
    public void TestGetUserByUUID() {
      UserList userList = UserList.getInstance();
      User new_user = null;
      assertSame(new_user, userList.getUserByUUID(new_user.getID()));   
    }

    @Test
    public void TestfoundUserInList() {
      UserList userList = UserList.getInstance();
      User new_user = userList.getUser("UziThaGoat13");
      assertTrue(userList.foundUser(new_user.getUsername()));   
    }

    @Test
    public void TestfoundUserNotInList() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), "username", "John", "Doe", "JD@JD.com", "password", null);
      assertFalse(userList.foundUser(new_user.getUsername()));   
    }

    @Test
    public void TestfoundUserNullUsername() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), null, "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      assertFalse(userList.foundUser(new_user.getUsername()));   
    }

    @Test
    public void TestGetUserNullUsername() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), null, "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      assertNull(userList.getUser(new_user.getUsername()));   
    }

    @Test
    public void TestGetUserByUsernameNotInList() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), "I<3testing", "John", "Doe", "JD@JD.com", "password", null);
      assertNull(userList.getUser(new_user.getUsername()));   
    }

    @Test
    public void TestGetUserByUsernameInList() {
      UserList userList = UserList.getInstance();
      User new_user = new Student(UUID.randomUUID(), "I<3testing", "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      assertNotNull(userList.getUser(new_user.getUsername()));   
    }
}