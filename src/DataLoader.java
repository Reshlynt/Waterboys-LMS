
import java.util.ArrayList;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataLoader extends DataConstants {
  public static ArrayList<User> getUsers() {
    // purpose of this method is to read Users.json, go through entries, create a
    // User instance for each entry, then push to an array list
    ArrayList<User> users = new ArrayList<User>();
    try {
      FileReader reader = new FileReader("json/Users.json");
      JSONParser parser = new JSONParser();
      JSONArray usersJSON = (JSONArray) parser.parse(reader);

      for (int i = 0; i < usersJSON.size(); i++) {
        JSONObject userJSONObject = (JSONObject) usersJSON.get(i);
        UUID id = UUID.fromString((String) userJSONObject.get(USER_ID));
        String userName = (String) userJSONObject.get(USER_NAME);
        String firstName = (String) userJSONObject.get(FIRST_NAME);
        String lastName = (String) userJSONObject.get(LAST_NAME);
        String email = (String) userJSONObject.get(EMAIL);
        String password = (String) userJSONObject.get(PASSWORD);
        Date dateOfBirth = parseDate((String) userJSONObject.get(DOB_DATE));
        String userType = (String) userJSONObject.get(TYPE);

        if (userType.equalsIgnoreCase("student"))
          users.add(new Student(id, userName, firstName, lastName, email, password, dateOfBirth));
        else if (userType.equalsIgnoreCase("teacher"))
          users.add(new Teacher(id, userName, firstName, lastName, email, password, dateOfBirth));
      }
      return users;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static ArrayList<Course> getCourses() {
    // purpose of this method is to read Users.json, go through entries, create a
    // User instance for each entry, then push to an array list
    ArrayList<Course> courses = new ArrayList<Course>();
    try {
      FileReader reader = new FileReader("json/Courses.json");
      JSONParser parser = new JSONParser();
      JSONArray courseJSON = (JSONArray) parser.parse(reader);

      for (int i = 0; i < courseJSON.size(); i++) {
        JSONObject courseJSONObject = (JSONObject) courseJSON.get(i);
        String courseTitle = (String) courseJSONObject.get(TITLE);
        Difficulty courseDifficulty = (Difficulty) courseJSONObject.get(DIFFICULTY);
        UUID courseID = UUID.fromString((String) courseJSONObject.get(COURSE_ID));
        CourseType courseType = (CourseType) courseJSONObject.get(COURSE_TYPE);

        // modules will be a JSONArray
        ArrayList<Module> modules = new ArrayList<Module>();
        JSONArray modulesJSON = (JSONArray) courseJSONObject.get(MODULES);
        for (int j = 0; j < modulesJSON.size(); j++) {
          JSONObject moduleJSONObject = (JSONObject) modulesJSON.get(j);
          String module_title = (String) moduleJSONObject.get(MODULE_TITLE);

          // slides will also be a JSONArray
          ArrayList<Slide> slides = new ArrayList<Slide>();
          JSONArray slidesJSON = (JSONArray) moduleJSONObject.get(SLIDES);
          for (int k = 0; k < slidesJSON.size(); k++) {
            JSONObject slideJSONObject = (JSONObject) slidesJSON.get(k);
            String slideTitle = (String) slideJSONObject.get(SLIDE_TITLE);
            String slideDescription = (String) slideJSONObject.get(CONTENT);

            Slide parsedSlide = new TextSlide(slideTitle, slideDescription);
            slides.add(parsedSlide);
          }

          JSONArray quizJSON = (JSONArray) moduleJSONObject.get(QUIZ);
          for (int l = 0; l < slidesJSON.size(); l++) {
            JSONObject quizJSONObject = (JSONObject) quizJSON.get(l);
            String question = (String) quizJSONObject.get(QUESTION);

            // insert JSON parsing for answer choices

            String correctAnswer = (String) quizJSONObject.get(CORRECT_ANSWER);

          }

          // parse comments here
          JSONArray commentJSON = (JSONArray) moduleJSONObject.get(COMMENTS);
          for (int m = 0; m < slidesJSON.size(); m++) {
            JSONObject commentJSONObject = (JSONObject) commentJSON.get(m);
            UUID commenterID = UUID.fromString((String) commentJSONObject.get(COMMENTER_ID));
            String commentText = (String) commentJSONObject.get(COMMENT_TEXT);

            JSONArray repliesJSON = (JSONArray) commentJSONObject.get(REPLIES);
            for (int n = 0; n < repliesJSON.size(); n++) {
              JSONObject replyJSONObject = (JSONObject) repliesJSON.get(n);
              UUID replierID = UUID.fromString((String) replyJSONObject.get(REPLIER_ID));
              String replyText = (String) replyJSONObject.get(REPLY_TEXT);
              // add another for loop about replies to replies lol
            }

          }
          // modules has a module_title and slides, now add an array list of comments as
          // well as a lessonQuiz
          // modules.add(new Module(module_title, slides,))
        }

        // parse students here
        JSONArray studentsJSON = (JSONArray) courseJSONObject.get(STUDENTS);
        for (int o = 0; o < studentsJSON.size(); o++) {
          JSONObject studentJSONObject = (JSONObject) studentsJSON.get(o);
          UUID studentID = UUID.fromString((String) studentJSONObject.get(STUDENT_ID));
          // deal with grades here(array in JSON)
        }

        // course comments
        JSONArray courseCommentJSON = (JSONArray) courseJSONObject.get(COURSE_COMMENTS);
        for (int m = 0; m < courseCommentJSON.size(); m++) {
          JSONObject courseCommentJSONObject = (JSONObject) courseCommentJSON.get(m);
          UUID courseCommenterID = UUID.fromString((String) courseCommentJSONObject.get(COURSE_COMMENTER_ID));
          String courseCommentText = (String) courseCommentJSONObject.get(COURSE_COMMENT_TEXT);

          JSONArray courseRepliesJSON = (JSONArray) courseCommentJSONObject.get(COURSE_COMMENT_REPLIES);
          for (int n = 0; n < courseRepliesJSON.size(); n++) {
            JSONObject courseReplyJSONObject = (JSONObject) courseRepliesJSON.get(n);
            UUID replierID = UUID.fromString((String) courseReplyJSONObject.get(COURSE_COMMENT_REPLY_ID));
            String replyText = (String) courseReplyJSONObject.get(COURSE_COMMENT_REPLY_TEXT);
            // add another for loop about replies to replies lol(MORE_REPLIES)
          }
        }
      }
      return courses;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
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

  public static void main(String[] args) {
    // ArrayList<User> readUsers = getUsers();
    // for (int i = 0; i < readUsers.size(); i++) {
    // User user = readUsers.get(i);
    // String printString = user.getEmail() + " " + user.getFirstName() + " " +
    // user.getLastName() + " "
    // + user.getPassword() + " " + user.getUserName() + " " + user.getID();
    // System.out.println(printString);
    // }
    // }
    UserList myList = UserList.getInstance();
  }
}