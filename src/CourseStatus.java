package src;
import java.util.ArrayList;
public class CourseStatus {
    private Course course;
    private double progress;
    private Long totalGrade;
    private ArrayList<Double> grades;
    private boolean completed;
    public CourseStatus(Course course, ArrayList<Double> grades){
      this.course = course;
      this.grades = grades;
    }
    public double getProgress() {
        return progress;
    }
    public void setGrades(ArrayList<Double> grades){
      this.grades = grades;
    }
    public Long getGrade() {
        return totalGrade;
    }
    public Course getCourse(){
      return this.course;
    }
    public ArrayList<Double> getGradeList(){
      return this.grades;
    }
    public void calculateProgress(){
      double total = 0;
      for(int i = 0; i < grades.size(); i++){
        total += grades.get(i);
      }
      progress = total / totalGrade;
    }
    public void setCompletion(boolean completed){
      this.completed = completed;
    }
}
