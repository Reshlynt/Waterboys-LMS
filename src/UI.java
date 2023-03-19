import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class UI {
    public static final String FIVESTAR = "*****", FOURSTAR = "****", SPACESTAR = " *";
    public static final Scanner INPUT = new Scanner(System.in);
    public static final LMSSystem LMS = new LMSSystem();
    public static void main(String[] args) {
        run();
    }
    public static void run() {
        boolean quit = false;
        //Part 1 - Logging in or Signing up
        while (!quit) {
            User user = null;
            switch(Welcome()) {
                case 1:
                    user = Login();
                    break;
                case 2:
                    user = SignUp();
                    break;
                default:
                    System.out.println("\n\n\n\n\n");
                    for (int i = 0; i < 13; i++)
                        System.out.print(" ");
                    System.out.println("You entered an invalid choice. Press Enter to Continue");
                    INPUT.nextLine();
                    run();
                    break;
            }
            System.out.print("\n\n\n\n\n\n");
            if (user != null) {
                DataWriter.saveUsers();
                quit = true;
            } else {
                System.out.println("Press Enter to Continue");
                INPUT.nextLine();
                continue;
            }
            if (user.getType().equalsIgnoreCase("teacher")) {
                TeacherMenu(user);
            } else if (user.getType().equalsIgnoreCase("student")) {
                StudentMenu(user);
            } 
        }
    }
    public static int Welcome() {
        String choice = null;
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
            System.out.println("You entered an invalid choice. Press Enter or to Continue");
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
        for (int i = 0; i <  iterator; i++)
            System.out.print(item2);
        System.out.println(item1);
    }
    private static void WelcomeLine3(String item1, String item2, int iterator) {
        System.out.print(item1);
        for (int i = 0; i <  iterator; i++)
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
        for(int i = 0; i < iterator; i++)
            System.out.print(' ');
        System.out.print(item1);
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
        String first_name = "", last_name = "", email = "", username = "", password = "", confirm = "", birthday = "", job = "";
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
        String username = "", password= "";
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

    public static void TeacherMenu(User teacher) {
        String welcomeLine = "Welcome, " + teacher.getUsername();
        int linehelper = (71 - welcomeLine.length())/2;
        if (welcomeLine.length() % 2 == 0) {
            linehelper -= 1;
        }
        WelcomeLine1();
        WelcomeLine4(FOURSTAR, welcomeLine, linehelper);
        WelcomeLine1();
        System.out.println();
        WelcomeLine5(25, "1.) Add Student to Course\n");
        WelcomeLine5(25, "2.) Create a Course\n");
        WelcomeLine5(25, "3.) Remove a Student from a course\n");
        WelcomeLine5(25, "4.) View Profile\n");
        WelcomeLine5(25, "5.) View Courses\n");
        WelcomeLine5(25, "9.) Exit LMS\n");


    }
    public static void StudentMenu(User student) {
        String welcomeLine = "Welcome, " + student.getUsername();
        int linehelper = (71 - welcomeLine.length())/2;
        if (welcomeLine.length() % 2 == 0) {
            linehelper -= 1;
        }
        WelcomeLine1();
        WelcomeLine4(FOURSTAR, welcomeLine, linehelper);
        WelcomeLine1();
        System.out.println();
        WelcomeLine5(25, "1.) Register for Course\n");
        WelcomeLine5(25, "2.) Access your Coruses\n");
        WelcomeLine5(25, "3.) Access Certifications\n");
        WelcomeLine5(25, "4.) View Profile\n");
        WelcomeLine5(25, "9.) Exit LMS\n");
    }
}