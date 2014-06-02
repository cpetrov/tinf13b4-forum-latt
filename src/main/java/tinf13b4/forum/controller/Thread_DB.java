
package tinf13b4.forum.content;

import tinf13b4.forum.database.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

public class Thread_DB {

	private ResultSet rs;
	private ConnectionManager conn;
	private int threadId;
	private int categoryId;
	private ArrayList<ThreadModel> threads;
	private ThreadModel thread;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
		conn = new ConnectionManager();
		try {
			getAllThreadsInCategorie(categoryId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
		try {
			getThread(threadId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ThreadModel> getThreads() {
		return threads;
	}

	public void setThread(ThreadModel thread) {
		this.thread = thread;
		createNewThread();
	}

	public void getThread(int id) throws SQLException {
		rs = conn.executeCommand("SELECT  Title, T.Content, T.Date, T.User_ID, Category_ID, P.Post_ID"
				+ " FROM Threads T, ThreadPostRelationTable TPR, Posts P " + "WHERE T.Thread_ID = 2 "
				+ "AND T.Thread_ID = TPR.Thread_ID " + "AND TPR.Post_ID = P.Post_ID;");
		if (rs == null)
			return;
		while (rs.next()) {
			thread = new ThreadModel(id, rs.getString("Title"));
			thread.setContent(rs.getString("Content"));
			thread.setDate(rs.getDate("Date"));
			thread.setUser_ID(rs.getInt("User_ID"));
			thread.setCategory_ID(rs.getInt("Category_ID"));
			thread.addPost(rs.getInt("post_ID"));
		}
	}

	public void getAllThreadsInCategorie(int category_ID) throws SQLException {
		rs = conn.executeCommand("SELECT Thread_ID, Title FROM Threads WHERE Category_ID = " + category_ID + ";");
		threads = new ArrayList<ThreadModel>();
		if (rs == null)
			return;
		while (rs.next()) {
			threads.add(new ThreadModel(rs.getInt("Thread_ID"), rs.getString("Title")));
		}
		rs.close();
		conn.closeConnection();
	}

	private void createThread(String title, String content, int user_ID, int category_ID) throws SQLException {
		conn.insertCommand("INSERT INTO `pmforum`.`Threads` (`Title`, `Content`, `Date`, `User_ID`, `Category_ID`) VALUES ("
				+ "'"
				+ title
				+ "', "
				+ "'"
				+ content
				+ "', "
				+ "'"
				+ new java.sql.Date(new Date().getTime())
				+ "', "
				+ user_ID + ", " + category_ID + ");");
	}

	private void createNewThread() {
		try {
			createThread(thread.getTitle(), thread.getContent(), thread.getUser_ID(), thread.getCategory_ID());
			rs = conn.executeCommand("SELECT LAST_INSERT_ID();");
			if (rs == null)
				return;
			while (rs.next())
				thread.setId(rs.getInt("Thread_ID"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
