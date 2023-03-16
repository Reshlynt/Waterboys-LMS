package src;
import java.util.Date;
public class Certificate {
    private Course course;
    private String name;
    private Date date;
    private String author;
    public Certificate(Course course, String name, Date date, String author) {
        this.course = course;
        this.name = name;
        this.date = date;
        this.author = author;
    }
    public String toString() {
        return name + " recieved this certificate for completing " + course.getName() + " by " + author + " on " + date + ".";
    }
}
