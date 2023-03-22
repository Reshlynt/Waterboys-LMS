package src;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;

@SuppressWarnings("unchecked")

public class DataWriter extends DataConstants {


    /**
     * Saves all User objects to a JSON file - Users.json
     */
    public static void saveUsers() {
        // (1) Get your UserList list and establish your
        // JSON array object.
        UserList userList = UserList.getInstance();
        ArrayList<User> all_users_list = userList.getUserList();
        JSONArray jsonUsers = new JSONArray();

        // creating all the json objects
        for (int i = 0; i < all_users_list.size(); i++) {
            if (all_users_list.get(i) instanceof Student)
                jsonUsers.add(StudentModifiedUserJSON((Student) all_users_list.get(i)));
            else
                jsonUsers.add(TeacherModifiedUserJSON((Teacher) all_users_list.get(i)));
            //    jsonUsers.add(getUserJSON(all_users_list.get(i)));
        }

        // Write JSON file
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {

            file.write(jsonUsers.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Course saving.
     */
    public static void saveCourses() {
        CourseList courseList = CourseList.getInstance();
        ArrayList<Course> all_courses_list = courseList.getCourseList();
        JSONArray jsonCourses = new JSONArray();


        // creating all the json objects
        for (int i = 0; i < all_courses_list.size(); i++) {
            jsonCourses.add(getCourseJSON(all_courses_list.get(i), null));
        }

        // Write JSON file
        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {

            file.write(jsonCourses.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates a JSON object for a Student.
     * @param student
     * @return
     */
    private static JSONObject StudentModifiedUserJSON(Student student) {
        JSONObject studentDetails = new JSONObject();
        studentDetails.put(CERTIFICATES, getCertificateJSONArray(student.getCertificates()));
        getUserJSON(studentDetails, student);
        return studentDetails;

    }
    /**
     * Creates a JSON object for a Teacher.
     * @param teacher
     * @return
     */
    private static JSONObject TeacherModifiedUserJSON(Teacher teacher) {
        JSONObject teacherDetails = new JSONObject();

        JSONArray studentArray = new JSONArray();
        for (int i = 0;teacher.getStudents() != null && i < teacher.getStudents().size(); i++) {
            studentArray.add(teacher.getStudents().get(i).getID().toString());
        }
        teacherDetails.put(STUDENTS, studentArray);
        getUserJSON(teacherDetails, teacher);
        return teacherDetails;
    }
    /**
     * Creates User JSON object.
     * 
     * @param user - User object.
     * @return JSON object that contains User data.
     */
    private static void getUserJSON(JSONObject userDetails, User user) {
        userDetails.put(USER_ID, user.getID().toString());

        userDetails.put(USER_NAME, user.getUsername());

        userDetails.put(FIRST_NAME, user.getFirstName());

        userDetails.put(LAST_NAME, user.getLastName());

        userDetails.put(EMAIL, user.getEmail());

        userDetails.put(PASSWORD, user.getPassword());

        userDetails.put(TYPE, user.getType());

        userDetails.put(DOB_DATE, DateFormatting(user.getDOB().toString()));

    }

    // ---------------------------------------------------------------------------------------------
    // This is for the Certificate JSON arrays.
    // ---------------------------------------------------------------------------------------------
   
    /**
     * Creates an array consisting of which course the certificate is for, who owns it, the date issued, and who issued it.
     * @param certificates
     * @return
     */
    private static JSONArray getCertificateDetails(Certificate certificate) {
        JSONArray certificateDetails = new JSONArray();
        certificateDetails.add(certificate.getCourse().getTitle()); // Course name
        certificateDetails.add(certificate.getUser().getFullName()); // User name
        certificateDetails.add(DateFormatting(certificate.getDate().toString())); // Date issued
        certificateDetails.add(certificate.getTeacher().getFullName()); // Teacher name
        return certificateDetails;
    }

    /**
     * Creates a Certificate JSON array.
     * @param courseProgresses
     * @return
     */
    public static JSONArray getCertificateJSONArray(ArrayList<Certificate> certificates) {
        JSONArray certificateArray = new JSONArray();
        for (int i = 0; certificates != null && i < certificates.size(); i++) {
            certificateArray.add(getCertificateDetails(certificates.get(i)));
        }
        return certificateArray;
    }


    // ---------------------------------------------------------------------------------------------
    // This is for the Course JSON object, and all of its needed methods.
    // ---------------------------------------------------------------------------------------------

    /**
     * Creates Course JSON object.
     * 
     * @param user
     * @return
     */
    private static JSONObject getCourseJSON(Course course, Teacher teacher) {
        JSONObject courseDetails = new JSONObject();

        courseDetails.put(TITLE, course.getTitle());

        courseDetails.put(DIFFICULTY, course.getDifficulty().toString()); 

        courseDetails.put(COURSE_ID, course.getID().toString());

        courseDetails.put(COURSE_TYPE, course.getCourseType().toString()); // String shit

        if (teacher != null) {
            courseDetails.put(TEACHER, teacher.getFullName());
            courseDetails.put(EXAM, course.getAssessment().getTitle());
            // courseDetails.put(MODULES, course.getModule. I don't know the structure of this yet.
            return courseDetails;
        }

        courseDetails.put(TEACHER_ID, course.getAuthor().getID().toString()); // get author id

        // Gets the exam JSON array
        courseDetails.put(EXAM, getAssessmentJSONArray(course.getAssessment()));

        courseDetails.put(MODULES, getModuleJSONArray(course.getModules())); // JSON array

        // course comments
        courseDetails.put(COURSE_COMMENTS, getCommentJSONArray(course.getComments()));

        // enrolled students
        courseDetails.put(STUDENTS, getStudentJSONArray(course.getStudents(), course));

        return courseDetails;
    }

    // ---------------------------------------------------------------------------------------------
    // This deals with the Student JSON object. (For the Course JSON object)
    // ---------------------------------------------------------------------------------------------
   
    /**
     * Creates a Grade JSON array.
     * @param courseStatus - A CourseStatus array list.
     * @return
     */
    private static JSONArray getGradeJSONArray(ArrayList<Long> grades) {
        JSONArray gradeArray = new JSONArray();
        for (int i = 0; i < grades.size(); i++) {
            gradeArray.add(grades.get(i));
        }
        return gradeArray;
    }
    /**
     * Creates a Student JSON object.
     * @param student - A Student object.
     * @return A JSON object representing a Student.
     */
    private static JSONObject getStudentJSON(Student student, Course course) {
        JSONObject studentDetails = new JSONObject();
        studentDetails.put(STUDENT_ID, student.getID().toString());
        for (int i = 0; i < student.getCourseProgresses().size(); i++) {
            if (student.getCourseProgresses().get(i).getCourse().equals(course)) {
                studentDetails.put(GRADES, getGradeJSONArray(student.getCourseProgresses().get(i).getGradeList()));
                break;
            }
        }
        

        return studentDetails;
    }
    /**
     * Creates a Student JSON array. (For the Course JSON object)
     * @param students - A Student array list.
     * @return A JSON array representing a Student.
     */
    private static JSONArray getStudentJSONArray(ArrayList<Student> students, Course course) {
        JSONArray studentArray = new JSONArray();
        for (int i = 0; i < students.size(); i++) {
            studentArray.add(getStudentJSON(students.get(i), course));
        }
        return studentArray;
    }

    // ---------------------------------------------------------------------------------------------
    // This deals with the TextSlide JSON object.
    // ---------------------------------------------------------------------------------------------
    
    /**
     * Creates a TextSlide Json object.
     * @param textSlides
     * @return
     */
    private static JSONObject getTextSlideJSON(TextSlide textSlide) {
        JSONObject textSlideDetails = new JSONObject();
        textSlideDetails.put(SLIDE_TITLE, textSlide.getTitle());
        textSlideDetails.put(CONTENT, textSlide.getContents());
        return textSlideDetails;
    }

    /**
     * Create a JSON array of TextSlide objects.
     * @param textSlide - A Text Slide array list.
     * @return A JSON array representing a Text Slide.
     */
    private static JSONArray getTextSlideJSONArray(ArrayList<TextSlide> textSlides) {
        JSONArray textSlideArray = new JSONArray();
        for (int i = 0; i < textSlides.size(); i++) {
            textSlideArray.add(getTextSlideJSON(textSlides.get(i)));
        }
        return textSlideArray;
    }

    // ---------------------------------------------------------------------------------------------
    // This deals with the Module JSON object.
    // ---------------------------------------------------------------------------------------------

    /**
     * Create a JSON objects for Comment objects.
     * @param comment - A Comment object.
     * @return A JSON object representing a Comment.
     */
    private static JSONObject getCommentJSON(Comment comment) {
        JSONObject commentDetails = new JSONObject();
        commentDetails.put(COMMENTER_ID, comment.getPostingUser().toString());
        commentDetails.put(COMMENT_TEXT, comment.getPost());
        commentDetails.put(REPLIES, getCommentJSONArray(comment.getReplies()));
        return commentDetails;
    }

    /**
     * Create a JSON array of Comment JSON objects.
     * @param comments - An array list of Comment objects.
     * @return A JSON array of Comment JSON objects.
     */
    private static JSONArray getCommentJSONArray(ArrayList<Comment> comments) {
        JSONArray commentArray = new JSONArray();
        for (int i = 0; comments != null && i < comments.size(); i++) {
            commentArray.add(getCommentJSON(comments.get(i)));
        }
        return commentArray;
    }

    // ---------------------------------------------------------------------------------------------
    // This deals with the Module JSON object.
    // ---------------------------------------------------------------------------------------------

    /**
     * Creates a Module JSON object.
     * @param module - A module object.
     * @return A JSON object of a module.
     */
    private static JSONObject getModuleJSON(Module module) {
        JSONObject moduleDetails = new JSONObject();
        moduleDetails.put(MODULE_TITLE, module.getTitle());

        // slides
        moduleDetails.put(SLIDES, getTextSlideJSONArray(module.getSlides()));

        // comments
        moduleDetails.put(MODULE_COMMENTS, getCommentJSONArray(module.getComments()));

        moduleDetails.put(QUIZ, getAssessmentJSONArray(module.getQuiz()));;
        return moduleDetails;
    }

    /**
     * Creates a Module JSON Array. This will store Module JSON objects.
     * @param module - An array list of modules.
     * @return A JSON array of modules.
     */
    private static JSONArray getModuleJSONArray(ArrayList<Module> modules) {
        JSONArray moduleArray = new JSONArray();
        for (int i = 0; i < modules.size(); i++)
            moduleArray.add(getModuleJSON(modules.get(i)));
        return moduleArray;
    }

    // ---------------------------------------------------------------------------------------------
    // This below deals with the Assessment JSON object.
    // ---------------------------------------------------------------------------------------------

    /**
     * Creates an Assessment JSON object.
     * 
     * @param assessment - An assessment.
     * @return A JSON object representing the assessment.
     */
    private static JSONArray getAssessmentJSONArray(Assessment assessment) {
        JSONArray assessmentDetails = new JSONArray();
        // Exam is a JSON array, and it stores Question JSON objects.
        for (int i = 0; i < assessment.getQuestions().size(); i++)
            assessmentDetails.add(getQuestionJSON(assessment.getQuestions().get(i)));
        return assessmentDetails;
    }

    /**
     * Creates Question JSON Array.
     * 
     * @param question - A list of questions.
     * @return A JSON array representing the questions.
     */
    private static JSONObject getQuestionJSON(Question questions) {
        JSONObject questionDetails = new JSONObject();
        questionDetails.put(QUESTION, questions.getQuestionContent());
        questionDetails.put(ANSWER_CHOICES, getAnswerChoiceJSONArray(questions.getAnswerChoices()));
        questionDetails.put(CORRECT_ANSWER, questions.getCorrectAnswer());
        return questionDetails;
    }
    /**
     * Creates an JSON array of answer choices.
     * @param 
     * @return
     */
    private static JSONArray getAnswerChoiceJSONArray(ArrayList<String> answerChoices) {
        JSONArray answerChoiceArray = new JSONArray();
        JSONObject answerChoiceDetails = new JSONObject();
        String[] choices = {A, B, C, D};
        for (int i = 0; i < answerChoices.size(); i++) {      
            answerChoiceDetails.put(choices[i], answerChoices.get(i));
        }
        answerChoiceArray.add(answerChoiceDetails);
        return answerChoiceArray;
    }

    /**
     * Converts the string acquired from the Date object to the format: MMDDYYYY.
     * 
     * @param date - String acquired from the Date object.
     * @return - String in the format: MMDDYYYY.
     */
    private static String DateFormatting(String date) {
        // date = MonthToInt(date.substring(4, 7)) + date.substring(8, 10) + date.substring(24, 28);
        // Assume that date is the date returned by calling toSting() on a Date object.
        Date month;
        try {
            month = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(date.substring(4, 7));
            Calendar cal = Calendar.getInstance();
            cal.setTime(month);
            int month_int = cal.get(Calendar.MONTH) + 1;

            if (month_int < 10) {
                return "0" + month_int + date.substring(8, 10) + date.substring(24, 28);
            }
            return "" + month_int + date.substring(8, 10) + date.substring(24, 28);
        } catch (ParseException e) {
            e.printStackTrace();
            return "01011990"; // Default date if error occurs. January 1, 1990.
        }
    }

    public static void main(String[] args) {
        saveUsers();
        //saveCourses();
    }
}
