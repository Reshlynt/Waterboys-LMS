package src;

import java.util.Date;

public class Certificate {
  private Course course;
  private User user;
  private Date date;
  private int grade;
  private Teacher teacher;

  /**
   * Constructor
   * 
   * @param course  which course the user has completed
   * @param user    which user has completed the course
   * @param date    on what date they completed the course
   * @param teacher of the course
   */
  public Certificate(Course course, User user, Date date, Teacher teacher, int grade) {
    this.course = course;
    this.user = user;
    this.date = date;
    this.teacher = teacher;
    this.grade = grade;
  }

  /**
   * Getter for Grade
   * 
   * @return a double specifying the final grade in the class
   */
  public double getGrade() {
    return grade;
  }

  /**
   * Getter for Course
   * 
   * @return the course that was completed
   */
  public Course getCourse() {
    return course;
  }

  /**
   * Getter for User
   * 
   * @return the user that completed the course
   */
  public User getUser() {
    return user;
  }

  /**
   * Getter for Date
   * 
   * @return the date that the certificate was completed
   */
  public Date getDate() {
    return date;
  }

  /**
   * Getter for Teacher
   * 
   * @return the teacher that taught the course
   */
  public Teacher getTeacher() {
    return teacher;
  }

  /**
   * toString method for printing
   * 
   * @return a string with all of the necessary info of the certificate
   */
  public String toString() {
    return this.user.getFullName() + " recieved this certificate for completing " + this.course.getTitle() + " by "
        + this.teacher.getFullName() + " on " + this.date + ".\nFinal Grade: " + this.grade;
  }
}
