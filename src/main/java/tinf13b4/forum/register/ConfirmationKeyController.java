package tinf13b4.forum.register;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.RandomStringUtils;

import tinf13b4.forum.database.ConnectionManager;

public class ConfirmationKeyController {

	// Get Database Connection
	// TODO - Throwing Exception When DBConfig is missing
	static ConnectionManager connection = new ConnectionManager();

	private String confirmationKey;
	public void setConfirmationKey (String confirmationKey) {
		this.confirmationKey = confirmationKey;
		confirmUserRegistration(confirmationKey);
	}
	
	// Get A Unique ConfirmationKey
	public String getUniqueConfirmationKey(String keyalphabet){
		
		// Generate Confirmation Key with given Alphabet
		String confirmationkey = generateConfirmationKey(keyalphabet);
		
		
		// If Confirmation Key Is Unique Return confirmationkey
		if (!findExistingKeys(confirmationkey)) {
			
			// Call Method Again
			return getUniqueConfirmationKey(keyalphabet);

		} else {
			return confirmationkey;
		}		
	}
	
	public ConfirmationKeyController() {
		// TODO Auto-generated constructor stub
	}
	
	// Generate Confirmation Key Return confirmationkey
	public String generateConfirmationKey(String keyalphabet) {

		// Generate Alpha-Numeric Random String 
		String confirmationkey = RandomStringUtils.random(36, 1, 36, true, true, keyalphabet.toCharArray());

		return confirmationkey;
	}

	
	// Check Existing Keys In Database
	public boolean findExistingKeys(String confirmationkey) {
		
		// Query Result
		try {

			// If Query Result Is Empty return true
			if (query(confirmationkey).next()) {
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
	private void confirmUserRegistration(String confirmationkey) {
		
		// Check Query Result
		try {
			ResultSet rs = query(confirmationkey);
			while(rs.next())
			{
				String userid = rs.getString(1);
				connection.insertCommand("UPDATE Users SET Confirmation_Key='0', Confirmed='1' WHERE  User_ID='" + userid + "';");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	// Get The Result Set From Database
	public ResultSet query(String confirmationkey) {
		
		// Query String
		ResultSet querystring = connection.executeCommand("SELECT User_ID, Confirmation_Key FROM Users WHERE Confirmation_Key='" + confirmationkey + "';");
	
		return querystring;
	}
}