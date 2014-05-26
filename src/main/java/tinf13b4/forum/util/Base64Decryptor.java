
package tinf13b4.forum.util;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

public class Base64Decryptor {

	public static List<String> getDecryptedStringList(List<String> encryptedStringList) {
		checkArgument(encryptedStringList != null, "EncryptedStringList must not be null.");
		return decryptStringList(encryptedStringList);
	}

	private static List<String> decryptStringList(List<String> stringList) {
		List<String> decryptedStringList = new ArrayList<>();
		for (int i = 0; i < stringList.size(); i++) {
			decryptedStringList.add(i, decrypt(stringList.get(i)));
		}
		return decryptedStringList;
	}

	private static String decrypt(String string) {
		return new String(DatatypeConverter.parseBase64Binary(string));
	}
}
