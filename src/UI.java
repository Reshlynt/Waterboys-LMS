package src;
import java.util.*;
public class UI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(Welcome(input));
    }
    public static String Welcome(Scanner input) {
        String FiveStar = "*****", FourStar = "****";
        String SpaceStar = " *";
        String choice = null;
        //Line 1
        WelcomeLine1();
        //Line 2
        WelcomeLine2(FiveStar, SpaceStar, 34);
        //Line 3
        WelcomeLine3(FiveStar, " ", 69);
        //Line 4
        WelcomeLine4(FourStar, "Welcome to", 30);
        //Line 5
        WelcomeLine4(FiveStar, "the Waterboys\'", 27);
        //Line 6
        WelcomeLine4(FourStar, "Learning Management System", 22);
        //Line 7
        WelcomeLine3(FiveStar, " ", 69);
        //Line 8
        WelcomeLine4(FourStar, "\"Teaching Others About Coding Languages\"", 15);
        //Line 9
        WelcomeLine3(FiveStar, " ", 69);
        //Line 10
        WelcomeLine2(FiveStar, SpaceStar, 34);
        //Line 11
        WelcomeLine1();
        //Lines 12 - 15
        System.out.print("\n\n\n");
        //Line 16
        WelcomeLine5(35, "1.) Login\n");
        //Line 17
        WelcomeLine5(35, "2.) Sign Up\n");
        //Lines 18 - 20
        System.out.print("\n\n");
        //Line 21
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
            return Welcome(input);
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
}