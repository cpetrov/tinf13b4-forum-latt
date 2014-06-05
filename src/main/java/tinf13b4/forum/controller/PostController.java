package tinf13b4.forum.controller;

import static com.google.common.base.Preconditions.checkArgument;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Post;
import tinf13b4.forum.model.PostBuilder;

public class PostController {

	private QueryExecutor executor;
	private ArrayList<Post> posts;
	private ResultSet rs;
	
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public PostController() {
		ConnectionFactory factory = new ConnectionFactory();
		executor = new QueryExecutor(factory.createConnection());
	}

	protected PostController(QueryExecutor executor) {
		this.executor = executor;
	}
	
	public ArrayList<Post> getPosts(int threadId) {
		posts = new ArrayList<Post>();
		checkArgument(threadId>=0, "ThreadID must be >= 0, but was " + threadId);
		rs = executor.executeQuery("SELECT P.Post_ID, P.Content, P.Date, P.User_ID "
				+ "FROM Posts P, Threads T "
				+ "WHERE P.Thread_ID = T.Thread_ID "
				+ "AND T.Thread_ID = " + threadId + ";");
		if (rs == null)
			return posts;
		else {
			try {
				while (rs.next()) {
					posts.add(buildPost());
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return posts;
	}
	
	
	public Post buildPost() throws SQLException {
		PostBuilder postBuilder = new PostBuilder();
		postBuilder.setPostId(rs.getInt("Post_ID"));
		postBuilder.setUserId(rs.getInt("User_ID"));
		postBuilder.setThreadId(rs.getInt("Thread_ID"));
		postBuilder.setContent(rs.getString("Content"));
		postBuilder.setDate(rs.getDate("Date"));
		return postBuilder.build();
	}
}
