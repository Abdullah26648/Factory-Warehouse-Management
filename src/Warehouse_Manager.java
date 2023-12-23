import java.util.*;

public class Warehouse_Manager extends Warehouse_Management implements Manager{
    public void managerMenu() {
    int choice;
    do {
        System.out.println(Warehouse_Management.B + "\n🛠️ Manager Menu 🛠️");
        System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⚜️");
        System.out.println(Warehouse_Management.G + "1️⃣ 🗃️ Create Product");
        System.out.println(Warehouse_Management.G + "2️⃣ 📥 Enter Product");
        System.out.println(Warehouse_Management.R + "3️⃣ 📤 Exit Product");
        System.out.println(Warehouse_Management.B + "4️⃣ 🔄 Place Product to Shelf");
        System.out.println(Warehouse_Management.B + "5️⃣ 🔄 Update Product");
        System.out.println(Warehouse_Management.R + "6️⃣ 🗑️ Delete Product");
        System.out.println(Warehouse_Management.B + "7️⃣ 📋 List Products");
        System.out.println(Warehouse_Management.R + "0️⃣ 🚪 Exit");
        System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⚜️");

        System.out.print(Warehouse_Management.Y + "✒️ Enter your choice: ");
        choice = Input_Exception_Handler.getIntInput("");
        switch (choice) {
            case 1:
                createProduct();
                break;
            case 2:
                enterProduct();
                break;
            case 3:
                exitProduct();
                break;
            case 4:
                placeProduct();
                break;
            case 5:
                updateProduct();
                break;
            case 6:
                deleteProduct();
                break;
            case 7:
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
    public void createProduct() {
        super.createProduct();
    }

    @Override
    public void enterProduct() {
        super.enterProduct();
    }

    @Override
    public void exitProduct() {
        super.exitProduct();
    }

    @Override
    public void placeProduct() {
        super.placeProduct();
    }

    @Override
    public void updateProduct() {
        super.updateProduct();
    }

    @Override
    public void deleteProduct() {
        super.deleteProduct();
    }

    public void listProducts(){
        super.listProducts();
    }

}
