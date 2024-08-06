public class InventoryManagementTest {
    public static void main(String[] args) {
        Product prod1 = new Product(1, "abc", 10);
        Product prod2 = new Product(2, "abc", 10);
        Product prod3 = new Product(3, "abc", 10);
        Product prod4 = new Product(4, "abc", 10);
        InventoryManagement products = new InventoryManagement();
        products.addProduct(prod1);
        products.addProduct(prod2);
        products.addProduct(prod3);
        products.addProduct(prod4);
        products.displayProducts();
        products.removeProduct(prod2.getId());
        products.displayProducts();
        products.updateProductQuantity(3, 20);
        products.displayProducts();
    }
}
