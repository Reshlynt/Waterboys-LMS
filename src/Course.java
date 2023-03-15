
import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID id;
    private String title;
    private Difficulty difficulty;
    private String description;
    private Teacher author;
    private Assessment exam;
    private CourseType coursetype;
    private ArrayList<Module> lessons;
    private ArrayList<Comment> courseComments;
    
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

    //Return course author.
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
