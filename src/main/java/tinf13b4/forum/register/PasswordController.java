package tinf13b4.forum.register;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordController {


	// Decrypt Password For Database Insert
	public String encryptPassword(String password) {

		// Hash Password
		String hashedpassword = BCrypt.hashpw(password, BCrypt.gensalt());

		return hashedpassword;
	}


	// Encrypt Password And Test Matching
	public boolean decryptPassword(String password, String hashedPassword) {

		// Check That An Unencrypted Password Matches
		if (BCrypt.checkpw(hashedPassword, password)) {
			return true;
		} else {
			return false;
		}
	}
}
