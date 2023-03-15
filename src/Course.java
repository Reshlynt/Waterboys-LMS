package src;
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
}
