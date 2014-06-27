package tinf13b4.forum.controller;

import org.mindrot.jbcrypt.BCrypt;

import tinf13b4.forum.util.AlphaNumericStringGeneratorUtil;


public class PasswordController {
	
	private final AlphaNumericStringGeneratorUtil alphaNumericString;
	
	private static final int PASSWORD_LENGTH = 12;
	
	public PasswordController() {
		alphaNumericString = new AlphaNumericStringGeneratorUtil();
	}	

	// Decrypt Password For Database Insert
	public String encryptPassword(String password) {

		// Hash Password
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

		return hashedPassword;
	}


	// Encrypt Password And Test Matching
	public boolean decryptPassword(String password, String hashedPassword) {

		// Check That An Unencrypted Password Matches
		if (BCrypt.checkpw(password, hashedPassword)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String generateNewPassword() {
		
		// Generate Alpha-Numeric Random String
		String generatedPassword = alphaNumericString.generateAlphaNumericString(PASSWORD_LENGTH);
				
		return generatedPassword;
	}
}
