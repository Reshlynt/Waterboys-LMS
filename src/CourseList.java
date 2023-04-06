package src;

import java.util.ArrayList;
//import java.util.zip.GZIPOutputStream;
import java.util.UUID;

public class CourseList {
  private static CourseList courseList;
  private static ArrayList<Course> courses = new ArrayList<Course>();

  private CourseList() {
    courses = DataLoader.getCourses();
  }

  /**
   * 
   * @return Instance of the CourseList object.
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

  public boolean found(String name) {
    for (Course course : courses) {
      if (course.getTitle().equals(name)) {
        return true;
      }
    }
    return false;
  }

  public Course getEnrolledCourse(String name) {
    ArrayList<Course> possibleCourses = getCourseByKeyword(name);
    for (int i = 0; i < possibleCourses.size(); i++) {

    }
    return null;
  }

  public Course getCourseByUUID(UUID id) {
    for (Course course : courses) {
      if (course.getID().equals(id)) {
        return course;
      }
    }
    return null;
  }

  public ArrayList<Course> getCourseList() {
    return courses;
  }

  public static void saveCourses() {
    DataWriter.saveCourses();
  }

  public static void writeModule(Module module) {
    DataWriter.WriteModule(module);
  }
}
