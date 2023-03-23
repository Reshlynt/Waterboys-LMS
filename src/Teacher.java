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
        System.out.println("What is the course type? JavaScript or Python?");
        String response = "";
        CourseType courseType = null;
        do {
            response = System.console().readLine();
            response.toLowerCase();
            if (response.equals("javascript")) {
                courseType = CourseType.JAVASCRIPT;
            } else if (response.equals("python")) {
                courseType = CourseType.PYTHON;
            } else {
                System.out.println("Please enter JavaScript or Python");
            }
        } while (!System.console().readLine().equals("JavaScript") && !System.console().readLine().equals("Python"));

        // Lesson creator:
        ArrayList<Module> lessons = new ArrayList<Module>();
        boolean addAnotherModule = true;
        boolean addAnotherSlide = true;
        
        

        while (addAnotherModule) {
        System.out.println("What is the title of the module?");
        String moduleTitle = System.console().readLine();
        ArrayList<TextSlide> slides = new ArrayList<TextSlide>();
            // Create slide loop
            while (addAnotherSlide) {
                System.out.println("What do you want to title the slide?");
                String slideTitle = System.console().readLine();
                System.out.println("What do you want to print on the slide?");
                String slideContents = System.console().readLine();
                TextSlide tSlide = new TextSlide(slideTitle, slideContents);
                slides.add(tSlide);
                System.out.println("Do you want to add another slide? (Y/N)");
                String addSlide = System.console().readLine();
                addSlide.toUpperCase();
                if (response.equals("N")) {
                    addAnotherSlide = false;
                }
            }


            Module aModule = new Module (moduleTitle, slides);
                
        }

        Course new_course = new Course(this, title, difficulty, description, null, courseType, null, null);
        return new_course;
        // ask for title
        // ask for description
        // ask for difficulty
        // ask for course type
        // go through modules one by one asking for each slide - save to ArrayList of slides
        // at end of each module, create a quiz and ask if they want to add another module
        // at end of course, go through quiz process but for exam

        return null;
    }
    public Assessment makeQuiz() {
        System.out.println("What is the title of the assessment?");
        String title = System.console().readLine();
        System.out.println("What is the description of the assessment?");
        String description = System.console().readLine();
        for (int i = 0; i < 5; i++) {
            Question new_question = new Question();
            System.out.println("What is the question?");
            String question = System.console().readLine();
            System.out.println("What is the answer?");
            String answer = System.console().readLine();
            Question new_question = new Question(question, answer);
        }
    }
    public Assessment makeExam() {
        System.out.println("What is the title of the assessment?");
        String title = System.console().readLine();
        System.out.println("What is the description of the assessment?");
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
