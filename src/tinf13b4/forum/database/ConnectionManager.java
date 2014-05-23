package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import tinf13b4.forum.security.DecryptBase64;

public class ConnectionManager 
{
	private ArrayList<String> data;
	// Testing
	private Connection connection;
	public Connection getConnection() {
		openConnection();
		return connection;
	}
	
	public ConnectionManager() 
	{
		data = new DecryptBase64().DecryptBase64(filepath);
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
			
			Statement stat = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
