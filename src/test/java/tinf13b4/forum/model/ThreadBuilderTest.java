
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class ThreadBuilderTest {

	@Test
	public void testBuildsThread() {
		Date date = new Date();
		ThreadBuilder builder = new ThreadBuilder().setCategoryId(1)
													.setContent("foo")
													.setDate(date)
													.setId(2)
													.setReadonly(true)
													.setThreadStarterId(3)
													.setTitle("bar");
		Thread thread = new Thread(2, 3, 1, "bar", "foo", date, true);
		
		assertEquals(thread, builder.build());
	}
}
