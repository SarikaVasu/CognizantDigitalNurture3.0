public class BookCollectionTest {
    public static void main(String[] args) {
        BookCollection books = new BookCollection();
        books.addBook("B10");
        books.addBook("B8");
        books.addBook("B7");
        books.addBook("B10");
        books.addBook("B12");
        books.addBook("N9");
        books.displayBooks();
        books.removeBook("B7");
        books.displayBooks();
    }
}
