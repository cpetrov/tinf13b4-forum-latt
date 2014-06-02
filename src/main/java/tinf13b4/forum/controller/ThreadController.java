
package tinf13b4.forum.controller;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
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

	private ResultSet resultSet;
	private QueryExecutor executor;

	public ThreadController() {
		ConnectionFactory factory = new ConnectionFactory(new File("src/main/config/DBConfig.cfg"));
		executor = new QueryExecutor(factory.createConnection());
	}

	protected ThreadController(QueryExecutor executor) {
		this.executor = executor;
	}

	public List<Thread> getThreads(int categoryId) {
		checkArgument(categoryId>=0, "CategoryId must be >= 0, but was " + categoryId);
		resultSet = executor.executeQuery("SELECT * FROM Thread WHERE Category_ID = " + categoryId + ";");
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

	private Thread buildThread() throws SQLException {
		ThreadBuilder threadBuilder = new ThreadBuilder();
		threadBuilder.setId(resultSet.getInt("Thread_ID"));
		threadBuilder.setCategoryId(resultSet.getInt("Category_ID"));
		threadBuilder.setTitle(resultSet.getString("Title"));
		threadBuilder.setContent(resultSet.getString("Content")); //TODO not implemented in the DB?
		threadBuilder.setDate(new Date(resultSet.getDate("Date").getTime())); //TODO not implemented in the DB?
		threadBuilder.setReadonly(resultSet.getBoolean("Readonly")); //TODO not implemented in the DB?
		threadBuilder.setThreadStarterId(resultSet.getInt("Thread_Starter_ID")); //TODO not implemented in the DB?
		return threadBuilder.build();
	}
}
