import java.util.*;
interface Stock {
    void register(Observer observer);
    void deregister(Observer observer);
    void notifyObservers();
}
interface Observer {
    void update(String stocks);
}
class StockMarket implements Stock {
    private List<Observer> observers;
    private String stocks;
    public StockMarket() {
        this.observers = new ArrayList<>();
    }
    public void register(Observer observer) {
        observers.add(observer);
    }
    public void deregister(Observer observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update(stocks);
        }
    }
    public void setStocks(String stocks) {
        this.stocks = stocks;
        notifyObservers();
    }
}
class MobileApp implements Observer {
    private String name;
    public MobileApp(String name) {
        this.name = name;
    }
    public void update(String stocks) {
        System.out.println(name + "stocks: " + stocks);
    }
}
class WebApp implements Observer {
    private String name;
    public WebApp(String name) {
        this.name = name;
    }
    public void update(String stocks) {
        System.out.println(name + "stocks: " + stocks);
    }
}

public class ObserverPatternExample {
    public static void main(String[] args){
        StockMarket stockMarket = new StockMarket();
        Observer mobile1 = new MobileApp("Mobile app 1");
        Observer web1 = new WebApp("Web app 1");

        stockMarket.register(mobile1);
        stockMarket.register(web1);
        stockMarket.setStocks("Updated stocks 1");
        stockMarket.deregister(mobile1);
        stockMarket.setStocks("Updates stocks 2");
    }
}