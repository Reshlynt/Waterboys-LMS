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
    clearScreen();
    User user = run();
    if (user != null)
      run2(user);
  }

  public static User run() {
    boolean quit = false;
    // Part 1 - Logging in or Signing up
    while (!quit) {
      User user = null;
      switch (Welcome()) {
        case 1:
          while (user == null) {
            clearScreen();
            user = Login();
          }
          break;
        case 2:
          user = SignUp();
          UserList.getInstance().addUser(user);
          DataWriter.saveUsers();
          break;
        case 9:
          Quit();
        default:
          clearScreen();
          WelcomeLine7("You entered an invalid choice. Press Enter to Continue");
          INPUT.nextLine();
          run();
          break;
      }
      clearScreen();
      if (user != null) {
        quit = true;
      } else {

      }
      return user;
    }
    return null;
  }

  public static void run2(User user) {
    clearScreen();
    boolean quit = true;
    while (quit) {
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
            boolean quiz = AccessCourse(course, (Student) user);
            if (quiz)
              System.out.print(" ");
            else
              System.out.println("There are currently no assessments you can take for this course...");
            break;
          case 3:
              viewCertificates((Student) user);
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
      clearScreen();
      return value;
    } catch (Exception e) {
      INPUT.nextLine();
      clearScreen();
      System.out.println("You entered an invalid choice. Press Enter to Continue");
      INPUT.nextLine();
      clearScreen();
      return Welcome();
    }
  }

  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
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
    for (int i = 0; i < line / 2; i++)
      System.out.print(" ");
    System.out.println(item1);
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
    if (!password.equals(confirm)) {
      WelcomeLine7("Your password and confirm password do not match!");
      WelcomeLine7("Press Enter to Continue");
      INPUT.nextLine();
      clearScreen();
      return SignUp();
    }
    WelcomeLine5(25, "Are you are Student or Teacher: ");
    job = INPUT.nextLine();
    clearScreen();
    return LMS.SignUp(first_name, last_name, username, email, password, DataLoader.parseDate(birthday), job);
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
    clearScreen();
    return LMS.Login(username, password);
  }

  public static int TeacherMenu(User teacher) {
    WelcomeLine1();
    WelcomeLine6("Welcome, " + teacher.getUsername());
    WelcomeLine1();
    System.out.println();
    WelcomeLine5(15, "1.) Add Student to Course\n");
    WelcomeLine5(15, "2.) Create a Course\n");
    WelcomeLine5(15, "3.) Remove a Student from a course\n");
    WelcomeLine5(15, "4.) View Profile\n");
    WelcomeLine5(15, "5.) View Courses\n");
    WelcomeLine5(15, "9.) Exit LMS\n\n");
    WelcomeLine5(15, "Choose an option: ");
    try {
      int value = INPUT.nextInt();
      INPUT.nextLine();
      clearScreen();
      return value;
    } catch (Exception e) {
      INPUT.nextLine();
      clearScreen();
      System.out.println("You entered an invalid choice. Press Enter or to Continue");
      INPUT.nextLine();
      clearScreen();
      return TeacherMenu(teacher);
    }
  }

  public static int StudentMenu(Student student) {
    WelcomeLine1();
    WelcomeLine6("Welcome, " + student.getUsername());
    WelcomeLine1();
    System.out.println();
    WelcomeLine5(15, "1.) Register for Course\n");
    WelcomeLine5(15, "2.) Access your Courses\n");
    WelcomeLine5(15, "3.) Access Certifications\n");
    WelcomeLine5(15, "4.) View Profile\n");
    WelcomeLine5(15, "9.) Exit LMS\n\n");
    WelcomeLine5(31, "Choose an option: ");
    try {
      int value = INPUT.nextInt();
      INPUT.nextLine();
      clearScreen();
      return value;
    } catch (Exception e) {
      INPUT.nextLine();
      clearScreen();
      WelcomeLine5(11, "You entered an invalid choice. Press Enter or to Continue");
      INPUT.nextLine();
      clearScreen();
      return StudentMenu(student);
    }
  }

  private static void ViewTeacherProfile(Teacher user) {
    clearScreen();
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
    clearScreen();
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
          WelcomeLine5(25, "\t- " + course.getTitle());
        }
      }
    }
    System.out.println("\n\n");
    WelcomeLine7("Press Enter to Continue");
    INPUT.nextLine();
    clearScreen();
  }

  public static void viewCertificates(Student user) {
    clearScreen();
    String header = (user.getUsername() + "\'s Certificates");
    WelcomeLine1();
    WelcomeLine6(header);
    WelcomeLine1();
    System.out.println("\n");
    ArrayList<Certificate> certificates = user.getCertificates();
    if (certificates.size() > 0) {
      for (int i = 0; i < certificates.size(); i++) {
        Certificate certificate = certificates.get(i);
        WelcomeLine5(25, "Certificate " + (i + 1) + ":\n");
        WelcomeLine5(25, "\tCourse: " + certificate.getCourse().getTitle() + "\n");
        WelcomeLine5(25, "\tDate: " + certificate.getDate() + "\n");
        WelcomeLine5(25, "\tGrade: " + certificate.getGrade() + "\n");
      }
    } else {
      WelcomeLine7("You have no certificates");
    }
    System.out.println("\n\n");
    WelcomeLine7("Press Enter to Continue");
    INPUT.nextLine();
    clearScreen();
  }

  public static Course createCourseFromScratch(Teacher teacher) {
    System.out.println("What is the title of the course?");
    String title = System.console().readLine();
    clearScreen();
    System.out.println("What is the description of the course?");
    String description = System.console().readLine();
    clearScreen();
    System.out.println("What is the difficulty of the course?");
    String difficultyString = System.console().readLine();
    clearScreen();
    Difficulty difficulty = null;
    difficultyString.toUpperCase();
    if (difficultyString == "BEGINNER") {
      difficulty = Difficulty.BEGINNER;
    } else {
      difficulty = Difficulty.INTERMEDIATE;
    }
    clearScreen();
    System.out.println("What is the course type? JavaScript or Python?");
    CourseType courseType = null;
    do {
      String response = System.console().readLine();
      response.toLowerCase();
      clearScreen();
      if (response.equals("javascript")) {
        courseType = CourseType.JAVASCRIPT;
      } else if (response.equals("python")) {
        courseType = CourseType.PYTHON;
      } else {
        System.out.println("Please enter JavaScript or Python");
      }
    } while (!System.console().readLine().equals("JavaScript") && !System.console().readLine().equals("Python"));

    // Lesson creator:
    ArrayList<Module> lessons = new ArrayList<Module>();
    boolean addAnotherModule = true;
    boolean addAnotherSlide = true;

    while (addAnotherModule) {
      System.out.println("What is the title of the module?");
      String moduleTitle = System.console().readLine();
      ArrayList<TextSlide> slides = new ArrayList<TextSlide>();
      // Create slide loop
      while (addAnotherSlide) {
        System.out.println("What do you want to title the slide?");
        String slideTitle = System.console().readLine();
        System.out.println("What do you want to print on the slide?");
        String slideContents = System.console().readLine();
        TextSlide tSlide = new TextSlide(slideTitle, slideContents);
        slides.add(tSlide);
        System.out.println("Do you want to add another slide? (Y/N)");
        String addSlide = System.console().readLine();
        addSlide.toUpperCase();
        if (addSlide.equals("N")) {
          addAnotherSlide = false;
        }
      }
      Module aModule = new Module(moduleTitle, slides);
      System.out.println("Create the quiz for this module: ");
      Assessment moduleQuiz = makeAssessment();
      moduleQuiz.setType(Type.QUIZ);
      aModule.setLessonQuiz(moduleQuiz);
      System.out.println("Add another module? (Y/N)");
      String addModule = System.console().readLine();
      addModule.toUpperCase();
      if (addModule.equals("N")) {
        addAnotherModule = false;
      }
    }
    // Create exam
    System.out.println("Create the exam for this course: ");
    Assessment exam = makeAssessment();
    exam.setType(Type.EXAM);
    System.out.println("Do you want to add any students? (Y/N)");
    String addStudents = System.console().readLine();
    addStudents.toUpperCase();
    if (addStudents.equals("Y")) {
      System.out.println("Enter the usernames of the students you want to add, pressing enter after each:");
      ArrayList<Student> students = new ArrayList<Student>();
      boolean addMoreStudents = true;
      while (addMoreStudents) {
        String studentUsername = System.console().readLine();
        UserList userList = UserList.getInstance();
        if (!userList.foundUser(studentUsername)) {
          System.out.println("That user does not exist. Please try again.");
          continue;
        } else {
          students.add((Student) userList.getUser(studentUsername));
          System.out.println("Do you want to add another student? (Y/N)");
          String addStudent = System.console().readLine();
          addStudent.toUpperCase();
          if (addStudent.equals("N")) {
            addMoreStudents = false;
          }
        }
      }
      Course newCourse = new Course(teacher, title, difficulty, description, exam, courseType, lessons, students);
      return newCourse;
    }

    return null;
  }

  public static Assessment makeAssessment() {
    System.out.println("What is the title of the assessment?");
    String title = System.console().readLine();
    System.out.println("What is the description of the assessment?");
    ArrayList<Question> questions = new ArrayList<Question>();
    boolean addMoreQuestions = true;
    while (addMoreQuestions) {
      System.out.println("What is the question?");
      String question = System.console().readLine();
      System.out.println("Enter 4 answer choices, pressing enter after each:");
      ArrayList<String> answers = new ArrayList<String>();
      for (int i = 0; i < 4; i++) {
        answers.add(System.console().readLine());
      }
      System.out.println("What is the correct answer? a, b, c, or d?");
      String correctAnswer = System.console().readLine();
      correctAnswer.toLowerCase();
      questions.add(new Question(question, answers, correctAnswer));
      System.out.println("Do you want to add another question? (Y/N)");
      String addQuestion = System.console().readLine();
      addQuestion.toUpperCase();
      if (addQuestion.equals("N")) {
        addMoreQuestions = false;
      }
    }
    return new Assessment(title, questions);
  }

  private static void CreateCourse(Teacher teacher) {
    boolean valid = false;
    while (!valid) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("Would you like to create a course from scratch or from a template?");
      System.out.println("1.) Create from scratch");
      System.out.println("2.) Create from template");
      switch (INPUT.nextLine()) {
        case "1":
          createCourseFromScratch(teacher);
          break;
        case "2":
          createCourseFromTemplate(teacher);
          break;
        default:
          System.out.println("Invalid input");
          break;
      }
    }
  }

  private static void createCourseFromTemplate(Teacher teacher) {
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
    WelcomeLine7(
        "We are going to provide you with our default modules for said course and difficulty, if you want you can add more!");

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
      WelcomeLine5(30, "Choose an option: ");
      try {
        num = INPUT.nextInt();
        INPUT.nextLine();
        System.out.println("\n\n\n\n\n");
        return student_courses.get(num - 1);
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

  public static boolean AccessCourse(Course course, Student student) {
    ArrayList<Module> modules = course.getModules();
    if (modules != null) {
      clearScreen();
      WelcomeLine1();
      WelcomeLine6(course.getTitle());
      WelcomeLine1();
      System.out.println("\n");
      int num = 1;
      for (Module module : modules) {
        WelcomeLine5(15, (num + ".) " + module.getTitle() + "\n"));
        num++;
      }
      WelcomeLine5(31, "Choose an option: ");
      try {
        num = INPUT.nextInt();
        INPUT.nextLine();
        clearScreen();
        Module module = modules.get(num - 1);
        for (Slide slide : module.getSlides()) {
          clearScreen();
          WelcomeLine1();
          System.out.println(slide);
          WelcomeLine5(28, "Press Enter to continue");
          INPUT.nextLine();
        }
        if (module.getQuiz() != null &&
            module.getQuiz().getQuestions().size() != 0) {
          takeQuiz(course, module, student);
        }
        clearScreen();
        return true;
      } catch (Exception e) {
        INPUT.nextLine();
        clearScreen();
        WelcomeLine7("You entered an invalid choice. Press Enter to Continue");
        INPUT.nextLine();
        clearScreen();
        return AccessCourse(course, student);
      }
    } else {
      return false;
    }
  }

  public static void takeQuiz(Course course, Module module, Student student) {
    boolean takequiz = true;
    while (takequiz) {
      WelcomeLine5(14, "Would you like to take a Quiz? (Enter Yes or No): ");
      String choice = INPUT.nextLine();
      if (choice.equalsIgnoreCase("yes")) {
        int size = 0, numQuestions = module.getQuiz().getQuestions().size(), correct = 0;
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
          if (size != module.getQuiz().getQuestions().size()) {
            WelcomeLine7("Press Enter to continue to the next question");
            INPUT.nextLine();
          } else {
            WelcomeLine7("You have finished this module's quiz!");
            WelcomeLine7("You scored " + correct + " out of " + numQuestions + " points!");
            // add student's grade to their courseProgress for this course
            double score = (double) correct / numQuestions;
            student.updateCourseProgress(course, score);
            WelcomeLine7("Press Enter to continue");

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

  private static void Quit() {
    for (int i = 0; i < 32; i++)
      System.out.print(" ");
    System.out.println("Quitting the LMS...");
    DataWriter.saveUsers();
    DataWriter.saveCourses();
    System.exit(0);
  }
}