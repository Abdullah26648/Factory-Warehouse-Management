import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input_Exception_Handler {
    public static Scanner scanner = new Scanner(System.in);
    public static final String R = "\u001B[31m"; // Red
    public static final String Y = "\u001B[33m"; // Yellow

    public static int getIntInput(String prompt) {

        while (true) {
            try {
                int input = scanner.nextInt();
                if (input < 0) {
                    System.out.println(R + "‼️ Invalid input. Please enter a non-negative value. ‼️");
                } else {
                    return input;
                }
            } catch (InputMismatchException e) {
                System.out.println(R + "‼️ Invalid input type. Please enter a valid numeric value ‼️");
                System.out.print(Y + "✒️ Please enter a valid numeric value: ");
                scanner.nextLine(); /*💰💲💰*/
            }
        }
    }

    public static String getNonBlankStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println(R + "‼️ Input cannot be blank. ‼️");
                System.out.print(Y + "✒️ Please enter a non-blank value: ");
            }
        }
    }
}
