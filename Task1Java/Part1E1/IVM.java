//Data structures are effective way of storing data such that they are easy to access and manipulate

import java.util.*;
class Product {
    int productId;
    String productName;
    int quantity;
    float price;
    Product(int productId, String productName, int quantity, float price) {
        this.addProduct(productId, productName, quantity, price);
    }
    public void addProduct(int productId, String productName, int quantity, float price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    public void updateProduct(int productId, String productName, int quantity, float price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    public void printProduct() {
        System.out.println(this.productId + " " + this.productName + " " + this.quantity + " " + this.price);
    }
}
public class IVM {
    ArrayList<Product> products = new ArrayList<>();
    public void addProduct(int productId, String 
    productName, int quantity, float price) {
        Product newProduct = new Product(productId, productName, quantity, price);
        products.add(newProduct);
    }
    public void updateProduct(int productId, String productName, int quantity, float price) {
        for(int i=0; i<products.size(); i++) {
            if(products.get(i).productId == productId) {
                products.get(i).updateProduct(productId, productName, quantity, price);
            }
        }
    }
    public void deleteProduct(int productId) {
        for(int i=0; i<products.size(); i++) {
            if(products.get(i).productId == productId) {
                products.remove(products.get(i));
            }
        }
    }
    public void printProducts() {
        for(int i=0; i<products.size(); i++) {
            products.get(i).printProduct();
        }
    }
    public static void main(String[] args) {
        IVM ivmExample = new IVM();
        ivmExample.addProduct(1, "abc", 2, 34f);
        ivmExample.printProducts();
    }
}

//ArrayList is space efficient but can still be mode efficient with linked lists.
//Inserting takes O(1) time complexity 
//Accessing elements in arraylist takes O(n) time complexity.
//Deleting product also takes O(n) time cmplexity as there is a need to check for the product id

//Using HashMap to map id with product details is also a ver good alternative that can used
//Inserting takes O(1) time complexity 
//Accessing elements in arraylist takes O(1) time complexity, as the product can be accessed using key which is the product id
//Deleting product also takes O(1) time cmplexity as key is present
c