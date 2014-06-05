package tinf13b4.forum.search;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;

public class SearchBean {
	
	private ResultSet rs;
	private QueryExecutor executor;

	private String searchObject;
	private double destination;		

	public SearchBean() {
		ConnectionFactory factory = new ConnectionFactory();
		executor = new QueryExecutor(factory.createConnection());
	}
		
	public String getSearchObject() {
	    return searchObject;
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
	
		 //TODO �berpr�fung der Eingabe auf G�ltigkeit. Keine SQL-Befehle
	
	public ArrayList<Object> getResult() throws SQLException {
		 ArrayList<Object> objects;
			
		if(destination==1){
			rs = executor.executeQuery("SELECT * FROM Threads "
					+ "WHERE Content LIKE '%" + searchObject + "%' "
					+ "OR Title LIKE '%" + searchObject + "%';");

			if(rs == null)
				return new ArrayList<Object>();
			
			objects = new ArrayList<Object>();
			while(rs.next())
			{
				/*
				 * TODO if other changes can be pulled.
				 * Need build operations from Controller classes
				 * threads.add(buildThread());
				 */
			}
			return objects;
		}
		else if(destination==2){			
			rs = executor.executeQuery("SELECT User_ID, Name, Picture, Email, JoinedOn FROM Users "
			 		+ "WHERE Name LIKE '%" + searchObject + "%' "
			 		+ "AND Confirmed = 1;");

			if(rs == null)
				return new ArrayList<Object>();
			
			objects = new ArrayList<Object>();
			while(rs.next())
			{
				/*
				 * TODO if other changes can be pulled.
				 * Need build operations from Controller classes
				 */
			}
			return objects;
		}
		else if(destination==3){
		    rs = executor.executeQuery("SELECT * FROM Category "
		    		+ "WHERE Title LIKE '%" + searchObject + "%' "
		    		+ "OR Subtitle LIKE '%" + searchObject + "%';");

			if(rs == null)
				return new ArrayList<Object>();
			
			objects = new ArrayList<Object>();
			while(rs.next())
			{
				/*
				 * TODO if other changes can be pulled.
				 * Need build operations from Controller classes
				 */
			}
			return objects;
		}
		return new ArrayList<Object>();
	}
}