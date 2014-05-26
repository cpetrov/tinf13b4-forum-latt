
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class PostTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativePostId() {
		new Post(-1, 2, 2, "foo", "bar", new Date());
	}

	@Test
	public void testStoresPostId() {
		Post post = new Post(3, 2, 2, "foo", "bar", new Date());
		
		assertEquals(3, post.getPostId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativeThreadId() {
		new Post(2, -1, 2, "foo", "bar", new Date());
	}

	@Test
	public void testStoresThreadId() {
		Post post = new Post(3, 2, 4, "foo", "bar", new Date());
		
		assertEquals(2, post.getThreadId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativePosterId() {
		new Post(2, 3, -1, "foo", "bar", new Date());
	}

	@Test
	public void testStoresPosterId() {
		Post post = new Post(3, 2, 4, "foo", "bar", new Date());
		
		assertEquals(4, post.getPosterId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTitleMustNotBeNull() {
		new Post(3, 2, 4, null, "bar", new Date());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTitleMustNotBeEmpty() {
		new Post(3, 2, 4, "", "bar", new Date());
	}

	@Test
	public void testStoresTitle() {
		Post post = new Post(3, 2, 4, "foo", "bar", new Date());
		
		assertEquals("foo", post.getTitle());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testContentMustNotBeNull() {
		new Post(3, 2, 4, "foo", null, new Date());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testContentMustNotBeEmpty() {
		new Post(3, 2, 4, "foo", null, new Date());
	}
	
	@Test
	public void testStoresContent() {
		Post post = new Post(3, 2, 4, "foo", "bar", new Date());
		
		assertEquals("bar", post.getContent());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullDate() {
		new Post(3, 2, 4, "foo", "bar", null); 
	}
}
