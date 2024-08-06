public class ProductCatalogTest {
    public static void main(String[] args) {
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog.addProduct("P1");
        productCatalog.displayProducts();
        productCatalog.addProduct("P2");
        productCatalog.addProduct("P3");
        productCatalog.displayProducts();
        productCatalog.removeProduct("P2");
        productCatalog.displayProducts();
    }
}
