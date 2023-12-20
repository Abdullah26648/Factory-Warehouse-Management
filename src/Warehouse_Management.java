import java.util.*;

public class Warehouse_Management {
    public static final Map<Integer, List<Warehouse_Product>> productsMap = new HashMap<>();
    public Scanner scanner = new Scanner(System.in);
    private static final String MANAGER_PASSWORD = "12345";

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
        int id = InputExceptionHandler.getIntInput("✒️ Enter product ID: ");
        System.out.print("✒️ Enter quantity to add: ");
        int quantityToAdd = InputExceptionHandler.getIntInput("");

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);
        if (warehouseProductList != null && !warehouseProductList.isEmpty()) {
            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                int currentQuantity = warehouseProduct.getQuantity();
                warehouseProduct.setQuantity(currentQuantity + quantityToAdd);
            }
            System.out.println("✔️ Product quantity updated successfully.");

            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                System.out.println("🗃️ Updated Product: " + warehouseProduct);
            }
        } else {
            System.out.println("‼️ Product with ID " + id + " not found.");
            System.out.println("✒️ Would you like to enter a new product ?");
            System.out.println("1️⃣ for Yes");
            System.out.println("2️⃣ for No");
            int answer = InputExceptionHandler.getIntInput("");
            if (answer == 1){
                enterProduct();
            } else {
                System.out.println("🚪 Returning to the Manager Menu...");
                Returning();
            }
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
                    System.out.println("‼️ Exit quantity for product with ID " + id + " exceeds available quantity.");
                    System.out.println(productsMap.get(id));
                }
            }
        } else {
            System.out.println("‼️ Product with ID " + id + " not found.");
        }
    }

    private void initializeProduct(int id, String name, String producer, int quantity, String unit, String shelf) {
        Warehouse_Product product = new Warehouse_Product(id, name, producer, quantity, unit, shelf);
        productsMap.computeIfAbsent(product.getId(), k -> new ArrayList<>()).add(product);
    }

    public void initProduct() {
        initializeProduct(1000, "Vücut Sabunu", "HacıSakir", 100, "Karton", "Temizlik");
        initializeProduct(1001, "El Sabunu", "HacıSakir", 50, "Karton", "Temizlik");
        initializeProduct(1002, "Yüz Sabunu", "HacıSakir", 200, "Karton", "Temizlik");
        initializeProduct(1003, "Sac Şampuanı", "HacıSakir", 150, "Karton", "Temizlik");
        initializeProduct(1004, "Çimento", "Medcem", 250, "Cuval", "Yapı");
        initializeProduct(1005, "Alçı", "Onat", 190, "Cuval", "Yapı");
        initializeProduct(1006, "Kireç", "Nur", 350, "Cuval", "Yapı");
        initializeProduct(1007, "Elma", "Isparta", 230, "Kasa", "Gıda");
        initializeProduct(1008, "Portakal", "Mersin", 430, "Kasa", "Gıda");
        initializeProduct(1009, "Üzüm", "Manisa", 105, "Kasa", "Gıda");
        initializeProduct(1010, "Muz", "Anamur", 105, "Kasa", "Gıda");
    }
    public void Returning() {

        for (int i = 0; i < 30; i++) {
            System.out.print("\uD83D\uDCB2");
            try {
                Thread.sleep(100);
            } catch (InterruptedException t) {
            }
        }
        System.out.println();
        authorizationMenu();
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
                Returning();
        }
    }
    public void CustomerMenu() {
        int choice;
        do {
            System.out.println("\n🛍️ Customer Menu 🛍️");
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
                    Returning();
                    break;
                default:
                    System.out.println("‼️ Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);
    }

    public void ManagerMenu() {
        System.out.print("✒️ Enter manager password: ");
        String enteredPassword = scanner.nextLine();
        System.out.println("✔️ Successfully entered manager mode.");

        if (enteredPassword.equals(MANAGER_PASSWORD)) {
        int choice;
        do {
            System.out.println("\n🛠️ Manager Menu 🛠️");
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
                    Returning();
                    break;
                default:
                    System.out.println("‼️ Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);
        } else {
            System.out.println("‼️ Incorrect password. Access denied.");
            Returning();
        }
    }
}
