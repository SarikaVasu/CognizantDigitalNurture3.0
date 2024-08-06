import java.util.LinkedHashSet;

public class BookCollection {
    private LinkedHashSet<String> books = new LinkedHashSet<>();
    public void addBook(String bookTitle) {
        books.add(bookTitle);
    }
    public void removeBook(String bookTitle) {
        if(books.contains(bookTitle)) {
            books.remove(bookTitle);
        }
    }
    public void displayBooks() {
        if(!books.isEmpty())  {
            System.out.println(books);
        }
    }
}
