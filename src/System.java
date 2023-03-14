package src;

public class System {
    public UserList userList;
    public CourseList courseList;

    public User Login(String username, String password) {
        if (userList.found(username)) {
            if (userList.getUser(username).getPassword().equals(password)) {
                System.out.println("Login successfully!");
            } else {
                System.out.println("Wrong password!");
            }
        } else {
            System.out.println("User not found!");
        }
    }

    public void Logout() {
        System.out.println("Logout successfully!");
    }

    public void SignUp(String firstName, String lastName, String username, String email, String password, String dateOfBirth) {
        if (userList.found(username)) {
            System.out.println("User already exists!");
        } else {
            User user = new User(firstName, lastName, username, email, password, dateOfBirth);
            userList.addUser(user);
            System.out.println("Sign up successfully!");
        }
    }

    public void goToCourse(Course course) {
        System.out.println("You are now in " + course.getName() + " course!");
    }

    public void goToCourse(String name) {
        if (courseList.getCourseByKeyword(name).size() == 0) {
            System.out.println("Course not found!");
        } else if (courseList.getCourseByKeyword(name).size() == 1) {
            System.out.println("You are now in " + courseList.getCourseByKeyword(name).get(0).getName() + " course!");
        } else {
            System.out.println("There are more than one course with this name, please choose one:");
            for (Course course : courseList.getCourseByKeyword(name)) {
                System.out.println(course.getName());
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
