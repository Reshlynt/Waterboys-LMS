package src;

import java.util.ArrayList;

public class CourseStatus {
  private Course course;
  private double progress;

  private Double courseGrade;
  private ArrayList<Double> grades = new ArrayList<Double>();
  private boolean completed;

  public CourseStatus(Course course, ArrayList<Double> grades) {
    this.course = course;
    this.grades = grades;
    courseGrade = 0.0;
  }

  public double getProgress() {
    return progress;
  }

  public void setGrades(ArrayList<Double> grades) {
    this.grades = grades;
  }

  public Double getGrade() {
    return this.courseGrade;
  }

  public Course getCourse() {
    return this.course;
  }

  public ArrayList<Double> getGradeList() {
    return this.grades;
  }

  public void calculateProgress() {
    // calculate average of grades arraylist
    Double sum = 0.0;
    for (Double grade : grades)
      sum += grade;
    courseGrade = sum / (grades.size());
  }

  public void updateScore(int numCorrect, int numQuestions) {
    Double quizScore = 100 * Double.valueOf((double) numCorrect / (double) numQuestions);
    grades.add(quizScore);
    calculateProgress();
  }

  public void setCompletion(boolean completed) {
    this.completed = completed;
  }

  public boolean getCompleted() {
    return completed;
  }

  public void addGrade(double grade) {
    grades.add(grade);
  }

  public Certificate getCertificate(Student student) {
    if (!completed)
      return null;
    Certificate certificate = new Certificate(course, student, null, course.getAuthor());
    return certificate;
  }
}
