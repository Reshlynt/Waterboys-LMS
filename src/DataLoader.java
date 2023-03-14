import java.util.ArrayList;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.UUID;

public class DataLoader extends DataConstants {
  public static ArrayList<User> getUsers() {
    // purpose of this method is to read Users.json, go through entries, create a
    // User instance for each entry, then push to an array list
    ArrayList<User> users = new ArrayList<User>();
    try {
			FileReader reader = new FileReader("json/Users.json");
			JSONParser parser = new JSONParser();
			JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < usersJSON.size(); i++) {
				JSONObject userJSONObject = (JSONObject)usersJSON.get(i);
				UUID id = UUID.fromString((String)userJSONObject.get(ID));
        String userName = (String)userJSONObject.get(USER_NAME);
        String firstName = (String)userJSONObject.get(FIRST_NAME);
        String lastName = (String)userJSONObject.get(LAST_NAME);
        
				String phoneNumber = (String)personJSON.get(PEOPLE_PHONE_NUMBER);
				
				users.add(new Person(firstName, lastName, phoneNumber));
			}
			
			return people;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

  return null;

  }

  public static ArrayList<Course> getCourses() {
    return null;
  }
}
