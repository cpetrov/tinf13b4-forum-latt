package tinf13b4.forum.beans;

import static tinf13b4.forum.controller.ResultSetUtil.buildCategory;
import static tinf13b4.forum.controller.ResultSetUtil.buildUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tinf13b4.forum.controller.CategoryController;
import tinf13b4.forum.controller.PostController;
import tinf13b4.forum.controller.ThreadController;
import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Category;
import tinf13b4.forum.model.Thread;
import tinf13b4.forum.model.User;

public class SearchBean {

	private ResultSet resultSet;
	private QueryExecutor executor;

	private String searchObject;
	private double destination;
	private List<Thread> threads;
	private List<User> users;
	private List<Category> categories;
	private PostController postController;
	private CategoryController categoryController;
	private ThreadController threadController;

	public SearchBean() {
		ConnectionFactory factory = new ConnectionFactory();
		executor = new QueryExecutor(factory.createConnection());
		postController = new PostController(executor);
		categoryController = new CategoryController(executor);
		threadController = new ThreadController(executor);
		threads = new ArrayList<>();
		users = new ArrayList<>();
		categories = new ArrayList<>();
	}

	public String getSearchObject() {
		return searchObject;
	}

	public List<Thread> getThreads() {
		try {
			getResult();
		} catch (SQLException e) {
			throw new IllegalStateException("SQL Error: " + e);
		}
		return threads;
	}

	public List<User> getUsers() {
		try {
			getResult();
		} catch (SQLException e) {
			throw new IllegalStateException("SQL Error: " + e);
		}
		return users;
	}

	public List<Category> getCategories() {
		try {
			getResult();
		} catch (SQLException e) {
			throw new IllegalStateException("SQL Error: " + e);
		}
		return categories;
	}

	public void setSearchObject(String searchObject) {
		this.searchObject = searchObject;
	}

	public double getDestination() {
		return destination;
	}

	public void setDestination(double destination) {
		this.destination = destination;
	}


	// Set the list below to the searched objects
	public void getResult() throws SQLException {
		String[] blacklist = {"SELECT", "INSERT", "UPDATE", "DELETE", "DROP", "CREATE", "USE", "SHOW", "ALTER", "LOAD"};
		if(searchObject == null)
			return;
		for(String item : blacklist)
			if(searchObject.equals(item))
				return;

		switch ((int) destination) {
		case 1:
			createThreads();
			break;
		case 2:
			createUsers();
			break;
		case 3:
			createCategories();
			break;
		}
	}

	private void createThreads() throws SQLException {
		resultSet = executor.executeQuery("SELECT Thread_ID, Title, Content, Date, ReadOnly, Category_ID, U.User_ID, U.Name, U.Picture, U.Email, U.JoinedOn FROM Threads T, Users U "
				+ "WHERE Content LIKE '%" + searchObject + "%' "
				+ "OR Title LIKE '%" + searchObject + "%';");

		if(resultSet == null)
			return;

		while(resultSet.next()) {
			threadController.setRs(resultSet);
			threads.add(threadController.buildThread());
		}
	}

	private void createUsers() throws SQLException {
		resultSet = executor.executeQuery("SELECT User_ID, Name, Picture, Email, JoinedOn FROM Users "
		 		+ "WHERE Name LIKE '%" + searchObject + "%' "
		 		+ "AND Confirmed = 1;");
		if(resultSet == null)
			return;

		while(resultSet.next()) {
			users.add(buildUser(resultSet, postController));
		}
	}

	private void createCategories() throws SQLException {
	    resultSet = executor.executeQuery("SELECT * FROM Categories "
	    		+ "WHERE Title LIKE '%" + searchObject + "%' "
	    		+ "OR Subtitle LIKE '%" + searchObject + "%';");

		if(resultSet == null)
			return;

		while(resultSet.next())
		{
			categoryController.setRs(resultSet);
			categories.add(buildCategory(resultSet));
		}
	}
}