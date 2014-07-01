
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
		new User(0, "foo", 1, "bar", "baz", date, true, 0);
	}

	@Test
	public void testStoresId() throws Exception {
		User user = new User(1, "foo", 1, "bar", "foo", date, true, 0);

		assertEquals(1, user.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullName() {
		new User(1, null, 1, "bar", "foo", date, true, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyName() {
		new User(1, "", 1, "bar", "foo", date, true, 0);
	}

	@Test
	public void testStoresName() {
		User user = new User(1, "foo", 1, "bar", "foo", date, true, 0);

		assertEquals("foo", user.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullMail() {
		User user = new User(1, "foo", 1, "bar", "foo", date, true, 0);

		user.setMail(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyMail() {
		User user = new User(1, "foo", 1, "bar", "foo", date, true, 0);

		user.setMail("");
	}

	@Test
	public void testDoesntFailWithNullPicture() {
		new User(1, "foo", 1, null, "foo", date, true, 0);
	}

	@Test
	public void testStoresPicture() {
		User user = new User(1, "foo", 1, "bar", "bar", date, true, 0);

		String userPicture = user.getPicture();

		assertEquals("bar", userPicture);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullJoinedOn() {
		new User(1, "foo", 1, "bar", "bar", null, true, 0);
	}

	@Test
	public void testStoresJoinedOn() {
		User user = new User(1, "foo", 1, "bar", "bar", date, true, 0);

		Date joinedOn = user.getJoinedOn();

		assertEquals(date, joinedOn);
	}

	@Test
	public void testStoresMail() {
		User user = new User(1, "foo", 1, "bar", "foo", date, true, 0);

		user.setMail("bar");

		assertEquals("bar", user.getMail());
	}

	@Test
	public void testStoresDate() {
		Date joinedOn = date;
		User user = new User(1, "foo", 1, "bar", "foo", date, true, 0);

		assertEquals(joinedOn, user.getJoinedOn());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativePostCount() {
		new User(1, "foo", -1, null, "bar", date, true, 0);
	}

	@Test
	public void testStoresPostsCount() {
		User user = new User(1, "foo", 1, null, "bar", date, true, 0);

		assertEquals(1, user.getPostCount());
	}
	
	@Test
	public void testStoresPermission() {
		User user = new User(1, "foo", 1, null, "bar", date, true, 1);
		
		assertEquals(1, user.getPermission());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithPermissionBigger2() {
		new User(1, "foo", -1, null, "bar", date, true, 3);
	}

}
