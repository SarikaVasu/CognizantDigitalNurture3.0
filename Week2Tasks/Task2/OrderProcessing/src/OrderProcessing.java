import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderProcessing {
    List<Order> orders = new ArrayList<>();
    public void addOrder(Order order) {
        orders.add(order);
    }
    public List<Order> processOrder(int amount) {
        OrderFilter filter = ( order )-> order.getAmount() > amount;
        OrderProcessor processor = (Order order) -> order.setStatus("Complete");
        List<Order> filteredOrders = orders.stream().filter(filter::filter).toList();
        filteredOrders.forEach(processor::process);
        return filteredOrders;
    }
    public void displayOrders() {
        for(Order order : orders) {
            System.out.println(order);
        }
    }
    public static void main(String[] args) {
        Order order1 = new Order(1, "abc", 20, "Pending");
        Order order2 = new Order(2, "abc", 30, "Pending");
        Order order3 = new Order(3, "abc", 19, "Pending");
        Order order4 = new Order(4, "abc", 23, "Pending");
        OrderProcessing orderProcessing = new OrderProcessing();
        orderProcessing.addOrder(order1);
        orderProcessing.addOrder(order2);
        orderProcessing.addOrder(order3);
        orderProcessing.addOrder(order4);
        orderProcessing.displayOrders();
        orderProcessing.processOrder(20).forEach((x)-> System.out.println(x));
        orderProcessing.displayOrders();
    }
}

