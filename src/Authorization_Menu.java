import java.util.Scanner;

public class Authorization_Menu extends Warehouse_Management {
    private static final String MANAGER_PASSWORD = "12345";

    public static void startAuthorization() {
        System.out.println("\n⚜️⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⚜️");
        System.out.println(Y + "🔐 Welcome to the Authorization Menu 🔐");
        System.out.println(W + "🔐 Please choose your role 🕵️");
        System.out.println(G + "1️⃣ 🛍️ Customer");
        System.out.println(B + "2️⃣ 🛠️ Manager");
        System.out.println(R + "0️⃣ 🚪 Exit");
        System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⚜️");

        System.out.print(Y + "✒️ Enter your choice: ");
        int roleChoice = Input_Exception_Handler.getIntInput("");

        switch (roleChoice) {
            case 1:
                Retail_Customer retailCustomer = new Retail_Customer();
                retailCustomer.CustomerMenu();
                break;
            case 2:
                Warehouse_Manager warehouseManager = new Warehouse_Manager();
                if (authorizeManager()) {
                    warehouseManager.managerMenu();
                } else {
                    System.out.println(R + "‼️ Incorrect password. Access denied.");
                    startAuthorization();
                }
                break;
            case 0:
                System.out.println(G + "👋 Exiting the system. Goodbye! 👋");
                Loading();
                System.exit(0);
            default:
                System.out.println(R + "‼️ Invalid choice. Please enter a valid option.");
                Loading();
                startAuthorization();
        }
    }

    private static boolean authorizeManager() {
        System.out.print(Y + "✒️ Enter manager password: ");
        String enteredPassword = new Scanner(System.in).nextLine();
        return enteredPassword.equals(MANAGER_PASSWORD);
    }
}
