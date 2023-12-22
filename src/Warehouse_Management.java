import java.util.*;

public class Warehouse_Management {
    public static final Map<Integer, List<Warehouse_Product>> productsMap = new HashMap<>();
    public Scanner scanner = new Scanner(System.in);
    private static final String MANAGER_PASSWORD = "12345";
    public static final String W = "\u001B[37m"; // White
    public static final String R = "\u001B[31m"; // Red
    public static final String G = "\u001B[32m"; // Green
    public static final String Y = "\u001B[33m"; // Yellow
    public static final String B = "\u001B[34m"; // Blue

    public void createProduct() {
        System.out.println(Y + "✒️ Enter product details:");
        System.out.print(W + "💾 ID: ");
        int id = Input_Exception_Handler.getIntInput("");
        if (isProductIdExists(id)) {
            System.out.println(R + "‼️ Product with ID " + id + " already exists. Please enter a different ID.");
            return;
        }
        System.out.print(W + "📕 Name: ");
        String name = Input_Exception_Handler.getNonBlankStringInput("");
        System.out.print(W + "📗 Producer: ");
        String producer = Input_Exception_Handler.getNonBlankStringInput("");
        System.out.print(W + "🗃️ Quantity: ");
        int quantity = Input_Exception_Handler.getIntInput("");
        System.out.print(W + "📢 Unit: ");
        String unit = Input_Exception_Handler.getNonBlankStringInput("");
        System.out.print(W + "📃 Shelf: ");
        String shelf = Input_Exception_Handler.getNonBlankStringInput("");

        Warehouse_Product warehouseProduct = new Warehouse_Product(id, name, producer, quantity, unit, shelf);
        productsMap.computeIfAbsent(id, k -> new ArrayList<>()).add(warehouseProduct);
        System.out.println(G + "✔️ Product created successfully.");
    }

    public void enterProduct() {
        System.out.print(Y + "✒️ Enter product ID: ");
        int id = Input_Exception_Handler.getIntInput("");

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);

        if (isProductIdExists(id) && warehouseProductList != null && !warehouseProductList.isEmpty()) {

            System.out.print(Y + "✒️ Enter quantity to enter: ");
            int quantityToAdd = Input_Exception_Handler.getIntInput("");

            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                int currentQuantity = warehouseProduct.getQuantity();
                warehouseProduct.setQuantity(currentQuantity + quantityToAdd);
            }
            System.out.println(G + "✔️ Product quantity updated successfully.");

            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                System.out.println(G + "🗃️ Updated Product: " + warehouseProduct);
            }
        } else {
            int answer;
            do {
                System.out.println(R + "‼️ Product with ID " + id + " not found.");
                System.out.println(R + "❓ Would you like to create product ❓");
                System.out.println(W + "1️⃣ for Yes");
                System.out.println(W + "2️⃣ for No");
                System.out.print(Y + "✒️ Enter your choice: ");

                answer = Input_Exception_Handler.getIntInput("");

                if (answer == 1) {
                    createProduct();
                } else if (answer == 2) {
                    System.out.println(B + "🚪 Returning to the Manager Menu...");
                    Loading();
                } else {
                    System.out.println(R + "‼️ Please enter 1 or 2. Try again.");
                }
            } while (answer != 1 && answer != 2);
        }
    }

    public void exitProduct() {
        System.out.print(Y + "✒️ Enter product ID to exit: ");
        int id = Input_Exception_Handler.getIntInput("");

        if (!isProductIdExists(id)) {
            System.out.println(R + "‼️ Product with ID " + id + " not exists.");
            return;
        }

        System.out.print(Y + "✒️ Enter quantity to exit: ");
        int exitQuantity = Input_Exception_Handler.getIntInput("");

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);
        if (warehouseProductList != null && !warehouseProductList.isEmpty()) {
            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                int remainingQuantity = warehouseProduct.getQuantity() - exitQuantity;
                if (remainingQuantity > 0) {
                    warehouseProduct.setQuantity(remainingQuantity);
                    System.out.println(G + "✔️ Product exited successfully.");
                    System.out.println(W + "🗃️ Updated Product: " + warehouseProduct);
                } else if (remainingQuantity == 0) {
                    int answer;
                    do {
                        System.out.println(R + "‼️ Product with ID " + id + " quantity reached 0.");
                        System.out.println(R + "❓ Would you like to delete product ❓");
                        System.out.println(W + "1️⃣ for Yes");
                        System.out.println(W + "2️⃣ for No");
                        System.out.print(Y + "✒️ Enter your choice: ");

                        answer = Input_Exception_Handler.getIntInput("");

                        if (answer == 1) {
                            deleteProduct();
                        } else if (answer == 2) {
                            System.out.println(B + "🚪 Returning to the Manager Menu...");
                            Loading();
                        } else {
                            System.out.println(R + "‼️ Please enter 1 or 2. Try again.");
                        }
                    } while (answer != 1 && answer != 2);

                } else {
                    System.out.println(R + "‼️ Exit quantity for product with ID " + id + " exceeds available quantity.");
                    System.out.println(W + "🗃️ Available Quantity: " + warehouseProduct.getQuantity());
                }
            }
        } else {
            System.out.println(R + "‼️ Product with ID " + id + " not found.");
        }
    }

    public void buyProduct() {
        System.out.print(Y + "✒️ Enter product ID to buy: ");
        int id = Input_Exception_Handler.getIntInput("");

        if (!isProductIdExists(id)) {
            System.out.println(R + "‼️ Product with ID " + id + " not exists.");
            return;
        }

        System.out.print(Y + "✒️ Enter quantity to buy: ");
        int exitQuantity = Input_Exception_Handler.getIntInput("");

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);
        if (warehouseProductList != null && !warehouseProductList.isEmpty()) {
            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                int remainingQuantity = warehouseProduct.getQuantity() - exitQuantity;
                if (remainingQuantity >= 0) {
                    warehouseProduct.setQuantity(remainingQuantity);
                    System.out.println(G + "🛒✔️ Product purchase successful.");
                } else {
                    System.out.println(R + "‼️ Exit quantity for product with ID " + id + " exceeds available quantity.");
                    System.out.println(W + "🗃️ Available Quantity: " + warehouseProduct.getQuantity());
                }
            }
        } else {
            System.out.println(R + "‼️ Product with ID " + id + " not found.");
        }
    }

    public void updateProduct() {
        System.out.print(Y + "✒️ Enter product ID to update: ");
        int id = Input_Exception_Handler.getIntInput("");

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);

        if (warehouseProductList != null && !warehouseProductList.isEmpty()) {
            System.out.println(Y + "✒️ Enter updated product details:");

            System.out.print(W + "📕 Name: ");
            String name = Input_Exception_Handler.getNonBlankStringInput("");

            System.out.print(W + "📗 Producer: ");
            String producer = Input_Exception_Handler.getNonBlankStringInput("");

            System.out.print(W + "🗃️ Quantity: ");
            int quantity = Input_Exception_Handler.getIntInput("");

            System.out.print(W + "📢 Unit: ");
            String unit = Input_Exception_Handler.getNonBlankStringInput("");

            System.out.print(W + "📃 Shelf: ");
            String shelf = Input_Exception_Handler.getNonBlankStringInput("");

            Warehouse_Product updatedProduct = new Warehouse_Product(id, name, producer, quantity, unit, shelf);

            for (int i = 0; i < warehouseProductList.size(); i++) {
                Warehouse_Product currentProduct = warehouseProductList.get(i);

                if (currentProduct.getId() == id) {
                    warehouseProductList.set(i, updatedProduct);
                    System.out.println(G + "✔️ Product updated successfully.");

                    System.out.println(W + "🗃️ Updated Product: " + updatedProduct);
                    return;
                }
            }
        } else {
            System.out.println(R + "‼️ Product with ID " + id + " not found.");
            System.out.println(B + "🚪 Returning to the Manager Menu...");
            Loading();
        }
    }

    public void deleteProduct() {
        System.out.print(Y + "✒️ Enter product ID to delete: ");
        int id = Input_Exception_Handler.getIntInput("");

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);

        if (warehouseProductList != null && !warehouseProductList.isEmpty()) {
            productsMap.remove(id);
            System.out.println(G + "✔️ Product deleted successfully.");
        } else {
            System.out.println(R + "‼️ Product with ID " + id + " not found.");
            System.out.println(B + "🚪 Returning to the Manager Menu...");
            Loading();
        }
    }

    public void listProducts() {
        System.out.println(W + "🗃️ List of Products:");

        productsMap.values().stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(Warehouse_Product::getId))
                .forEach(System.out::println);
    }

    public void initializeProduct(int id, String name, String producer, int quantity, String unit, String shelf) {
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

    public void Loading() {
        for (int i = 0; i < 30; i++) {
            System.out.print("💲");
            try {
                Thread.sleep(50);
            } catch (InterruptedException t) {
                /*💰💲💲💲💲💲💲💲💲💲💲💲💲💲💲💲💲💲💲💰*/
            }
        }
        System.out.println(); /*💰💲💰*/
    }

    public boolean isProductIdExists(int productId) {
        return productsMap.containsKey(productId);
    }

    public void authorizationMenu() {
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
                CustomerMenu();
                break;
            case 2:
                ManagerMenu();
                break;
            case 0:
                System.out.println(G + "👋 Exiting the system. Goodbye! 👋");
                System.exit(0);
            default:
                System.out.println(R + "‼️ Invalid choice. Please enter a valid option.");
                Loading();
        }
    }

    public void ManagerMenu() {
        System.out.print(Y + "✒️ Enter manager password: ");
        String enteredPassword = scanner.nextLine();

        if (enteredPassword.equals(MANAGER_PASSWORD)) {
            System.out.println(G + "✔️ Successfully entered manager mode.");
            Loading();
            int choice;
            do {
                System.out.println(B + "\n🛠️ Manager Menu 🛠️");
                System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⚜️");
                System.out.println(G + "1️⃣ 🗃️ Create Product");
                System.out.println(G + "2️⃣ 📥 Enter Product");
                System.out.println(R + "3️⃣ 📤 Exit Product");
                System.out.println(B + "4️⃣ 🔄 Update Product");
                System.out.println(R + "5️⃣ 🗑️ Delete Product");
                System.out.println(B + "6️⃣ 📋 List Products");
                System.out.println(R + "0️⃣ 🚪 Exit");
                System.out.println("⚜️⭐⭐⭐⭐⭐⭐⭐⭐⚜️");

                System.out.print(Y + "✒️ Enter your choice: ");
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
                        updateProduct();
                        break;
                    case 5:
                        deleteProduct();
                        break;
                    case 6:
                        listProducts();
                        break;
                    case 0:
                        System.out.println(B + "🚪 Returning to the Authorization Menu...");
                        Loading();
                        authorizationMenu();
                        break;
                    default:
                        System.out.println(R + "‼️ Invalid choice. Please enter a valid option.");
                }

            } while (choice != 0);
        } else {
            System.out.println(R + "‼️ Incorrect password. Access denied.");
            Loading();
            authorizationMenu();
        }
    }

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
                    authorizationMenu();
                    break;
                default:
                    System.out.println(R + "‼️ Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);
    }
}
