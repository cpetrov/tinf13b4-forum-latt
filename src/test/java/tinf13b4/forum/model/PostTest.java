
package tinf13b4.forum.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class PostTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativePostId() {
		new Post(-1, 2, 1, "foo", new Date());
	}

	@Test
	public void testStoresPostId() {
		Post post = new Post(3, 2, 1, "foo", new Date());

		assertEquals(3, post.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativePosterId() {
		new Post(2, -1, 1, "foo", new Date());
	}

	@Test
	public void testStoresUserId() {
		Post post = new Post(2, 4, 1, "foo", new Date());

		assertEquals(4, post.getUserId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativeThreadId() throws Exception {
		new Post(1, 1, -1, "foo", new Date());
	}
	
	@Test
	public void testStoresThreadId() {
		Post post = new Post(1, 2, 3, "foo", new Date());
		
		assertEquals(3, post.getThreadId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTitleMustNotBeNull() {
		new Post(2, 1, 1, null, new Date());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTitleMustNotBeEmpty() {
		new Post(2, 1, 1, "", new Date());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContentMustNotBeNull() {
		new Post(2, 1, 1, null, new Date());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContentMustNotBeEmpty() {
		new Post(2, 1, 1, "", new Date());
	}

	@Test
	public void testStoresContent() {
		Post post = new Post(2, 1, 1, "foo", new Date());

		assertEquals("foo", post.getContent());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullDate() {
		new Post(2, 1, 1, "foo", null);
	}
}
