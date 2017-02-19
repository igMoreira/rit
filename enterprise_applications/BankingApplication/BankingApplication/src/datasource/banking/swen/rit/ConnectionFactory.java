package datasource.banking.swen.rit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String connectionString = "jdbc:mysql://localhost/banking?" +
            "user=root&password=pass";
	
	public static Connection getConn()
	{
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
}
