package tinf13b4.forum.register;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateRegister {
	
	private String name;
	
	private String email;
	private String confirmemail;
	
	private String password;
	private String confirmpassword;
	
	private String error;
	
	private static final String emailpattern = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getConfirmemail() {
		return confirmemail;
	}

	public String getPassword() {
		return password;
	}

	public String getError() {
		return error;
	}

		 
	public EmailValidator() {
		email = email.compile(emailpattern);
	}
	 
	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 * hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {
	 
		matcher = email.matcher(hex);
		return matcher.matches();
	 
	}
}
