package src;

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
import java.util.Map;

public class DataLoader extends DataConstants {
  /**
   * Method reads the Users.json file and reads all of the User data, adding each
   * user to the array list
   * 
   * @return an ArrayList of Users populated from the JSON
   */
  public static ArrayList<User> getUsers() {
    ArrayList<User> users = new ArrayList<User>();// to be returned
    try {
      // opens json file, and intializes json parser(which will interpret the info)
      FileReader reader = new FileReader("json/Users.json");
      JSONParser parser = new JSONParser();

      // creates a json array of all of the users in the json file
      JSONArray usersJSON = (JSONArray) parser.parse(reader);

      // for loop iterates through all of the users
      for (int i = 0; i < usersJSON.size(); i++) {

        // json object of one specific user
        JSONObject userJSON = (JSONObject) usersJSON.get(i);

        // below is all of the necessary information needed to fill user constructor
        // everything is either read as a String or a Long in JSONs, hence the casting
        UUID id = UUID.fromString((String) userJSON.get(USER_ID));
        String userName = (String) userJSON.get(USER_NAME);
        String firstName = (String) userJSON.get(FIRST_NAME);
        String lastName = (String) userJSON.get(LAST_NAME);
        String email = (String) userJSON.get(EMAIL);
        String password = (String) userJSON.get(PASSWORD);
        Date dateOfBirth = parseDate((String) userJSON.get(DOB_DATE));
        String userType = (String) userJSON.get(TYPE);
        // JSONArray createdCourses = (JSONArray) userJSONObject.get(CREATED_COURSES);
        // TODO: read in the certificate/course progress info from JSON

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

  /**
   * Method reads the Courses.json file and reads all of the Course data, adding
   * each
   * course and its information to the array list
   * 
   * @return an ArrayList of Courses populated from the JSON
   */
  public static ArrayList<Course> getCourses() {
    ArrayList<Course> courses = new ArrayList<Course>();// to be returned
    try {
      // opens json file, and intializes json parser(which will interpret the info)
      FileReader reader = new FileReader("json/Courses.json");
      JSONParser parser = new JSONParser();

      // creates a json array of all of the courses in the json file
      JSONArray coursesJSON = (JSONArray) parser.parse(reader);

      // for loop iterates through all of the courses
      for (int i = 0; i < coursesJSON.size(); i++) {
        // particular course
        JSONObject courseJSON = (JSONObject) coursesJSON.get(i);

        // necessary information for course constructor that can be extracted simply
        String courseTitle = (String) courseJSON.get(TITLE);
        Difficulty courseDifficulty = Difficulty.valueOf((String) courseJSON.get(DIFFICULTY));
        UUID courseID = UUID.fromString((String) courseJSON.get(COURSE_ID));
        UUID teacherID = UUID.fromString((String) courseJSON.get(TEACHER_ID));
        CourseType courseType = CourseType.valueOf((String) courseJSON.get(COURSE_TYPE));

        // modules will be a JSONArray, readModules parses and returns an array list of
        // type Module
        JSONArray modulesJSON = (JSONArray) courseJSON.get(MODULES);
        ArrayList<Module> modules = readModules(modulesJSON);

        // students will be a JSONArray, readStudentGrades reads and populates an
        // arraylist of hashmaps:
        // where each hashmap has 1 student and 1 arraylist of grades. getStudents
        // extracts the students arraylist from the arraylist of hashmaps
        JSONArray studentsJSON = (JSONArray) courseJSON.get(STUDENTS);
        ArrayList<HashMap<Student, ArrayList<Long>>> gradeMaps = readStudentGrades(studentsJSON);
        ArrayList<Student> students = getStudents(gradeMaps);

        // course comments will be a JSONArray, readComments parses and returns an array
        // list of type Comment
        JSONArray courseCommentJSON = (JSONArray) courseJSON.get(COURSE_COMMENTS);
        ArrayList<Comment> courseComments = readComments(courseCommentJSON);

        // exam is a JSONArray with 1 element, readAssessment parses it and returns an
        // Assessment object
        JSONArray examJSON = (JSONArray) courseJSON.get(EXAM);
        Assessment readExam = readAssessment(examJSON, courseTitle + " exam", Type.EXAM);

        // creates a course with all of the information that was read from the JSON, and
        // appends to the array list
        Course readCourse = new Course(courseID, (Teacher) UserList.getInstance().getUserByUUID(teacherID), courseTitle,
            courseDifficulty, courseTitle, readExam, courseType, modules, courseComments, students);
        courses.add(readCourse);

        // iterates through students and updates all of their grades that were parsed
        // and put into the array list of hashmaps
        setCourseGrades(readCourse, students, gradeMaps);

      }
      return courses;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Method is meant to convert a DOB string to a java.util.Date object
   * 
   * @param dob string in the form "MMDDYYYY" specifying the date of birth of a
   *            user
   * @return Converted Date object specifying date of birth
   */
  private static Date parseDate(String dob) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
    try {
      return dateFormat.parse(dob);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Method is meant to parse a JSONArray of comments(either module or course) and
   * create an arraylist of type comment
   * 
   * @param commentsJSON a JSONArray that has all of the comments(including
   *                     subcomments)
   * @return an array list of comments from the JSONArray
   */
  private static ArrayList<Comment> readComments(JSONArray commentsJSON) {
    ArrayList<Comment> comments = new ArrayList<Comment>();// to be returned

    /*
     * note regarding comments: we are only representing 3 levels of comments: top
     * level comments which will be the main comments to either a module or a
     * course, first replies which will be replies to those main comments, and
     * second replies which will be replies to those first replies
     * Any more replies are disgusting to implement, and not representative of real
     * life situations
     */

    // for loop iterates through all of the top level comments
    for (int i = 0; i < commentsJSON.size(); i++) {
      // particular top level comment is a JSONObject
      JSONObject commentJSON = (JSONObject) commentsJSON.get(i);

      // Comment consists of a UUID and text
      UUID commenterID = UUID.fromString((String) commentJSON.get(COMMENTER_ID));
      String commentText = (String) commentJSON.get(COMMENT_TEXT);

      // Comment also has a list/JSONArray of first replies
      JSONArray repliesJSON = (JSONArray) commentJSON.get(REPLIES);
      ArrayList<Comment> replies = new ArrayList<Comment>();

      for (int j = 0; j < repliesJSON.size(); j++) {
        // particular first reply is a JSONObject
        JSONObject replyJSON = (JSONObject) repliesJSON.get(j);

        // First reply also consists of a UUID and text
        UUID replierID = UUID.fromString((String) replyJSON.get(REPLIER_ID));
        String replyText = (String) replyJSON.get(REPLY_TEXT);

        // Reply also has a list/JSONArray of second replies
        JSONArray secondRepliesJSON = (JSONArray) replyJSON.get(SECOND_REPLIES);
        ArrayList<Comment> secondReplies = new ArrayList<Comment>();

        for (int k = 0; k < secondRepliesJSON.size(); k++) {
          // Second reply only consists of a UUID and text
          JSONObject secondReplyJSON = (JSONObject) secondRepliesJSON.get(k);

          UUID second_replierID = UUID.fromString((String) secondReplyJSON.get(SECOND_REPLIER_ID));
          String second_replyText = (String) secondReplyJSON.get(SECOND_REPLY_TEXT);

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

  /**
   * Method is meant to be given JSON information regarding the exam/quiz, and
   * return a created Assessment object
   * 
   * @param assessmentJSON JSONArray of assessment questions
   * @param label          assessment title
   * @param type           assessment type, check Type.java
   * @return an assessment object
   */
  private static Assessment readAssessment(JSONArray assessmentJSON, String label, Type type) {
    ArrayList<Question> questions = new ArrayList<Question>();

    // for loop iterates through all of the questions in an assessment
    for (int i = 0; i < assessmentJSON.size(); i++) {

      // questionJSON is specific question: including question, answer choices, and
      // correct answer
      JSONObject questionJSON = (JSONObject) assessmentJSON.get(i);

      // question
      String question = (String) questionJSON.get(QUESTION);

      // answer choices will be a JSONArray/arraylist
      JSONArray answerChoicesJSON = (JSONArray) questionJSON.get(ANSWER_CHOICES);
      ArrayList<String> answerChoices = new ArrayList<String>();

      for (int j = 0; j < answerChoicesJSON.size(); j++) {
        JSONObject answerChoiceJSON = (JSONObject) answerChoicesJSON.get(j);

        // adds chocies for a, b, c, and d
        answerChoices.add((String) answerChoiceJSON.get(A));
        answerChoices.add((String) answerChoiceJSON.get(B));
        answerChoices.add((String) answerChoiceJSON.get(C));
        answerChoices.add((String) answerChoiceJSON.get(D));
      }

      // correct answer
      String correctAnswer = (String) questionJSON.get(CORRECT_ANSWER);

      // pushes complete question object to questions arraylist
      questions.add(new Question(question, answerChoices, correctAnswer));
    }

    // returns assessment with title, all questions, and type(exam or quiz)
    return new Assessment(label, questions, type);
  }

  /**
   * Method is meant to parse a JSONArray of Modules and their info, and return an
   * ArrayList of type module
   * 
   * @param modulesJSON JSONArray of modules
   * @return an array list of type Module
   */
  private static ArrayList<Module> readModules(JSONArray modulesJSON) {
    ArrayList<Module> modules = new ArrayList<Module>();// to return

    // for loop iterates through all of the modules
    for (int i = 0; i < modulesJSON.size(); i++) {
      // particular module
      JSONObject moduleJSON = (JSONObject) modulesJSON.get(i);

      // title
      String moduleTitle = (String) moduleJSON.get(MODULE_TITLE);

      // module will have an array list of slides

      JSONArray slidesJSON = (JSONArray) moduleJSON.get(SLIDES);
      ArrayList<TextSlide> slides = new ArrayList<TextSlide>();

      // for loop iterates through all of the slides
      for (int j = 0; j < slidesJSON.size(); j++) {

        // particular slide is a json object
        JSONObject slideJSON = (JSONObject) slidesJSON.get(j);

        // title and description
        String slideTitle = (String) slideJSON.get(SLIDE_TITLE);
        String slideDescription = (String) slideJSON.get(CONTENT);

        // creates a new text slide object, and adds it to the slide arraylist
        TextSlide parsedSlide = new TextSlide(slideTitle, slideDescription);
        slides.add(parsedSlide);
      }

      // gets quiz(array of question stuff) and calls readAssessment method to create
      // a Assessment object
      JSONArray quizJSON = (JSONArray) moduleJSON.get(QUIZ);
      Assessment readQuiz = readAssessment(quizJSON, moduleTitle + " quiz", Type.QUIZ);

      // module comments will be a JSONArray, readComments parses and returns an array
      // list of type Comment
      JSONArray moduleCommentsJSON = (JSONArray) moduleJSON.get(MODULE_COMMENTS);
      ArrayList<Comment> moduleComments = readComments(moduleCommentsJSON);

      // creates new module with title, slides of info, comments, and a quiz
      modules.add(new Module(moduleTitle, slides, moduleComments, readQuiz));
    }
    return modules;
  }

  /**
   * Method is meant to link the grades of the students to the correct students
   *
   * @param studentsJSON JSONArray of students(which has their ids and their
   *                     grades)
   * @return Returns an Arraylist of HashMaps, Each HashMap is gonna hash between
   *         a singular student
   *         and their ArrayList of Grades
   */
  private static ArrayList<HashMap<Student, ArrayList<Long>>> readStudentGrades(JSONArray studentsJSON) {
    // to return
    ArrayList<HashMap<Student, ArrayList<Long>>> gradeMaps = new ArrayList<HashMap<Student, ArrayList<Long>>>();

    // for loop iterates through all of the students
    for (int i = 0; i < studentsJSON.size(); i++) {

      // creates a particular HashMap(student is the key, grades are the value)
      HashMap<Student, ArrayList<Long>> gradeMap = new HashMap<Student, ArrayList<Long>>();

      // particular student
      JSONObject studentJSONObject = (JSONObject) studentsJSON.get(i);

      // gets the student UUID, and calls existing UserList to get the existing
      // instance of student
      UUID studentID = UUID.fromString((String) studentJSONObject.get(STUDENT_ID));
      Student student = (Student) UserList.getInstance().getUserByUUID(studentID);

      // grades is an array list of longs
      JSONArray gradesJSON = (JSONArray) studentJSONObject.get(GRADES);
      ArrayList<Long> grades = new ArrayList<Long>();

      // goes through all of the grades and appends to the array list
      for (int j = 0; j < gradesJSON.size(); j++)
        grades.add((Long) gradesJSON.get(j));

      // adds the student and grades to the HashMap, then adds the HashMap to the
      // array list
      gradeMap.put(student, grades);
      gradeMaps.add(gradeMap);
    }
    return gradeMaps;
  }

  /**
   * Method is meant to parse the ArrayList of HashMaps from the readStudentGrades
   * method
   * and return an array list of just the students
   * 
   * @param gradeMaps Arraylist of HashMaps, Each HashMap is gonna hash between
   *                  a singular student
   *                  and their ArrayList of Grades
   * @return ArrayList of type student
   */
  private static ArrayList<Student> getStudents(ArrayList<HashMap<Student, ArrayList<Long>>> gradeMaps) {
    ArrayList<Student> students = new ArrayList<Student>();// to return

    // method goes through all of the hashmaps
    for (int i = 0; i < gradeMaps.size(); i++) {
      // gets the particular hashmap
      HashMap<Student, ArrayList<Long>> gradeMap = gradeMaps.get(i);

      // 1 iteration for loop, adds the key of the hashmap to the arraylist
      for (Map.Entry<Student, ArrayList<Long>> set : gradeMap.entrySet())
        students.add(set.getKey());
    }
    return students;
  }

  /**
   * Method is meant to set the correct grades in a course for each student in the
   * course
   * 
   * @param course    which course the students grades are in
   * @param students  array list of the students
   * @param gradeMaps Arraylist of HashMaps, Each HashMap is gonna hash between
   *                  a singular student
   *                  and their ArrayList of Grades
   */
  private static void setCourseGrades(Course course, ArrayList<Student> students,
      ArrayList<HashMap<Student, ArrayList<Long>>> gradeMaps) {
    // goes through all of the students
    for (int i = 0; i < students.size(); i++) {
      // particular student(gets it from the UserList to prevent duplicate instances)
      Student listedStudent = (Student) UserList.getInstance().getUserByUUID(students.get(i).getID());

      // .get(h) get the particular HashMap from the ArrayList of HashMaps, and
      // .get(ListedStudent) hashes using the student as the key
      ArrayList<Long> particularGrades = gradeMaps.get(i).get(listedStudent);

      // calls setCourseGrade method which updates the students grade for the
      // particular course
      listedStudent.setCourseGrade(course, particularGrades);
    }
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
    ArrayList<Course> readCourses = getCourses();
    for (int i = 0; i < readCourses.size(); i++) {
      Course course = readCourses.get(i);
      System.out.println(course);
    }
  }
}