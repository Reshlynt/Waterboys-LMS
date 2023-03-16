import java.util.ArrayList;
import java.util.UUID;

public class Course {
  private UUID courseID;
  // private UUID teacherID;
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
  }

  public Course(UUID courseID, Teacher teacher, String title, Difficulty difficulty, String description, Teacher author,
      Assessment exam,
      CourseType courseType, ArrayList<Module> lessons, ArrayList<Comment> courseComments,
      ArrayList<Student> students) {
    this.courseID = UUID.randomUUID();
    this.teacher = teacher;
    this.title = title;
    this.difficulty = difficulty;
    this.description = description;
    this.courseType = courseType;
    this.lessons = lessons;
    this.courseComments = courseComments;
    this.students = students;
  }

  int minage = 13;

  public boolean displayComments() {
    if (User.getAge() < minage) {
      return false;
    } else {
      return true;
    }
  }

  public void displaySlides() {
    for (Module lesson : lessons) {
      lesson.displaySlides();
    }
  }

  public Certificate createCertificate(User user) {
    return new Certificate(this, user, new Date(), this.author);
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

  public String toString() {
    String info = title + "\n" + difficulty + "\n" + teacher + "\n" + courseID + "\n" + courseType + "\n";
    for (int i = 0; i < lessons.size(); i++) {
      info += (lessons.get(i) + "\n");
    }
    for (int i = 0; i < courseComments.size(); i++) {
      info += (courseComments.get(i) + "\n");
    }
    for (int i = 0; i < students.size(); i++) {
      info += (students.get(i) + "\n");
    }
    return info;
  }
}
