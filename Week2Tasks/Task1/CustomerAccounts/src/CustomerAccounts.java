import java.util.Map;
import java.util.TreeMap;

public class CustomerAccounts {
    private TreeMap<Integer, Customer> customers = new TreeMap<>();
    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }
    public Customer removeCustomer(int customerId) {
            return customers.remove(customerId);
    }
    public void displayCustomers() {
        if(!customers.isEmpty()) {
            for(Map.Entry<Integer, Customer> customerEntry : customers.entrySet()) {
                System.out.println(customerEntry.getValue());
            }
        }
    }
}
