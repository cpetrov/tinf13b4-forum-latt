
package tinf13b4.forum.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;

public class ConfirmationKeyUtil {

	private final QueryExecutor queryExecutor;
	private final Connection connection;
	private final AlphaNumericStringGeneratorUtil alphaNumericString;
	
	private static final int CONFIRMATION_KEY_LENGTH = 36;
	
	public ConfirmationKeyUtil() {
		connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
		alphaNumericString = new AlphaNumericStringGeneratorUtil();
	}

	
	public void setConfirmationKey(String confirmationKey) {
		confirmUserRegistration(confirmationKey);
	}
	

	// Get A Unique ConfirmationKey
	public String getUniqueConfirmationKey() {
		// Generate Confirmation Key with given Alphabet
		String confirmationKey = generateConfirmationKey();
		// If Confirmation Key Is Unique Return confirmationkey
		if (!findExistingKeys(confirmationKey)) {
			// Call Method Again
			return getUniqueConfirmationKey();
		} else {
			return confirmationKey;
		}
	}

	
	// Generate Confirmation Key Return confirmationkey
	public String generateConfirmationKey() {
		// Generate Alpha-Numeric Random String
		String confirmationKey = alphaNumericString.generateAlphaNumericString(CONFIRMATION_KEY_LENGTH);
		
		return confirmationKey;
	}

	
	// Check Existing Keys In Database
	public boolean findExistingKeys(String confirmationKey) {
		// Query Result
		try {
			// If Query Result Is Empty return true
			if (query(confirmationKey).next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	// Confirm User Registration And Clean Database
	private void confirmUserRegistration(String confirmationKey) {
		// Check Query Result
		try {
			ResultSet rs = query(confirmationKey);
			while (rs.next()) {
				String userid = rs.getString(1);
				queryExecutor.executeUpdate("UPDATE Users SET Confirmation_Key='0', Confirmed='1' WHERE  User_ID='"
						+ userid + "';");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// Get The Result Set From Database
	public ResultSet query(String confirmationKey) {
		// Query String
		ResultSet queryString = queryExecutor
				.executeQuery("SELECT User_ID, Confirmation_Key FROM Users WHERE Confirmation_Key='" + confirmationKey
						+ "';");
		return queryString;
	}
}
