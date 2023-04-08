package src;

public class DataConstants {
  // Assessment Constants
  /*
   * private String label;
   * private ArrayList<Question> questions;
   * private String correctAnswers;
   * private String inputtedAnswers;
   * private int score;
   * private Type type;
   */

  // Assessment Constants
  public static final String LABEL = "label";
  public static final String QUESTIONS = "Questions";
  public static final String CORRECT_ANSWERS = "correctAnswers";
  public static final String INPUTTED_ANSWERS = "inputtedAnswers";
  public static final String SCORE = "score";
  public static final String ASSESSMENT_TYPE = "type";

  // Course Constants
  public static final String COURSE_CREATOR = "author";
  public static final String TITLE = "title";
  public static final String DIFFICULTY = "difficulty";
  public static final String COURSE_ID = "course_id";
  public static final String TEACHER_ID = "teacher_id";
  public static final String CREATED_COURSES = "createdCourses";
  public static final String COURSE_TYPE = "courseType";
  public static final String DESCRIPTION = "description";
  public static final String MODULES = "modules";
  protected static final String COURSE_FILE_NAME = "./json/Courses.json";
  public static final String COURSES = "courses";

  // module things
  public static final String MODULE_TITLE = "module_title";

  // Slides
  public static final String SLIDES = "slides";
  public static final String SLIDE_TITLE = "slide_title";
  public static final String CONTENT = "description";

  // Assessment Constants (for quiz and exam)
  public static final String EXAM = "exam"; // Note this also appears for courses
  public static final String QUIZ = "quiz";

  public static final String QUESTION = "question";
  public static final String ANSWER_CHOICES = "answer_choices";
  public static final String A = "a";
  public static final String B = "b";
  public static final String C = "c";
  public static final String D = "d";
  public static final String CORRECT_ANSWER = "correct_answer";

  // Comments Constants
  public static final String MODULE_COMMENTS = "module_comments";
  public static final String COURSE_COMMENTS = "course_comments";
  public static final String COMMENTER_ID = "id";
  public static final String COMMENT_TEXT = "text";
  public static final String REPLIES = "replies";// replies to comments
  public static final String REPLIER_ID = "id";
  public static final String REPLY_TEXT = "text";

  // public static final String SECOND_REPLIES = "more_replies";// replies to
  // replies
  // public static final String SECOND_REPLIER_ID = "id";
  // public static final String SECOND_REPLY_TEXT = "text";

  public static final String LIKES = "likes";
  public static final String DISLIKES = "dislikes";

  // Student Constants (for Courses)
  public static final String STUDENTS = "students";
  public static final String STUDENT_ID = "id";
  public static final String COMPLETED = "completed";
  public static final String GRADES = "grades";

  // User Constants
  public static final String USER_ID = "id";
  public static final String USER_NAME = "username";
  public static final String FIRST_NAME = "firstName";
  public static final String LAST_NAME = "lastName";
  public static final String EMAIL = "email";
  public static final String PASSWORD = "password";
  public static final String DOB_DATE = "dateOfBirth";
  public static final String TYPE = "type";
  protected static final String USER_FILE_NAME = "./json/Users.json";

  // Student User Constants
  public static final String COURSE_PROGRESS = "courseProgress";
  public static final String CERTIFICATES = "certificates";

  // Teacher User Constants
  public static final String TEACHER = "teacher";
}
