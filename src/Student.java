
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Arrays;
import java.util.List;

public class Student extends User {
  private ArrayList<CourseStatus> courseProgresses;// list of courses and respective grades
  private ArrayList<Certificate> certificates;

  public Student(UUID id, String username, String firstName, String lastName, String email, String password, Date DOB) {
    super(id, username, firstName, lastName, email, password, DOB);
    courseProgresses = new ArrayList<CourseStatus>();
  }

  public void setCourseGrade(Course course, ArrayList<Long> grades) {
    // this method will get an array list of grades, which signify the grades for a
    // specific class(module quiz/exam grades
    CourseStatus newCourseStatus = new CourseStatus(course, grades);
    courseProgresses.add(newCourseStatus);
  }

  public void saveCourseProgress() {
    courseProgresses.add();
  }

  public void registerCourse(Course course) {
    // this method will add a course to the student's list of courses
    // and add the student to the course's list of students
    courseProgresses.add(new CourseStatus(course, grades));
    course.addStudent(this);
  }

  public void makeComment(String comment) {
    Comment.addPost(comment);
  }

  // Return the student's certificates
  public ArrayList<Certificate> getCertificates() {
    return certificates;
  }

  // public void setGrades(Integer[] grades) {
  // // set grades array list to array grades
  // this.grades = Arrays.asList(grades);
  // }

  // Returns the student's course status
  public ArrayList<CourseStatus> getCourseProgresses() {
    return courseProgresses;
  }

  public String getType() {
    return "student";
  }

  // Gives a student a certificate
  public void giveCertificate(Certificate certificate) {
    certificates.add(certificate);
  }
}
