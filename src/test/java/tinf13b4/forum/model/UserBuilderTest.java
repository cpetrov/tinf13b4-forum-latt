package tinf13b4.forum.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class UserBuilderTest {
	private Date date;
	@Before
	public void setUp() {
		date = new Date();
	}
	@Test
	public void testBuildsUser() throws Exception {
		UserBuilder userBuilder = new UserBuilder();
		userBuilder.setEmail("email");
		userBuilder.setId(1);
		userBuilder.setJoinedOn(date);
		userBuilder.setName("name");
		userBuilder.setPicture("picturePath");
		userBuilder.setPostsCount(2);

		User builtUser = userBuilder.build();

		User user = new User(1, "name", 2, "picturePath", "email", date, true, 0);
		assertEquals( user, builtUser );
	}
}
