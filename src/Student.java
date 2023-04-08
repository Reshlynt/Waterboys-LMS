package src;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Student extends User {
  private ArrayList<CourseStatus> courseProgresses;// list of courses and respective grades
  private ArrayList<Certificate> certificates;

  public Student(UUID id, String username, String firstName, String lastName, String email, String password, Date DOB) {
    super(id, username, firstName, lastName, email, password, DOB);
    courseProgresses = new ArrayList<CourseStatus>();
    certificates = new ArrayList<Certificate>();
  }

  public Student(String username, String firstName, String lastName, String email, String password, String DOB) {
    super(UUID.randomUUID(), username, firstName, lastName, email, password, parseDate(DOB));
    courseProgresses = new ArrayList<CourseStatus>();
    certificates = new ArrayList<Certificate>();
  }

  public void setCourseProgress(Course course, ArrayList<Double> grades, boolean completed) {
    // construct an array list of hashmaps
    ArrayList<HashMap<Module, Double>> moduleGrades = new ArrayList<HashMap<Module, Double>>();
    CourseStatus newCourseStatus = new CourseStatus(course, grades);
    newCourseStatus.setCompletion(completed);
    courseProgresses.add(newCourseStatus);
  }

  public void registerCourse(Course course) {
    // this method will add a course to the student's list of courses
    // and add the student to the course's list of students
    CourseStatus newCourseStatus = new CourseStatus(course, null);
    courseProgresses.add(newCourseStatus);
  }

  // public void makeComment(String comment) {
  // Comment.addPost(comment);
  // }

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

  public void updateCourseProgress(Course course, int numCorrect, int numQuestions) {
    System.out.println(this.username + " " + this.getID());
    for (int i = 0; i < courseProgresses.size(); i++) {
      if (courseProgresses.get(i).getCourse().equals(course)) {
        courseProgresses.get(i).updateScore(numCorrect, numQuestions);
      }
    }
  }

  public boolean completedCourse(Course course) {
    // find course in the courseprogresses
    for (int i = 0; i < courseProgresses.size(); i++)
      if (courseProgresses.get(i).getCourse().equals(course))
        return (courseProgresses.get(i).getCompleted());
    return false;
  }

  // Gives a student a certificate
  public void giveCertificate(Certificate certificate) {
    certificates.add(certificate);
  }

  public double getCourseGrade(Course course) {
    for (CourseStatus courseStatus : courseProgresses) {
      if (courseStatus.getCourse().equals(course))
        return courseStatus.getGrade().doubleValue();
    }
    return 69.0;
  }

  public ArrayList<Double> getCourseGradeList(Course course) {
    for (int i = 0; i < courseProgresses.size(); i++) {
      if (courseProgresses.get(i).getCourse().equals(course))
        return courseProgresses.get(i).getGradeList();
    }
    return null;
  }

  private static Date parseDate(String dob) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
    try {
      return dateFormat.parse(dob);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
