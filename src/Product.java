public interface Product {
    boolean isProductIdExists(int product);
    void initializeProduct(int id, String name, String producer, int quantity, String unit, String shelf);
}
