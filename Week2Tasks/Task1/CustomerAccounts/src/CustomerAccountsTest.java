public class CustomerAccountsTest {
    public static void main(String[] args) {
        Customer customer1 = new Customer(1, "abc", "xyz");
        Customer customer2 = new Customer(2, "abc", "xyz");
        Customer customer3 = new Customer(3, "abc", "xyz");
        Customer customer4 = new Customer(4, "abc", "xyz");
        CustomerAccounts customers = new CustomerAccounts();
        customers.addCustomer(customer1);
        customers.addCustomer(customer2);
        customers.addCustomer(customer3);
        customers.addCustomer(customer4);
        customers.displayCustomers();
        customers.removeCustomer(2);
        customers.displayCustomers();
    }
}
