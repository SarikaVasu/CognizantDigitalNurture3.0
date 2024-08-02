class Order {
    int orderId;
    String orderName;
    int totalPrice;
    Order(int orderId, String orderName, int totalPrice) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.totalPrice = totalPrice;
    }
    public void printOrder() {
        System.out.println(this.orderId + " " + this.orderName + " " + this.totalPrice);
    }
}
public class TMS {
    Order[] orders = new Order[10];
    public void bubbleSort() {
        for(int i=0; i<orders.length; i++) {
            for(int j=1; j<orders.length-i; j++) {
                if(orders[j].totalPrice < orders[j-1].totalPrice) {
                    swap(j, j-1);
                }
            }
        }
    }
    public void quickSortOrders() {
        quickSort(0, orders.length -1);
    }
    private void quickSort(int low, int high) {
        if(low < high) {
            int par = partition(low, high);
            quickSort(low, par - 1);
            quickSort(par + 1, high);
        }
    }
    private int partition(int low, int high)
    {
        Order pivot = orders[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (orders[j].totalPrice < pivot.totalPrice) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }
    private void swap(int i, int j)
    {
        Order temp = orders[i];
        orders[i] = orders[j];
        orders[j] = temp;
    }
    public void printOrders() {
        for(int i=0; i<orders.length; i++) {
            orders[i].printOrder();
        }
    }
    public static void main(String[] args) {
        TMS tms = new TMS();
        for(int i=0; i<10; i++) {
            tms.orders[i] = new Order(i+20, "abc", i*(10-i)*(23));
        }
        tms.printOrders();
        // tms.bubbleSort();
        tms.quickSortOrders();
        tms.printOrders();
    }
}

//To sort large volume of data quick sort is most efficient
//Bubble sort takes O(n^2)
//Whereas Quick sort has time complexity of O(nlogn)
//For large number n^2 is very large than nlogn


