
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;

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
     * 
     * public void saveCourses() { // (1) Get your UserList list and establish your // JSON array
     * object. CourseList courseList = CourseList.getInstance(); ArrayList<Course> all_course_list =
     * courseList.getAllCourses(); JSONArray jsonCourse = new JSONArray();
     * 
     * // creating all the json objects for (int i = 0; i < all_course_list.size(); i++) {
     * jsonCourse.add(getCourseJSON(all_course_list.get(i))); }
     * 
     * // Write JSON file try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
     * 
     * file.write(jsonCourse.toJSONString()); file.flush();
     * 
     * } catch (IOException e) { e.printStackTrace(); } }
     */
    /**
     * Creates User JSON object.
     * 
     * @param user
     * @return
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

        String date = user.getDOB().toString();
        String gamer = date.substring(4, 7); // + date.substring(8, 10) + date.substring(24, 28);
        date = MonthToInt(gamer) + date.substring(8, 10) + date.substring(24, 28);


        userDetails.put(DOB_DATE, date); // Manipulate with string so that only the month, day, and
                                          // year is stored.


        return userDetails;
    }

    /**
     * Creates Class JSON object.
     * 
     * @param user
     * @return
     */
    /*
     * private static JSONObject getCourseJSON(Course course) { JSONObject courseDetails = new
     * JSONObject();
     * 
     * courseDetails.put(COURSE_ID, course.getID().toString());
     * 
     * courseDetails.put(TITLE, course.getTitle());
     * 
     * courseDetails.put(DIFFICULTY, course.getDifficulty().toString(); // String shit
     * 
     * courseDetails.put(DESCRIPTION, course.getDescription());
     * 
     * courseDetails.put(COURSE_AUTHOR_ID, course.getAuthor().getID().toString()); // get author id
     * 
     * courseDetails.put(EXAM, getAssessmentJSON(course.getAssessment())); // Embed obj into JSON
     * File. JSONS deep down. // Write a new method like this one to create a JSON object that holds
     * the Assessment object's data.
     * 
     * courseDetails.put(COURSE_TYPE, course.getCourseType().toString()); // String shit
     * 
     * courseDetails.put(LESSONS, course.dateToString()); // JSON array
     * 
     * return courseDetails; }
     */
    /*
     * private static JSONObject getAssessmentJSON(Assessment assessment) { JSONObject
     * assessmentDetails = new JSONObject();
     * 
     * assessmentDetails.put(LABEL, assessment.getLabel());
     * 
     * assessmentDetails.put(CORRECT_ANSWERS, assessment.getCorrectAnswers());
     * 
     * assessmentDetails.put(INPUTTED_ANSWERS, assessment.getInputtedAnswers());
     * 
     * Integer assessmentDetails.put(SCORE, assessment.getScore().toString());
     * 
     * 
     * return assessmentDetails; }
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

    private static String MonthToInt(String month_name) {
        switch (month_name) {
            case "january":
            case "Jan":
                month_name = "01";
                break;

            case "febuary":
            case "Feb":
                month_name = "02";
                break;

            case "march":
            case "Mar":
                month_name = "03";
                break;

            case "april":
            case "Apr":
                month_name = "04";
                break;

            case "May":
                month_name = "05";
                break;

            case "june":
            case "Jun":
                month_name = "06";
                break;

            case "july":
            case "Jul":
                month_name = "07";
                break;

            case "august":
            case "Aug":
                month_name = "08";
                break;

            case "september":
            case "Sep":
            case "sept":
                month_name = "09";
                break;

            case "october":
            case "Oct":
                month_name = "10";
                break;

            case "november":
            case "Nov":
                month_name = "11";
                break;

            case "december":
            case "Dec":
                month_name = "12";
                break;
        }
        return month_name;
    }

    public static void main(String[] args) {
        // public User(UUID id, String username, String firstName, String lastName, String email,
        // String password, Date DOB)

        User funny = new Teacher(UUID.randomUUID(), "AphixDragon", "Trees", "Trago", "weeee.com",
                "password74", parseDate("12151984"));
        UserList userList = UserList.getInstance();
        userList.addUser(funny);
        saveUsers();

    }
}
