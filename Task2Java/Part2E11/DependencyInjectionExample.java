interface CustomerRepository {
    Customer findCustomerById(String id);
}
class CustomerRepositoryImpl  implements CustomerRepository {
    public Customer findCustomerById(String id) {
        return new Customer(id, "A");
    }
}
class Customer {
    private String id;
    private String name;
    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void printCustomerDetails() {
        System.out.println("Id: "+ id + "name: " + name);
    }
}
class CustomerService {
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer findCustomerById(String id) {
        return customerRepository.findCustomerById(id);
    }
}
public class DependencyInjectionExample {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerService(customerRepository);
        Customer customer = customerService.findCustomerById("123");
        customer.printCustomerDetails();
    }
}