package src;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
     */
    public void saveCourses() {
        // (1) Get your UserList list and establish your
        // JSON array object.
        CourseList courseList = CourseList.getInstance();
        ArrayList<Course> all_users_list = courseList.getAllCourses();
        JSONArray jsonUsers = new JSONArray();

        // creating all the json objects
        for (int i = 0; i < all_users_list.size(); i++) {
            jsonUsers.add(getCourseJSON(all_users_list.get(i)));
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
     * Creates User JSON object.
     * @param user
     * @return
     */
    private static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getId().toString());
        userDetails.put(USER_NAME, user.getUserName());
        userDetails.put(FIRST_NAME, user.getFirstName());
        userDetails.put(LAST_NAME, user.getLastName());
        userDetails.put(EMAIL, user.getPhoneNumber());
        userDetails.put(DOB_DATE, user.dateToString());
        return userDetails;
    }
    /**
     * Creates Class JSON object.
     * @param user
     * @return
     */
    private static JSONObject getCourseJSON(Course course) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(COURSE_ID, course.getId().toString());
        userDetails.put(TITLE, course.getUserName());
        userDetails.put(DIFFICULTY, course.getFirstName()); // String shit
        userDetails.put(DESCRIPTION, course.getLastName());
        userDetails.put(AUTHOR, course.getPhoneNumber()); //get author id
        userDetails.put(EXAM, course.dateToString()); // Embed obj into JSON File.
        userDetails.put(COURSE_TYPE, course.dateToString()); // String shit
        userDetails.put(LESSONS, course.dateToString()); // JSON array
        return userDetails;
    }
}
