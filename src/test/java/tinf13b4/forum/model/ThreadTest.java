
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class ThreadTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativeId() {
		new Thread(-1, 2, 3, "foo", "bar", new Date(), false);
	}

	@Test
	public void testStoresId() {
		Thread thread = new Thread(1, 2, 3, "foo", "bar", new Date(), false);
		assertEquals(1, thread.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativeThreadStarterId() {
		new Thread(1, -1, 3, "foo", "bar", new Date(), false);
	}

	@Test
	public void testStoresThreadStarterId() {
		Thread thread = new Thread(1, 2, 3, "foo", "bar", new Date(), false);
		assertEquals(2, thread.getThreadStarterId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativeCategoryId() {
		new Thread(1, 2, -1, "foo", "bar", new Date(), false);
	}

	@Test
	public void testStoresCategoryId() {
		Thread thread = new Thread(1, 2, 3, "foo", "bar", new Date(), false);
		assertEquals(3, thread.getCategoryId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTitleMustNotBeNull() {
		new Thread(1, 2, 3, null, "bar", new Date(), false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTitleMustNotBeEmpty() {
		new Thread(1, 2, 3, "", "bar", new Date(), false);
	}

	@Test
	public void testStoresTitle() {
		Thread thread = new Thread(1, 2, 3, "foo", "bar", new Date(), false);
		assertEquals("foo", thread.getTitle());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContentMustNotBeNull() {
		new Thread(1, 2, 3, "foo", null, new Date(), false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContentMustNotBeEmpty() {
		new Thread(1, 2, 3, "foo", "", new Date(), false);
	}

	@Test
	public void testStoresContent() {
		Thread thread = new Thread(1, 2, 3, "foo", "bar", new Date(), false);
		assertEquals("bar", thread.getContent());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDateMustNotBeNull() {
		new Thread(1, 2, 3, "foo", "bar", null, false);
	}
}
