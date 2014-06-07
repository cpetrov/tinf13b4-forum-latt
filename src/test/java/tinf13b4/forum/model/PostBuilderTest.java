
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Test;

public class PostBuilderTest {

	@Test
	public void testBuildsPost() {
		User user = mock(User.class);
		Date date = mock(Date.class);

		PostBuilder builtPost = buildPost(user, date);
		Post post = new Post(2, user, 3, "foo", date);

		assertEquals(post, builtPost.build());
	}

	private PostBuilder buildPost(User user, Date date) {
		PostBuilder builtPost = new PostBuilder();
		builtPost.setUser(user);
		builtPost.setContent("foo");
		builtPost.setDate(date);
		builtPost.setPostId(2);
		builtPost.setThreadId(3);
		return builtPost;
	}
}
