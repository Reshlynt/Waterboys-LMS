
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
public class Teacher extends User {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;

    public Teacher(UUID id, String username, String firstName, String lastName, String email, String password, Date DOB) {
        super(id, username, firstName, lastName, email, password, DOB);

    }
    public Course createCourse() {
        return new Course();
    }
    public boolean addToCourse() {
        return true;
    }
    public boolean removeFromCourse() {
        return true;
    }
    public void makeComment() {
        return;
    }
}
