import java.util.*;

public class Warehouse_Manager {
    private static final Map<Integer, List<Warehouse_Product>> productsMap = new HashMap<>();
    public Scanner scanner = new Scanner(System.in);

    public void enterProduct() {
        System.out.println("✒️ Enter product details:");
        System.out.print("💾 ID: ");
        int id = InputExceptionHandler.getIntInput("");
        System.out.print("📕 Name: ");
        String name = scanner.nextLine();
        System.out.print("📗 Producer: ");
        String producer = scanner.nextLine();
        System.out.print("🗃️ Quantity: ");
        int quantity = InputExceptionHandler.getIntInput("");
        System.out.print("📢 Unit: ");
        String unit = scanner.nextLine();
        System.out.print("📃 Shelf: ");
        String shelf = scanner.nextLine();
        System.out.println("✔️ Product entered successfully.");

        Warehouse_Product warehouseProduct = new Warehouse_Product(id, name, producer, quantity, unit, shelf);
        productsMap.computeIfAbsent(id, k -> new ArrayList<>()).add(warehouseProduct);
    }

    public void addProduct() {
        System.out.print("✒️ Enter product ID: ");
        int id = InputExceptionHandler.getIntInput("");
        System.out.print("✒️ Enter quantity to add: ");
        int quantityToAdd = InputExceptionHandler.getIntInput("");

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);
        if (warehouseProductList != null && !warehouseProductList.isEmpty()) {
            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                int currentQuantity = warehouseProduct.getQuantity();
                warehouseProduct.setQuantity(currentQuantity + quantityToAdd);
            }
            System.out.println("✔️ Product quantity updated successfully.");
        } else {
            System.out.println("‼️ Product not found.");
        }
    }

    public void exitProduct() {
        System.out.print("✒️ Enter product ID to exit: ");
        int id = InputExceptionHandler.getIntInput("");
        System.out.print("✒️ Enter quantity to exit: ");
        int exitQuantity = InputExceptionHandler.getIntInput("");

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);
        if (warehouseProductList != null && !warehouseProductList.isEmpty()) {
            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                int remainingQuantity = warehouseProduct.getQuantity() - exitQuantity;
                if (remainingQuantity >= 0) {
                    warehouseProduct.setQuantity(remainingQuantity);
                    System.out.println("✔️ Product exited successfully.");
                } else {
                    System.out.println("‼️ Exit quantity exceeds available quantity.");
                }
            }
        } else {
            System.out.println("‼️ Product not found.");
        }
    }

    public void initProduct() {
        Warehouse_Product product1 = new Warehouse_Product(1000, "Vücut Sabunu", "HacıSakir", 100, "Karton", "Temizlik");
        Warehouse_Product product2 = new Warehouse_Product(1001, "El Sabunu", "HacıSakir", 50, "Karton", "Temizlik");
        Warehouse_Product product3 = new Warehouse_Product(1002, "Yüz Sabunu", "HacıSakir", 200, "Karton", "Temizlik");
        Warehouse_Product product4 = new Warehouse_Product(1003, "Sac Icin", "HacıSakir", 150, "Karton", "Temizlik");

        productsMap.computeIfAbsent(product1.getId(), k -> new ArrayList<>()).add(product1);
        productsMap.computeIfAbsent(product2.getId(), k -> new ArrayList<>()).add(product2);
        productsMap.computeIfAbsent(product3.getId(), k -> new ArrayList<>()).add(product3);
        productsMap.computeIfAbsent(product4.getId(), k -> new ArrayList<>()).add(product4);
    }

    public void listProducts() {
        System.out.println("List of Products:");

        productsMap.values().stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(Warehouse_Product::getId))
                .forEach(System.out::println);
    }

    public void authorizationMenu() {
        System.out.println("\n⚜️⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⚜️");
        System.out.println("🔐 Welcome to the Authorization Menu 🔐");
        System.out.println("🔐 Please choose your role 🕵️");
        System.out.println("1️⃣ 🛍️ Customer");
        System.out.println("2️⃣ 🛠️ Manager");
        System.out.println("0️⃣ 🚪 Exit");
        System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐⚜️");

        System.out.print("✒️ Enter your choice: ");
        int roleChoice = InputExceptionHandler.getIntInput("");

        switch (roleChoice) {
            case 1:
                CustomerMenu();
                break;
            case 2:
                ManagerMenu();
                break;
            case 0:
                System.out.println("👋 Exiting the system. Goodbye!");
                System.exit(0);
            default:
                System.out.println("‼️ Invalid choice. Please enter a valid option.");
                authorizationMenu(); // Repeat the authorization menu if the choice is invalid
        }
    }

    public void CustomerMenu() {
        int choice;
        do {
            System.out.println("\n🛍️ Welcome, Customer! 🛍️");
            System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⚜️");
            System.out.println("1️⃣ 📤 Exit Product");
            System.out.println("2️⃣ 📋 List Products");
            System.out.println("0️⃣ 🚪 Exit");
            System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⚜️");

            System.out.print("✒️ Enter your choice: ");
            choice = InputExceptionHandler.getIntInput("");
            switch (choice) {
                case 1:
                    exitProduct();
                    break;
                case 2:
                    listProducts();
                    break;
                case 0:
                    System.out.println("🚪 Returning to the Authorization Menu...");
                    authorizationMenu();
                    break;
                default:
                    System.out.println("‼️ Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);
    }

    public void ManagerMenu() {
        int choice;
        do {
            System.out.println("\n🛠️ Welcome, Manager! 🛠️");
            System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⚜️");
            System.out.println("1️⃣ 📥 Enter Product");
            System.out.println("2️⃣ 🗃️ Add Product");
            System.out.println("3️⃣ 📤 Exit Product");
            System.out.println("4️⃣ 📋 List Products");
            System.out.println("0️⃣ 🚪 Exit");
            System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⚜️");

            System.out.print("✒️ Enter your choice: ");
            choice = InputExceptionHandler.getIntInput("");
            switch (choice) {
                case 1:
                    enterProduct();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    exitProduct();
                    break;
                case 4:
                    listProducts();
                    break;
                case 0:
                    System.out.println("🚪 Returning to the Authorization Menu...");
                    authorizationMenu();
                    break;
                default:
                    System.out.println("‼️ Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);
    }

}