
package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class MemberTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNonPositiveId() throws Exception {
		new Member(0, "foo", 2, new Date());
	}

	@Test
	public void testStoresId() throws Exception {
		Member member = new Member(1, "foo", 2, new Date());
		
		assertEquals(1, member.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullName() {
		new Member(1, null, 2, new Date());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyName() {
		new Member(1, "", 2, new Date());
	}

	@Test
	public void testStoresName() {
		Member member = new Member(1, "foo", 2, new Date());
		
		assertEquals("foo", member.getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativePosts() {
		new Member(2, "foo", -1, new Date());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullMail() {
		Member member = new Member(1, "foo", 2, new Date());
		
		member.setMail(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyMail() {
		Member member = new Member(1, "foo", 2, new Date());
		
		member.setMail("");
	}

	@Test
	public void testStoresMail() {
		Member member = new Member(1, "foo", 2, new Date());
		
		member.setMail("bar");
		
		assertEquals("bar", member.getMail());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullFacebook() {
		Member member = new Member(1, "foo", 2, new Date());
		
		member.setFacebook(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyFacebook() {
		Member member = new Member(1, "foo", 2, new Date());
		
		member.setFacebook("");
	}

	@Test
	public void testStoresFacebook() {
		Member member = new Member(1, "foo", 2, new Date());
		
		member.setFacebook("bar");
		
		assertEquals("bar", member.getFacebook());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullJoinedOn() {
		new Member(1, "foo", 2, null);
	}
	
	@Test
	public void testStoresDate() {
		Date joinedOn = new Date();
		Member member = new Member(1, "foo", 2, joinedOn);
		
		assertEquals(joinedOn, member.getJoinedOn());
	}
}
