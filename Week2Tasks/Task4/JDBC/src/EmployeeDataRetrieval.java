import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeDataRetrieval {
    public void getEmployees() {
        String query = "SELECT * FROM employees";
        try (Connection connection = JDBCConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                double salary = resultSet.getDouble("salary");
                System.out.println(id + " " + name + " " +position + " " + salary);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
    public void addEmployee(String name, String position, double salary) {
        String query = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, position);
            preparedStatement.setDouble(3, salary);
            System.out.println(preparedStatement.executeUpdate() + "rows affected");
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
    public void updateEmployee(int id, String name, String position, double salary) {
        String query = "UPDATE employees SET name = ?, position = ?, salary = ?  WHERE id = ?";
        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, position);
            preparedStatement.setDouble(3, salary);
            preparedStatement.setInt(4, id);
            System.out.println(preparedStatement.executeUpdate() + "rows affected");
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
    public void deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement.executeUpdate() + "rows affected");
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        EmployeeDataRetrieval edr = new EmployeeDataRetrieval();
//        edr.addEmployees("ABC", "Dev", 78900);
//        edr.addEmployees("XYZ", "Manager", 90000);
//        edr.getEmployees();
//        edr.updateEmployee(8, "XYZ", "Manager", 100000);
//        edr.getEmployees();
//        edr.deleteEmployee(7);
//        edr.deleteEmployee(8);
//        edr.getEmployees();

        EmployeeDataRetrieval edr = new EmployeeDataRetrieval();
        Scanner sc = new Scanner(System.in);
        boolean loop = true;

        while(loop) {
            System.out.println("Employee Data Management: ");
            System.out.println("1. Add Employees");
            System.out.println("2. Update Employees");
            System.out.println("3. Delete Employees");
            System.out.println("4. View Employees");
            System.out.println("5. Exit");
            System.out.print("Enter option from 1 to 5: ");
            int choice = 0;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException err) {
                System.out.println("invalid input.");
                sc.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("Enter emp name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter emp position: ");
                    String position = sc.nextLine();
                    System.out.println("Enter employee salry:");
                    double salary = sc.nextDouble();
                    edr.addEmployee(name, position, salary);
                    break;
                case 2:
                    System.out.println("Enter id to update employee: ");
                    int updateId = sc.nextInt();
                    System.out.println("Enter emp name: ");
                    String updateName = sc.nextLine();
                    System.out.println("Enter emp position: ");
                    String updatePosition = sc.nextLine();
                    System.out.println("Enter employee salry:");
                    double updateSalary = sc.nextDouble();
                    edr.updateEmployee(updateId, updateName, updatePosition, updateSalary);
                    break;
                case 3:
                    System.out.println("Enter id to delete employee: ");
                    int deleteId = sc.nextInt();
                    edr.deleteEmployee(deleteId);
                    break;
                case 4:
                    edr.getEmployees();
                    break;
                case 5:
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
