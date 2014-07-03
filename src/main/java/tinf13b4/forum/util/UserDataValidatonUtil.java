package tinf13b4.forum.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;

public class UserDataValidatonUtil {


	private final QueryExecutor queryExecutor;

	public UserDataValidatonUtil() {
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

	
	public List<String> regexUsernameValidation(String username) {
		
		// Create Error Array
		List<String> errors = new ArrayList<String>();
		
		// Validate With Regular Expressions
		if (!username.matches(USERNAME_PATTERN)) {
			errors.add("The username is invalid, it´s only allowed to use letters and numbers");
		}
		
		return errors;
	}
	
	
	public List<String> regexEmailAddressValidation(String emailAddress) {
		
		// Create Error Array
		List<String> errors = new ArrayList<String>();
		
		// Validate With Regular Expressions
		if (!emailAddress.matches(EMAIL_PATTERN)) {
			errors.add("The email address is not valid");
		}
		
		return errors;
	}
	
	
	public List<String> regexPasswordValidation(String password) {
		
		// Create Error Array
		List<String> errors = new ArrayList<String>();
		
		// Validate With Regular Expressions
		if (!password.matches(PASSWORD_PATTERN)) {
			errors.add("The password is invalid please use a big letter, small letter, and one of these @, #, $, %");
		}
		
		return errors;
	}
		
		
	private ResultSet userdataQuery(String username, String emailAddress) {
		
		// Validate With Database
		ResultSet rs = queryExecutor.executeQuery("SELECT Name, Email "
				+ "FROM Users WHERE "
				+ "Name='" + username + "' "
				+ "OR Email='" + emailAddress + "';");
		
		return rs;
	}
	
	
	public List<String> registerDataValidation(String username, String emailAddress, String password) {
		
		// Create Error Array
		List<String> errors = new ArrayList<String>();
		
		// Get Errors from Regex Validations
		List<String> regexUsernameValidation = regexUsernameValidation(username);
		List<String> regexEmailaddressValidation = regexEmailAddressValidation(emailAddress);
		List<String> regexPasswordValidation = regexPasswordValidation(password);
		
		errors.addAll(regexUsernameValidation);
		errors.addAll(regexEmailaddressValidation);
		errors.addAll(regexPasswordValidation);
		
		// Validate Form Data
		if(errors.size() > 0){
			
			// Return Regular Expression Errors
			return errors;
			
		} else {
			
			// Return Database Errors
			return userdataDatabaseValidaton(username, emailAddress);
		}
	}
	
	public List<String>  userdataDatabaseValidaton(String username, String emailAddress) {

		// Create Error Array
		List<String> errors = new ArrayList<String>();

		ResultSet rs = userdataQuery(username, emailAddress);

		// Check Query Result
		try {
			if (rs.next()) {

				// Move Cursor To The Beginning Of The Line
				rs.previous();

				while (rs.next()) {

					String name = rs.getString(1);
					String email = rs.getString(2);

					if (name.equals(username)) {
						errors.add("Usernmae already taken");
					}

					if (email.equals(emailAddress)) {
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
	
	
	public OverwriteVariablesWithResultUtil  forgottenDatabaseDataValidator(String username, String emailAddress) {

		// Create Error Array
		List<String> errors = new ArrayList<String>();

		ResultSet rs = userdataQuery(username, emailAddress);

		
		// Check Query Result
		try {
			if (rs.next()) {

				// Move Cursor To The Beginning Of The Line
				rs.previous();

				while (rs.next()) {

					String name = rs.getString(1);
					String email = rs.getString(2);

					if (!name.equals(username) && !email.equals(emailAddress)) {
						errors.add("Username or E-Mail address doe´s not exist");
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
