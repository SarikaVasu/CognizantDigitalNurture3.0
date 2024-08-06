import java.util.HashMap;
import java.util.Map;

public class InventoryManagement {
    private HashMap<Integer, Product> products = new HashMap<>();
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }
    public Product removeProduct(int productId) {
        if(products.containsKey(productId)) {
            return products.remove(productId);
        }
        return null;
    }
    public void updateProductQuantity(int productId, int newQuantity) {
        if(products.containsKey(productId)) {
            products.get(productId).updateQuantity(newQuantity);
        }
    }
    public void displayProducts() {
        if(!products.isEmpty()) {
            for(Map.Entry<Integer, Product> productEntry : products.entrySet()) {
                System.out.println(productEntry.getValue());
            }
        }
    }
}
