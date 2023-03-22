package src;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.util.Date;
public class Teacher extends User {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;

    public Teacher(UUID id, String username, String firstName, String lastName, String email, String password, Date DOB) {
        super(id, username, firstName, lastName, email, password, DOB);
        //this.courses = setCoursesCreated(courses);;
    }
    public Teacher(String username, String firstName, String lastName, String email, String password, Date DOB) {
        super(username, firstName, lastName, email, password, DOB);
        this.id = UUID.randomUUID();
    }

    public Course createCourse() {
        System.out.println("What is the title of the course?");
        String title = System.console().readLine();
        System.out.println("What is the description of the course?");
        String description = System.console().readLine();
        System.out.println("What is the difficulty of the course?");
        String difficulty = System.console().readLine();
        System.out.println("What is the course type?");
        String courseType = System.console().readLine();
        Course new_course = new Course(this.id, this, title, difficulty, description, this, null, null, null, null, null);
        // ask for title
        // ask for description
        // ask for difficulty
        // ask for course type
        // go through modules one by one asking for each slide - save to ArrayList of slides
        // at end of each module, create a quiz and ask if they want to add another module
        // at end of course, go through quiz process but for exam

        return null;
    }
    public boolean addToCourse(String userName) {
        
        return true;
    }
    public boolean removeFromCourse() {
        return true;
    }
    public void makeComment() {
        return;
    }
    public String getType() {
        return "teacher";
    }
    public ArrayList<Course> setCoursesCreated(JSONArray courses) {
        UUID courseID = UUID.randomUUID();
        Teacher teacher = this;
        this.courses = new ArrayList<Course>();
        for (int i = 0; i < courses.size(); i++) {
            JSONObject courseJSONObject = (JSONObject) courses.get(i);
            String title = (String) courseJSONObject.get("title");
            Difficulty difficulty = (Difficulty) courseJSONObject.get("difficulty");
            String description = (String) courseJSONObject.get("description");
            Course new_course = new Course(courseID, teacher, title, null, description, teacher, null, null, null, null, null);
            this.courses.add(new_course);
        }
        courses.toString();
        return this.courses;
    }
    public ArrayList<Course> getCoursesCreated() {
        for (int i = 0; i < courses.size(); i++)
            System.out.println(courses.get(i).getTitle());
        return null;
    }

    // Returns the courses that the teacher has created
    public ArrayList<Course> getCourses() {
        return courses;
    }

    // Returns the teacher's students
    public ArrayList<Student> getStudents() {
        return students;
    }
}
