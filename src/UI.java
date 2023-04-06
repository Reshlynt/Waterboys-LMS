package src;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class UI {
  public static final String FIVESTAR = "*****", FOURSTAR = "****", SPACESTAR = " *";
  public static final Scanner INPUT = new Scanner(System.in);
  public static final LMSSystem LMS = new LMSSystem();
  public static CourseList courseList = CourseList.getInstance();
  public static UserList userList = UserList.getInstance();

  public static void main(String[] args) {
    clearScreen();
    User user = promptUser();
    if (user != null)
      presentMenu(user);
  }

  public static User promptUser() {
    boolean quit = false;
    // Part 1 - Logging in or Signing up
    User user = null;
    while (!quit) {
      switch (Welcome()) {
        case 1:
          user = Login();
          break;
        case 2:
          user = SignUp();
          UserList userList = UserList.getInstance();
          userList.addUser(user);
          DataWriter.saveUsers();
          break;
        case 9:
          Quit();
        default:
          clearScreen();
          WelcomeLine7("You entered an invalid choice. Press Enter to Continue");
          INPUT.nextLine();
          break;
      }
      clearScreen();
      if (user != null) {
        quit = true;
        return user;
      }
    }
    return null;
  }

  public static void presentMenu(User user) {
    clearScreen();
    String userType = user.getType();
    boolean quit = true;
    while (quit) {
      if (userType.equalsIgnoreCase("teacher")) {
        switch (TeacherMenu((Teacher) user)) {
          case 1:
            addToCourse((Teacher) user);
            // add student to course
            break;
          case 2:
            CreateCourse((Teacher) user);
            break;
          case 3:
            removeStudentFromCourse((Teacher) user);
            // remove student from course
            break;
          case 4:
            ViewTeacherProfile((Teacher) user);
            break;
          case 5:
            // view courses
            viewCourses((Teacher) user);
            break;
          case 9:
            Quit();
            break;
        }
      } else if (userType.equalsIgnoreCase("student")) {
        switch (StudentMenu((Student) user)) {
          case 1:
            Course course = getCourses((Student) user);
            AccessCourse(course, user);
            break;
          case 2:
            viewCertificates((Student) user);
            break;
          case 3:
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
    WelcomeLine5(35, "9.) Quit LMS\n\n");
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

  public static void enterToContinue() {
    WelcomeLine5(28, "Press Enter to continue");
    INPUT.nextLine();
    clearScreen();
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static boolean checkIfE(String input) {
    if (input.equalsIgnoreCase("e")) {
      clearScreen();
      return true;
    }
    return false;
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
      enterToContinue();
      return SignUp();
    }
    WelcomeLine5(25, "Are you are Student or Teacher: ");
    job = INPUT.nextLine();
    clearScreen();
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
    clearScreen();
    return LMS.Login(username, password);
  }

  public static int TeacherMenu(User teacher) {
    WelcomeLine1();
    WelcomeLine6("Welcome, " + teacher.getUsername());
    WelcomeLine1();
    System.out.println();
    WelcomeLine5(10, "1.) Add Student to Course\n");
    WelcomeLine5(10, "2.) Create a Course\n");
    WelcomeLine5(10, "3.) Remove a Student from a course\n");
    WelcomeLine5(10, "4.) View Profile\n");
    WelcomeLine5(10, "5.) View Courses\n");
    WelcomeLine5(10, "9.) Exit LMS\n\n");
    WelcomeLine5(15, "Choose an option: ");
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
      return TeacherMenu(teacher);
    }
  }

  public static int StudentMenu(Student student) {
    WelcomeLine1();
    WelcomeLine6("Welcome, " + student.getUsername());
    WelcomeLine1();
    System.out.println();
    WelcomeLine5(10, "1.) Access your Courses\n");
    WelcomeLine5(10, "2.) Access Certifications\n");
    WelcomeLine5(10, "3.) View Profile\n");
    WelcomeLine5(10, "9.) Exit LMS\n\n");
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

  public static void addToCourse(Teacher teacher) {
    clearScreen();
    WelcomeLine1();
    WelcomeLine6("Add Student to Course:");
    WelcomeLine1();
    System.out.println("\n");
    ArrayList<Course> courses = courseList.getAllCourses();
    ArrayList<Course> teacherCourses = new ArrayList<Course>();
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getAuthor().getID().equals(teacher.getID())) {
        teacherCourses.add(courses.get(i));
      }
    }
    if (teacherCourses.size() == 0) {
      WelcomeLine7("You have no courses. Press Enter to Continue");
      INPUT.nextLine();
      clearScreen();
      return;
    }
    WelcomeLine5(10, "Select a course to add a student to: \n");
    for (int i = 0; i < teacherCourses.size(); i++) {
      WelcomeLine5(10, i + 1 + ".) " + teacherCourses.get(i).getTitle() + "\n");
    }
    int num = INPUT.nextInt();
    INPUT.nextLine();
    clearScreen();
    boolean addMore = true;
    while (addMore) {
      WelcomeLine1();
      WelcomeLine6("Add Student to Course");
      WelcomeLine1();
      System.out.println();
      WelcomeLine5(10, "Enter the username of the student you would like to add: ");
      String username = INPUT.nextLine();
      Student student = (Student) userList.getUser(username);
      clearScreen();
      if (student == null) {
        WelcomeLine7("The student you entered does not exist. Press Enter to Continue");
        INPUT.nextLine();
        clearScreen();
        addToCourse(teacher);
      } else {
        teacher.addToCourse(student, teacherCourses.get(num - 1));
        WelcomeLine7("Student added to course. Press Enter to Continue");
        INPUT.nextLine();
        clearScreen();
        addMore = false;
      }
    }
  }

  private static void ViewTeacherProfile(Teacher user) {
    clearScreen();
    String header = (user.getUsername() + "\'s Profile");
    WelcomeLine1();
    WelcomeLine6(header);
    WelcomeLine1();
    System.out.println("\n");
    WelcomeLine5(10, "Name: " + user.getFirstName() + " " + user.getLastName() + "\n");
    WelcomeLine5(10, "Email: " + user.getEmail() + "\n");
    WelcomeLine5(10, "Date of Birth: " + user.getDOB() + "\n");
    if (courseList.getAllCourses() != null) {
      ArrayList<Course> readCourses = courseList.getAllCourses();
      System.out.println("\n");
      WelcomeLine5(10, "Courses Created:\n");
      for (int i = 0; i < readCourses.size(); i++) {
        Course course = readCourses.get(i);
        Teacher teacher = course.getAuthor();
        if (user.getUsername().equals(teacher.getUsername())) {
          WelcomeLine5(20, "\t" + course.getTitle());
        }
      }
    } else {
      WelcomeLine7("There are no courses currently");
    }
    enterToContinue();
  }

  public static void removeStudentFromCourse(Teacher teacher) {
    clearScreen();
    WelcomeLine1();
    WelcomeLine6("Remove Student From Course:");
    WelcomeLine1();
    System.out.println("\n");
    ArrayList<Course> courses = courseList.getAllCourses();
    ArrayList<Course> teacherCourses = new ArrayList<Course>();
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getAuthor().getID().equals(teacher.getID())) {
        teacherCourses.add(courses.get(i));
      }
    }
    if (teacherCourses.size() == 0) {
      WelcomeLine7("You have no courses. Press Enter to Continue");
      INPUT.nextLine();
      clearScreen();
      return;
    }
    WelcomeLine5(10, "Select a course to remove a student from:\n\n");
    for (int i = 0; i < teacherCourses.size(); i++) {
      WelcomeLine5(10, (i + 1) + ".) " + teacherCourses.get(i).getTitle());
      System.out.println("\n");
    }
    int num = INPUT.nextInt();
    INPUT.nextLine();
    clearScreen();
    Course course = teacherCourses.get(num - 1);
    boolean addMore = true;
    while (addMore) {
      WelcomeLine1();
      WelcomeLine6("Add Student to Course");
      WelcomeLine1();
      System.out.println();
      WelcomeLine5(10, "Enter the username of the student you would like to add: ");
      String username = INPUT.nextLine();
      Student student = (Student) userList.getUser(username);
      clearScreen();
      if (student == null) {
        WelcomeLine7("The student you entered does not exist. Press Enter to Continue");
        INPUT.nextLine();
        clearScreen();
        return;
      } else {
        if (course.removeFromCourse(student))
          WelcomeLine7("Student removed from course. Press Enter to Continue");
        else
          WelcomeLine7("Error removing student. Press Enter to Continue");
        INPUT.nextLine();
        clearScreen();
        addMore = false;
      }
    }
  }

  public static void viewCourses(Teacher teacher) {
    clearScreen();
    WelcomeLine1();
    WelcomeLine6("Courses:");
    WelcomeLine1();
    System.out.println("\n");
    ArrayList<Course> courses = courseList.getAllCourses();
    ArrayList<Course> teacherCourses = new ArrayList<Course>();
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getAuthor().getID().equals(teacher.getID())) {
        teacherCourses.add(courses.get(i));
      }
    }
    if (!(teacherCourses.size() == 0)) {
      int num = 1;
      WelcomeLine7("Enter 'E' to edit a course, or 'V' if you only want to view.");
      boolean edit = (INPUT.nextLine().equalsIgnoreCase("e") ? true : false);
      clearScreen();
      if (!edit) {
        AccessCourse(teacherCourses.get(num - 1), teacher);
        return;
      }
      WelcomeLine7("What courses would you like to access?\n");
      for (Course course : courses) {
        WelcomeLine7(num + ".) " + course.getTitle());
        num++;
      }
      System.out.println();
      WelcomeLine5(30, "Choose an option: ");
      try {
        num = INPUT.nextInt();
        INPUT.nextLine();
        System.out.println("\n\n\n\n\n");
        editCourse(teacherCourses.get(num - 1), teacher);
        return;
      } catch (Exception e) {
        INPUT.nextLine();
        System.out.println("\n\n\n\n\n");
        WelcomeLine7("You entered an invalid choice. Press Enter to Continue");
        enterToContinue();
        return;
      }
    } else {
      WelcomeLine7("You have no courses!");
      return;
    }
  }

  public static void editCourse(Course course, Teacher teacher) {
    // Proceed through course;
    // At any point if the teacher types 'e', they will be prompted to edit the
    // course
    if (course == null) {
      enterToContinue();
      return;
    }
    ArrayList<Module> modules = course.getModules();
    if (modules != null) {
      clearScreen();
      WelcomeLine1();
      WelcomeLine6(course.getTitle());
      WelcomeLine1();
      System.out.println();
      int num = 1;
      for (Module module : modules) {
        WelcomeLine5(10, (num + ".) " + module.getTitle() + "\n"));
        num++;
      }
      System.out.println("\n");
      WelcomeLine7("Choose an option, or enter 'E' to add modules: ");
      try {
        String input = INPUT.nextLine();
        int inputVal;
        Module module;
        if (input.equalsIgnoreCase("e")) {
          WelcomeLine7("At what index would you like to insert it? (1-" + (modules.size() + 1) + ");");
          num = INPUT.nextInt();
          INPUT.nextLine();
          clearScreen();
          modules.add(num - 1, createModule());
          clearScreen();
          return;
        } else {
          inputVal = Integer.parseInt(input);
          System.out.println("Input: " + inputVal);
        }
        if (inputVal > 0 && inputVal <= modules.size()) {
          // module = modules.get(num - 1);
          module = modules.get(inputVal - 1);
        } else {
          clearScreen();
          WelcomeLine7("You entered an invalid choice. Press Enter to Continue");
          System.out.println("input: " + inputVal);
          enterToContinue();
          return;
        }
        clearScreen();
        int slideIndex = 0;
        for (Slide slide : module.getSlides()) {
          WelcomeLine1();
          System.out.println(slide + "\n\n\n");
          WelcomeLine7("Press Enter to Continue or 'I' to insert a slide after this one.");
          input = INPUT.nextLine();
          if (input.equalsIgnoreCase("i")) {
            clearScreen();
            module.addSlide(slideIndex + 1, createSlide());
            WelcomeLine7("Slide added. Press Enter to Continue");
            return;
          }
          slideIndex++;
        }
        WelcomeLine7("You have finished this module!");
        boolean option = true;
        int value = 0;
        while (option) {
          WelcomeLine7("What would you like to do?\n");
          WelcomeLine5(10, "1.) View Comments\n");
          WelcomeLine5(10, "2.) View Quiz\n");
          WelcomeLine5(10, "3.) Go Back to Main Menu\n\n");
          WelcomeLine5(30, "Choose an option: ");
          try {
            value = INPUT.nextInt();
            INPUT.nextLine();
            clearScreen();
            option = false;
          } catch (Exception e) {
            INPUT.nextLine();
            clearScreen();
            System.out.println("You entered an invalid choice. Press Enter to Continue");
            INPUT.nextLine();
            clearScreen();
          }

          if (value == 1) {
            if (module.getComments() != null && module.getComments().size() != 0) {
              module.printComments();
            } else {
              clearScreen();
              WelcomeLine7("There are no comments for this module! Would you like to add one?\n");
              WelcomeLine7("Enter \"Yes\" or \"No\"\n");
              String choice = INPUT.nextLine();
              if (choice.equalsIgnoreCase("yes")) {
                WelcomeLine7("Tell everyone what you would like to say! (Press Enter when done)\n");
                module.addComment(INPUT.nextLine(), teacher);
              } else if (choice.equalsIgnoreCase("no")) {
                WelcomeLine7("This comment section is looking awfully quiet...");
              } else {
                WelcomeLine7("You entered an invalid choice, moving on...");
              }
            }
          } else if (value == 2) {
            if (module.getQuiz() != null &&
                module.getQuiz().getQuestions().size() != 0) {
              editQuiz(module.getQuiz());
            } else {
              WelcomeLine7("There are currently no quizzes for this module.");
              return;
            }
          } else if (value == 3) {
            return;
          } else {
            System.out.println("You entered an invalid choice. Press Enter to Continue");
            INPUT.nextLine();
            clearScreen();
          }
        }
        enterToContinue();
        return;
      } catch (Exception e) {
        INPUT.nextLine();
        clearScreen();
        WelcomeLine7("You entered an invalid choice. Press Enter to Continue");
        enterToContinue();
        return;
      }
    } else {
      return;
    }

  }

  private static void ViewStudentProfile(Student user) {
    clearScreen();
    String header = (user.getUsername() + "\'s Profile");
    WelcomeLine1();
    WelcomeLine6(header);
    WelcomeLine1();
    System.out.println("\n");
    WelcomeLine5(10, "Name: " + user.getFirstName() + " " + user.getLastName() + "\n");
    WelcomeLine5(10, "Email: " + user.getEmail() + "\n");
    WelcomeLine5(10, "Date of Birth: " + user.getDOB() + "\n");
    System.out.println("\n");
    WelcomeLine5(10, "Courses Enrolled:\n");
    ArrayList<Course> readCourses = courseList.getAllCourses();
    for (int i = 0; i < readCourses.size(); i++) {
      Course course = readCourses.get(i);
      ArrayList<Student> students = course.getStudents();
      for (Student student : students) {
        if (student.equals(user.getID())) {
          WelcomeLine5(20, "\t- " + course.getTitle());
        }
      }
    }
    System.out.println("\n\n");
    enterToContinue();
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
    String title = INPUT.nextLine();
    clearScreen();
    System.out.println("What is the description of the course?");
    String description = INPUT.nextLine();
    clearScreen();
    System.out.println("What is the difficulty of the course? Beginner or Intermediate?");
    String difficultyString = INPUT.nextLine();
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
      String response = INPUT.nextLine();
      response.toLowerCase();
      clearScreen();
      if (response.equals("javascript")) {
        courseType = CourseType.JAVASCRIPT;
      } else if (response.equals("python")) {
        courseType = CourseType.PYTHON;
      } else {
        System.out.println("Please enter JavaScript or Python");
      }
    } while (!INPUT.nextLine().equals("JavaScript") && !INPUT.nextLine().equals("Python"));
    System.out.println("Moving on now to the modules;");
    ArrayList<Module> lessons = createModules();
    // Create exam
    System.out.println("Create the exam for this course: ");
    Assessment exam = makeAssessment();
    exam.setType(Type.EXAM);
    Course newCourse = new Course(teacher, title, difficulty, description, exam, courseType, lessons, populateClass());
    return newCourse;
  }

  public static ArrayList<Student> populateClass() {
    System.out.println("Do you want to add any students? (Y/N)");
    String addStudents = INPUT.nextLine();
    addStudents.toUpperCase();
    if (addStudents.equals("Y")) {
      System.out.println("Enter the usernames of the students you want to add, pressing enter after each:");
      ArrayList<Student> students = new ArrayList<Student>();
      boolean addMoreStudents = true;
      while (addMoreStudents) {
        String studentUsername = INPUT.nextLine();
        UserList userList = UserList.getInstance();
        if (!userList.foundUser(studentUsername)) {
          System.out.println("That user does not exist. Please try again.");
          continue;
        } else {
          students.add((Student) userList.getUser(studentUsername));
          System.out.println("Do you want to add another student? (Y/N)");
          String addStudent = INPUT.nextLine();
          addStudent.toUpperCase();
          if (addStudent.equals("N")) {
            addMoreStudents = false;
          }
        }
      }
    }
    return null;
  }

  public static TextSlide createSlide() {
    WelcomeLine7("What do you want to title the slide?");
    String slideTitle = INPUT.nextLine();
    WelcomeLine7("What do you want to print on the slide?");
    String slideContents = INPUT.nextLine();
    clearScreen();
    return new TextSlide(slideTitle, slideContents);
  }

  public static Module createModule() {
    boolean addAnotherSlide = true;
    WelcomeLine7("What is the title of the module?");
    String moduleTitle = INPUT.nextLine();
    clearScreen();
    ArrayList<TextSlide> slides = new ArrayList<TextSlide>();
    // Create slide loop
    while (addAnotherSlide) {
      slides.add(createSlide());
      WelcomeLine7("Do you want to add another slide? (Y/N)");
      String addSlide = INPUT.nextLine();
      clearScreen();
      if (addSlide.equalsIgnoreCase("N")) {
        addAnotherSlide = false;
      }
    }
    Module aModule = new Module(moduleTitle, slides);
    System.out.println("Create the quiz for this module: ");
    Assessment moduleQuiz = makeAssessment();
    moduleQuiz.setType(Type.QUIZ);
    aModule.setLessonQuiz(moduleQuiz);
    return aModule;
  }

  public static ArrayList<Module> createModules() {
    clearScreen();
    // Lesson creator:
    ArrayList<Module> lessons = new ArrayList<Module>();
    boolean addAnotherModule = true;
    while (addAnotherModule) {
      lessons.add(createModule());
      System.out.println("Add another module? (Y/N)");
      String addModule = INPUT.nextLine();
      if (addModule.equalsIgnoreCase("N")) {
        addAnotherModule = false;
      }
    }
    return lessons;
  }

  public static Assessment makeAssessment() {
    System.out.println("What is the title of the assessment?");
    String title = INPUT.nextLine();
    clearScreen();
    ArrayList<Question> questions = new ArrayList<Question>();
    boolean addMoreQuestions = true;
    while (addMoreQuestions) {
      questions.add(makeQuestion());
      System.out.println("Do you want to add another question? (Y/N)");
      String addQuestion = INPUT.nextLine();
      if (addQuestion.equalsIgnoreCase("N")) {
        addMoreQuestions = false;
      }
    }
    return new Assessment(title, questions);
  }

  public static Question makeQuestion() {
    System.out.println("What is the question?");
    String question = INPUT.nextLine();
    System.out.println("Enter 4 answer choices, pressing enter after each:");
    ArrayList<String> answers = new ArrayList<String>();
    for (int i = 0; i < 4; i++) {
      System.out.print("\t");
      answers.add(INPUT.nextLine());
    }
    System.out.println("What is the correct answer? a, b, c, or d?");
    String correctAnswer = INPUT.nextLine();
    correctAnswer.toLowerCase();
    clearScreen();
    return new Question(question, answers, correctAnswer);
  }

  private static void CreateCourse(Teacher teacher) {
    boolean valid = false;
    while (!valid) {
      valid = true;
      clearScreen();
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
          valid = false;
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
    // String difficultyString = (difficulty == 1) ? "BEGINNER" : ((difficulty == 2)
    // ? "INTERMEDIATE" : "EXPERT");
    // Difficulty courseDifficulty = Difficulty.valueOf(difficultyString);
    WelcomeLine7(
        "We are going to provide you with our default modules for said course and difficulty, if you want you can add more!");

    // What info to make a course? Difficulty,
  }

  public static Course getCourses(Student user) {
    ArrayList<Course> allCourses = CourseList.getInstance().getCourseList();
    ArrayList<Course> studentCourses = new ArrayList<Course>();
    for (Course course : allCourses) {
      for (Student student : course.getStudents()) {
        if (user.getID().equals(student.getID())) {
          studentCourses.add(course);
        }
      }
    }
    if (!(studentCourses.size() == 0)) {
      int num = 1;
      WelcomeLine7("What courses would you like to access?\n");
      for (Course course : studentCourses) {
        if (user.completedCourse(course)) {
          System.out.println(ConsoleColor.GREEN);
          WelcomeLine7(num + ".) " + course.getTitle());
          System.out.println(ConsoleColor.RESET);
        } else
          WelcomeLine7(num + ".) " + course.getTitle());
        num++;
      }
      System.out.println();
      WelcomeLine5(30, "Choose an option: ");
      try {
        num = INPUT.nextInt();
        INPUT.nextLine();
        System.out.println("\n\n\n\n\n");
        return studentCourses.get(num - 1);
      } catch (Exception e) {
        INPUT.nextLine();
        System.out.println("\n\n\n\n\n");
        WelcomeLine7("You entered an invalid choice. Press Enter or to Continue");
        INPUT.nextLine();
        System.out.println("\n\n\n\n\n");
        return getCourses((Student) user);
      }
    } else {
      WelcomeLine7("You have no courses!");
      return null;
    }
  }

  public static void AccessModule(Module module) {
    for (Slide slide : module.getSlides()) {
      WelcomeLine1();
      System.out.println(slide + "\n");
      enterToContinue();
    }
  }

  public static void AccessCourse(Course course, User user) {
    if (course == null) {
      enterToContinue();
      return;
    }
    ArrayList<Module> modules = course.getModules();
    if (modules != null) {
      clearScreen();
      WelcomeLine1();
      WelcomeLine6(course.getTitle());
      WelcomeLine1();
      System.out.println();
      int num = 1;
      String grade = "";

      for (int i = 0; i < modules.size(); i++) {
        boolean studentCompletedModule = user.getType().equalsIgnoreCase("student") && modules.get(i).hasQuiz() && i > 0
            && (i <= ((Student) user).getCourseGradeList(course).size());
        if (studentCompletedModule) {
          grade = ConsoleColor.CYAN + ((Student) user).getCourseGradeList(course).get(i - 1).toString();
          WelcomeLine5(10, ConsoleColor.GREEN
              + (num + ".) " + modules.get(i).getTitle() + "\t" + ConsoleColor.RESET + grade + "\n"
                  + ConsoleColor.RESET));
        } else
          WelcomeLine5(10, (num + ".) " + modules.get(i).getTitle() + "\t" + grade + "\n"));
        num++;
        grade = "";
      }
      System.out.println();
      WelcomeLine5(31, "Choose an option: ");
      try {
        num = INPUT.nextInt();
        INPUT.nextLine();
        clearScreen();
        Module module = modules.get(num - 1);
        for (Slide slide : module.getSlides()) {
          WelcomeLine1();
          System.out.println(slide + "\n");
          enterToContinue();
        }
        WelcomeLine7("You have finished this module!");
        boolean option = true;
        int value = 0;
        while (option) {
          WelcomeLine7("What would you like to do?\n");
          WelcomeLine5(10, "1.) View Comments\n");
          WelcomeLine5(10, "2.) View Module Quiz\n");
          WelcomeLine5(10, "3.) View Other Modules\n");
          WelcomeLine5(10, "4.) Print Module to a File\n");
          WelcomeLine5(10, "5.) Print Certificate to a File\n");
          WelcomeLine5(10, "6.) Go Back to Main Menu\n\n");
          WelcomeLine5(30, "Choose an option: ");
          try {
            value = INPUT.nextInt();
            INPUT.nextLine();
            clearScreen();
            option = false;
          } catch (Exception e) {
            INPUT.nextLine();
            clearScreen();
            System.out.println("You entered an invalid choice. Press Enter to Continue");
            INPUT.nextLine();
            clearScreen();
          }

          if (value == 1) {
            if (module.getComments() != null && module.getComments().size() != 0) {
              getCommentsAndReplies(module.getComments(), (Student) user, 1);
              WelcomeLine7("Would you like to add a comment?\n");
              WelcomeLine7("Enter \"Yes\" or \"No\"\n");
              String choice = INPUT.nextLine();
              if (choice.equalsIgnoreCase("yes")) {
                clearScreen();
                WelcomeLine7("Tell everyone what you would like to say! (Press Enter when done)\n");
                Comment new_comment = new Comment(INPUT.nextLine(), user);
                WelcomeLine7("Is this a post or reply? (Enter \"Reply\" or \"Post\")");
                String item = INPUT.nextLine();
                clearScreen();
                if (item.equalsIgnoreCase("post")) {
                  module.addComment(INPUT.nextLine(), user);
                  CourseList.saveCourses();
                } else if (item.equalsIgnoreCase("reply")) {
                  postReplies(module.getComments(), (Student) user, 1, new_comment);
                  CourseList.saveCourses();
                } else {
                  WelcomeLine7("You entered an invalid choice, moving on...");
                }
                CourseList.saveCourses();
              } else if (choice.equalsIgnoreCase("no")) {
                WelcomeLine7("Moving on...");
              } else {
                WelcomeLine7("You entered an invalid choice, moving on...");
              }
            } else if (user.ofAge() == false) {
              WelcomeLine7("Comments cannot be viewed by users under 13");
            } else {
              clearScreen();
              WelcomeLine7("There are no comments for this module! Would you like to add one?\n");
              WelcomeLine7("Enter \"Yes\" or \"No\"\n");
              String choice = INPUT.nextLine();
              if (choice.equalsIgnoreCase("yes")) {
                WelcomeLine7("Tell everyone what you would like to say! (Press Enter when done)\n");
                module.addComment(INPUT.nextLine(), user);
                CourseList.saveCourses();
              } else if (choice.equalsIgnoreCase("no")) {
                WelcomeLine7("This comment section is looking awfully quiet...");
              } else {
                WelcomeLine7("You entered an invalid choice, moving on...");
              }
            }
          } else if (value == 2) {
            if (user.getType().equals("teacher")) {
              editQuiz(module.getQuiz());
              return;
            }
            if (module.getQuiz() != null &&
                module.getQuiz().getQuestions().size() != 0) {
              takeQuiz(course, module, (Student) user);
            } else {
              WelcomeLine7("There are currently no quizzes for this module.");
            }
          } else if (value == 3) {
            AccessCourse(course, user);
          } else if (value == 4) {
            DataWriter.WriteModule(module);
          } else if (value == 5) {
            DataWriter.WriteCertificate((Student) user);
          } else if (value == 6) {
            return;
          } else {
            System.out.println("You entered an invalid choice. Press Enter to Continue");
            INPUT.nextLine();
            clearScreen();
          }
        }
        enterToContinue();
        return;
      } catch (Exception e) {
        INPUT.nextLine();
        clearScreen();
        WelcomeLine7("You entered an invalid choice. Press Enter to Continue");
        INPUT.nextLine();
        clearScreen();
        AccessCourse(course, user);
      }
    } else {
      return;
    }
  }

  private static void editQuiz(Assessment quiz) {
    for (int i = 0; i < quiz.getQuestions().size(); i++) {
      clearScreen();
      System.out.println((i + 1) + ": \n" + quiz.getQuestions().get(i).toString() + "\n");
      WelcomeLine7("Enter 'I' to insert a question\n");
      String input = INPUT.nextLine();
      if (input.equalsIgnoreCase("I")) {
        clearScreen();
        quiz.addQuestion(i, makeQuestion());
      }
    }
    WelcomeLine7("End of quiz; press enter to continue");
    INPUT.nextLine();
  }

  // goes through comments array, prints out comments and replies
  private static void getCommentsAndReplies(ArrayList<Comment> comments, Student user, int count) {
    if (comments != null && comments.size() != 0 && count < 4) {
      for (Comment comment : comments) {
        for (int i = 0; i < count; i++)
          System.out.print('\t');
        System.out.println(comment.getPostingUser().getUsername() + ": \"" + comment.getPost() + "\"\n");
        if (comment.getReplies() != null && comment.getReplies().size() != 0) {
          getCommentsAndReplies(comment.getReplies(), user, count + 1);
        }
      }
    }
  }

  private static void postReplies(ArrayList<Comment> comments, Student user, int count, Comment new_comment) {
    if (comments != null && comments.size() != 0 && count < 3) {
      for (Comment comment : comments) {
        for (int i = 0; i < count; i++)
          System.out.print('\t');
        System.out.println(comment.getPostingUser().getUsername());
        for (int i = 0; i <= count; i++)
          System.out.print('\t');
        System.out.println("\"" + comment.getPost() + "\"\n");
        System.out.println();
        WelcomeLine7("Would you like to post here? (Enter \"Yes\" or \"No\")\n");
        String choice = INPUT.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
          comment.replyToComment(new_comment.getPost(), new_comment.getPostingUser());
          return;
        } else if (choice.equalsIgnoreCase("no")) {
          WelcomeLine7("Moving on...");
          clearScreen();
        } else {
          WelcomeLine7("You entered an invalid choice, moving on...");
        }
        if (comment.getReplies() != null && comment.getReplies().size() != 0) {
          postReplies(comment.getReplies(), user, count + 1, new_comment);
        }
      }
    }
  }

  public static void takeQuiz(Course course, Module module, Student student) {
    int size = 0, numQuestions = module.getQuiz().getQuestions().size(), numCorrect = 0;
    for (Question question : module.getQuiz().getQuestions()) {
      size += 1;
      WelcomeLine7(question.getQuestionContent());
      System.out.println();
      char ansChoice = 'A';
      for (String answer_choice : question.getAnswerChoices()) {
        System.out.println(ansChoice + ".) " + answer_choice);
        ansChoice++;
      }
      System.out.print("\nWhat is your answer? ");
      String answer = INPUT.nextLine();
      if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
        System.out.println("Correct!");
        numCorrect += 1;
      } else {
        System.out.println("Incorrect!");
        System.out.println("Correct Answer: " + question.getCorrectAnswer());
      }

      if (size != module.getQuiz().getQuestions().size()) {
        WelcomeLine7("Press Enter to continue to the next question");
        INPUT.nextLine();
        clearScreen();
      } else {
        WelcomeLine7("You have finished this module's quiz!");
        WelcomeLine7("You scored " + numCorrect + " out of " + numQuestions + " points!");
        // add student's grade to their courseProgress for this course
        // double score = (double) correct / (double) numQuestions;
        student.updateCourseProgress(course, numCorrect, numQuestions);
        System.out
            .println(ConsoleColor.RED + "Current Course Grade: " + student.getCourseGrade(course) + ConsoleColor.RESET);
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

  private static Date parseDate(String dob) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
    try {
      return dateFormat.parse(dob);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}