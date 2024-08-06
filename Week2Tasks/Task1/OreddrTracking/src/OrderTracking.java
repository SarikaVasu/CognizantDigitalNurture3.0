import java.util.LinkedList;

public class OrderTracking {
    private LinkedList<Order> orders = new LinkedList<Order>();
    public void addOrder(Order order) {
        orders.add(order);
    }
    public Order processOrder() {
        if(!orders.isEmpty()) {
            return orders.poll();
        }
        return null;
    }
    public void displayOrders() {
        if(!orders.isEmpty()) {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }
}
