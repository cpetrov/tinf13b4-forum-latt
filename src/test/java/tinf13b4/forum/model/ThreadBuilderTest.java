
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.mockito.Mockito;

public class ThreadBuilderTest {

	@Test
	public void testBuildsThread() {
		Date date = new Date();
		User user = Mockito.mock(User.class);
		ThreadBuilder builder = new ThreadBuilder().setCategoryId(1)
				.setContent("foo")
				.setDate(date)
				.setId(2)
				.setReadOnly(true)
				.setUser(user)
				.setTitle("bar");
		Thread thread = new Thread(2, user, 1, "bar", "foo", date, true);

		assertEquals(thread, builder.build());
	}
}
