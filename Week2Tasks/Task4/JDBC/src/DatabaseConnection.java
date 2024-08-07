import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/employee_db";
    private static final String user = "sari";
    private static final String pswd = "sari";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  //load mysql jdbc drive
            connection = DriverManager.getConnection(url, user, pswd);
            if(connection != null) {
                System.out.println("Connected successfully");
            }
        } catch (ClassNotFoundException err) {
            System.out.println("Driver NOt found: ");
            err.printStackTrace();
        } catch (SQLException err) {
            System.out.println("Failed connection");
            err.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException err) {
                    err.printStackTrace();
                }
            }
        }
    }
}
