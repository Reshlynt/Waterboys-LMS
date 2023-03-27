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

    public Assessment makeAssessment() {
        System.out.println("What is the title of the assessment?");
        String title = System.console().readLine();
        System.out.println("What is the description of the assessment?");
        ArrayList<Question> questions = new ArrayList<Question>();
        boolean addMoreQuestions = true;
        while (addMoreQuestions) {
            System.out.println("What is the question?");
            String question = System.console().readLine();
            System.out.println("Enter 4 answer choices, pressing enter after each:");
            ArrayList<String> answers = new ArrayList<String>();
            for (int i = 0; i < 4; i++) {
                answers.add(System.console().readLine());
            }
            System.out.println("What is the correct answer? a, b, c, or d?");
            String correctAnswer = System.console().readLine();
            correctAnswer.toLowerCase();
            questions.add( new Question(question, answers, correctAnswer));
            System.out.println("Do you want to add another question? (Y/N)");
            String addQuestion = System.console().readLine();
            addQuestion.toUpperCase();
            if (addQuestion.equals("N")) {
                addMoreQuestions = false;
            }
        }
        return new Assessment(title, questions);
    }
    public boolean addToCourse(Student student, Course course) {
        course.addToCourse(student);
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
            Assessment exam = (Assessment) courseJSONObject.get("exam");
            CourseType courseType = (CourseType) courseJSONObject.get("courseType");
            // TODO: get ArrayList instance variables
            ArrayList<Module> lessons = new ArrayList<Module>();
            for (int j = 0; j < courseJSONObject.get("lessons").size(); j++) {
                
            }

            ArrayList<Module> lessons = (ArrayList<Module>) courseJSONObject.get("lessons");
            ArrayList<Comment> courseComments = (ArrayList<Comment>) courseJSONObject.get("courseComments");
            ArrayList<Student> students = (ArrayList<Student>) courseJSONObject.get("students");

            Course new_course = new Course(courseID, teacher, title, difficulty, description, exam, courseType, lessons, courseComments, students);
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
