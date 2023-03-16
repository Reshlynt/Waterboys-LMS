
import java.util.ArrayList;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

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
        UUID teacherID = UUID.fromString((String) courseJSONObject.get(TEACHER_ID));
        CourseType courseType = (CourseType) courseJSONObject.get(COURSE_TYPE);

        // modules will be a JSONArray
        JSONArray modulesJSON = (JSONArray) courseJSONObject.get(MODULES);
        ArrayList<Module> modules = readModules(modulesJSON);

        // make parsing students its own function using studentsJSON
        // parse students here
        JSONArray studentsJSON = (JSONArray) courseJSONObject.get(STUDENTS);
        ArrayList<HashMap<Student, ArrayList<Long>>> gradeMaps = readStudents(studentsJSON);
        ArrayList<Student> students = new ArrayList<Student>();

        for (int k = 0; k < gradeMaps.size(); k++) {
          HashMap<Student, ArrayList<Long>> gradeMap = new HashMap<Student, ArrayList<Long>>();
          gradeMap = gradeMaps.get(i);
          Set<Student> oneStudent = gradeMap.keySet();
          // this for loop only has 1 iteration
          for (Map.Entry<Student, ArrayList<Long>> set : gradeMap.entrySet()) {
            students.add(set.getKey());
          }
        }

        // course comments
        JSONArray courseCommentJSON = (JSONArray) courseJSONObject.get(COURSE_COMMENTS);
        ArrayList<Comment> courseComments = readComments(courseCommentJSON);

        // exam
        JSONArray examJSON = (JSONArray) courseJSONObject.get(EXAM);
        Assessment readExam = readAssessment(examJSON, courseTitle + " exam", Type.EXAM);

        // ask what exactly we need to construct a course from JSON, edit constructor
        // for Course and DataConstants accordingly
        Course readCourse = new Course(courseID, (Teacher) UserList.getInstance().getUserByUUID(teacherID), courseTitle,
            courseDifficulty, courseTitle, readExam, courseType, modules, courseComments, students);
        courses.add(readCourse);

        // for each student in students, set their grades using a setGrades method or
        // soemthing.
        // This code definitely might be wrong, debug later if needed
        for (int h = 0; h < students.size(); h++) {
          Student particularStudent = students.get(h);
          Student listedStudent = (Student) UserList.getInstance().getUserByUUID(particularStudent.getID());
          ArrayList<Long> particularGrades = gradeMaps.get(i).get(listedStudent);
          listedStudent.setCourseGrade(readCourse, particularGrades);
        }

        // set students grades for the course here
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

  private static Assessment readAssessment(JSONArray assessmentJSON, String label, Type type) {
    ArrayList<Question> questions = new ArrayList<Question>();
    for (int i = 0; i < assessmentJSON.size(); i++) {
      // questionJSON will consist of the question, answer choices, as well as the
      // correct answer
      JSONObject questionJSON = (JSONObject) assessmentJSON.get(i);

      String question = (String) questionJSON.get(QUESTION);

      JSONArray answerChoicesJSON = (JSONArray) questionJSON.get(ANSWER_CHOICES);
      ArrayList<String> answerChoices = new ArrayList<String>();
      for (int j = 0; j < answerChoicesJSON.size(); j++) {
        JSONObject answerChoiceJSON = (JSONObject) answerChoicesJSON.get(j);
        answerChoices.add((String) answerChoiceJSON.get(A));
        answerChoices.add((String) answerChoiceJSON.get(B));
        answerChoices.add((String) answerChoiceJSON.get(C));
        answerChoices.add((String) answerChoiceJSON.get(D));
      }

      String correctAnswer = (String) questionJSON.get(CORRECT_ANSWER);

      questions.add(new Question(question, answerChoices, correctAnswer));
    }
    return new Assessment(label, questions, type);
  }

  private static ArrayList<Module> readModules(JSONArray modulesJSON) {

    ArrayList<Module> modules = new ArrayList<Module>();
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
      Assessment readQuiz = readAssessment(quizJSON, moduleTitle + " quiz", Type.QUIZ);

      // parse comments here
      JSONArray moduleCommentsJSON = (JSONArray) moduleJSONObject.get(MODULE_COMMENTS);
      ArrayList<Comment> moduleComments = readComments(moduleCommentsJSON);
      // modules has a module_title and slides, now add an array list of comments as
      // well as a lessonQuiz
      modules.add(new Module(moduleTitle, slides, moduleComments, readQuiz));
    }
    return modules;
  }

  private static ArrayList<HashMap<Student, ArrayList<Long>>> readStudents(JSONArray studentsJSON) {
    // Arraylist of HashMaps, Each HashMap is gonna hash between a singular student
    // and an ArrayList of Grades
    // convoluted, i know
    ArrayList<HashMap<Student, ArrayList<Long>>> gradeMaps = new ArrayList<HashMap<Student, ArrayList<Long>>>();
    for (int i = 0; i < studentsJSON.size(); i++) {
      HashMap<Student, ArrayList<Long>> gradeMap = new HashMap<Student, ArrayList<Long>>();

      JSONObject studentJSONObject = (JSONObject) studentsJSON.get(i);
      UUID studentID = UUID.fromString((String) studentJSONObject.get(STUDENT_ID));

      Student student = (Student) UserList.getInstance().getUserByUUID(studentID);

      JSONArray gradesJSON = (JSONArray) studentJSONObject.get(GRADES);
      ArrayList<Long> grades = new ArrayList<Long>();
      for (int g = 0; g < gradesJSON.size(); g++) {
        grades.add((Long) gradesJSON.get(g));
      }
      gradeMap.put(student, grades);
      gradeMaps.add(gradeMap);
    }
    return gradeMaps;
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