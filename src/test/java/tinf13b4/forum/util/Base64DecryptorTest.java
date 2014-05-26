
package tinf13b4.forum.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Base64DecryptorTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullStringList() {
		Base64Decryptor.getDecryptedStringList(null);
	}

	@Test
	public void testDecryptsStringList() {
		List<String> list = new ArrayList<>();
		list.add("Zm9v");
		list.add("YmFy");

		List<String> decryptedList = Base64Decryptor.getDecryptedStringList(list);

		assertEquals("foo", decryptedList.get(0));
		assertEquals("bar", decryptedList.get(1));
	}
}
