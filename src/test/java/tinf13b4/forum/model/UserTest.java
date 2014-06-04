
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

		user.setMail(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyMail() {
		User user = new User(1, "foo", null, "foo", new Date(), new ArrayList<Post>());

		user.setMail("");
	}

	@Test
	public void testStoresMail() {
		User user = new User(1, "foo", null, "foo", new Date(), new ArrayList<Post>());

		user.setMail("bar");

		assertEquals("bar", user.getMail());
	}

	@Test
	public void testStoresDate() {
		Date joinedOn = new Date();
		User user = new User(1, "foo", null, "foo", new Date(), new ArrayList<Post>());

		assertEquals(joinedOn, user.getJoinedOn());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullPosts() {
		new User(1, "foo", null, "foo", new Date(), null);
	}
}
