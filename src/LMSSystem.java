package src;

import java.util.Date;
import java.util.UUID;

public class LMSSystem {
  public UserList userList = UserList.getInstance();
  public CourseList courseList;

  public User Login(String username, String password) {
    if (userList.foundUser(username)) {
      if (userList.getUser(username).getPassword().equals(password)) {
        UI.WelcomeLine7("Login successful!");
        UI.enterToContinue();
        return userList.getUser(username);
      } else {
        UI.WelcomeLine7("Wrong password!");
        UI.enterToContinue();
        return null;
      }
    } else {
      UI.WelcomeLine7("User not found!");
      UI.enterToContinue();
      return null;
    }
  }

  public void Logout() {
    System.out.println("Logout successfully!");
    UI.Welcome();
    return;
  }

  public void addComment(Course course, User user, String input) {
    course.addComment(input, user);
  }

  public void addComment(Module module, User user, String input) {
    module.addComment(input, user);
  }

  public User SignUp(String firstName, String lastName, String username, String email, String password,
      Date dateOfBirth, String job) {
    if (userList.foundUser(username)) {
      UI.WelcomeLine7("User already exists!");
    } else {
      User new_user = null;
      if (job.equalsIgnoreCase("student")) {
        new_user = new Student(UUID.randomUUID(), username, firstName, lastName, email, password, dateOfBirth);

      } else if (job.equalsIgnoreCase("teacher")) {
        new_user = new Teacher(UUID.randomUUID(), username, firstName, lastName, email, password, dateOfBirth);
      } else {
        UI.WelcomeLine7("You did not enter \"student\" or \"teacher\"");
        UI.enterToContinue();
        return null;
      }
      userList.addUser(new_user);
      UI.WelcomeLine7("Sign up successfully!");
      UI.enterToContinue();
      return new_user;
    }
    UI.enterToContinue();
    return null;
  }

  public void goToCourse(Course course) {
    System.out.println("You are now in " + course.getTitle() + " course!");
  }

  public void goToCourse(String name) {
    if (courseList.getCourseByKeyword(name).size() == 0) {
      System.out.println("Course not found!");
    } else if (courseList.getCourseByKeyword(name).size() == 1) {
      System.out.println("You are now in " + courseList.getCourseByKeyword(name).get(0).getTitle() + " course!");
    } else {
      System.out.println("There are more than one course with this name, please choose one:");
      for (Course course : courseList.getCourseByKeyword(name)) {
        System.out.println(course.getTitle());
      }
    }
  }

  public void submitFeedback(String feedback) {
    System.out.println("Feedback submitted successfully!");
  }

  public User getUser(String username) {
    return userList.getUser(username);
  }

  public boolean addCourse(Course course) {
    return courseList.addCourse(course);
  }

  public CourseList getCourseList() {
    return courseList;
  }

  public void saveInfo() {
    UserList.saveUsers();
    CourseList.saveCourses();
  }
}