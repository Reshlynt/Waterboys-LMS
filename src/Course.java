
import java.util.ArrayList;
import java.util.UUID;

public class Course {
  private UUID id;
  private String title;
  private Difficulty difficulty;
  private String description;
  private Teacher author;
  private Assessment exam;
  private CourseType courseType;
  private ArrayList<Module> lessons;
  private ArrayList<Comment> courseComments;

  public Course(UUID id, String title, Difficulty difficulty, String description, Teacher author, Assessment exam,
      CourseType courseType, ArrayList<Module> lessons, ArrayList<Comment> courseComments) {
    this.id = id;
    this.title = title;
    this.difficulty = difficulty;
    this.description = description;
    this.author = author;
    this.courseType = courseType;
    this.lessons = lessons;
    this.courseComments = courseComments;
  }

  public boolean displayComments() {
    return true;
  }

  public void displaySlides() {
    return;
  }

  public Certificate createCertificate() {
    return new Certificate();
  }

  public boolean listComment() {
    return true;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  // Returns the course's UUID
  public UUID getID() {
    return id;
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
    return author;
  }

  // Return course type
  public CourseType getCourseType() {
    return coursetype;
  }

  // Return course assessment
  public Assessment getAssessment() {
    return exam;
  }
}
