package tinf13b4.forum.register;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;

public class RegisterDataValidator {


	private final QueryExecutor queryExecutor;

	public RegisterDataValidator() {
		Connection connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
	}

	// Description
	// TODO - JS musst be the same
	//
	// ^                    		# 	Start of the line
	// 		[A-Za-z0-9_-]			# 	Match characters and symbols in the list, a-z, 0-9, underscore, hyphen
	// 			{3,15}  			# 	Length at least 3 characters and maximum length of 15
	// $                    		# 	End of the line

	private static final String USERNAME_PATTERN = "^[A-Za-z0-9_-]{3,15}$";


	// Description
	// TODO - JS musst be the same
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
		if(regexDataValidator(username, emailAddress, password).size() > 0){

			// Return Regular Expression Errors
			return regexDataValidator(username, emailAddress, password);
		} else {

			// Return Database Errors
			return databaseDataValidator(username, emailAddress, password);
		}
	}


	public List<String> regexDataValidator(String username, String emailAddress, String password) {

		// Create Error Array
		List<String> errors = new ArrayList<String>();


		// Validate With Regular Expressions
		if (!username.matches(USERNAME_PATTERN)) {
			errors.add("W�hlen Sie einen neuen Namen");
		}

		if (!emailAddress.matches(EMAIL_PATTERN)) {
			errors.add("Email Adresse nicht g�ltig");
		}

		if (!password.matches(PASSWORD_PATTERN)) {
			errors.add("Passwort entspricht nicht den Anforderungen");
		}


		// Return Error List
		return errors;

	}


	public List<String>  databaseDataValidator(String username, String emailAddress, String password) {

		// Create Error Array
		List<String> errors = new ArrayList<String>();


		// Validate With Database
		ResultSet querystring = queryExecutor.executeQuery("SELECT Name, Email "
				+ "FROM Users WHERE "
				+ "Name='" + username + "' "
				+ "OR Email='" + emailAddress + "';");


		// Check Query Result
		try {
			if (querystring.next()) {

				// Move Cursor To The Beginning Of The Line
				querystring.previous();

				while (querystring.next()) {

					String name = querystring.getString(1);
					String email = querystring.getString(2);

					if (name.equals(username)) {
						errors.add("Name bereits vergeben");
					}

					if (email.equals(emailAddress)) {
						errors.add("E-Mail Adresse bereits vergeben");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		// Return List Of Errors
		return errors;

	}
}
