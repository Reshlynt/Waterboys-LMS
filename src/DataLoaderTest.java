package src;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataLoaderTest {
  private UserList users = UserList.getInstance();
  private ArrayList<User> userList = users.getUserList();

  @BeforeEach
  public void setup() {
    userList.clear();
    userList.add(new Student("asmith", "Amy", "Smith", "asmith@email.sc.edu", "amypassword", "04182003"));
    userList.add(new Student("bwhite", "Bob", "White", "bwhite@email.sc.edu", "bobpassword", "10121980"));
    DataWriter.saveUsers();

    CourseList.getInstance().getCourseList().clear();

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
    UserList.getInstance().getUserList().clear();
    userList.add(new Student("asmith", "Amy", "Smith", "wrong email haha", "amypassword", "04182003"));
    DataWriter.saveUsers();
    assertEquals("default_email@sc.edu", UserList.getInstance().getUserList().get(0).getEmail());
  }

  @Test
  void userInvalidBirthday() {
    UserList.getInstance().getUserList().clear();
    DataWriter.saveUsers();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
    userList.add(new Student("asmith", "Amy", "Smith", "wrong email haha", "amypassword", "1234"));
    DataWriter.saveUsers();

    try {
      assertEquals(dateFormat.parse("1234"), UserList.getInstance().getUserList().get(0).getDOB());
    } catch (ParseException e) {
    }
  }

  @Test
  void userInvalidID() {

    UserList.getInstance().getUserList().clear();
    DataWriter.saveUsers();

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
    try {
      userList.add(new Student(UUID.fromString("asdfasdfadf"), "asmith", "amy", "schumer", "blank@email.sc.edu",
          "brothisisapassword", dateFormat.parse("12231973")));
    } catch (ParseException e) {
    }
    DataWriter.saveUsers();
    assertEquals(UUID.fromString("asdfasdfadf"), UserList.getInstance().getUserList().get(0).getDOB());

  }

  @Test
  void courseNoTeacher() {
    ArrayList<Question> questions = new ArrayList<Question>();
    Assessment test = new Assessment("assessment", questions);
    ArrayList<Module> modules = new ArrayList<Module>();
    ArrayList<Student> students = new ArrayList<Student>();
    for (User user : userList)
      students.add((Student) (user));

    Course newCourse = new Course(null, "Intro to python", Difficulty.BEGINNER, "Course as intro to python", test,
        CourseType.PYTHON, modules, students);
    CourseList.getInstance().getCourseList().add(newCourse);
    DataWriter.saveCourses();

    ArrayList<Course> courseList = DataLoader.getCourses();
    assertEquals(null, courseList.get(0).getAuthor());
  }

  @Test
  void courseEmptyModule() {
    Teacher teacher = new Teacher("anthony", "anthony", "smith", "asmith@gmail.com", "password", "12061793");
    ArrayList<Question> questions = new ArrayList<Question>();
    Assessment test = new Assessment("assessment", questions);
    ArrayList<Module> modules = null;
    ArrayList<Student> students = new ArrayList<Student>();
    for (User user : userList) {
      students.add((Student) (user));
    }
    Course newCourse = new Course(teacher, "Intro to python", Difficulty.BEGINNER, "Course as intro to python", test,
        CourseType.PYTHON, modules, students);
    CourseList.getInstance().getCourseList().add(newCourse);
    DataWriter.saveCourses();

    ArrayList<Course> courseList = DataLoader.getCourses();
    assertEquals(null, courseList.get(0).getModules().get(0));
  }

  @Test
  void courseNoAssessment(){
    Teacher teacher = new Teacher("anthony", "anthony", "smith", "asmith@gmail.com", "password", "12061793");
    ArrayList<Question> questions = new ArrayList<Question>();
    Assessment test = null;
    ArrayList<Module> modules = new ArrayList<Module>();
    ArrayList<Student> students = new ArrayList<Student>();
    for (User user : userList) {
      students.add((Student) (user));
    }
    Course newCourse = new Course(teacher, "Intro to python", Difficulty.BEGINNER, "Course as intro to python", test,
        CourseType.PYTHON, modules, students);
    CourseList.getInstance().getCourseList().add(newCourse);
    DataWriter.saveCourses();

    ArrayList<Course> courseList = DataLoader.getCourses();
    assertEquals(null, courseList.get(0).getAssessment());
  }
  
  @Test
  void courseNoGrades(){
    Teacher teacher = new Teacher("anthony", "anthony", "smith", "asmith@gmail.com", "password", "12061793");
    ArrayList<Question> questions = new ArrayList<Question>();
    Assessment test = new Assessment("title", questions);
    ArrayList<Module> modules = new ArrayList<Module>();
    ArrayList<Student> students = new ArrayList<Student>();
    for (User user : userList) {
      students.add((Student) (user));
    }
    Course newCourse = new Course(teacher, "Intro to python", Difficulty.BEGINNER, "Course as intro to python", test,
        CourseType.PYTHON, modules, students);
    for(int i = 0; i < students.size();i++){
      students.get(i).setCourseProgress(newCourse, null, false);
    }
    CourseList.getInstance().getCourseList().add(newCourse);
    DataWriter.saveCourses();

    ArrayList<Course> courseList = DataLoader.getCourses();
    assertEquals(courseList.get(0).getStudents().get(0).getCourseGrade(newCourse), 0);

  }
  /**
   * user had no grades in course,
   */
}