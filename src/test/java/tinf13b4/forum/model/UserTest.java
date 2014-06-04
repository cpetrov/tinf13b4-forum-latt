
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class UserTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNonPositiveId() throws Exception {
		new User(0, "foo", null, "foo", new Date(), new ArrayList<Post>());
	}

	@Test
	public void testStoresId() throws Exception {
		User user = new User(1, "foo", null, "foo", new Date(), new ArrayList<Post>());

		assertEquals(1, user.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullName() {
		new User(1, null, null, "foo", new Date(), new ArrayList<Post>());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyName() {
		new User(1, "", null, "foo", new Date(), new ArrayList<Post>());
	}

	@Test
	public void testStoresName() {
		User user = new User(1, "foo", null, "foo", new Date(), new ArrayList<Post>());

		assertEquals("foo", user.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullMail() {
		User user = new User(1, "foo", null, "foo", new Date(), new ArrayList<Post>());

		user.setEMail(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyMail() {
		User user = new User(1, "foo", null, "foo", new Date(), new ArrayList<Post>());

		user.setEMail("");
	}

	@Test
	public void testStoresMail() {
		User user = new User(1, "foo", null, "foo", new Date(), new ArrayList<Post>());

		user.setEMail("bar");

		assertEquals("bar", user.getEMail());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullJoinedOn() {
		new User(1, "foo", null, "foo", null, new ArrayList<Post>());
	}

	@Test
	public void testStoresDate() {
		Date joinedOn = new Date();
		User user = new User(1, "foo", null, "foo", new Date(), new ArrayList<Post>());

		assertEquals(joinedOn, user.getJoinedOn());
	}
	
	@Test
	public void testFailsWithNullPosts() {
		new User(1, "foo", null, "foo", new Date(), null);
	}
}