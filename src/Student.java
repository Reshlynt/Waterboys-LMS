package src;
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Arrays;
import java.util.List;

public class Student extends User {
  private ArrayList<CourseStatus> courseProgress;
  private ArrayList<Certificate> certificates;
  private List<Integer> grades = new ArrayList<Integer>();

  public Student(UUID id, String username, String firstName, String lastName, String email, String password, Date DOB) {
    super(id, username, firstName, lastName, email, password, DOB);
  }

  public void saveCourseProgress() {
    return;
  }

  public void registerCourse() {
    return;
  }

  public void makeComment() {
    return;
  }

  public void setGrades(Integer[] grades) {
    // set grades array list to array grades
    this.grades = Arrays.asList(grades);
  }
}
