import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLExample {
    public static void main(String[] args) {
        // Database connection URL
        String url = "jdbc:mysql://localhost:3306/company";  // Replace with your database URL
        String user = "root"; // Your MySQL username
        String password = "password"; // Your MySQL password
        
        // Initialize the connection and statement objects
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Step 1: Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Step 2: Establish the connection
            conn = DriverManager.getConnection(url, user, password);
            
            // Step 3: Create a statement object
            stmt = conn.createStatement();
            
            // Step 4: Execute the query to fetch data from the Employee table
            String sql = "SELECT EmpID, Name, Salary FROM Employee";
            rs = stmt.executeQuery(sql);
            
            // Step 5: Process the result set and display the data
            System.out.println("EmpID | Name         | Salary");
            System.out.println("------------------------------");
            while (rs.next()) {
                int empID = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println(empID + "     | " + name + "  | " + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
