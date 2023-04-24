package src;

import java.sql.Date;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import java.text.SimpleDateFormat;


/**
 * Test cases for the CourseList class
 * @author Adam Stewart
 */
public class CourseListTest {
    ArrayList<Question> questions = new ArrayList<Question>();
    Assessment test = new Assessment("assessment", questions);
    ArrayList<Module> modules = new ArrayList<Module>();
    ArrayList<Student> students = new ArrayList<Student>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
    public Course newCourse = new Course(null, "Intro to python", Difficulty.BEGINNER, "Course as intro to python", test, CourseType.PYTHON, modules, students);
    private UUID id;
    public Teacher wisdom = new Teacher(this.id = UUID.randomUUID(), "jWisdom", "John", "Wisdom", "jwisdom@gmail.com", "pythonRocks0", Date.valueOf("1971-03-25"));
    public Course existingCourse = new Course(wisdom, "Introduction to Python", Difficulty.BEGINNER, "Course as intro to python", test, CourseType.PYTHON, modules, students);

    @BeforeClass
	public void oneTimeSetup() {}
	
	@AfterClass
	public void oneTimeTearDown() {}
	
	@BeforeEach
	public void setup() {}
	
	@AfterEach
	public void tearDown() {}

    @Test
    public void testing() {
        assertTrue(true);
    }
    @Test
    public void testAddNullCourse() {
        CourseList courseList = CourseList.getInstance();
        boolean testBool = courseList.addCourse(null);
        assertFalse(testBool);
    }
    @Test
    public void testAddPreexistingCourse() {
        CourseList courseList = CourseList.getInstance();
        courseList.addCourse(existingCourse);
        boolean testBool = courseList.addCourse(existingCourse);
        assertFalse(testBool);
    }
    @Test
    public void testDeleteCourseNotInList() {
        CourseList courseList = CourseList.getInstance();
        boolean testBool = false;
        courseList.deleteCourse(newCourse);
        if(courseList.found("Intro to python")) {
            testBool = true;
        }
        assertFalse(testBool);
    }
    @Test
    public void testDeleteCourseInList() {
        CourseList courseList = CourseList.getInstance();
        courseList.addCourse(existingCourse);
        boolean testBool = false;
        courseList.deleteCourse(existingCourse);
        if(!courseList.found("Introduction to Python")) {
            testBool = true;
        }
        assertTrue(testBool);
    }
    @Test
    public void testDeleteCourseNull() {
        CourseList courseList = CourseList.getInstance();
        boolean testBool = false;
        courseList.deleteCourse(null);
        if(!courseList.found("Introduction to Python")) {
            testBool = true;
        }
        assertFalse(testBool);
    }
    @Test
    public void testCourseByNullKeyword() {
        CourseList courseList = CourseList.getInstance();
        courseList.addCourse(existingCourse);
        if(courseList.getCourseByKeyword(null) == null) {
            assertTrue(true);
        }
    }
    @Test
    public void testCourseByEmptyKeyword() {
        CourseList courseList = CourseList.getInstance();
        courseList.addCourse(existingCourse);
        if(courseList.getCourseByKeyword("") == null) {
            assertTrue(true);
        }
    }
    @Test
    public void testCourseByKeyword() {
        CourseList courseList = CourseList.getInstance();
        courseList.addCourse(existingCourse);
        if(courseList.getCourseByKeyword("Introduction to Python") == null) {
            assertTrue(true);
        }
    }
    @Test
    public void testFoundNullName() {
        CourseList courseList = CourseList.getInstance();
        courseList.addCourse(existingCourse);
        if(courseList.found(null)) {
            assertTrue(true);
        }
    }
    @Test
    public void testFoundEmptyName() {
        CourseList courseList = CourseList.getInstance();
        courseList.addCourse(existingCourse);
        if(courseList.found("")) {
            assertTrue(true);
        }
    }
    @Test
    public void testFoundName() {
        CourseList courseList = CourseList.getInstance();
        courseList.addCourse(existingCourse);
        if(courseList.found("Introduction to Python")) {
            assertTrue(true);
        }
    }
    @Test
    public void testFoundNullId() {
        CourseList courseList = CourseList.getInstance();
        courseList.addCourse(existingCourse);
        if(courseList.found(null)) {
            assertTrue(true);
        }
    }
    @Test
    public void testFoundEmptyId() {
        CourseList courseList = CourseList.getInstance();
        courseList.addCourse(existingCourse);
        if(courseList.found("")) {
            assertTrue(true);
        }
    }
    @Test
    public void testFoundId() {
        CourseList courseList = CourseList.getInstance();
        courseList.addCourse(existingCourse);
        if(courseList.found(existingCourse.getID().toString())) {
            assertTrue(true);
        }
    }
}