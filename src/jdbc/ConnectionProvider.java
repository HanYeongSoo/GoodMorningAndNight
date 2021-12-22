package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/OpenProject?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "1234";
		
		return DriverManager.getConnection(url, user, password);
	}
}
