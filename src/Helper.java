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
                sc.nextLine(); // discard the invalid input
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

    public static String getTitleInput(String prompt) {
        String string;
        boolean numCheck;
        while (true) {
            System.out.println(prompt);
            string = sc.nextLine();
            numCheck = isValid(string);
            if (!numCheck) {
                System.out.println("Invalid input. Please enter a string of alphabets and numbers only");
                continue;
            } else {
                return string;
            }
        }
    }

    public static boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z ]+");
    }

    public static boolean isValid(String str) {
        // Check if the string is not solely composed of spaces
        if (str.trim().isEmpty()) {
            return false;
        }

        // Check if the string contains only letters, numbers, and spaces
        return str.matches("[a-zA-Z0-9 ]+");
    }

    public static String getContactInfo(String prompt) {
        while (true) {
            System.out.println(prompt);
            String str = sc.nextLine();
            boolean isValidNumber = true;
            for (int i = 0; i < str.length(); i++) {
                if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                    isValidNumber = false;
                    break;
                }
            }
            if (isValidNumber) {
                return str;
            } else {
                System.out.println("Invalid input. Please enter a valid contact number");
                continue;
            }
        }
    }
}