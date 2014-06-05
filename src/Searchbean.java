

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
		//prüft ob gültige Eingabe
	    if (what.matches("true")==false){
			 what="";}
	    else{
	    	this.where = where;
	    }
}
	
		 
	
	public String getResult() throws SQLException{
		 ResultSet res;
		 String result="test";
			
		if(where==1){
			res=conn.executeCommand("SELECT Content, Date FROM Threads, Posts WHERE Content LIKE '%"+what+"%';");
			result=res.getString(2);
		}
		else if(where==2){
			 res=conn.executeCommand("SELECT Content, Date FROM Posts WHERE Poster LIKE '%"+what+"%';");
			 result=res.getString(3);
		}
		else if(where==3){
		    res=conn.executeCommand("SELECT Name FROM Categories WHERE Name LIKE '%"+what+"%';");
		    result=res.getString(2);
		}
		
		 
		 
		
		return result;
	}
}
	
