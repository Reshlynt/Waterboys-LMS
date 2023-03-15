package src;
import java.util.*;
public class UI {
    public static void main(String[] args) {
        //System.out.println(Welcome());
        SignUp();
    }
    public static String Welcome() {
        Scanner input = new Scanner(System.in);
        String FiveStar = "*****", FourStar = "****";
        String SpaceStar = " *";
        String choice = null;
        WelcomeLine1();
        WelcomeLine2(FiveStar, SpaceStar, 34);
        WelcomeLine3(FiveStar, " ", 69);
        WelcomeLine4(FourStar, "Welcome to", 30);
        WelcomeLine4(FiveStar, "the Waterboys\'", 27);
        WelcomeLine4(FourStar, "Learning Management System", 22);
        WelcomeLine3(FiveStar, " ", 69);
        WelcomeLine4(FourStar, "\"Teaching Others About Coding Languages\"", 15);
        WelcomeLine3(FiveStar, " ", 69);
        WelcomeLine2(FiveStar, SpaceStar, 34);
        WelcomeLine1();
        System.out.print("\n\n\n");
        WelcomeLine5(35, "1.) Login\n");
        WelcomeLine5(35, "2.) Sign Up\n");
        System.out.print("\n\n");
        WelcomeLine5(32, "Choose an option: ");
        choice = input.nextLine();
        System.out.println();
        try {
            int value = Integer.parseInt(choice);
        } catch (Exception e) {
            for (int i = 0; i < 13; i++)
                System.out.print(" ");
            System.out.println("You entered an invalid choice. Press Enter to Continue");
            input.nextLine();
            System.out.println("\n\n\n\n\n");
            return Welcome();
        }
        return choice;
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
    
    public static void SignUp() {
        Scanner input = new Scanner(System.in);
        String FiveStar = "*****", FourStar = "****";
        String SpaceStar = " *";
        String first_name = "", last_name = "", email = "", username = "", password = "", confirm = "", birthday = "";
        WelcomeLine1();
        WelcomeLine4(FourStar, "New User Sign-Up", 27);
        WelcomeLine1();
        System.out.println();
        WelcomeLine5(25, "First Name: ");
        first_name = input.nextLine();
        WelcomeLine5(25, "Last Name: ");
        last_name = input.nextLine();
        WelcomeLine5(25, "Email: ");
        email = input.nextLine();
        WelcomeLine5(25, "Date of Birth (MMDDYYYY): ");
        birthday = input.nextLine();
        WelcomeLine5(25, "User Name: ");
        username = input.nextLine();
        WelcomeLine5(25, "Password: ");
        password = input.nextLine();
        WelcomeLine5(25, "Confirm Password: ");
        confirm = input.nextLine();
        User new_user = new User();
    }
}