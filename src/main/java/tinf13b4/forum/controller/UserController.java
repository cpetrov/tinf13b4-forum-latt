package tinf13b4.forum.controller;

import static tinf13b4.forum.controller.ResultSetUtil.buildUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.User;

public class UserController {

	private ResultSet resultSet;
	private QueryExecutor executor;

	public UserController() {
		this(new QueryExecutor(new ConnectionFactory().createConnection()));
	}

	protected UserController(QueryExecutor executor) {
		this.executor = executor;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public List<User> getUsers() {
		PostController postController = new PostController(executor);
		resultSet = executor.executeQuery("SELECT User_ID, Name, Picture, Email, JoinedOn "
									+ "FROM Users "
									+ "WHERE Confirmed='1';");
		ArrayList<User> users = new ArrayList<User>();
		if (resultSet == null)
			return new ArrayList<>();
		else {
			try {
				while (resultSet.next()) {
					users.add(buildUser(resultSet, postController));
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return users;
	}

}
