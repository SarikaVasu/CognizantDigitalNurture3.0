public class OrderTrackingTest {
    public static void main(String[] args) {
        Order order1 = new Order(1, "abc");
        Order order2 = new Order(2, "abc");
        Order order3 = new Order(3, "abc");
        OrderTracking orders = new OrderTracking();
        orders.addOrder(order1);
        orders.addOrder(order2);
        orders.addOrder(order3);
        orders.displayOrders();
        orders.processOrder();
        orders.displayOrders();
    }
}
