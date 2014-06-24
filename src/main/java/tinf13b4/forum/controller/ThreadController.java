package tinf13b4.forum.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static tinf13b4.forum.controller.ResultSetUtil.buildUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Thread;
import tinf13b4.forum.model.ThreadBuilder;

public class ThreadController {

	private ResultSet resultSet;
	private QueryExecutor executor;

	public ThreadController() {
		this(new QueryExecutor(new ConnectionFactory().createConnection()));
	}

	public ThreadController(QueryExecutor executor) {
		this.executor = executor;
	}

	public void setRs(ResultSet rs) {
		this.resultSet = rs;
	}

	public void createThread(String title, String content, int userId, int categoryId, boolean readOnly) {
		executor.executeUpdate("INSERT INTO Threads (Title, Content, Date, ReadOnly, User_ID, Category_ID) "
							 + "VALUES ('" + title + "', " +
							 		   "'" + content + "', " +
							 		   "'" + new Timestamp(new Date().getTime()) + "', "
							 		   	   + readOnly + ", "
							 		       + userId + ", "
							 		       + categoryId + ");");
	}

	public List<Thread> getThreadsWithCategory(int categoryId) {
		checkArgument(categoryId >= 0, "CategoryId must be >= 0, but was " + categoryId);
		resultSet = executor.executeQuery("SELECT Thread_ID, Title, Content, Date, ReadOnly, Category_ID, U.User_ID, U.Name, U.Picture, U.Email, U.JoinedOn, U.Confirmed "
											+ "FROM Threads T, Users U "
											+ "WHERE Category_ID = " + categoryId
											+ " AND T.User_ID = U.User_ID "
											+ "AND U.Confirmed = 1;");
		List<Thread> threads = new ArrayList<Thread>();
		if (resultSet == null)
			return new ArrayList<Thread>();
		else {
			try {
				while (resultSet.next()) {
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
		resultSet = executor.executeQuery("SELECT Thread_ID, Title, Content, Date, ReadOnly, Category_ID, U.User_ID, U.Name, U.Picture, U.Email, U.JoinedOn, U.Confirmed "
											+ "FROM Threads T, Users U "
											+ "WHERE T.Thread_ID = " + id
											+ " AND T.User_ID = U.User_ID "
											+ "AND U.Confirmed = 1;");
		List<Thread> threads = new ArrayList<Thread>();
		if (resultSet == null)
			return new ArrayList<Thread>();
		else {
			try {
				while (resultSet.next()) {
					threads.add(buildThread());
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return threads;
	}

	public List<Thread> getThreadsWithUser(int userId) {
		checkArgument(userId >= 0, "UserId must be >= 0, but was " + userId);
		resultSet = executor.executeQuery("SELECT Thread_ID, Title, Content, Date, ReadOnly, Category_ID, U.User_ID, U.Name, U.Picture, U.Email, U.JoinedOn, U.Confirmed "
											+ "FROM Threads T, Users U "
											+ "WHERE T.User_ID = U.User_ID "
											+ "AND U.Confirmed = 1 "
											+ "AND T.User_ID = " + userId);
		List<Thread> threads = new ArrayList<Thread>();
		if (resultSet == null)
			return new ArrayList<Thread>();
		else {
			try {
				while (resultSet.next()) {
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
		threadBuilder.setId(resultSet.getInt("Thread_ID"));
		threadBuilder.setCategoryId(resultSet.getInt("Category_ID"));
		threadBuilder.setTitle(resultSet.getString("Title"));
		threadBuilder.setContent(resultSet.getString("Content"));
		threadBuilder.setDate(new Date(resultSet.getTimestamp("Date").getTime()));
		threadBuilder.setReadOnly(resultSet.getBoolean("ReadOnly"));
		threadBuilder.setUser(buildUser(resultSet, new PostController(executor)));
		return threadBuilder.build();
	}

	public int getLastInsertId() {
		ResultSet set = executor.executeQuery("SELECT LAST_INSERT_ID();");
		try {
			set.next();
			return set.getInt("LAST_INSERT_ID()");
		} catch (SQLException e) {
			throw new IllegalStateException("SQL error while retrieving last insert id: " + e);
		}
	}
}
