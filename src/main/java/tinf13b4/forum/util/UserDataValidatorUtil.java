package tinf13b4.forum.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;

public class UserDataValidatorUtil {


	private final QueryExecutor queryExecutor;

	public UserDataValidatorUtil() {
		Connection connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
	}

	// Description
	//
	// ^                    		# 	Start of the line
	// 		[A-Za-z0-9_-]			# 	Match characters and symbols in the list, a-z, 0-9, underscore, hyphen
	// 			{3,15}  			# 	Length at least 3 characters and maximum length of 15
	// $                    		# 	End of the line

	private static final String USERNAME_PATTERN = "^[A-Za-z0-9_-]{3,15}$";


	// Description
	//
	// (							# 	Start of group
	//		(?=.*\d)				#   must contains one digit from 0-9
	// 		(?=.*[a-z])				#   must contains one lowercase characters
	//		(?=.*[A-Z])				#   must contains one uppercase characters
	//		(?=.*[@#$%])			#   must contains one special symbols in the list "@#$%"
	//			.					#	match anything with previous condition checking
	//	     		{6,20}			#   length at least 6 characters and maximum of 20
	// )							# 	End of group

	private static final String PASSWORD_PATTERN =
			"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";


	// Description
	//
	// ^							#	start of the line
	//  	[_A-Za-z0-9-\\+]+		#  	must start with string in the bracket [ ], must contains one or more (+)
	//  	(						#   start of group #1
	//    		\\.[_A-Za-z0-9-]+	#   follow by a dot "." and string in the bracket [ ], must contains one or more (+)
	//  	)*						#   end of group #1, this group is optional (*)
	//    	@						#   must contains a "@" symbol
	//     	[A-Za-z0-9-]+      		#   follow by string in the bracket [ ], must contains one or more (+)
	//      (						#   start of group #2 - first level TLD checking
	//      	\\.[A-Za-z0-9]+  	#   follow by a dot "." and string in the bracket [ ], must contains one or more (+)
	//      )*						#   end of group #2, this group is optional (*)
	//      (						#   start of group #3 - second level TLD checking
	//       	\\.[A-Za-z]{2,}  	#   follow by a dot "." and string in the bracket [ ], with minimum length of 2
	//      )						#   end of group #3
	// $							#	end of the line

	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	
	public List<String> registerDataValidator(String username, String emailAddress, String password) {

		// Validate Form Data
		if(registerRegexDataValidator(username, emailAddress, password).size() > 0){

			// Return Regular Expression Errors
			return registerRegexDataValidator(username, emailAddress, password);
		} else {

			// Return Database Errors
			return registerDatabaseDataValidator(username, emailAddress);
		}
	}
	

	public List<String> registerRegexDataValidator(String username, String emailAddress, String password) {

		// Create Error Array
		List<String> errors = new ArrayList<String>();


		// Validate With Regular Expressions
		if (!checkUsername(username)) {
			errors.add("The username is invalid, it´s only allowed to use letters and numbers");
		}

		if (!checkMail(emailAddress)) {
			errors.add("The email address is not valid");
		}

		if (!checkPassword(password)) {
			errors.add("The password is invalid please use a big letter, small letter, @, #, $, %");
		}

		// Return Error List
		return errors;

	}
	
	public boolean checkUsername(String username) {
		if (!username.matches(USERNAME_PATTERN)) {
			return false;
		}
		return true;
	}
	
	public boolean checkMail(String emailAddress) {
		if (!emailAddress.matches(EMAIL_PATTERN)) {
			return false;
		}
		return true;
	}
	
	public boolean checkPassword(String password) {
		if (!password.matches(PASSWORD_PATTERN)) {
			return false;
		}
		return true;
	}
	
	public List<String>  checkExistingEmailAdress(String emailAddress) {

		// Create Error Array
		List<String> errors = new ArrayList<String>();


		// Validate With Database
		ResultSet querystring = queryExecutor.executeQuery("SELECT UPPER(Email) "
				+ "FROM Users WHERE "
				+ "OR Email='" + emailAddress.toUpperCase() + "';");


		// Check Query Result
		try {
			if (querystring.next()) {

				// Move Cursor To The Beginning Of The Line
				querystring.previous();

				while (querystring.next()) {

					String email = querystring.getString(1);

					if (email.equalsIgnoreCase(emailAddress)) {
						errors.add("E-Mail address already taken");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		// Return List Of Errors
		return errors;

	}
	
	
	public List<String>  registerDatabaseDataValidator(String username, String emailAddress) {

		// Create Error Array
		List<String> errors = new ArrayList<String>();

		ResultSet querystring = validateData(username, emailAddress);

		// Check Query Result
		try {
			if (querystring.next()) {

				// Move Cursor To The Beginning Of The Line
				querystring.previous();

				while (querystring.next()) {

					String name = querystring.getString(1);
					String email = querystring.getString(2);

					if (name.equalsIgnoreCase(username)) {
						errors.add("Username already taken");
					}

					if (email.equalsIgnoreCase(emailAddress)) {
						errors.add("E-Mail address already taken");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		// Return List Of Errors
		return errors;

	}
	
	public ResultSet validateData(String username, String emailAddress) {
		// Validate With Database
		ResultSet querystring = queryExecutor.executeQuery("SELECT UPPER(Name), UPPER(Email) "
				+ "FROM Users WHERE "
				+ "Name='" + username.toUpperCase() + "' "
				+ "OR Email='" + emailAddress.toUpperCase() + "';");
		return querystring;
				
	}
	
	public OverwriteVariablesWithResultUtil  forgottenDatabaseDataValidator(String username, String emailAddress) {

		// Create Error Array
		List<String> errors = new ArrayList<String>();

		ResultSet querystring = validateData(username, emailAddress);

		// Check Query Result
		try {
			if (querystring.next()) {

				// Move Cursor To The Beginning Of The Line
				querystring.previous();

				while (querystring.next()) {

					String name = querystring.getString(1);
					String email = querystring.getString(2);

					if (!name.equalsIgnoreCase(username) && !email.equalsIgnoreCase(emailAddress)) {
						errors.add("Username or E-Mail address does not exist");
					} else {
						emailAddress = email;
						username = name;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		// Return List Of Errors
		return new OverwriteVariablesWithResultUtil(errors, username, emailAddress);

	}
}
