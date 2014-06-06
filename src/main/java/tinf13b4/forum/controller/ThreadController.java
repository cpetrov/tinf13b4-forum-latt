package tinf13b4.forum.controller;

import static com.google.common.base.Preconditions.checkArgument;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Thread;
import tinf13b4.forum.model.ThreadBuilder;

public class ThreadController {

	private ResultSet rs;
	private QueryExecutor executor;

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
	public ThreadController() {
		ConnectionFactory factory = new ConnectionFactory();
		executor = new QueryExecutor(factory.createConnection());
	}

	protected ThreadController(QueryExecutor executor) {
		this.executor = executor;
	}

	public List<Thread> getThreadsWithCategory(int categoryId) {
		checkArgument(categoryId >= 0, "CategoryId must be >= 0, but was " + categoryId);
		rs = executor.executeQuery("SELECT Thread_ID, Title, Content, Date, ReadOnly, Category_ID, U.User_ID, U.Name, U.Picture, U.Email, U.JoinedOn "
				+ "FROM Threads T, Users U "
				+ "WHERE Category_ID = " + categoryId
				+ " AND T.User_ID = U.User_ID "
				+ "AND U.Confirmed = 1;");
		List<Thread> threads = new ArrayList<Thread>();
		if (rs == null)
			return new ArrayList<Thread>();
		else {
			try {
				while (rs.next()) {
					threads.add(buildThread());
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return threads;
	}
	
	public List<Thread> getThreadsWithId(int id) {
		checkArgument(id >= 0, "CategoryId must be >= 0, but was " + id);
		rs = executor.executeQuery("SELECT Thread_ID, Title, Content, Date, ReadOnly, Category_ID, U.User_ID, U.Name, U.Picture, U.Email, U.JoinedOn "
				+ "FROM Threads T, Users U "
				+ "WHERE T.Thread_ID = " + id
				+ " AND T.User_ID = U.User_ID "
				+ "AND U.Confirmed = 1;");
		List<Thread> threads = new ArrayList<Thread>();
		if (rs == null)
			return new ArrayList<Thread>();
		else {
			try {
				while (rs.next()) {
					threads.add(buildThread());
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return threads;
	}

	public Thread buildThread() throws SQLException {
		ThreadBuilder threadBuilder = new ThreadBuilder();
		threadBuilder.setId(rs.getInt("Thread_ID"));
		threadBuilder.setCategoryId(rs.getInt("Category_ID"));
		threadBuilder.setTitle(rs.getString("Title"));
		threadBuilder.setContent(rs.getString("Content"));
		threadBuilder.setDate(new Date(rs.getTimestamp("Date").getTime()));
		threadBuilder.setReadOnly(rs.getBoolean("ReadOnly"));
		UserController controller = new UserController();
		controller.setRs(rs);
		threadBuilder.setUser(controller.buildUser());
		return threadBuilder.build();
	}
}
