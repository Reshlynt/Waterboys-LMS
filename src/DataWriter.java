
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;

@SuppressWarnings("unchecked")

public class DataWriter extends DataConstants {

    /**
     * Saves users to a json file - Users.json
     */
    public static void saveUsers() {
        // (1) Get your UserList list and establish your
        // JSON array object.
        UserList userList = UserList.getInstance();
        ArrayList<User> all_users_list = userList.getUserList();
        JSONArray jsonUsers = new JSONArray();

        // creating all the json objects
        for (int i = 0; i < all_users_list.size(); i++) {
            jsonUsers.add(getUserJSON(all_users_list.get(i)));
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
            jsonCourses.add(getCourseJSON(all_courses_list.get(i)));
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
     * Creates User JSON object.
     * 
     * @param user - User object.
     * @return JSON object that contains User data.
     */
    private static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();

        userDetails.put(USER_ID, user.getID().toString());

        userDetails.put(USER_NAME, user.getUsername());

        userDetails.put(FIRST_NAME, user.getFirstName());

        userDetails.put(LAST_NAME, user.getLastName());

        userDetails.put(EMAIL, user.getEmail());

        userDetails.put(PASSWORD, user.getPassword());

        userDetails.put(TYPE, user.getType());

        userDetails.put(DOB_DATE, DateFormatting(user.getDOB().toString()));


        return userDetails;
    }

    /**
     * Creates Course JSON object.
     * 
     * @param user
     * @return
     */

    private static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();

        courseDetails.put(TITLE, course.getName());

        courseDetails.put(DIFFICULTY, course.getDifficulty().toString()); 

        courseDetails.put(COURSE_ID, course.getID().toString());

        courseDetails.put(TEACHER_ID, course.getAuthor().getID().toString()); // get author id

        courseDetails.put(EXAM, getAssessmentJSON(course.getAssessment()));

        courseDetails.put(COURSE_TYPE, course.getCourseType().toString()); // String shit

        courseDetails.put(DESCRIPTION, course.getDescription());

        courseDetails.put(MODULES, getModuleJSONArray(course.getModules())); // JSON array

        // course comments

        // enrolled students

        return courseDetails;
    }
    /**
     * Create a JSON array of TextSlide objects.
     * @param module
     * @return
     */
    private static JSONArray getTextSlideJSONArray(ArrayList<TextSlide> textSlide) {
        JSONArray textSlideArray = new JSONArray();
        JSONObject textSlideDetails = new JSONObject();
        for (int i = 0; i < textSlide.size(); i++) {
            // Creating a JSON object for each TextSlide
            textSlideDetails.put(SLIDE_TITLE, textSlide.get(i).getTitle());
            textSlideDetails.put(SLIDES, textSlide.get(i).getContents());
            textSlideArray.add(textSlideDetails);
        }
        return textSlideArray;
    }
    /**
     * Create a JSON objects for Comment objects.
     * @param comment
     * @return
     */
    private static JSONObject getCommentJSON(Comment comment) {
        JSONObject commentDetails = new JSONObject();
        commentDetails.put(COMMENT_TEXT, comment.getPost());
        commentDetails.put(COMMENTER_ID, comment.getPostingUser().toString());
        commentDetails.put(LIKES, comment.getLikes() + "");
        commentDetails.put(DISLIKES, comment.getDislikes() + "");
        commentDetails.put(CHILD_COMMENTS, getCommentJSONArray(comment.getComments()));
        return commentDetails;
    }
    /*
     * Create a JSON array of Comment JSON objects.
     */
    private static JSONArray getCommentJSONArray(ArrayList<Comment> comment) {
        JSONArray commentArray = new JSONArray();
        for (int i = 0; i < comment.size(); i++) {
            commentArray.add(getCommentJSON(comment.get(i)));
        }
        return commentArray;
    }
    /**
     * Creates Module JSON object.
     * @param module
     * @return
     */
    private static JSONObject getModuleJSON(Module module) {
        JSONObject moduleDetails = new JSONObject();
        moduleDetails.put(TITLE, module.getTitle());
        // slides
        moduleDetails.put(SLIDES, getTextSlideJSONArray(module.getSlides()));
        // comments
        moduleDetails.put(MODULE_COMMENTS, getCommentJSONArray(module.getComments()));

        moduleDetails.put(QUIZ, getAssessmentJSON(module.getQuiz()));;
        return moduleDetails;
    }
    /**
     * Creates Module JSON Array.
     * @param dob
     * @return
     */
    private static JSONArray getModuleJSONArray(ArrayList<Module> module) {
        JSONArray moduleArray = new JSONArray();
        for (int i = 0; i < module.size(); i++)
            moduleArray.add(getModuleJSON(module.get(i)));
        return moduleArray;
    }
    /**
     * Creates Assessment JSON object.
     * 
     * @param assessment
     * @return
     */
    private static JSONObject getAssessmentJSON(Assessment assessment) {
        JSONObject assessmentDetails = new JSONObject();
        assessmentDetails.put(LABEL, assessment.getLabel());
        assessmentDetails.put(QUESTIONS, getQuestionJSONArray(assessment.getQuestions()));
        assessmentDetails.put(CORRECT_ANSWERS, assessment.getCorrectAnswers());
        assessmentDetails.put(INPUTTED_ANSWERS, assessment.getInputtedAnswers());
        assessmentDetails.put(SCORE, assessment.getScore() + "");
        assessmentDetails.put(ASSESSMENT_TYPE, assessment.getType().toString());
        return assessmentDetails;
    }

    /**
     * Creates Question JSON Array.
     * 
     * @param question
     * @return
     */
    private static JSONArray getQuestionJSONArray(ArrayList<Question> question) {
        JSONArray questionDetails = new JSONArray();
        for (int i = 0; i < question.size(); i++)
            questionDetails.add(getQuestionJSON(question.get(i)));
        return questionDetails;
    }

    /**
     * Creates Question JSON object.
     * 
     * @param dob
     * @return
     */
    private static JSONObject getQuestionJSON(Question question) {
        JSONObject questionDetails = new JSONObject();
        JSONArray answerChoices = new JSONArray();
        JSONObject answerOptions = new JSONObject();

        String[] choices = {A, B, C, D};
        for (int i = 0; i < question.getAnswerChoices().size(); i++) {
            answerOptions.put(choices[i], question.getAnswerChoices().get(i));
        }

        answerChoices.add(answerOptions);
        
        questionDetails.put(QUESTION, question.getQuestion());
        questionDetails.put(ANSWER_CHOICES, answerChoices);
        questionDetails.put(CORRECT_ANSWER, question.getCorrectAnswer());

        return questionDetails;
    }

    private static Date parseDate(String dob) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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
            return "01011990";
        }
    }

    public static void main(String[] args) {

    }
}
