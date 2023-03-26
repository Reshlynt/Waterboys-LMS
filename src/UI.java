package src;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class UI {
  public static final String FIVESTAR = "*****", FOURSTAR = "****", SPACESTAR = " *";
  public static final Scanner INPUT = new Scanner(System.in);
  public static final LMSSystem LMS = new LMSSystem();

  public static void main(String[] args) {
    run();
  }

  public static void run() {
    boolean quit = false;
    // Part 1 - Logging in or Signing up
    while (!quit) {
      User user = null;
      switch (Welcome()) {
        case 1:
          user = Login();
          break;
        case 2:
          user = SignUp();
          break;
        case 9:
          Quit();
        default:
          System.out.println("\n\n\n\n\n");
          WelcomeLine7("You entered an invalid choice. Press Enter to Continue");
          INPUT.nextLine();
          run();
          break;
      }
      System.out.print("\n\n\n\n\n\n");
      if (user != null) {
        // DataWriter.saveUsers();
        quit = true;
      } else {
        WelcomeLine7("Press Enter to Continue");
        INPUT.nextLine();
        continue;
      }
      if (user.getType().equalsIgnoreCase("teacher")) {
        switch (TeacherMenu((Teacher) user)) {
          case 1:
            break;
          case 2:
            // work on creating a course
            CreateCourse((Teacher) user);
            break;
          case 3:
            break;
          case 4:
            ViewTeacherProfile((Teacher) user);
            break;
          case 9:
            Quit();
            break;
        }
      } else if (user.getType().equalsIgnoreCase("student")) {
        switch (StudentMenu((Student) user)) {
          case 1:
            break;
          case 2:
            Course course = getAccessCourse((Student) user);
            boolean quiz = AccessCourse(course);
            if (quiz)
              System.out.print(" ");
            else
              System.out.println("There are currently no assessments you can take for this course...");
            break;
          case 3:
            break;
          case 4:
            ViewStudentProfile((Student) user);
            break;
          case 9:
            Quit();
            break;
        }
      }
    }
  }

  public static int Welcome() {
    WelcomeLine1();
    WelcomeLine2(FIVESTAR, SPACESTAR, 34);
    WelcomeLine3(FIVESTAR, " ", 69);
    WelcomeLine4(FOURSTAR, "Welcome to", 30);
    WelcomeLine4(FIVESTAR, "the Waterboys\'", 27);
    WelcomeLine4(FOURSTAR, "Learning Management System", 22);
    WelcomeLine3(FIVESTAR, " ", 69);
    WelcomeLine4(FOURSTAR, "\"Teaching Others About Coding Languages\"", 15);
    WelcomeLine3(FIVESTAR, " ", 69);
    WelcomeLine2(FIVESTAR, SPACESTAR, 34);
    WelcomeLine1();
    System.out.print("\n\n\n");
    WelcomeLine5(35, "1.) Login\n");
    WelcomeLine5(35, "2.) Sign Up\n");
    WelcomeLine5(35, "9.) Quit LMS");
    System.out.print("\n\n");
    WelcomeLine5(32, "Choose an option: ");
    try {
      int value = INPUT.nextInt();
      INPUT.nextLine();
      System.out.println("\n\n\n\n\n");
      return value;
    } catch (Exception e) {
      INPUT.nextLine();
      System.out.println("\n\n\n\n\n");
      System.out.println("You entered an invalid choice. Press Enter to Continue");
      INPUT.nextLine();
      System.out.println("\n\n\n\n\n");
      return Welcome();
    }
  }

  private static void WelcomeLine1() {
    for (int i = 0; i < 79; i++)
      System.out.print('*');
    System.out.println();
  }

  private static void WelcomeLine2(String item1, String item2, int iterator) {
    System.out.print(item1 + "*");
    for (int i = 0; i < iterator; i++)
      System.out.print(item2);
    System.out.println(item1);
  }

  private static void WelcomeLine3(String item1, String item2, int iterator) {
    System.out.print(item1);
    for (int i = 0; i < iterator; i++)
      System.out.print(item2);
    System.out.println(item1);
  }

  private static void WelcomeLine4(String item1, String item2, int iterator) {
    System.out.print(item1);
    for (int i = 0; i < iterator; i++)
      System.out.print(' ');
    System.out.print(item2);
    for (int i = 0; i < (iterator + 1); i++)
      System.out.print(' ');
    System.out.println(item1);
  }

  private static void WelcomeLine5(int iterator, String item1) {
    for (int i = 0; i < iterator; i++)
      System.out.print(' ');
    System.out.print(item1);
  }

  private static void WelcomeLine6(String item1) {
    int count = 71 - item1.length();
    System.out.print(FOURSTAR);
    for (int i = 0; i < count / 2; i++)
      System.out.print(" ");
    System.out.print(item1);
    for (int i = 0; i < (count - count / 2); i++)
      System.out.print(" ");
    System.out.println(FOURSTAR);
  }
  public static void WelcomeLine7(String item1) {
    int line = 79 - item1.length();
    for (int i = 0; i < line/2; i++)
      System.out.print(" ");
      System.out.println(item1);
  }
  private static Date parseDate(String dob) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
    Date date = null;
    try {
      date = dateFormat.parse(dob);
    } catch (ParseException e) {
      System.out.println("             We cannot create a date based on the given input");
    }
    return date;
  }

  public static User SignUp() {
    String first_name = "", last_name = "", email = "", username = "", password = "", confirm = "", birthday = "",
        job = "";
    WelcomeLine1();
    WelcomeLine4(FOURSTAR, "New User Sign-Up", 27);
    WelcomeLine1();
    System.out.println();
    WelcomeLine5(25, "First Name: ");
    first_name = INPUT.nextLine();
    WelcomeLine5(25, "Last Name: ");
    last_name = INPUT.nextLine();
    WelcomeLine5(25, "Email: ");
    email = INPUT.nextLine();
    WelcomeLine5(25, "Date of Birth (MMDDYYYY): ");
    birthday = INPUT.nextLine();
    WelcomeLine5(25, "User Name: ");
    username = INPUT.nextLine();
    WelcomeLine5(25, "Password: ");
    password = INPUT.nextLine();
    WelcomeLine5(25, "Confirm Password: ");
    confirm = INPUT.nextLine();
    WelcomeLine5(25, "Are you are Student or Teacher: ");
    job = INPUT.nextLine();
    System.out.println("\n\n\n\n\n");
    return LMS.SignUp(first_name, last_name, username, email, password, parseDate(birthday), job);
  }

  public static User Login() {
    String username = "", password = "";
    WelcomeLine1();
    WelcomeLine4(FOURSTAR, "Login ", 32);
    WelcomeLine1();
    System.out.println();
    WelcomeLine5(25, "User Name: ");
    username = INPUT.nextLine();
    WelcomeLine5(25, "Password: ");
    password = INPUT.nextLine();
    System.out.println("\n\n\n\n\n");
    return LMS.Login(username, password);
  }

  public static int TeacherMenu(User teacher) {
    WelcomeLine1();
    WelcomeLine6("Welcome, " + teacher.getUsername());
    WelcomeLine1();
    System.out.println();
    WelcomeLine7("1.) Add Student to Course");
    WelcomeLine7("2.) Create a Course");
    WelcomeLine7("3.) Remove a Student from a course");
    WelcomeLine7("4.) View Profile");
    WelcomeLine7("5.) View Courses");
    WelcomeLine7("9.) Exit LMS\n");
    WelcomeLine7("Choose an option: ");
    try {
      int value = INPUT.nextInt();
      INPUT.nextLine();
      System.out.println("\n\n\n\n\n");
      return value;
    } catch (Exception e) {
      INPUT.nextLine();
      System.out.println("\n\n\n\n\n");
      System.out.println("You entered an invalid choice. Press Enter or to Continue");
      INPUT.nextLine();
      System.out.println("\n\n\n\n\n");
      return TeacherMenu(teacher);
    }
  }

  public static int StudentMenu(Student student) {
    WelcomeLine1();
    WelcomeLine6("Welcome, " + student.getUsername());
    WelcomeLine1();
    System.out.println();
    WelcomeLine7("1.) Register for Course");
    WelcomeLine7("2.) Access your Courses");
    WelcomeLine7("3.) Access Certifications");
    WelcomeLine7("4.) View Profile");
    WelcomeLine7("9.) Exit LMS\n");
    WelcomeLine5(31, "Choose an option: ");
    try {
      int value = INPUT.nextInt();
      INPUT.nextLine();
      System.out.println("\n\n\n\n\n");
      return value;
    } catch (Exception e) {
      INPUT.nextLine();
      System.out.println("\n\n\n\n\n");
      WelcomeLine5(11, "You entered an invalid choice. Press Enter or to Continue");
      INPUT.nextLine();
      System.out.println("\n\n\n\n\n");
      return StudentMenu(student);
    }
  }

  private static void ViewTeacherProfile(Teacher user) {
    String header = (user.getUsername() + "\'s Profile");
    WelcomeLine1();
    WelcomeLine6(header);
    WelcomeLine1();
    System.out.println("\n");
    WelcomeLine5(25, "Name: " + user.getFirstName() + " " + user.getLastName() + "\n");
    WelcomeLine5(25, "Email: " + user.getEmail() + "\n");
    WelcomeLine5(25, "Date of Birth: " + user.getDOB() + "\n");
    if (DataLoader.getCourses() != null) {
      ArrayList<Course> readCourses = DataLoader.getCourses();
      System.out.println("\n");
      WelcomeLine5(25, "Courses Created:\n");
      for (int i = 0; i < readCourses.size(); i++) {
        Course course = readCourses.get(i);
        Teacher teacher = course.getAuthor();
        if (user.getUsername().equals(teacher.getUsername())) {
          WelcomeLine5(25, "\t" + course.getTitle());
        }
      }
    } else {
      WelcomeLine7("There are no courses currently");
    }
  }

  private static void ViewStudentProfile(Student user) {
    String header = (user.getUsername() + "\'s Profile");
    WelcomeLine1();
    WelcomeLine6(header);
    WelcomeLine1();
    System.out.println("\n");
    WelcomeLine5(25, "Name: " + user.getFirstName() + " " + user.getLastName() + "\n");
    WelcomeLine5(25, "Email: " + user.getEmail() + "\n");
    WelcomeLine5(25, "Date of Birth: " + user.getDOB() + "\n");
    System.out.println("\n");
    WelcomeLine5(25, "Courses Enrolled:\n");
    ArrayList<Course> readCourses = DataLoader.getCourses();
    for (int i = 0; i < readCourses.size(); i++) {
      Course course = readCourses.get(i);
      ArrayList<Student> students = course.getStudents();
      for (Student student : students) {
        if (student.equals(user.getID())) {
          WelcomeLine5(25, "\t" + course.getTitle());
        }
      }
    }
  }

  private static void CreateCourse(Teacher teacher) {
    // ask questions
    System.out.println("Lets create a course!");
    WelcomeLine1();
    WelcomeLine7("What language would like the course to be taught in?");
    System.out.println();
    WelcomeLine5(25, "Choose an option: ");
    String courseType = INPUT.nextLine();
    while ((!courseType.equalsIgnoreCase("Python") && !courseType.equalsIgnoreCase("JavaScript"))) {
        WelcomeLine7("We currently do not support that language...");
        WelcomeLine7("Choose another language (we recommend JavaScript or Python)\n");
        WelcomeLine5(25, "Choose an option: ");
        courseType = INPUT.nextLine();
    }
    if (courseType.equalsIgnoreCase("Python"))
      WelcomeLine7("GREAT, you wanna teach Python?");
    else if (courseType.equalsIgnoreCase("JavaScript"))
      WelcomeLine7("JavaScript it is");

    WelcomeLine7("What diffculty like the course to be?");
    System.out.println();
    WelcomeLine7("1.) Beginner");
    WelcomeLine7("2.) Intermediate");
    WelcomeLine7("3.) Expert");
    WelcomeLine5(31, "Choose an option: ");
    int difficulty = INPUT.nextInt();
    while (!(difficulty == 1 || difficulty == 2 || difficulty == 3)) {
      WelcomeLine5(25, "Enter 1,2, or 3:");
      difficulty = INPUT.nextInt();
    }
    String difficultyString = (difficulty == 1) ? "BEGINNER" : ((difficulty == 2) ? "INTERMEDIATE" : "EXPERT");
    Difficulty courseDifficulty = Difficulty.valueOf(difficultyString);
    WelcomeLine7("We are going to provide you with our default modules for said course and difficulty, if you want you can add more!");
    

    // What info to make a course? Difficulty,
  }

  public static Course getAccessCourse(Student user) {
    ArrayList<Course> student_courses = DataLoader.getCourses();
    if (student_courses != null) {
      int num = 1;
      WelcomeLine7("What courses would you like to access?");
      for (Course course : student_courses) {
        WelcomeLine7(num + ".) " + course.getTitle());
        num++;
      }
      WelcomeLine5(30 ,"Choose an option: ");
      try {
        num = INPUT.nextInt();
        INPUT.nextLine();
        System.out.println("\n\n\n\n\n");
        return student_courses.get(num-1);
      } catch (Exception e) {
        INPUT.nextLine();
        System.out.println("\n\n\n\n\n");
        WelcomeLine7("You entered an invalid choice. Press Enter or to Continue");
        INPUT.nextLine();
        System.out.println("\n\n\n\n\n");
        return getAccessCourse((Student) user);
      }
    } else {
      WelcomeLine7("You have no courses!");
      return null;
    }
  }
  
  public static boolean AccessCourse(Course course) {
    int final_score = 0, final_correct = 0;
    WelcomeLine1();
    ArrayList<Module> modules = course.getModules();
    if (modules != null) {
      for (Module module : modules) {
        WelcomeLine1();
        System.out.println(module.getTitle());
        for (Slide slide : module.getSlides()) {
          System.out.println(slide);
          System.out.println();
        }
        if (module.getQuiz() != null &&
            module.getQuiz().getQuestions().size() != 0) {
          boolean takequiz = true;
          while(takequiz) {
            WelcomeLine5(14 ,"Would you like to take a Quiz? (Enter Yes or No): ");
            String choice = INPUT.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
              int size = 0, score = 0, correct = 0;
              for (Question question : module.getQuiz().getQuestions()) {
                size += 1;
                WelcomeLine7(question.getQuestionContent());
                System.out.println();
                char num = 'A';
                for (String answer_choice : question.getAnswerChoices()) {
                  System.out.println(num + ".) " + answer_choice);
                  num++;
                }
                System.out.println("\nWhat is your answer?");
                String answer = INPUT.nextLine();
                if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
                  System.out.println("Correct!");
                  correct += 1;
                } else {
                  System.out.println("Incorrect!");
                }
                score += 1;
                if (size != module.getQuiz().getQuestions().size()) {
                  WelcomeLine7("Press Enter to continue to the next question");
                  INPUT.nextLine();
                } else {
                  WelcomeLine7("You have finished this module's quiz!");
                  WelcomeLine7("You scored " + correct + " out of " + score + " points!");
                  final_score += score;
                  final_correct += correct;
                  score = 0;
                  correct = 0;
                }
              }
              takequiz = false;
            } else if (choice.equalsIgnoreCase("no")) {
              WelcomeLine7("Moving on...");
              takequiz = false;
            } else {
              WelcomeLine7("You entered an invalid choice. Press Enter to Continue");
              INPUT.nextLine();
            }
          }
        }
        WelcomeLine7("Press Enter to continue");
        INPUT.nextLine();
      }
      WelcomeLine7("You have finished the course!");
      WelcomeLine7("You scored " + final_correct + " out of " + final_score + " points!");
      return true;
    } else {
      return false;
    }
  }


  private static void Quit() {
    for (int i = 0; i < 32; i++)
      System.out.print(" ");
    System.out.println("Quitting the LMS...");
    System.exit(0);
  }
}