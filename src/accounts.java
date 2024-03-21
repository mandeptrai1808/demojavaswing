
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class accounts {
	public accounts() {
	}
	
	public void getusers() {
		Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
		 try {
			 	conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "");
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
	
	public boolean login(String username, String password) {
		Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        String sql = "SELECT * FROM login.log where username = '"+username+"' and password = '"+password+"';";
        try {
        	conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "");
        	stmt = conn.createStatement();
        	rs = stmt.executeQuery(sql);
        	
        	if (rs.next()) {
        		System.out.println("login success");
        		return true;
        	}
        } catch (SQLException e) {
        	System.out.println("Error occurred while fetching data from database.");
            e.printStackTrace();
		}finally {
		    
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error occurred while closing resources.");
                e.printStackTrace();
            }
        }
        System.out.println("Login failed!");
        return false;
	}
}
