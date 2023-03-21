package src;
import java.util.Date;
import java.util.UUID;

public class LMSSystem {
    public UserList userList = new UserList();
    public CourseList courseList;

    public User Login(String username, String password) {
        if (userList.foundUser(username)) {
            if (userList.getUser(username).getPassword().equals(password)) {
                System.out.println("Login successfully!");
                return userList.getUser(username);
            } else {
                System.out.println("Wrong password!");
                return null;
            }
        } else {
            System.out.println("User not found!");
            return null;
        }
    }

    public void Logout() {
        System.out.println("Logout successfully!");
    }

    public User SignUp(String firstName, String lastName, String username, String email, String password, Date dateOfBirth, String job) {
        if (userList.foundUser(username)) {
            System.out.println("User already exists!");
        } else {
            User new_user = null;
            if (job.equalsIgnoreCase("student")) {
                new_user = new Student(UUID.randomUUID(), username, firstName, lastName, email, password, dateOfBirth);
                
            } else if (job.equalsIgnoreCase("teacher")) {
                new_user = new Teacher(UUID.randomUUID(), username, firstName, lastName, email, password, dateOfBirth);
            } else {
                System.out.println("             You did not enter \"student\" or \"teacher\"");
                return null;
            }
            userList.addUser(new_user);
            System.out.println("Sign up successfully!");
            return new_user;
        }
        return null;
    }

    public void goToCourse(Course course) {
        System.out.println("You are now in " + course.getTitle() + " course!");
    }

    public void goToCourse(String name) {
        if (courseList.getCourseByKeyword(name).size() == 0) {
            System.out.println("Course not found!");
        } else if (courseList.getCourseByKeyword(name).size() == 1) {
            System.out.println("You are now in " + courseList.getCourseByKeyword(name).get(0).getTitle() + " course!");
        } else {
            System.out.println("There are more than one course with this name, please choose one:");
            for (Course course : courseList.getCourseByKeyword(name)) {
                System.out.println(course.getTitle());
            }
        }
    }

    public void submitFeedback(String feedback) {
        System.out.println("Feedback submitted successfully!");
    }

    public User getUser(String username) {
        return userList.getUser(username);
    }

    public boolean addCourse(Course course) {
        return courseList.addCourse(course);
    }

    public boolean addModule(Module module) {
        return courseList.addModule(module);
    }

    public CourseList getCourseList() {
        return courseList;
    }

    public boolean createCourse(String name, String description, String teacherName) {
        if (courseList.found(name)) {
            System.out.println("Course already exists!");
            return false;
        } else {
            Course course = new Course(name, description, teacherName);
            courseList.addCourse(course);
            System.out.println("Course created successfully!");
            return true;
        }
    }
}