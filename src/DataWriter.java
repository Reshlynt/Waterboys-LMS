import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
    /**
     *  Saves users to a json file.
     */
    public static void saveUsers() {
        // (1) Get all the objects needed to write to a json
        UserList all_users_list = UserList.getInstance();
        ArrayList<User> userList = all_users_list.getUsers();
        JSONArray jsonUsers = new JSONArray();

        // (2) Create the json objects.
        for (int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        // (3) Write the JSON file given that array you got in
        //     the JSON array file.
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {

            file.write(jsonUsers.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCourses() {

    }
}
