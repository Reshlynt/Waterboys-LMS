package src;

import java.util.ArrayList;
import java.util.UUID;

import java.util.Date;

public class Teacher extends User {
  private ArrayList<Course> courses = new ArrayList<Course>();
  private ArrayList<Student> students = new ArrayList<Student>();

  public Teacher(UUID id, String username, String firstName, String lastName, String email, String password, Date DOB) {
    super(id, username, firstName, lastName, email, password, DOB);
  }

  public Teacher(String username, String firstName, String lastName, String email, String password, Date DOB) {
    super(username, firstName, lastName, email, password, DOB);
    this.id = UUID.randomUUID();
  }

  public boolean addToCourse(Student student, Course course) {
    course.addToCourse(student);
    return true;
  }

  public boolean removeFromCourse(Student student, Course course) {
    return course.removeFromCourse(student);
  }

  public void makeComment() {
    return;
  }

  public String getType() {
    return "teacher";
  }

  // Returns the courses that the teacher has created
  public ArrayList<Course> getCourses() {
    return this.courses;
  }

  // Returns the teacher's students
  public ArrayList<Student> getStudents() {
    return students;
  }

  public void addCourse(Course course) {
    this.courses.add(course);
  }
}
