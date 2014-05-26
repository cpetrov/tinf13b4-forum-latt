package tinf13b4.forum.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tinf13b4.forum.security.DecryptBase64;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionManager 
{
	private ArrayList<String> data;

	private Connection conn;
	private Statement stat;
//	public Connection getConnection() {
//		openConnection();
//		return connection;
//	}
	
	public ConnectionManager() 
	{
//		data = new DecryptBase64().DecryptBase64(System.getProperty("java.class.path"));
	}
	
	public ConnectionManager(String filepath)
	{
		data = new DecryptBase64().DecryptBase64(filepath);
	}
	
	private Connection openConnection()
	{
		try {
	//		Context context = new InitialContext();
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName(data.get(0));
			dataSource.setPort(Integer.parseInt(data.get(1)));
			dataSource.setDatabaseName(data.get(2));
			dataSource.setUser(data.get(3));
			dataSource.setPassword(data.get(4));
			
			
			conn = dataSource.getConnection();
			stat = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	private void closeConnection()
	{
		try {
			stat.close();
			conn.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public ResultSet executeCommand(String command) {
		openConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
		return rs;
	}
}
