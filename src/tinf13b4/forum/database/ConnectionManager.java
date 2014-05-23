package tinf13b4.forum.database;

import java.util.ArrayList;
import java.sql.*;
import tinf13b4.forum.security.DecryptBase64;

public class ConnectionManager 
{
	private ArrayList<String> data;
	// Testing
	private Connection connection;
	private Statement stat;
	public Connection getConnection() {
		openConnection();
		return connection;
	}
	
	public ConnectionManager() 
	{
		data = new DecryptBase64().DecryptBase64(System.getProperty("java.class.path"));
	}
	
	private Connection openConnection()
	{
		// Open Connection
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"
					+ data.get(0) + ":"
					+ data.get(1) + "/"
					+ data.get(2),
					data.get(3),
					data.get(4));
			
			stat = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
