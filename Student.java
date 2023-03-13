import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {
    private ArrayList<CourseStatus> courseProgress;
    private ArrayList<Certificate> certificates;

    public Student(UUID id, String username, String firstName, String lastName, String email, String password, String DOB) {
        super(id, username, firstName, lastName, email, password, DOB);
    }
    public void saveCourseProgress() {
        return;
    }
    public void registerCourse() {
        return;
    }
    public void makeComment() {
        return;
    }
}
