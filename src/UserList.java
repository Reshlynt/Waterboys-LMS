package src;

import java.util.ArrayList;
import java.util.UUID;

public class UserList {
  private static ArrayList<User> userList;
  private static UserList singletonUserList;

  private UserList() {
    userList = DataLoader.getUsers();
  }

  // Singleton pattern
  public static UserList getInstance() {
    if (singletonUserList == null)
      singletonUserList = new UserList();
    return singletonUserList;
  }
  
  /**
   * 
   * @return Instance of the UserList object.
   */

  public void addUser(User user) {
    if (!userList.contains((user)))
      userList.add(user);
  }

  // method to remove a user from the list
  public void deleteUser(User user) {
    userList.remove(user);
  }

  // method to get the list of users
  public ArrayList<User> getUserList() {
    return userList;
  }

  // method to get a user by their ID
  public User getUserByUUID(UUID id) {
    for (User user : userList) {
      if (user.equals(id)) {
        return user;
      }
    }
    return null;
  }

  // method to get a user by their username
  public boolean foundUser(String username) {
    for (User user : userList) {
      if (user.getUsername().equals(username)) {
        return true;
      }
    }
    return false;
  }

  // method to get a user by their username
  public User getUser(String username) {
    for (User user : userList) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null;
  }

  // method to print the list of users
  public void Print() {
    for (int i = 0; i < userList.size(); i++) {
      System.out.println(userList.get(i));
    }
  }

  public static void saveUsers() {
    DataWriter.saveUsers();
  }
}
