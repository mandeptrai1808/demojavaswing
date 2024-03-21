import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login {
	
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "");
            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException e) {
            System.out.println("Failed!");
            System.out.println(e);
        }

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from login.log");
            while (rs.next()) {
  
                String username = rs.getString("username"); 
                String password = rs.getString("password"); 
   
                System.out.println("Username: " + username + ", Password: " + password);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching data from database.");
            e.printStackTrace();
        } finally {
    
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error occurred while closing resources.");
                e.printStackTrace();
            }
        }
    }
}
