
package tinf13b4.forum.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.ThreadBuilder;
import tinf13b4.forum.model.Thread;
import tinf13b4.forum.util.ConnectionUtil;

import com.mysql.jdbc.PreparedStatement;

public class Thread_DB {

	private ResultSet rs;
	private int threadId;
	private int categoryId;
	private ArrayList<Thread> threads;
	private Thread thread;
	private final Connection connection;
	private final QueryExecutor queryExecutor;

	public Thread_DB() {
		connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public ArrayList<Thread> getThreads() {
		return threads;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
		createNewThread();
	}

	public void getThread(int id) throws SQLException {
		rs = queryExecutor.executeQuery("SELECT  Title, T.Content, T.Date, T.User_ID, Category_ID, P.Post_ID"
				+ " FROM Threads T, ThreadPostRelationTable TPR, Posts P " + "WHERE T.Thread_ID = 2 "
				+ "AND T.Thread_ID = TPR.Thread_ID " + "AND TPR.Post_ID = P.Post_ID;");
		if (rs == null)
			return;
		while (rs.next()) {
			ThreadBuilder builder = new ThreadBuilder();
			builder.setId(id);
			builder.setTitle(rs.getString("Title"));
			builder.setContent(rs.getString("Content"));
			builder.setDate(rs.getDate("Date"));
			builder.setThreadStarterId(rs.getInt("User_ID"));
			builder.setCategoryId(rs.getInt("Category_ID"));
			Thread thread = builder.build();
			thread.addPost(rs.getInt("post_ID"));
			//TODO beim Thread-Modell erzeugst du einen Post mit der gegebenen ID? Speichern wir nicht besser die post-ids in einer Integer-Liste?
		}
	}

	public void getAllThreadsInCategorie(int category_ID) throws SQLException {
		rs = queryExecutor.executeQuery("SELECT Thread_ID, Title FROM Threads WHERE Category_ID = " + category_ID + ";");
		threads = new ArrayList<Thread>();
		if (rs == null)
			return;
		while (rs.next()) {
			threads.add(new Thread(rs.getInt("Thread_ID"), rs.getString("Title"))); //TODO ein paar Sachen fehlen?
		}
		rs.close();
		ConnectionUtil.closeConnection(connection);
	}

	private void addThreadToDB(String title, String content, int user_ID, int category_ID) {
		queryExecutor.executeUpdate("INSERT INTO `pmforum`.`Threads` (`Title`, `Content`, `Date`, `User_ID`, `Category_ID`) VALUES ("
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
			addThreadToDB(thread.getTitle(), thread.getContent(), thread.getThreadStarterId(), thread.getCategoryId());
			rs = queryExecutor.executeQuery("SELECT LAST_INSERT_ID();");
			if (rs == null)
				return;
			while (rs.next())
				thread.setId(rs.getInt("Thread_ID"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
