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
	
	public static void main(String[] args) {
		
		System.out.print(loginDataValidator("Tester01", "Test123@"));
	}
	
	public static List<String> loginDataValidator(String logonCredential, String password) {
		
		// Create Error Array
		List<String> errors = new ArrayList<String>();
		
		// Validate With Database
		ResultSet queryString = queryExecutor.executeQuery("SELECT Name, Email, Password, Confirmed "
				+ "FROM Users WHERE "
				+ "Name='" + logonCredential + "' "
				+ "OR Email='" + logonCredential + "'"
				+ "AND Confirmed='1';");
		
		// Get query result into strings
		try {
			if (queryString.next()){
				
				queryString.previous();
				
				while (queryString.next()) {
	
					String hashedPassword = queryString.getString(3);
					
					if (passwordController.decryptPassword(password, hashedPassword)) {
						errors.add("Das eingegebene Passwort ist falsch");
					}
				}
			}else {
				errors.add("Der eingebene Benutzer existiert nicht oder die Registrierung ist nicht abgeschlossen");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return errors;
	}
}
