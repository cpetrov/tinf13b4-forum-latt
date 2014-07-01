package tinf13b4.forum.util;

import java.util.List;

public class OverwriteVariablesWithResultUtil {

	public List<String> errors;
	public String username;
	public String emailAddress;
	
	public OverwriteVariablesWithResultUtil(List<String> errors, String username, String emailAddress){
		this.errors = errors;
		this.username = username;
		this.emailAddress = emailAddress;
	}
	
}
