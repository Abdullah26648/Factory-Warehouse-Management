import java.util.*;

public class WarehouseManager implements Purchase, Sale {
    private static Map<Integer, List<Product>> productsMap = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void enterProduct() {
        try {
            System.out.println("Enter product details:");

            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Producer: ");
            String producer = scanner.nextLine();

            System.out.print("Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Unit: ");
            String unit = scanner.nextLine();

            System.out.println("Product entered successfully.");

            Product product = new Product(id, name, producer, quantity, unit, null);
            productsMap.computeIfAbsent(id, k -> new ArrayList<>()).add(product);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter the correct data type.");
            scanner.nextLine(); // Consume the invalid input

            // Provide a warning message
            System.out.println("Warning: Make sure to enter numeric values for ID and Quantity.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }

    @Override
    public void placeOnShelf() {
        try {
            System.out.print("Enter product ID to place on shelf: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter shelf: ");
            String shelf = scanner.nextLine();

            List<Product> productList = productsMap.get(id);
            if (productList != null && !productList.isEmpty()) {
                for (Product product : productList) {
                    product.setShelf(shelf);
                }
                System.out.println("Product placed on shelf successfully.");
            } else {
                System.out.println("Product not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter a numeric value for product ID.");
            scanner.nextLine(); // Consume the invalid input
        } catch (NoSuchElementException e) {
            System.out.println("Input not found. Please enter both product ID and shelf.");
            scanner.nextLine(); // Consume the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }

    @Override
    public void exitProduct() {
        try {
            System.out.print("Enter product ID to exit: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter quantity to exit: ");
            int exitQuantity = scanner.nextInt();

            List<Product> productList = productsMap.get(id);
            if (productList != null && !productList.isEmpty()) {
                for (Product product : productList) {
                    int remainingQuantity = product.getQuantity() - exitQuantity;
                    if (remainingQuantity >= 0) {
                        product.setQuantity(remainingQuantity);
                        System.out.println("Product exited successfully.");
                    } else {
                        System.out.println("Exit quantity exceeds available quantity.");
                    }
                }
            } else {
                System.out.println("Product not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter numeric values for ID and Quantity.");
            scanner.nextLine(); // Consume the invalid input
        } catch (NoSuchElementException e) {
            System.out.println("Input not found. Please enter both product ID and quantity.");
            scanner.nextLine(); // Consume the invalid input
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }

    public void listProducts() {
        System.out.println("List of Products:");
        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                System.out.println(product);
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
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n=== Enter Product ===");
                    enterProduct();
                    break;
                case 2:
                    System.out.println("\n=== Place on Shelf ===");
                    placeOnShelf();
                    break;
                case 3:
                    System.out.println("\n=== Exit Product ===");
                    exitProduct();
                    break;
                case 4:
                    System.out.println("\n=== List Products ===");
                    listProducts();
                    break;
                case 0:
                    System.out.println("\nExiting the warehouse management system.");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a valid option.");
            }

        } while (choice != 0);
    }
}