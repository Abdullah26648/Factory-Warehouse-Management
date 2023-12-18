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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    @Override
    public String toString() {
        return String.format("ID: %d\tName: %s\tProducer: %s\tQuantity: %d\tUnit: %s\tShelf: %s",
                id, name, producer, quantity, unit, shelf);
    }
}