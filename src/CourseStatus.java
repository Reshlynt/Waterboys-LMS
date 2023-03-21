package src;
import java.util.ArrayList;
public class CourseStatus {
    private Course course;
    private double progress;
    private Long totalGrade;
    private ArrayList<Long> grades;
    public CourseStatus(Course course, ArrayList<Long> grades){
      this.course = course;
      this.grades = grades;
    }
    public double getProgress() {
        return progress;
    }
    public void setGrades(ArrayList<Long> grades){
      this.grades = grades;
    }
    public Long getGrade() {
        return totalGrade;
    }
    public Course getCourse(){
      return this.course;
    }
}
