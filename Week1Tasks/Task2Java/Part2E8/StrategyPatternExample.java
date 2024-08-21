interface PaymentStrategy {
    void pay();
}
class creditCardPayment implements PaymentStrategy {
    public void pay() {
        System.out.println("Payment through credit card");
    }
}
class PayPalPayment implements PaymentStrategy {
    public void pay() {
        System.out.println("Payment through PayPal");
    }
}
class PaymentContext {
    PaymentStrategy paymentStrategy;
    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    public void executeStrategy() {
        paymentStrategy.pay();
    }
}
public class StrategyPatternExample {
    public static void main(String[] args) {
        PaymentStrategy paymentStrategyCreditcard = new creditCardPayment();
        PaymentStrategy paymentStrategyPayPal = new PayPalPayment();
        PaymentContext paymentContextCC = new PaymentContext(paymentStrategyCreditcard);
        paymentContextCC.executeStrategy();
        PaymentContext paymentContextPP = new PaymentContext(paymentStrategyPayPal);
        paymentContextPP.executeStrategy();
        
    }
}