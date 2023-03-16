import java.util.Date;
public class Certificate {
    private Course course;
    private User user;
    private Date date;
    private Teacher teacher;
    public Certificate(Course course, User user, Date date, Teacher teacher) {
        this.course = course;
        this.user = user;
        this.date = date;
        this.teacher = teacher;
    }
    public String toString() {
        return user.getFullName() + " recieved this certificate for completing " + course.getName() + " by " + teacher.getFullName() + " on " + date + ".";
    }
}
