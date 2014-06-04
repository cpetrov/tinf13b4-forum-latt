package tinf13b4.forum.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Post;
import tinf13b4.forum.model.User;
import tinf13b4.forum.model.UserBuilder;

public class UserController {

	private ResultSet rs;
	private QueryExecutor executor;

	public UserController() {
		ConnectionFactory factory = new ConnectionFactory();
		executor = new QueryExecutor(factory.createConnection());
	}

	protected UserController(QueryExecutor executor) {
		this.executor = executor;
	}

	public List<User> getUsers() {
		rs = executor
				.executeQuery("SELECT User_ID, Name, Picture, Email, JoinedOn "
						+ "FROM Users "
						+ "WHERE Confirmed='1';");
		ArrayList<User> users = new ArrayList<User>();
		if (rs == null)
			return new ArrayList<User>();
		else {
			try {
				while (rs.next()) {
					users.add(buildUser());
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return users;
	}

	private User buildUser() throws SQLException {
		UserBuilder userBuilder = new UserBuilder();
		userBuilder.setId(rs.getInt("User_ID"));
		userBuilder.setName(rs.getString("Name"));
		userBuilder.setPicture(rs.getBlob("Picture"));
		userBuilder.setEmail(rs.getString("Email"));
		userBuilder.setJoinedOn(rs.getDate("JoinedOn"));
		userBuilder.setPosts(getPostsForUser(rs.getInt("User_ID")));
		return userBuilder.build();
	}

	private ArrayList<Post> getPostsForUser(int userId) {
		rs = executor.executeQuery("SELECT * FROM Posts "
				+ "WHERE User_ID = " + userId + ";");
		ArrayList<Post> posts = new ArrayList<Post>();
		if(rs == null)
			return new ArrayList<Post>();
		try {
			while(rs.next())
			{
				PostController postController = new PostController();
				postController.setRs(rs);
				posts.add(postController.buildPost());
			}
		} catch (SQLException e) {
			new IllegalStateException("SQL Error: " + e);
		}
		
		return posts;
	}

}
