interface PaymentProcessor {
    void processPayment(int amount);
}
class PayPalGateway {
    public void pay(int amount) {
        System.out.println("Paying through PayPal: " + amount);
    }
}
class PhonePeGateway {
    public void pay(int amount) {
        System.out.println("Paying through PhonePe: " + amount);
    }
}
class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;
    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }
    public void processPayment(int amount) {
        this.payPalGateway.pay(amount);
    }
}
class PhonePeAdapter implements PaymentProcessor {
    private PhonePeGateway phonePeGateway;
    public PhonePeAdapter(PhonePeGateway phonePeGateway) {
        this.phonePeGateway = phonePeGateway;
    }
    public void processPayment(int amount) {
        this.phonePeGateway.pay(amount);
    }
}
public class AdapterPatternExample {
    public static void main(String[] args) {
        int amount = 1000;
        PayPalGateway payPalGateway =new PayPalGateway();
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPalGateway);
        payPalProcessor.processPayment(amount);

        PhonePeGateway phonePeGateway = new PhonePeGateway();
        PaymentProcessor phonePeProcessor = new PhonePeAdapter(phonePeGateway);
        phonePeProcessor.processPayment(amount);
    }
}