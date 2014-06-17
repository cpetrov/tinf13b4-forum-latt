
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	private Category category;

	@Before
	public void setUp() {
		category = new Category(1, "foo", "foo", 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativeId() {
		new Category(-1, "foo", "foo", 0);
	}

	@Test
	public void testStoresId() {
		assertEquals(1, category.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullName() {
		new Category(2, null, null, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyName() {
		new Category(2, "", "", 0);
	}

	@Test
	public void testStoresCategoryName() {
		assertEquals("foo", category.getTitle());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullDescription() {
		category.setDescription(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyDescription() {
		category.setDescription("");
	}

	@Test
	public void testStoresCategoryDescription() {
		category.setDescription("bar");

		assertEquals("bar", category.getSubtitle());
	}
}
