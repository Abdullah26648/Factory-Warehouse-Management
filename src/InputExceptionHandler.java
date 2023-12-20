import java.util.InputMismatchException;
import java.util.Scanner;

public class InputExceptionHandler {

    public static int getIntInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("‼️ Invalid input type. Please enter a valid numeric value ‼️");
                System.out.print("✒️ Enter your choice: ");
                scanner.nextLine();
            }
        }
    }
}
