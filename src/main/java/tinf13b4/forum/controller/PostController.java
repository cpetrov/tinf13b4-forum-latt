package tinf13b4.forum.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static tinf13b4.forum.controller.ResultSetUtil.getPostsCount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Post;

public class PostController {

	private QueryExecutor executor;
	private ResultSet resultSet;

	public PostController() {
		this(new QueryExecutor(new ConnectionFactory().createConnection()));
	}

	public PostController(QueryExecutor executor) {
		this.executor = executor;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public void addPostToThread(int threadId, int userId, String content) {
		executor.executeUpdate("INSERT INTO Posts (Content, Date, Thread_ID, User_ID) "
								+ "VALUES ('" +
									content + "', '" +
									new Timestamp(new Date().getTime()) + "', '" +
									threadId + "', '" +
									userId + "')");
	}

	public List<Post> getPostsForThread(int threadId) {
		List<Post> posts = new ArrayList<Post>();
		checkArgument(threadId>0, "ThreadID must be > 0, but was " + threadId);
		resultSet = executor.executeQuery("SELECT P.Post_ID, P.Content, P.Date, P.User_ID, U.Name, U.Picture, U.Email, U.JoinedOn, U.Confirmed, U.Permission "
											+ "FROM Posts P, Threads T, Users U "
											+ "WHERE P.Thread_ID = T.Thread_ID "
											+ "AND T.Thread_ID = " + threadId + " "
											+ "AND P.User_ID = U.User_ID "
											+ "AND U.Confirmed = 1 "
											+ "ORDER BY Date ASC;");
		if (resultSet == null)
			return posts;
		else {
			try {
				while (resultSet.next()) {
					posts.add(ResultSetUtil.buildPostForThreadId(resultSet, this, threadId));
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return posts;
	}

	public List<Post> getPostsForUser(int userId) {
		List<Post> posts = new ArrayList<Post>();
		checkArgument(userId>0, "UserId must be > 0, but was " + userId);
		resultSet = executor.executeQuery("SELECT P.Post_ID, P.Content, P.Date, P.User_ID, U.Name, U.Picture, U.Email, U.JoinedOn, U.Confirmed, U.Permission "
				+ "FROM Posts P, Threads T, Users U "
				+ "WHERE P.Thread_ID = T.Thread_ID "
				+ "AND P.User_ID = U.User_ID "
				+ "AND U.Confirmed = 1 "
				+ "AND P.User_ID = " + userId + " "
				+ "ORDER BY Date DESC;");
		if (resultSet == null)
			return posts;
		else {
			try {
				while (resultSet.next()) {
					posts.add(ResultSetUtil.buildPostForThreadId(resultSet, this, userId));
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return posts;
	}

	public int getPostsCountForUser(int userId) {
		return getPostsCount(executor.executeQuery("SELECT COUNT(*) FROM Posts WHERE User_ID = " + userId));
	}
}
