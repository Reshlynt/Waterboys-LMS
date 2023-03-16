package src;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
public class UI {
    public static final String FIVESTAR = "*****", FOURSTAR = "****", SPACESTAR = " *";
    public static final Scanner INPUT = new Scanner(System.in);
    public static final LMSSystem LMS = new LMSSystem();
    public static void main(String[] args) {
        //System.out.println(Welcome());
        Welcome();
    }
    public static void Welcome() {
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
        choice = INPUT.nextLine();
        System.out.println();
        try {
            int value = Integer.parseInt(choice);
            System.out.println("\n\n\n\n\n");
            switch(value) {
                case 1:
                    Login();
                    break;
                case 2:
                    SignUp();
                    break;
                default:
                    System.out.println("You entered an invalid choice. Press Enter or to Continue");
                    INPUT.nextLine();
                    Welcome();
            }
        } catch (Exception e) {
            //System.out.println(e);
            for (int i = 0; i < 13; i++)
                System.out.print(" ");
            System.out.println("You entered an invalid choice. Press Enter or to Continue");
            INPUT.nextLine();
            System.out.println("\n\n\n\n\n");
            Welcome();
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
          e.printStackTrace();
        }
        return date;
      }
    
    public static void SignUp() {
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
        LMS.SignUp(first_name, last_name, username, email, password, parseDate(birthday), job);
        System.out.println("\n\n\n\n\n");
    }

    public static void Login() {
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
    }


}