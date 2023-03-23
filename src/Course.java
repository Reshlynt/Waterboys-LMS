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
  private ArrayList<Module> lessons;
  private ArrayList<Comment> courseComments;
  private ArrayList<Student> students;

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

  public void addToCourse(Student student) {
    students.add(student);
  }
  public void addToCourseByUUID(UUID studentID) {
    UserList userList = UserList.getInstance();
    students.add((Student) userList.getUserByUUID(studentID));
  }

  public void displaySlides() {
    for (Module lesson : lessons) {
      lesson.displaySlides();
    }
  }

  public Certificate createCertificate(User user) {
    return new Certificate(this, user, new Date(), this.teacher);
  }

  public boolean listComment() {
    return true;
    // Check if current user is 13+ - maybe add User to parameters, add functionality
    // in LMSSystem to check if user is 13+; when printing slides, check first and 
    // only call printComments if user is 13+.
  }
  // Returns the course's difficulty
  public Difficulty getDifficulty() {
    return difficulty;
  }

  // Returns the course's UUID
  public UUID getID() {
    return courseID;
  }

  // Returns the course's title
  public String getTitle() {
    return title;
  }

  // Return course description
  public String getDescription() {
    return description;
  }

  // Return course author.
  public Teacher getAuthor() {
    return teacher;
  }

  // Return course type
  public CourseType getCourseType() {
    return courseType;
  }

  // Return course assessment
  public Assessment getAssessment() {
    return exam;
  }

  // Return the course's modules
  public ArrayList<Module> getModules() {
    return lessons;
  }

  public String toString() {
    String info = title + "\n" + difficulty + "\n" + teacher + "\n" + courseID + "\n" + courseType + "\n";
    info+="lessons:\n";
    for (int i = 0; i < lessons.size(); i++) {
      info += (lessons.get(i) + "\n");
    }
    info+="comments:\n";
    for (int i = 0; i < courseComments.size(); i++) {
      info += (courseComments.get(i) + "\n");
    }
    info+="students:\n";
    for (int i = 0; i < students.size(); i++) {
      info += (students.get(i) + "\n");
    }
    info += exam.toString();
    return info;
  }

  public void addComment(Comment comment) {
    courseComments.add(comment);
  }
  // Return the course's comments
  public ArrayList<Comment> getComments() {
    return courseComments;
  }

  public void removeComment(Comment comment) {
    while (courseComments.contains(comment)) {
      courseComments.remove(comment);
    }
  }

  // Return the course's students
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
  // Print the cumulative grade on the course certificate? Must get 100% on the exam to pass
  // Should there be a minimum grade to pass the quizzes?
}
