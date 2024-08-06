public class EmployeeManagementTest {
    public static void main(String[] args) {
        Employee emp1 = new Employee(1, "Sarika", "axcvnd");
        Employee emp12 = new Employee(12, "ABC", "axcvnd");
        Employee emp3 = new Employee(2, "XCV", "axcvnd");        EmployeeManagement employees= new EmployeeManagement();
        employees.addEmployee(emp1);
        employees.addEmployee(emp12);
        employees.addEmployee(emp3);
        employees.displayEmployees();
        employees.removeEmployee(12);
        employees.displayEmployees();
    }
}
