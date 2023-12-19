import java.util.*;

public class Warehouse_Manager {
    private static final Map<Integer, List<Warehouse_Product>> productsMap = new HashMap<>();
    public Scanner scanner = new Scanner(System.in);


    public void enterProduct() {
        System.out.println("✒\uFE0F Enter product details:");
        System.out.print("\uD83D\uDCBE ID: ");
        int id = InputExceptionHandler.getIntInput("");
        System.out.print("\uD83D\uDCD5 Name: ");
        String name = scanner.nextLine();
        System.out.print("\uD83C\uDFD7\uFE0F Producer: ");
        String producer = scanner.nextLine();
        System.out.print("\uD83E\uDDF1 Quantity: ");
        int quantity = InputExceptionHandler.getIntInput("");
        System.out.print("\uD83D\uDEE2\uFE0F Unit: ");
        String unit = scanner.nextLine();
        System.out.println("✔\uFE0F Product entered successfully.");

        Warehouse_Product warehouseProduct = new Warehouse_Product(id, name, producer, quantity, unit, null);
        productsMap.computeIfAbsent(id, k -> new ArrayList<>()).add(warehouseProduct);
    }


    public void placeOnShelf() {
        System.out.print("✒\uFE0F Enter product ID to place on shelf: ");
        int id = InputExceptionHandler.getIntInput("");
        System.out.print("✒\uFE0F Enter shelf: ");
        String shelf = scanner.nextLine();

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);
        if (warehouseProductList != null && !warehouseProductList.isEmpty()) {
            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                warehouseProduct.setShelf(shelf);
            }
            System.out.println("✔\uFE0F Product placed on shelf successfully.");
        } else {
            System.out.println("‼\uFE0F Product not found.");
        }
    }


    public void exitProduct() {
        System.out.print("✒\uFE0F Enter product ID to exit: ");
        int id = InputExceptionHandler.getIntInput("");
        System.out.print("✒\uFE0F Enter quantity to exit: ");
        int exitQuantity = InputExceptionHandler.getIntInput("");

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);
        if (warehouseProductList != null && !warehouseProductList.isEmpty()) {
            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                int remainingQuantity = warehouseProduct.getQuantity() - exitQuantity;
                if (remainingQuantity >= 0) {
                    warehouseProduct.setQuantity(remainingQuantity);
                    System.out.println("✔\uFE0F Product exited successfully.");
                } else {
                        System.out.println("‼\uFE0F Exit quantity exceeds available quantity.");
                }
            }
        } else {
            System.out.println("‼\uFE0F Product not found.");
        }
    }
    public void initProduct() {

        Warehouse_Product product1 = new Warehouse_Product(1000, "Vücut Sabunu", "HacıSakir", 100, "Karton", null);
        Warehouse_Product product2 = new Warehouse_Product(1001, "El Sabunu", "HacıSakir", 50, "Karton", null);
        Warehouse_Product product3 = new Warehouse_Product(1002, "Yüz Sabunu", "HacıSakir", 200, "Karton", null);
        Warehouse_Product product4 = new Warehouse_Product(1003, "Sac Icin", "HacıSakir", 150, "Karton", null);

        productsMap.computeIfAbsent(product1.getId(), k -> new ArrayList<>()).add(product1);
        productsMap.computeIfAbsent(product2.getId(), k -> new ArrayList<>()).add(product2);
        productsMap.computeIfAbsent(product3.getId(), k -> new ArrayList<>()).add(product3);
        productsMap.computeIfAbsent(product4.getId(), k -> new ArrayList<>()).add(product4);
    }

    public void listProducts() {
        System.out.println("List of Products:");
        for (List<Warehouse_Product> warehouseProductList : productsMap.values()) {
            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                System.out.println(warehouseProduct);
            }
        }
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐");
            System.out.println(" \uD83C\uDFED Warehouse Menu \uD83C\uDFED");
            System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐");
            System.out.println("1\uFE0F⃣. 📦 Enter Product");
            System.out.println("2\uFE0F⃣. 🛒 Place on Shelf");
            System.out.println("3\uFE0F⃣. 🛒 Exit Product");
            System.out.println("4\uFE0F⃣. 📋 List Products");
            System.out.println("0\uFE0F⃣. 🚪 Exit");
            System.out.println("⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐");

            System.out.print("✒\uFE0F Enter your choice: ");
            choice = InputExceptionHandler.getIntInput("");

            switch (choice) {
                case 1:
                    enterProduct();
                    break;
                case 2:
                    placeOnShelf();
                    break;
                case 3:
                    exitProduct();
                    break;
                case 4:
                    listProducts();
                    break;
                case 0:
                    System.out.println("👋 Exiting the warehouse management system. Goodbye!");
                    break;
                default:
                    System.out.println("‼\uFE0F Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);
    }
}