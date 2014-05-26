package tinf13b4.forum.content;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tinf13b4.forum.database.ConnectionManager;

public class ThreadController {
	private ResultSet rs;
	private ConnectionManager conn;
	private int id;
	private ArrayList<ThreadModel> threads;
	
	public ThreadController() {
		conn = new ConnectionManager();
	}
	
	public void getAllThreads(int categorie_ID) {
		rs = conn.executeCommand("SELECT * FROM Thread WHERE Categorie_ID = " + categorie_ID + ";");
		threads = new ArrayList<ThreadModel>();
		if(rs == null)
			return;
		else
		{
			try {
				while(rs.next())
				{
					threads.add(new ThreadModel(rs.getInt("Thread_ID"), rs.getString("Title")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<ThreadModel> getThreads() {
		getAllThreads(id);
		return threads;
	}

	public void setThreads(int id) {
		this.id = id;
	}
	
}
