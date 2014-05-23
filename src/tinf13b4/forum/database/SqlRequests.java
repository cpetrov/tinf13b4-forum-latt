package tinf13b4.forum.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.*;

public class SqlRequests 
{
	private ResultSet resultSet;
	public ResultSet getResultSet() {
		return resultSet;
	}
	private ConnectionManager mConnectionManager;
	private Connection conn;
	private Statement stat;

	public SqlRequests() 
	{
		this.mConnectionManager = new ConnectionManager();
/*
 * TODO
 * test with new MysqlConnectionTester();
 */
	}
	
	public void openConnection()
	{		
		conn = mConnectionManager.getConnection();
		try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection()
	{
		try {
			conn.close();
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void getAllThreads()
	{
		openConnection();
		try {
			resultSet = stat.executeQuery("SELECT * FROM Thread;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeConnection();
	}
}
