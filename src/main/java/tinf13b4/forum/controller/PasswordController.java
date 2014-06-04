package tinf13b4.forum.controller;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordController {

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
}
