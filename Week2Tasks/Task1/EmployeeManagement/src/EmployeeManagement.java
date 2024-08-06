import java.util.ArrayList;

public class EmployeeManagement {
    ArrayList<Employee> employees = new ArrayList<>();
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    public Employee removeEmployee(int employeeId){
        for(Employee emp : employees) {
            if(emp.getId() == employeeId) {
                employees.remove(emp);
                return emp;
            }
        }
        return null;
    }
    public void displayEmployees() {
        if(!employees.isEmpty()) {
            for(Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }
}
