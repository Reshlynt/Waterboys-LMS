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
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("What is the description of the course?");
        String description = System.console().readLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("What is the difficulty of the course?");
        String difficultyString = System.console().readLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Difficulty difficulty = null;
        difficultyString.toUpperCase();
        if (difficultyString == "BEGINNER") {
            difficulty = Difficulty.BEGINNER;
        } else {
            difficulty = Difficulty.INTERMEDIATE;
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("What is the course type? JavaScript or Python?");
        CourseType courseType = null;
        do {
            String response = System.console().readLine();
            response.toLowerCase();
            System.out.print("\033[H\033[2J");
            System.out.flush();
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
                if (addSlide.equals("N")) {
                    addAnotherSlide = false;
                }
            }
            Module aModule = new Module (moduleTitle, slides);
            System.out.println("Create the quiz for this module: ");
            Assessment moduleQuiz = makeAssessment();
            moduleQuiz.setType(Type.QUIZ);
            aModule.setLessonQuiz(moduleQuiz);
            System.out.println("Add another module? (Y/N)");
            String addModule = System.console().readLine();
            addModule.toUpperCase();
            if (addModule.equals("N")) {
                addAnotherModule = false;
            } 
        }
        // Create exam
        System.out.println("Create the exam for this course: ");
        Assessment exam = makeAssessment();
        exam.setType(Type.EXAM);
        System.out.println("Do you want to add any students? (Y/N)");
        String addStudents = System.console().readLine();
        addStudents.toUpperCase();
        if (addStudents.equals("Y")) {
            System.out.println("Enter the usernames of the students you want to add, pressing enter after each:");
            ArrayList<Student> students = new ArrayList<Student>();
            boolean addMoreStudents = true;
            while (addMoreStudents) {
                String studentUsername = System.console().readLine();
                UserList userList = UserList.getInstance();
                if (!userList.foundUser(studentUsername)) {
                    System.out.println("That user does not exist. Please try again.");
                    continue;
                }
                else {
                students.add((Student) userList.getUser(studentUsername));
                System.out.println("Do you want to add another student? (Y/N)");
                String addStudent = System.console().readLine();
                addStudent.toUpperCase();
                if (addStudent.equals("N")) {
                    addMoreStudents = false;
                }
                }
            }
            Course newCourse = new Course(this, title, difficulty, description, exam, courseType, lessons, students);
            return newCourse;
        }

        
        // ask for title
        // ask for description
        // ask for difficulty
        // ask for course type
        // go through modules one by one asking for each slide - save to ArrayList of slides
        // at end of each module, create a quiz and ask if they want to add another module
        // at end of course, go through quiz process but for exam

        return null;
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
