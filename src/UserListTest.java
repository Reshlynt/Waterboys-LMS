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
	//public User(UUID id, String username, String firstName, String lastName, String email, String password, Date DOB)

    @Test
    public void TestAddTeacher() {
      UserList userList = UserList.getInstance();

      User new_user = new Teacher(UUID.randomUUID(), "username", "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      assertSame(new_user, new_user);   
    }

    @Test
    public void TestAddStudent() {
      UserList userList = UserList.getInstance();

      User new_user = new Student(UUID.randomUUID(), "username", "John", "Doe", "JD@JD.com", "password", null);
      userList.addUser(new_user);
      assertSame(new_user, new_user);   
    }

    @Test
    public void TestAddNullTeacher() {
      UserList userList = UserList.getInstance();

      User new_user = new Teacher(null, null, null, null, null, null, null);
      userList.addUser(new_user);
      assertSame(new_user, null);   
    }

    @Test
    public void TestAddNullStudent() {
      UserList userList = UserList.getInstance();

      User new_user = new Student(null, null, null, null, null, null, null);
      userList.addUser(new_user);
      assertSame(new_user, null);   
    }
}
