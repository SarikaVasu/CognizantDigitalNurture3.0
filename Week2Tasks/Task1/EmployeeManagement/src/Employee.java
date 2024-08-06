public class Employee {
    private int id;
    private String name;
    private String address;
    public Employee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public int getId() {
        return this.id;
    }
    @Override
    public String toString() {
        return "EmpId: " + id + "\tName: " + name + "\tAddress: " + address;
    }
}
