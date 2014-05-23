package tinf13b4.forum.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlRequests 
{
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
	
	public int countThreads() throws SQLException
	{
		openConnection();
		ResultSet rs = stat.executeQuery("SELECT COUNT...;");
		int count = 0;
		while (rs.next())
			count = Integer.parseInt(rs.getString("unread"));
		closeConnection();
		return count;
	}
}
