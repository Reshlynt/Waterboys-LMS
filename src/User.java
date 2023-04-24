package src;

/**
 * Represents a user of the system
 * @author Waterboys
 */
import java.util.UUID;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public abstract class User {
  protected UUID id;
  protected String username;
  protected String firstName;
  protected String lastName;
  protected String email;
  protected String password;
  protected Date DOB;

  public User(UUID id, String username, String firstName, String lastName, String email, String password, Date DOB) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.DOB = DOB;
  }

  public User(String username, String firstName, String lastName, String email, String password, Date DOB) {
    this.id = UUID.randomUUID();
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.DOB = DOB;
  }

  /**
   * Checks validity of entered password and sets it if valid
   * 
   * @param pass the password to be set
   * @return true if the password is valid, false otherwise
   */
  public boolean setPassword(String pass) {
    if (pass.length() < 8) {
      return false;
    }
    for (int i = 0; i < pass.length(); i++) {
      if (pass.charAt(i) == ' ' || pass.charAt(i) == '\t' || pass.charAt(i) == '\n') {
        return false;
      }
    }
    password = pass;
    return true;
  }

  /**
   * Sets username after checking the length, contents, and if theres already an
   * existing user with that username
   * 
   * @param user the username to be set
   * @return true if the username is valid and not already taken, false otherwise
   */
  public boolean setUser(String user) {
    if (user.length() < 5 || user.length() > 14) {
      return false;
    }
    for (int i = 0; i < password.length(); i++) {
      if (user.charAt(i) == ' ' || user.charAt(i) == '\t' || user.charAt(i) == '\n' || user.charAt(i) == '@'
          || user.charAt(i) == '\\' || user.charAt(i) == '%') {
        return false;
      }
    }
    ArrayList<User> currentUsers = UserList.getInstance().getUserList();
    for (User u : currentUsers) {
      if (u.username.equals(user)) {
        return false;
      }
    }
    this.username = user;
    return true;
  }

  // print the user's information
  public String toString() {
    return "User: " + id + " " + username + " " + firstName + " " + lastName + " " + email + " " + password + " " + DOB;
  }

  // method to verify if the user is the same as the one passed in
  public boolean equals(UUID id) {
    return this.id.equals(id);
  }

  // getters for all instance variables
  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  // Return the ID of the user.
  public UUID getID() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public Date getDOB() {
    return DOB;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  // setters for all instance variables
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDOB(Date DOB) {
    this.DOB = DOB;
  }

  // Return true if the user is at least 13 years old.
  public boolean ofAge() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(DOB);
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    cal.setTime(new Date());
    int currentYear = cal.get(Calendar.YEAR);
    int currentMonth = cal.get(Calendar.MONTH);
    int currentDay = cal.get(Calendar.DAY_OF_MONTH);
    int age = currentYear - year;
    if (currentMonth < month) {
      age--;
    } else if (currentMonth == month) {
      if (currentDay < day) {
        age--;
      }
    }
    return (age >= 13);
  }

  // Return the type of user.
  public abstract String getType();
}
