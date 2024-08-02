class Book {
    int bookId;
    String title;
    String author;
    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }
}
public class LMS {
    Book[] books = new Book[10];
    public int linearSearch(String title) {
        for(int i=0; i<books.length; i++) {
            if(books[i].title.equals(title)) {
                return i;
            }
        }
        return -1;
    }
    public int binarySearch(String title) {
        int start = 0;
        int end = books.length - 1;
        while(start <= end) {
            int mid = start + (end - start)/2;
            if(books[mid].title.equals(title)) {
                return mid;
            } else if(books[mid].title.compareTo(title) < 0) {
                start = mid + 1;
            } else if(books[mid].title.compareTo(title) > 0) {
                end = mid - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        LMS lms = new LMS();
        for(int i=0; i<10; i++) {
            lms.books[i] = new Book(i+20, "abc" + i, "xyz");
        }
        System.out.println(lms.linearSearch("abc" + 3));
        System.out.println(lms.binarySearch("abc" + 3));
    }
}

//To search in large volume of data
//Given the array is sorted Binary search is better that linear as it takes O(logn) time complexity as the array is divided into 2 diff ones each iteration
//Linear search takes O(n^2) time
//Even when array is not sorted, effective sorting mechanism can be used like merge sort with takes time complexity of O(nlogn) stiil less than O(n^2)
//So binary search is prefferd than linear