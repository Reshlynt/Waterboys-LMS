
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

      // this gigantic for loop goes through each course object in the JSON
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
          String moduleTitle = (String) moduleJSONObject.get(MODULE_TITLE);

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
          ArrayList<Question> questions = new ArrayList<Question>();
          for (int l = 0; l < quizJSON.size(); l++) {
            JSONObject quizQuestionJSON = (JSONObject) quizJSON.get(l);
            String question = (String) quizQuestionJSON.get(QUESTION);

            // Reading as string array might now work, test later
            JSONArray answerChoicesJSON = (JSONArray) quizQuestionJSON.get(ANSWER_CHOICES);
            ArrayList<String> answerChoices = new ArrayList<String>();
            for (int x = 0; x < answerChoicesJSON.size(); x++) {
              JSONObject answerChoiceJSON = (JSONObject) answerChoicesJSON.get(x);
              answerChoices.add((String)answerChoiceJSON.get(A));
              answerChoices.add((String)answerChoiceJSON.get(B));
              answerChoices.add((String)answerChoiceJSON.get(C));
              answerChoices.add((String)answerChoiceJSON.get(D));
            }

            String correctAnswer = (String) quizQuestionJSON.get(CORRECT_ANSWER);

            Question readQuestion = new Question(question, answerChoices, correctAnswer);
            questions.add(readQuestion);
          }
          String quizLabel = moduleTitle + " quiz";
          // Question quiz
          // quiz needs String label,
          // ArrayList<Question> questions, String correctAnswers,
          // String inputtedAnswers, int score, Type type
          Assessment readQuiz = new Assessment(quizLabel, questions, Type.QUIZ);
          // parse comments here
          JSONArray moduleCommentsJSON = (JSONArray) moduleJSONObject.get(MODULE_COMMENTS);
          ArrayList<Comment> moduleComments = readComments(moduleCommentsJSON);
          // modules has a module_title and slides, now add an array list of comments as
          // well as a lessonQuiz
          // modules.add(new Module(module_title, moduleComments, QUIZ BRO));
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
        ArrayList<Comment> courseComments = readComments(courseCommentJSON);
        // Course readCourse = new Course(courseID, courseTitle, courseDifficulty,
        // courseTitle, null, null, courseType, studentsJSON, comments);
        // courses.add(readCourse);
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

  private static ArrayList<Comment> readComments(JSONArray commentsJSON) {
    ArrayList<Comment> comments = new ArrayList<Comment>();

    for (int i = 0; i < commentsJSON.size(); i++) {
      // JSON object(Comment) that has a UUID, text, and a JSON array of replies
      JSONObject commentJSON = (JSONObject) commentsJSON.get(i);

      UUID commenterID = UUID.fromString((String) commentJSON.get(COMMENTER_ID));
      String commentText = (String) commentJSON.get(COMMENT_TEXT);

      // JSON Array of replies, array list will hold those replies
      JSONArray repliesJSON = (JSONArray) commentJSON.get(REPLIES);
      ArrayList<Comment> replies = new ArrayList<Comment>();

      for (int j = 0; j < repliesJSON.size(); j++) {
        // JSON object(Reply) that has a UUID, text, and a JSON array of replies
        JSONObject replyJSON = (JSONObject) repliesJSON.get(j);

        UUID replierID = UUID.fromString((String) replyJSON.get(REPLIER_ID));
        String replyText = (String) replyJSON.get(REPLY_TEXT);

        // reply to a reply
        ArrayList<Comment> secondReplies = new ArrayList<Comment>();
        JSONArray secondRepliesJSON = (JSONArray) replyJSON.get(SECOND_REPLIES);

        for (int second_reply_index = 0; second_reply_index < secondRepliesJSON.size(); second_reply_index++) {
          // JSON object(Second Reply) that has a UUID and text
          JSONObject second_reply_JSONObject = (JSONObject) secondRepliesJSON.get(second_reply_index);

          UUID second_replierID = UUID.fromString((String) second_reply_JSONObject.get(SECOND_REPLIER_ID));
          String second_replyText = (String) second_reply_JSONObject.get(SECOND_REPLY_TEXT);

          // second reply will not have an array list of comments(it is the leaf of the
          // tree if you will)
          Comment secondReply = new Comment(second_replyText, second_replierID, null);

          // add each second reply to the array list of second replies
          secondReplies.add(secondReply);
        }
        // reply will have an array list of second replies underneath it
        Comment reply = new Comment(replyText, replierID, secondReplies);

        // add each reply to the array list of replies
        replies.add(reply);
      }
      // comment will have an array list of replies underneath it
      Comment comment = new Comment(commentText, commenterID, replies);

      // add each comment to the array list of comments
      comments.add(comment);
    }
    return comments;
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