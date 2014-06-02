package tinf13b4.forum.register;

import tinf13b4.forum.jBCrypt.BCrypt;

public class PasswordController {
	
	
	// Decrypt Password For Database Insert
	public String encryptpassword(String password) {
		
		// Hash Password
		String hashedpassword = BCrypt.hashpw(password, BCrypt.gensalt());
		
		return hashedpassword;
	}
	
	
	// Encrypt Password And Test Matching
	public boolean decryptpassword(String password, String hashedpassword) {
				
		// Check That An Unencrypted Password Matches
		if (BCrypt.checkpw(hashedpassword, password)) {
			return true;
		} else {
			return false;
		}
	}
}
