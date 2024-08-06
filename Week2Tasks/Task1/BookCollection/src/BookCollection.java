import java.util.LinkedHashSet;

public class BookCollection {
    private LinkedHashSet<String> books = new LinkedHashSet<>();
    public void addBook(String bookTitle) {
        books.add(bookTitle);
    }
    public void removeBook(String bookTitle) {
        books.remove(bookTitle);
    }
    public void displayBooks() {
        System.out.println(books);
    }
}
