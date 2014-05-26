
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class PostBuilderTest {

	@Test
	public void testBuildsPost() {
		Date currentDate = new Date();
		Post builtPost = new PostBuilder().setContent("foo").setDate(currentDate).setPosterId(2).setPostId(3).setThreadId(4).setTitle("bar").build();
		Post post = new Post(3, 4, 2, "bar", "foo", currentDate);
		assertEquals(post, builtPost);
	}
}
