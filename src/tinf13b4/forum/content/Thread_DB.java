package tinf13b4.forum.content;

import tinf13b4.forum.database.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;

public class Thread_DB {
	private ResultSet rs;
	private ConnectionManager conn;
	private ArrayList<Thread> threads;
	
	public Thread_DB() {
		conn = new ConnectionManager();
	}
	
	public void getAllThreadsInCategorie(int categorie_ID) {
		rs = conn.executeCommand("SELECT * FROM Thread WHERE Categorie_ID = " + categorie_ID + ";");
		threads = new ArrayList<Thread>();
		if(rs == null)
			return;
		else
		{
			try {
				while(rs.next())
				{
					threads.add(new Thread(rs.getInt("Thread_ID"), rs.getString("Title")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Thread> getThreads() {
		return threads;
	}

	public void setThreads(ArrayList<Thread> threads) {
		this.threads = threads;
	}
	
}
