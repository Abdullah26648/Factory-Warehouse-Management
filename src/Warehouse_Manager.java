import java.util.*;

public class Warehouse_Manager implements Purchase, Sale {
    private static final Map<Integer, List<Warehouse_Product>> productsMap = new TreeMap<>();
    public Scanner scanner = new Scanner(System.in);

    @Override
    public void enterProduct() {
        System.out.println("Enter product details:");
        System.out.print("ID: ");
        int id = InputExceptionHandler.getIntInput("Enter product ID: ");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Producer: ");
        String producer = scanner.nextLine();
        System.out.print("Quantity: ");
        int quantity = InputExceptionHandler.getIntInput("Enter product Quality: ");
        System.out.print("Unit: ");
        String unit = scanner.nextLine();
        System.out.println("Product entered successfully.");

        Warehouse_Product warehouseProduct = new Warehouse_Product(id, name, producer, quantity, unit, null);
        productsMap.computeIfAbsent(id, k -> new ArrayList<>()).add(warehouseProduct);
    }

    @Override
    public void placeOnShelf() {
        System.out.print("Enter product ID to place on shelf: ");
        int id = InputExceptionHandler.getIntInput("Enter product ID: ");
        System.out.print("Enter shelf: ");
        String shelf = scanner.nextLine();

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);
        if (warehouseProductList != null && !warehouseProductList.isEmpty()) {
            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                warehouseProduct.setShelf(shelf);
            }
            System.out.println("Product placed on shelf successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void exitProduct() {
        System.out.print("Enter product ID to exit: ");
        int id = InputExceptionHandler.getIntInput("Enter product ID to exit: ");
        System.out.print("Enter quantity to exit: ");
        int exitQuantity = InputExceptionHandler.getIntInput("Enter Quantity value with number to exit: ");

        List<Warehouse_Product> warehouseProductList = productsMap.get(id);
        if (warehouseProductList != null && !warehouseProductList.isEmpty()) {
            for (Warehouse_Product warehouseProduct : warehouseProductList) {
                int remainingQuantity = warehouseProduct.getQuantity() - exitQuantity;
                if (remainingQuantity >= 0) {
                    warehouseProduct.setQuantity(remainingQuantity);
                    System.out.println("Product exited successfully.");
                } else {
                    System.out.println("Exit quantity exceeds available quantity.");
                }
            }
        } else {
            System.out.println("Product not found.");
        }
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
            System.out.println("\n===========================");
            System.out.println("     Warehouse Menu");
            System.out.println("===========================");
            System.out.println("1. Enter Product");
            System.out.println("2. Place on Shelf");
            System.out.println("3. Exit Product");
            System.out.println("4. List Products");
            System.out.println("0. Exit");
            System.out.println("===========================");

            System.out.print("Enter your choice: ");
            choice = InputExceptionHandler.getIntInput("Enter your choice: ");

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
                    System.out.println("Exiting the warehouse management system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);
    }
}