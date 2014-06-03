
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class MemberTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNonPositiveId() throws Exception {
		new User(0, "foo", null, "foo", new Date());
	}

	@Test
	public void testStoresId() throws Exception {
		User member = new User(1, "foo", null, "foo", new Date());

		assertEquals(1, member.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullName() {
		new User(1, null, null, "foo", new Date());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyName() {
		new User(1, "", null, "foo", new Date());
	}

	@Test
	public void testStoresName() {
		User member = new User(1, "foo", null, "foo", new Date());

		assertEquals("foo", member.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullMail() {
		User member = new User(1, "foo", null, "foo", new Date());

		member.setEMail(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyMail() {
		User member = new User(1, "foo", null, "foo", new Date());

		member.setEMail("");
	}

	@Test
	public void testStoresMail() {
		User member = new User(1, "foo", null, "foo", new Date());

		member.setEMail("bar");

		assertEquals("bar", member.getEMail());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullJoinedOn() {
		new User(1, "foo", null, "foo", null);
	}

	@Test
	public void testStoresDate() {
		Date joinedOn = new Date();
		User member = new User(1, "foo", null, "foo", new Date());

		assertEquals(joinedOn, member.getJoinedOn());
	}
}
