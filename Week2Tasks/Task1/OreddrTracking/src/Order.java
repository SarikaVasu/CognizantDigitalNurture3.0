public class Order {
    int orderId;
    String orderDetails;
    public Order(int orderId, String orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }
    @Override
    public String toString() {
        return "OrderId: " + orderId + "\tDetails: " + orderDetails;
    }
}
