
package tinf13b4.forum.model;

import static org.junit.Assert.*;

import java.sql.Blob;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UserTest {

	private Blob picture;
	private Date date;

	@Before
	public void setUp() {
		picture = Mockito.mock(Blob.class);
		date = new Date();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNonPositiveId() throws Exception {
		new User(0, "foo", 1, picture, "foo", date);
	}

	@Test
	public void testStoresId() throws Exception {
		User user = new User(1, "foo", 1, picture, "foo", date);

		assertEquals(1, user.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullName() {
		new User(1, null, 1, picture, "foo", date);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyName() {
		new User(1, "", 1, picture, "foo", date);
	}

	@Test
	public void testStoresName() {
		User user = new User(1, "foo", 1, picture, "foo", date);

		assertEquals("foo", user.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullMail() {
		User user = new User(1, "foo", 1, picture, "foo", date);

		user.setMail(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyMail() {
		User user = new User(1, "foo", 1, picture, "foo", date);

		user.setMail("");
	}

	@Test
	public void testDoesntFailWithNullPicture() {
		new User(1, "foo", 1, null, "foo", date);
	}

	@Test
	public void testStoresPicture() {
		User user = new User(1, "foo", 1, picture, "bar", date);

		Blob userPicture = user.getPicture();

		assertEquals(picture, userPicture);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullJoinedOn() {
		new User(1, "foo", 1, picture, "bar", null);
	}

	@Test
	public void testStoresJoinedOn() {
		User user = new User(1, "foo", 1, picture, "bar", date);

		Date joinedOn = user.getJoinedOn();

		assertEquals(date, joinedOn);
	}

	@Test
	public void testStoresMail() {
		User user = new User(1, "foo", 1, picture, "foo", date);

		user.setMail("bar");

		assertEquals("bar", user.getMail());
	}

	@Test
	public void testStoresDate() {
		Date joinedOn = date;
		User user = new User(1, "foo", 1, picture, "foo", date);

		assertEquals(joinedOn, user.getJoinedOn());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativePostCount() {
		new User(1, "foo", -1, null, "bar", date);
	}

	@Test
	public void testStoresPostsCount() {
		User user = new User(1, "foo", 1, null, "bar", date);

		assertEquals(1, user.getPostCount());
	}

}
