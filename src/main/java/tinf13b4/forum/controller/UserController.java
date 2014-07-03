
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
				+ "FROM Users " + "WHERE Confirmed='1';");
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

	public User getUser(String userName) {
		PostController postController = new PostController(executor);
		resultSet = executor.executeQuery("SELECT User_ID, Name, Picture, Email, JoinedOn, Confirmed, Permission FROM Users WHERE Name LIKE '" + userName + "';");
		if(resultSet == null)
			return null;
		else {
			try {
				while(resultSet.next()) {
					return buildUser(resultSet, postController);
				}
			} catch (SQLException e) {
				throw new IllegalStateException("SQL Error: " + e);
			}
		}
		return null;
	}

	public void updateUser(int userId, String picturePath) {
		checkUserId(userId);
		checkArgument(picturePath != null, "PicturePath must not be null.");
		checkArgument(!picturePath.isEmpty(), "PicturePath must not be empty.");
		executor.executeUpdate("UPDATE Users SET Picture = '" + picturePath + "' WHERE User_Id = " + userId + ";");
	}

	public void updateUser(int userId, String mail, String password, boolean confirmed) {
		checkUserId(userId);
		String command = "UPDATE Users SET Confirmed = " + confirmed;
		if (mail != null){
			command += ", Email = '" + mail + "'";
		}
		if (confirmed)
			command += ", Confirmation_Key = 0";
		if (password != null) {
			String encryptedPassword = getEncryptedPassword(password);
			command += ", Password = '" + encryptedPassword + "'";
		}
		command += " WHERE User_Id = " + userId + ";";
		executor.executeUpdate(command);
	}

	private String getEncryptedPassword(String password) {
		PasswordController passwordController = new PasswordController();
		return passwordController.encryptPassword(password);
	}

	private void checkUserId(int userId) {
		checkArgument(userId > 0, "UserId must be > 0, but was: " + userId);
	}
}
