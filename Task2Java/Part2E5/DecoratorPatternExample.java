interface Notifier {
    void send(String msg);
}
abstract class NotifierDecorator implements Notifier {
    protected Notifier notifier;
    public NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }
    public void send(String msg) {
        notifier.send(msg);
    }
}
class EmailNotifier implements Notifier {
    public void send(String msg) {
        System.out.println("Message: " + msg + " sent");
    } 
}
class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }
    public void send(String msg) {
        super.send(msg);
        System.out.println("Sending SMS notification");
    }
}
class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }
    public void send(String msg) {
        super.send(msg);
        System.out.println("Sending Slack notification");
    }
}
public class DecoratorPatternExample {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        Notifier smsNot = new SMSNotifierDecorator(notifier);
        smsNot.send("Hello");
        Notifier slackNot = new SlackNotifierDecorator(smsNot);
        slackNot.send("Hi");
    }
}