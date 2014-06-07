package tinf13b4.forum.search;

import static tinf13b4.forum.controller.ResultSetUtil.buildCategory;
import static tinf13b4.forum.controller.ResultSetUtil.buildUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	private ArrayList<Thread> threads;
	private ArrayList<User> users;
	private ArrayList<Category> categories;

	public SearchBean() {
		ConnectionFactory factory = new ConnectionFactory();
		executor = new QueryExecutor(factory.createConnection());
	}

	public String getSearchObject() {
		return searchObject;
	}

	public ArrayList<Thread> getThreads() {
		try {
			getResult();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return threads;
	}

	public ArrayList<User> getUsers() {
		try {
			getResult();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<Category> getCategories() {
		try {
			getResult();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		resultSet = executor.executeQuery("SELECT * FROM Threads "
				+ "WHERE Content LIKE '%" + searchObject + "%' "
				+ "OR Title LIKE '%" + searchObject + "%';");

		if(resultSet == null)
			threads = new ArrayList<Thread>();

		threads = new ArrayList<Thread>();
		while(resultSet.next())
		{
			ThreadController controller = new ThreadController();
			controller.setRs(resultSet);
			threads.add(controller.buildThread());
		}
	}

	private void createUsers() throws SQLException {
		resultSet = executor.executeQuery("SELECT User_ID, Name, Picture, Email, JoinedOn FROM Users "
		 		+ "WHERE Name LIKE '%" + searchObject + "%' "
		 		+ "AND Confirmed = 1;");
		if(resultSet == null)
			users = new ArrayList<User>();

		users = new ArrayList<User>();
		while(resultSet.next()) {
			users.add(buildUser(resultSet, new PostController(executor)));
		}
	}

	private void createCategories() throws SQLException {
	    resultSet = executor.executeQuery("SELECT * FROM Category "
	    		+ "WHERE Title LIKE '%" + searchObject + "%' "
	    		+ "OR Subtitle LIKE '%" + searchObject + "%';");

		if(resultSet == null)
			categories = new ArrayList<Category>();

		categories = new ArrayList<Category>();
		while(resultSet.next())
		{
			CategoryController controller = new CategoryController();
			controller.setRs(resultSet);
			categories.add(buildCategory(resultSet));
		}
	}
}