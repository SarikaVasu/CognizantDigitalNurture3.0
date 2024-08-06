public class Product {
    private int id;
    private String name;
    private int quantity;
    public Product(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
    public int getId() {
        return this.id;
    }
    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "ProductId: " + id + "\tName: " + name + "\tQuantity: " + quantity;
    }
}
