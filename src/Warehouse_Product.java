public class Warehouse_Product {
    private int id;
    private String name;
    private String producer;
    private int quantity;
    private String unit;
    private String shelf;

    public Warehouse_Product(int id, String name, String producer, int quantity, String unit, String shelf) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.quantity = quantity;
        this.unit = unit;
        this.shelf = shelf;
    }

    public Warehouse_Product() {
    }

    public int getId() {
        return id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String toString() {
        return String.format("💾 ID: %-6d\t📕 Name: %-15s\t📗 Producer: %-15s\t🗃️ Quantity: %-6d\t📢 Unit: %-10s\t📃 Shelf: %-10s",
                id, name, producer, quantity, unit, shelf);
    }
}
