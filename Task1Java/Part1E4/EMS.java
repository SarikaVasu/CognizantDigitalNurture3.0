import java.util.*;
class Employee {
    int employeeId;
    String name;
    String position;
    int salary;
    Employee(int employeeId, String name, String position, int salary) {
        this.addEmployee(employeeId, name, position, salary);
    }
    public void addEmployee(int employeeId, String name, String position, int salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    public void printEmployee() {
        System.out.println(this.employeeId + " " + this.name + " " + this.position + " " + this.salary);
    }
}
public class EMS {
    ArrayList<Employee> employees = new ArrayList<>();
    public void addEmployee(int employeeId, String 
    name, String position, int salary) {
        Employee newEmployee = new Employee(employeeId, name, position, salary);
        employees.add(newEmployee);
    }
    public Employee searchEmployee(int employeeId) {
        for(int i=0; i<employees.size(); i++) {
            if(employees.get(i).employeeId == employeeId) {
                return employees.get(i);
            }
        }
        return null;
    }
    public void deleteEmployee(int employeeId) {
        for(int i=0; i<employees.size(); i++) {
            if(employees.get(i).employeeId == employeeId) {
                employees.remove(employees.get(i));
            }
        }
    }
    public void printEmployees() {
        for(int i=0; i<employees.size(); i++) {
            employees.get(i).printEmployee();
        }
    }
    public static void main(String[] args) {
        EMS emsExample = new EMS();
        emsExample.addEmployee(1, "abc", "tm", 23);
        emsExample.addEmployee(2, "qrs", "manager", 13);
        emsExample.addEmployee(3, "xyz", "tm", 24);
        emsExample.printEmployees();
        Employee emp = emsExample.searchEmployee(2);
        emp.printEmployee();
        emsExample.deleteEmployee(3);
        emsExample.printEmployees();
    }
}

//array is implemented as arraylist in java
//Inserting takes O(1) time complexity, it is done at end of array
//Accessing elements in arraylist takes O(n) time complexity.
//traversal can be done using iterator or index
//Deleting employee also takes O(n) time cmplexity as there is a need to check for the employee id

