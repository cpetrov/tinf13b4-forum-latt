package tinf13b4.forum.search;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tinf13b4.forum.controller.CategoryController;
import tinf13b4.forum.controller.ThreadController;
import tinf13b4.forum.controller.UserController;
import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Category;
import tinf13b4.forum.model.Thread;
import tinf13b4.forum.model.User;

public class SearchBean {

	private ResultSet rs;
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
		rs = executor.executeQuery("SELECT * FROM Threads "
				+ "WHERE Content LIKE '%" + searchObject + "%' "
				+ "OR Title LIKE '%" + searchObject + "%';");

		if(rs == null)
			threads = new ArrayList<Thread>();
		
		threads = new ArrayList<Thread>();
		while(rs.next())
		{
			ThreadController controller = new ThreadController();
			controller.setRs(rs);
			threads.add(controller.buildThread());
		}
	}
	
	private void createUsers() throws SQLException {
		rs = executor.executeQuery("SELECT User_ID, Name, Picture, Email, JoinedOn FROM Users "
		 		+ "WHERE Name LIKE '%" + searchObject + "%' "
		 		+ "AND Confirmed = 1;");
		if(rs == null)
			users = new ArrayList<User>();
		
		users = new ArrayList<User>();
		while(rs.next())
		{
			UserController controller = new UserController();
			controller.setRs(rs);
			users.add(controller.buildUser());
		}
	}
	
	private void createCategories() throws SQLException {
	    rs = executor.executeQuery("SELECT * FROM Category "
	    		+ "WHERE Title LIKE '%" + searchObject + "%' "
	    		+ "OR Subtitle LIKE '%" + searchObject + "%';");

		if(rs == null)
			categories = new ArrayList<Category>();
		
		categories = new ArrayList<Category>();
		while(rs.next())
		{
			CategoryController controller = new CategoryController();
			controller.setRs(rs);
			categories.add(controller.buildCategory());
		}
	}
}