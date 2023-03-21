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

    // Return the course that the certificate is for
    public Course getCourse() {
        return course;
    }

    // Return the user that recieved the certificate
    public User getUser() {
        return user;
    }

    // Return the date that the certificate was issued
    public Date getDate() {
        return date;
    }

    // Return the teacher that issued the certificate
    public Teacher getTeacher() {
        return teacher;
    }
    public String toString() {
        return user.getFullName() + " recieved this certificate for completing " + course.getTitle() + " by " + teacher.getFullName() + " on " + date + ".";
    }
}
