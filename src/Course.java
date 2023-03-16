import java.util.ArrayList;
import java.util.UUID;

public class Course {
  private UUID courseID;
  //private UUID teacherID;
  private String title;
  private Difficulty difficulty;
  private String description;
  private Teacher teacher;
  private Assessment exam;
  private CourseType courseType;
  private ArrayList<Module> lessons;
  private ArrayList<Comment> courseComments;
  private ArrayList<Student> students;

  public Course(UUID courseID, Teacher teacher, String title, Difficulty difficulty, String description, Teacher author, Assessment exam,
      CourseType courseType, ArrayList<Module> lessons, ArrayList<Comment> courseComments,
      ArrayList<Student> students) {
    this.courseID = courseID;
    //this.teacherID = teacherID;
    this.teacher = teacher;
    this.title = title;
    this.difficulty = difficulty;
    this.description = description;
    this.courseType = courseType;
    this.lessons = lessons;
    this.courseComments = courseComments;
    this.students = students;
  }

  public Course(User teacher, String title, Difficulty difficulty, String description, Teacher author, Assessment exam,
      CourseType courseType, ArrayList<Module> lessons, ArrayList<Comment> courseComments,
      ArrayList<Student> students) {
    this.courseID = UUID.randomUUID();
    //this.teacherID = teacherID;
    this.teacher = teacher;
    this.title = title;
    this.difficulty = difficulty;
    this.description = description;
    this.author = author;
    this.courseType = courseType;
    this.lessons = lessons;
    this.courseComments = courseComments;
    this.students = students;
  }

  public boolean displayComments() {
    if(User.getAge() < 13) {
      return false;
    } else { 
      return true;
    }
  }

  public void displaySlides() {
    return;
  }

  public Certificate createCertificate() {
    return new Certificate(User.getFullName(), this.title, this.author, this.courseType, this.difficulty);
  }

  public boolean listComment() {
    return true;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  // Returns the course's UUID
  public UUID getID() {
    return courseID;
  }

  // Returns the course's title
  public String getName() {
    return title;
  }

  // Return course description
  public String getDescription() {
    return description;
  }

  // Return course author.
  public Teacher getAuthor() {
    return author;
  }

  // Return course type
  public CourseType getCourseType() {
    return courseType;
  }

  // Return course assessment
  public Assessment getAssessment() {
    return exam;
  }
}
