
package tinf13b4.forum.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.RandomStringUtils;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;

public class ConfirmationKeyController {

	private final QueryExecutor queryExecutor;
	private final Connection connection;
	
	private static final int CONFIRMATION_KEY_LENGHT = 36;
	private static final int CONFIRMATION_KEY_BEGIN = 1;
	private static final boolean CONFIRMATION_KEY_ALLOW_NUMBERS = true;
	private static final boolean CONFIRMATION_KEY_ALLOW_LETTERS = true;

	public ConfirmationKeyController() {
		connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
	}

	public void setConfirmationKey(String confirmationKey) {
		confirmUserRegistration(confirmationKey);
	}

	// Get A Unique ConfirmationKey
	public String getUniqueConfirmationKey(String keyAlphabet) {
		// Generate Confirmation Key with given Alphabet
		String confirmationKey = generateConfirmationKey(keyAlphabet);
		// If Confirmation Key Is Unique Return confirmationkey
		if (!findExistingKeys(confirmationKey)) {
			// Call Method Again
			return getUniqueConfirmationKey(keyAlphabet);
		} else {
			return confirmationKey;
		}
	}

	// Generate Confirmation Key Return confirmationkey
	public String generateConfirmationKey(String keyAlphabet) {
		// Generate Alpha-Numeric Random String
		String confirmationKey = RandomStringUtils.random(CONFIRMATION_KEY_LENGHT, CONFIRMATION_KEY_BEGIN, CONFIRMATION_KEY_LENGHT, CONFIRMATION_KEY_ALLOW_LETTERS, CONFIRMATION_KEY_ALLOW_NUMBERS, keyAlphabet.toCharArray());
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