import java.util.HashSet;

public class ProductCatalog {
    private HashSet<String> products = new HashSet<>();
    public void addProduct(String productName) {
        products.add(productName);
    }
    public void removeProduct(String productName) {
        if(products.contains(productName)) {
            products.remove(productName);
        }
    }
    public boolean searchProduct(String productName) {
        return products.contains(productName);
    }
    public void displayProducts() {
        if(!products.isEmpty())  {
            System.out.println(products);
        }
    }
}
