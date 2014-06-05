package search;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.*;

import tinf13b4.forum.database.ConnectionManager;

public class Searchbean {

	private String what;
	private double where;		

	ConnectionManager conn = new ConnectionManager();
		
	public String getwhat() {
	    return what;
	}
	
	public void setwhat(String what) {
	    this.what = what;
	}
	
	public double getwhere() {
	    return where;
	}
	
	public void setwhere(double where) {
		this.where=where;
	
}
	
		 //TODO Überprüfung der Eingabe auf Gültigkeit. Keine SQL-Befehle
	
	public String getResult() throws SQLException{
		 ResultSet res;
		 String result="";
			
		if(where==1){
			res=conn.executeCommand("SELECT Content, Date FROM Threads WHERE Content LIKE "+what + ";");
			result=res.getString(2);
		}
		else if(where==2){
			 res=conn.executeCommand("SELECT Content, Date FROM Posts WHERE User_ID LIKE" +what+ ";");
			 result=res.getString(3);
		}
		else if(where==3){
		    res=conn.executeCommand("SELECT Name FROM Category WHERE Title LIKE "+what+";");
		    result=res.getString(2);
		}
		
		 
		 
		
		return result;
	}
}
	
