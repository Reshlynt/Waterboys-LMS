package src;
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Arrays;
import java.util.List;

public class Student extends User {
  private ArrayList<CourseStatus> courseProgresses;// list of courses and respective grades
  private ArrayList<Certificate> certificates;
  private boolean completed = false;

  public Student(UUID id, String username, String firstName, String lastName, String email, String password, Date DOB) {
    super(id, username, firstName, lastName, email, password, DOB);
    courseProgresses = new ArrayList<CourseStatus>();
    certificates = new ArrayList<Certificate>();
  }

  public void setCourseGrade(Course course, ArrayList<Long> grades) {
    // this method will get an array list of grades, which signify the grades for a
    // specific class(module quiz/exam grades
    CourseStatus newCourseStatus = new CourseStatus(course, grades);
    courseProgresses.add(newCourseStatus);
  }

  public void registerCourse(Course course) {
    // this method will add a course to the student's list of courses
    // and add the student to the course's list of students
    CourseStatus newCourseStatus = new CourseStatus(course, null);
    courseProgresses.add(newCourseStatus);
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

  // Given a course, returns a boolean whether the student has a certificate for
  // that course
  public boolean hasCertificate(Course course) {
    for (Certificate certificate : certificates) {
      if (certificate.getCourse().equals(course)) {
        return true;
      }
    }
    return false;
  }
  public void setCompleted(boolean completed){
    this.completed = completed;
  }
}
