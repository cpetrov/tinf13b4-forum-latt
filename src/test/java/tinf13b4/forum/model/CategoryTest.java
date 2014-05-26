
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	private Category category;

	@Before
	public void setUp() {
		category = new Category(1, "foo");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativeCategoryId() {
		new Category(-1, "foo");
	}

	@Test
	public void testStoresCategoryId() {
		assertEquals(1, category.getCategoryId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullName() {
		new Category(2, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyName() {
		new Category(2, "");
	}

	@Test
	public void testStoresCategoryName() {
		assertEquals("foo", category.getName());
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
		assertEquals("bar", category.getDescription());
	}
}
