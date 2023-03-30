package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Course {
  private UUID courseID;
  private Teacher teacher;
  private String title;
  private Difficulty difficulty;
  private String description;
  private Assessment exam;
  private CourseType courseType;
  private ArrayList<Module> lessons = new ArrayList<Module>();
  private ArrayList<Comment> courseComments = new ArrayList<Comment>();
  private ArrayList<Student> students = new ArrayList<Student>();

  /**
   * Constructor for loading from json
   * 
   * @param courseID       course UUID
   * @param teacher        teacher that teaches the course
   * @param title          title of the course
   * @param difficulty     difficulty of the course
   * @param description    description of the course
   * @param exam           final exam of the course
   * @param courseType     what language the class will be taught in
   * @param lessons        the modules that will be covered in the course
   * @param courseComments the course comments
   * @param students       the students in the course
   */
  public Course(UUID courseID, Teacher teacher, String title, Difficulty difficulty, String description,
      Assessment exam, CourseType courseType, ArrayList<Module> lessons, ArrayList<Comment> courseComments,
      ArrayList<Student> students) {
    this.courseID = courseID;
    this.teacher = teacher;
    this.title = title;
    this.difficulty = difficulty;
    this.description = description;
    this.courseType = courseType;
    this.lessons = lessons;
    this.courseComments = courseComments;
    this.students = students;
    this.exam = exam;
  }

  /**
   * Constructor for creating from UI
   * 
   * @param teacher     teacher that teaches the course
   * @param title       title of the course
   * @param difficulty  difficulty of the course
   * @param description description of the course
   * @param exam        final exam of the course
   * @param courseType  what language the class will be taught in
   * @param lessons     the modules that will be covered in the course
   * @param students    the students in the course
   */
  public Course(Teacher teacher, String title, Difficulty difficulty, String description,
      Assessment exam, CourseType courseType, ArrayList<Module> lessons,
      ArrayList<Student> students) {
    this.courseID = UUID.randomUUID();
    this.teacher = teacher;
    this.title = title;
    this.difficulty = difficulty;
    this.description = description;
    this.courseType = courseType;
    this.lessons = lessons;
    this.students = students;
  }

  /**
   * Method adds a given student to the course
   * 
   * @param student to be added to the course
   */
  public void addToCourse(Student student) {
    if (student != null)
      students.add(student);
  }

  /**
   * Method adds a given student to the course
   * 
   * @param studentID to be added to the course
   */
  public void addToCourse(UUID studentID) {
    UserList userList = UserList.getInstance();
    students.add((Student) userList.getUserByUUID(studentID));
  }

  public boolean removeFromCourse(Student student) {
    for (Student aStudent: students) {
      if (aStudent.getID().equals(student.getID())) {
        students.remove(student);
        return true;
      }
    }
    return false;
  }

  /**
   * Method displays the slides of each lesson
   */
  public void displaySlides() {
    for (Module lesson : lessons) {
      lesson.displaySlides();
    }
  }

  /**
   * Creates a new Certificate for a user
   * @param user that has completed the course
   * @return a new certificate
   */
  public Certificate createCertificate(User user) {
    return new Certificate(this, user, new Date(), this.teacher);
  }

  /**
   * Getter for difficulty
   * @return difficulty of the course
   */
  public Difficulty getDifficulty() {
    return difficulty;
  }

  /**
   * Getter for Course UUID
   * @return course uuid
   */
  public UUID getID() {
    return courseID;
  }

  /**
   * Getter for Course Title
   * @return string representing course title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Getter for course Description
   * @return 
   */
  public String getDescription() {
    return description;
  }

  /**
   * Getter for course teacher
   * @return Teacher object
   */
  public Teacher getAuthor() {
    return teacher;
  }

  /**
   * Getter for type of course (language that course is taught in)
   * @return CourseType
   */
  public CourseType getCourseType() {
    return courseType;
  }

  /**
   * Getter for final exam
   * @return Assessment object that is the final exam
   */
  public Assessment getAssessment() {
    return exam;
  }

  /**
   * Getter for course lessons
   * @return ArrayList of type Module
   */
  public ArrayList<Module> getModules() {
    return lessons;
  }

  /**
   * toString method for debugging
   */
  public String toString() {
    String info = title + "\n" + difficulty + "\n" + teacher + "\n" + courseID + "\n" + courseType + "\n";
    info += "lessons:\n";
    for (int i = 0; i < lessons.size(); i++) {
      info += (lessons.get(i) + "\n");
    }
    info += "comments:\n";
    for (int i = 0; i < courseComments.size(); i++) {
      info += (courseComments.get(i) + "\n");
    }
    info += "students:\n";
    for (int i = 0; i < students.size(); i++) {
      info += (students.get(i) + "\n");
    }
    info += exam.toString();
    return info;
  }

  /**
   * Method prints course comments
   */
  public void printComments() {
    for (int i = 0; i < courseComments.size(); i++) {
      System.out.println(courseComments.get(i));
      for (int j = 0; j < courseComments.get(i).getReplies().size(); j++) {
        courseComments.get(i).getReplies().get(j).printComments();
      }
    }
  }

  public void addComment(String input, User user) {
    courseComments.add(new Comment(input, user));
  }

  /**
   * Getter for course Comments
   * @return ArrayList of type Comment
   */
  public ArrayList<Comment> getComments() {
    return courseComments;
  }

  /**
   * Method removes comment from the comment list
   * @param comment that needs to be removed
   */
  public void removeComment(Comment comment) {
    for (int i = 0; i < courseComments.size(); i++) {
      if (courseComments.get(i).equals(comment)) {
        courseComments.remove(i);
      }
    }
  }

  /**
   * Getter for students
   * @return ArrayList of all of the Students
   */
  public ArrayList<Student> getStudents() {
    return students;
  }

  public int totalPossible() {
    int total = 0;
    total += this.lessons.size();
    total += this.exam.getQuestions().size();
    return total;
  }

  public int getPercentage(Assessment exam) {
    int totalcorrect = 0;
    for (int i = 0; i < exam.getQuestions().size(); i++) {
      totalcorrect += exam.getQuestions().get(i).getPoints();
    }
    return totalcorrect / exam.getQuestions().size();
  }
  // Print the cumulative grade on the course certificate? Must get 100% on the
  // exam to pass
  // Should there be a minimum grade to pass the quizzes?
}
