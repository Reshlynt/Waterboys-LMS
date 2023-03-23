package src;

import java.util.ArrayList;
//import java.util.zip.GZIPOutputStream;

public class CourseList {
  private static CourseList courseList;
  private static ArrayList<Course> courses = new ArrayList<Course>();

  private CourseList() {
    courses = DataLoader.getCourses();
  }

  /**
   * 
   * @return Instance of the UserList object.
   */
  public static CourseList getInstance() {
    if (courseList == null)
      courseList = new CourseList();
    return courseList;
  }

  public boolean addCourse(Course course) {
    return courses.add(course);
  }

  public void deleteCourse(Course course) {
    courses.remove(course);
  }

  public ArrayList<Course> getAllCourses() {
    return courses;
  }

  public ArrayList<Course> getCourseByKeyword(String name) {
    ArrayList<Course> result = new ArrayList<Course>();
    for (Course course : courses) {
      if (course.getTitle().contains(name)) {
        result.add(course);
      }
    }
    return result;
  }

  public Course getEnrolledCourse(String name) {
    ArrayList<Course> possibleCourses = getCourseByKeyword(name);
    for (int i = 0; i < possibleCourses.size(); i++) {
         
    }
    return null;
  }

  public ArrayList<Course> getCourseList() {
    return courses;
  }
}
