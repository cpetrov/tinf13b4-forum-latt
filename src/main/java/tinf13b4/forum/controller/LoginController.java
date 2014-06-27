package tinf13b4.forum.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;

public class LoginController {

	static Connection connection = new ConnectionFactory().createConnection();
	static QueryExecutor queryExecutor = new QueryExecutor(connection);
	static PasswordController passwordController = new PasswordController();	
	
	public List<String> loginDataValidator(String logonCredential, String password) {
		
		// Create Error Array
		List<String> errors = new ArrayList<String>();
		
		// Validate With Database
		ResultSet result = queryExecutor.executeQuery("SELECT Name, Email, Password, Confirmed "
				+ "FROM Users WHERE "
				+ "AND Confirmed='1' "
				+ "Name='" + logonCredential + "' "
				+ "AND Confirmed='1' "
				+ "OR Email='" + logonCredential + "'"
				+ "AND Confirmed='1' LIMIT 1;");
		
		// Get query result into strings
		try {
			if (result.next()){
				String hashedPassword = result.getString(3);
				
				if (!passwordController.decryptPassword(password, hashedPassword)) {
					errors.add("The Username or Password is wrong");
				}
			}else {
				errors.add("User does not exist or registration is not finished");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return errors;
	}
}
