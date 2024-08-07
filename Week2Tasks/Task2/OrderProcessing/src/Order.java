public class Order {
    private int orderId;
    private String customerName;
    private int orderAmount;
    private String status;
    public Order(int orderId, String customerName, int orderAmount, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderAmount = orderAmount;
        this.status = status;
    }
    public int getAmount() {
        return this.orderAmount;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "OrderId: " + orderId + "\tCustomer Name: " + customerName + "\tOrder Amount: " + orderAmount + "\tStatus: " + status;
    }

}
