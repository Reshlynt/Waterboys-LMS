
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@SuppressWarnings("unchecked")

public class DataWriter extends DataConstants {
    
    /**
     * Saves users to a json file.
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
     
    public void saveCourses() {
        // (1) Get your UserList list and establish your
        // JSON array object.
        CourseList courseList = CourseList.getInstance();
        ArrayList<Course> all_course_list = courseList.getAllCourses();
        JSONArray jsonCourse = new JSONArray();

        // creating all the json objects
        for (int i = 0; i < all_course_list.size(); i++) {
            jsonCourse.add(getCourseJSON(all_course_list.get(i)));
        }

        // Write JSON file
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {

            file.write(jsonCourse.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    /**
     * Creates User JSON object.
     * @param user
     * @return
     */
    private static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        // "User: " + id + " " + username + " " + firstName + " " + lastName + " " + email + " " + password + " " + DOB;
        String[] userArray = user.toString().substring(5).split(" ");

        userDetails.put(USER_ID, userArray[0]);

        userDetails.put(USER_NAME, userArray[1]);

        userDetails.put(FIRST_NAME, userArray[2]);

        userDetails.put(LAST_NAME, userArray[3]);

        userDetails.put(EMAIL, userArray[4]);

        userDetails.put(PASSWORD, userArray[5]);

        userDetails.put(DOB_DATE, userArray[6]);


        return userDetails;
    }
    /**
     * Creates Class JSON object.
     * @param user
     * @return
     */
    /* 
    private static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();
        
        courseDetails.put(COURSE_ID, course.getID().toString());

        courseDetails.put(TITLE, course.getTitle());

        courseDetails.put(DIFFICULTY, course.getDifficulty().toString(); // String shit

        courseDetails.put(DESCRIPTION, course.getDescription());

        courseDetails.put(COURSE_AUTHOR_ID, course.getAuthor().getID().toString()); // get author id

        courseDetails.put(EXAM, getAssessmentJSON(course.getAssessment())); // Embed obj into JSON File. JSONS deep down.
                                                        // Write a new method like this one to create a JSON object that holds the Assessment object's data.

        courseDetails.put(COURSE_TYPE, course.getCourseType().toString()); // String shit

        courseDetails.put(LESSONS, course.dateToString()); // JSON array

        return courseDetails;
    }
     */
    /* 
    private static JSONObject getAssessmentJSON(Assessment assessment) {
        JSONObject assessmentDetails = new JSONObject();

        assessmentDetails.put(LABEL, assessment.getLabel());

        assessmentDetails.put(CORRECT_ANSWERS, assessment.getCorrectAnswers());

        assessmentDetails.put(INPUTTED_ANSWERS, assessment.getInputtedAnswers());

        Integer 
        assessmentDetails.put(SCORE, assessment.getScore().toString());

        
        return assessmentDetails;
    }
    */
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
    public static void main(String[] args) {
        // public User(UUID id, String username, String firstName, String lastName, String email, String password, Date DOB)
        User funny = new Student(UUID.randomUUID(), "Gamerman", "John", "Doe", "email.com", "password", parseDate("12/15/2002"));
        UserList userList = UserList.getInstance();
        userList.addUser(funny);

    }
}
