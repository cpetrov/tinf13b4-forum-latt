package tinf13b4.forum.util;

import org.apache.commons.lang3.RandomStringUtils;

public class AlphaNumericStringGeneratorUtil {
	
	public String generateAlphaNumericString (int stringLength) {
		
		// Generate String
		String alphaNumeric = RandomStringUtils.randomAlphanumeric(stringLength);
		
		return alphaNumeric;
	}
}
