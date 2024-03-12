import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
    private static Scanner sc = new Scanner(System.in);

    public static int getIntInput(String prompt) {
        int number;
        while (true) {
            try {
                System.out.println(prompt);
                number = sc.nextInt();
                sc.nextLine(); // consume the newline left-over
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // discard the invalid input
            }
        }
        return number;
    }

    public static String getStringInput(String prompt) {
        String str;
        boolean numCheck;
        while (true) {
            System.out.println(prompt);
            str = sc.nextLine();
            numCheck = isAlphabetic(str);
            if (!numCheck) {
                System.out.println("Invalid input. Please enter a string of alphabets only");
                continue;
            } else {
                return str;
            }
        }
    }

    public static boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z ]+");
    }

    public static String getContactInfo(String prompt) {
        while(true){
        System.out.println(prompt);
        String str = sc.nextLine();
        boolean isValid = true;
        for (int i = 0; i < str.length(); i++) {
            if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                isValid = false;
                break;
            }
        }
        if(isValid){
            return str;
        }
        else {
                System.out.println("Invalid input. Please enter a valid contact number");
                continue;
            }
        }
    }
}