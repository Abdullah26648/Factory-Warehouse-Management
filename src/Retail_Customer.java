import java.util.*;

public class Retail_Customer extends Warehouse_Management implements Customer {
    public void CustomerMenu() {
        int choice;
        do {
            System.out.println(G + "\n🛍️ Customer Menu 🛍️");
            System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⚜️");
            System.out.println(G + "1️⃣ 🛒 Buy Product");
            System.out.println(B + "2️⃣ 📋 List Products");
            System.out.println(R + "0️⃣ 🚪 Exit");
            System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⚜️");

            System.out.print(Y + "✒️ Enter your choice: ");
            choice = Input_Exception_Handler.getIntInput("");
            switch (choice) {
                case 1:
                    buyProduct();
                    break;
                case 2:
                    listProducts();
                    break;
                case 0:
                    System.out.println(B + "🚪 Returning to the Authorization Menu...");
                    Loading();
                    Authorization_Menu.startAuthorization();
                    break;
                default:
                    System.out.println(R + "‼️ Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);
    }

    @Override
    public void buyProduct() {
        super.buyProduct();
    }
    @Override
    public void listProducts() {
        super.listProducts();
    }

}
