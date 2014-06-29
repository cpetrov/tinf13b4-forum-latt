package tinf13b4.forum.controller;

import static com.google.common.base.Preconditions.checkArgument;
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

	public UserController(QueryExecutor executor) {
		this.executor = executor;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public List<User> getAllUsers() {
		PostController postController = new PostController(executor);
		resultSet = executor.executeQuery("SELECT User_ID, Name, Picture, Email, JoinedOn, Confirmed, Permission "
											+ "FROM Users;");
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

	public List<User> getUsers() {
		PostController postController = new PostController(executor);
		resultSet = executor.executeQuery("SELECT User_ID, Name, Picture, Email, JoinedOn, Confirmed, Permission "
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

	public int getUserId(String userName) {
		resultSet = executor.executeQuery("SELECT User_ID FROM Users WHERE Name LIKE '" + userName + "';");
		if(resultSet == null)
			return -1;
		else {
			try {
				while(resultSet.next()) {
					return resultSet.getInt("User_ID");
				}
			} catch (SQLException e) {
				throw new IllegalStateException("SQL Error: " + e);
			}
		}
		return -1;
	}

	public void updateUser(int userId, String picturePath) {
		checkUserId(userId);
		checkArgument(picturePath!=null, "PicturePath must not be null.");
		checkArgument(!picturePath.isEmpty(), "PicturePath must not be empty.");
		executor.executeUpdate("UPDATE Users SET Picture = '" + picturePath + "' WHERE User_Id = " + userId + ";");
	}

	public void updateUser(int userId, String mail, boolean confirmed) {
		checkUserArguments(userId, mail, confirmed);
		String command = "UPDATE Users SET Email = '" + mail +"', Confirmed = " + confirmed;
		if(confirmed)
			command += ", Confirmation_Key = 0 ";
		else
			command += " ";
		command += "WHERE User_Id = " + userId + ";";
		executor.executeUpdate(command);
	}

	private void checkUserArguments(int userId, String mail, boolean confirmed) {
		checkUserId(userId);
		checkArgument(mail!=null, "Mail must not be null.");
		checkArgument(!mail.isEmpty(), "Mail must not be empty.");
		checkArgument(confirmed == true | confirmed == false, "Confirmed must be true or false.");
	}

	private void checkUserId(int userId) {
		checkArgument(userId>0, "UserId must be > 0, but was: " + userId);
	}

}
