package src;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataLoaderTest {
  private UserList users = UserList.getInstance();
  private ArrayList<User> userList = users.getUserList();

  @BeforeEach
  public void setup() {
    userList.clear();
    userList.add(new Student("asmith", "Amy", "Smith", "asmith@email.sc.edu", "amypassword", "04182003"));
    userList.add(new Student("bwhite", "Bob", "White", "bwhite@email.sc.edu", "bobpassword", "10121980"));
    DataWriter.saveUsers();

  }

  @AfterEach
  public void tearDown() {
    UserList.getInstance().getUserList().clear();
    DataWriter.saveUsers();
  }

  @Test
  public void Test() {
    assertTrue(true);
  }

  @Test
  void testGetUsersSize() {
    userList = DataLoader.getUsers();
    assertEquals(2, userList.size());
  }

  @Test
  void testGetUsersSizeZero() {
    UserList.getInstance().getUserList().clear();
    DataWriter.saveUsers();
    assertEquals(0, userList.size());
  }

  @Test
  void testGetUserFirstUserName() {
    userList = DataLoader.getUsers();
    assertEquals("asmith", userList.get(0).getUsername());
  }

  @Test
  void userInvalidEmail() {
    userList = DataLoader.getUsers();
    // add an invalid email
  }
}
