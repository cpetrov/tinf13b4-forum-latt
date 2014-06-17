
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private Date date;

	@Before
	public void setUp() {
		date = new Date();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNonPositiveId() throws Exception {
		new User(0, "foo", 1, "bar", "baz", date, true);
	}

	@Test
	public void testStoresId() throws Exception {
		User user = new User(1, "foo", 1, "bar", "foo", date, true);

		assertEquals(1, user.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullName() {
		new User(1, null, 1, "bar", "foo", date, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyName() {
		new User(1, "", 1, "bar", "foo", date, true);
	}

	@Test
	public void testStoresName() {
		User user = new User(1, "foo", 1, "bar", "foo", date, true);

		assertEquals("foo", user.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullMail() {
		User user = new User(1, "foo", 1, "bar", "foo", date, true);

		user.setMail(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyMail() {
		User user = new User(1, "foo", 1, "bar", "foo", date, true);

		user.setMail("");
	}

	@Test
	public void testDoesntFailWithNullPicture() {
		new User(1, "foo", 1, null, "foo", date, true);
	}

	@Test
	public void testStoresPicture() {
		User user = new User(1, "foo", 1, "bar", "bar", date, true);

		String userPicture = user.getPicture();

		assertEquals("bar", userPicture);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullJoinedOn() {
		new User(1, "foo", 1, "bar", "bar", null, true);
	}

	@Test
	public void testStoresJoinedOn() {
		User user = new User(1, "foo", 1, "bar", "bar", date, true);

		Date joinedOn = user.getJoinedOn();

		assertEquals(date, joinedOn);
	}

	@Test
	public void testStoresMail() {
		User user = new User(1, "foo", 1, "bar", "foo", date, true);

		user.setMail("bar");

		assertEquals("bar", user.getMail());
	}

	@Test
	public void testStoresDate() {
		Date joinedOn = date;
		User user = new User(1, "foo", 1, "bar", "foo", date, true);

		assertEquals(joinedOn, user.getJoinedOn());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativePostCount() {
		new User(1, "foo", -1, null, "bar", date, true);
	}

	@Test
	public void testStoresPostsCount() {
		User user = new User(1, "foo", 1, null, "bar", date, true);

		assertEquals(1, user.getPostCount());
	}

}
