class Product {
    int productId;
    String productName;
    String category;
    Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
}
public class SearchFunctions {
    Product[] products = new Product[10];
    public int linearSearch(int productId) {
        for(int i=0; i<products.length; i++) {
            if(products[i].productId == productId) {
                return i;
            }
        }
        return -1;
    }
    public int binarySearch(int productId) {
        int start = 0;
        int end = products.length - 1;
        while(start <= end) {
            int mid = start + (end - start)/2;
            if(products[mid].productId == productId) {
                return mid;
            } else if(products[mid].productId < productId) {
                start = mid + 1;
            } else if(products[mid].productId > productId) {
                end = mid - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        SearchFunctions sf = new SearchFunctions();
        for(int i=0; i<10; i++) {
            sf.products[i] = new Product(i+20, "abc", "xyz");
        }
        System.out.println(sf.linearSearch(23));
        System.out.println(sf.binarySearch(23));
    }
}

//To search in large volume of data
//Given the array is sorted Binary search is better that linear as it takes O(logn) time complexity as the array is divided into 2 diff ones each iteration
//Linear search takes O(n^2) time
//Even when array is not sorted, effective sorting mechanism can be used like merge sort with takes time complexity of O(nlogn) stiil less than O(n^2)
//So binary search is prefferd than linear