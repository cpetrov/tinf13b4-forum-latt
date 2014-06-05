package tinf13b4.forum.search;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;

public class Searchbean {
	
	private ResultSet rs;
	private QueryExecutor executor;

	private String what;
	private double where;		

	public Searchbean() {
		ConnectionFactory factory = new ConnectionFactory(new File("src/main/config/DBConfig.cfg"));
		executor = new QueryExecutor(factory.createConnection());
	}
		
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
		 String result="";
			
		if(where==1){
			rs = executor.executeQuery("SELECT Content, Date FROM Threads WHERE Content LIKE "+what + ";");
			result = rs.getString(2);
		}
		else if(where==2){
			 rs = executor.executeQuery("SELECT Content, Date FROM Posts WHERE User_ID LIKE" +what+ ";");
			 result = rs.getString(3);
		}
		else if(where==3){
		    rs = executor.executeQuery("SELECT Name FROM Category WHERE Title LIKE "+what+";");
		    result = rs.getString(2);
		}		
		return result;
	}
}