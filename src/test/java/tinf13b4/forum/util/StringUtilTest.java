
package tinf13b4.forum.util;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

public class StringUtilTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullInputStream() {
		StringUtil.getStringListFromInputStream(null);
	}

	@Test
	public void testReturnsListOfStringsFromInputStream() {
		InputStream inputStream = new ByteArrayInputStream("foo\nbar".getBytes());

		List<String> stringList = StringUtil.getStringListFromInputStream(inputStream);

		assertEquals("foo", stringList.get(0));
		assertEquals("bar", stringList.get(1));
	}
}
