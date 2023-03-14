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

    public void saveCourses() {

    }
    /**
     * Creates User JSON object.
     * @param user
     * @return
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getId().toString());
        userDetails.put(USER_USER_NAME, user.getUserName());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_AGE, user.getAge());
        userDetails.put(USER_PHONE_NUMBER, user.getPhoneNumber());

        return userDetails;
    }
}
