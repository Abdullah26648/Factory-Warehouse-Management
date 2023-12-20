import java.util.InputMismatchException;
import java.util.Scanner;

public class InputExceptionHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("‼️ Invalid input type. Please enter a valid numeric value ‼️");
                System.out.print("✒️ Enter your choice: ");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}