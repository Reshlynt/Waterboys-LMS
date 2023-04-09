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
    private CourseList courseList = CourseList.getInstance(); // Instance of a UserList
    private ArrayList<Course> Courses = courseList.getCourseList(); // List of users

    // Before each is doing pre-test things before testing.
    // Test is actual testing

    /**
     * Setup method for testing. Cleans the userList, and CourseList.
     */
    @BeforeEach
    public void setup() {
        UserList.getInstance().getUserList().clear();
        CourseList.getInstance().getCourseList().clear();
        DataWriter.saveUsers();
    }

    /*
     * Cleaning after testing method. Cleans the userList, and CourseList.
     */
    @AfterEach
    public void tearDown() {
        UserList.getInstance().getUserList().clear();
        CourseList.getInstance().getCourseList().clear();
        DataWriter.saveUsers();
    }

    // ---------------------------------------------------------
    // User Tests
    // ---------------------------------------------------------

    /*
     * Test 1: Determine whether DataWriter can save a user. The user will be found by UUID.
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
     * Test 3: Write an empty user. Determine if DataWriter can write to a JSON file if the user has
     * empty strings as a constructor.
     */
    @Test
    void testWritingEmptyUser() {
        Users.add(new Teacher("", "", "", "", "", ""));
        DataWriter.saveUsers();
        assertEquals("", DataLoader.getUsers().get(0).getFirstName());
    }

    /**
     * Test 4: Test zero users. Does DataWriter still function if the singleton UserList has no
     * users.
     */
    @Test
    void testWritingZeroUsers() {
        Users = DataLoader.getUsers();
        assertEquals(0, Users.size());
    }

    // ---------------------------------------------------------
    // Course Tests
    // ---------------------------------------------------------
    @Test
    void testWriteOneCourse() {
        Teacher sampleTeacher = new Teacher("Jorumganer_Ahsoka", "Kirby", "Kirbo",
                "schlitzmaltliqour@example.com", "password123", "04252002");

        // Create an exam
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", null, "Paris"));
        questions.add(new Question("What is the largest ocean on Earth?", null, "Pacific"));
        Assessment exam = new Assessment("Final Exam", questions);

        // Create a module with slides
        ArrayList<TextSlide> slides = new ArrayList<>();
        slides.add(new TextSlide("Slide 1", "This is the content of slide 1"));
        slides.add(new TextSlide("Slide 2", "This is the content of slide 2"));
        Module module = new Module("Module 1", slides);

        // Create some students
        Student student1 =
                new Student("jdoe", "John", "Doe", "jdoe@example.com", "password", "06251995");
        Student student2 =
                new Student("mike", "Mike", "Smith", "mike@example.com", "password", "03011998");

        // Create a course with the objects created above
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(module);
        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        Course course = new Course(sampleTeacher, "Intro to Programming", Difficulty.INTERMEDIATE,
                "This course teaches the basics of programming in Java", exam,
                CourseType.JAVASCRIPT, modules, students);

        Courses.add(course);
        DataWriter.saveCourses();
        assertEquals(course.getID(), DataLoader.getCourses().get(0).getID());
    }

}
