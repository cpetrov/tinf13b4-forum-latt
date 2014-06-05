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

	public void updateUser(int userId, String name, String picturePath, String mail) {
		checkUserArguments(userId, name, picturePath, mail);
		executor.executeUpdate("UPDATE Users "
								+ "SET Name = '" + name + "', Picture = '" + picturePath + "', Email = '" + mail + "' "
								+ "WHERE User_ID = " + userId + ";");
	}

	private void checkUserArguments(int userId, String name, String picturePath, String mail) {
		checkArgument(userId>0, "UserId must be > 0, but was: " + userId);
		checkArgument(name!=null, "Name must not be null.");
		checkArgument(!name.isEmpty(), "Name must not be empty.");
		checkArgument(picturePath!=null, "PicturePath must not be null.");
		checkArgument(!picturePath.isEmpty(), "PicturePath must not be empty.");
		checkArgument(mail!=null, "Mail must not be null.");
		checkArgument(!mail.isEmpty(), "Mail must not be empty.");
	}

}
