
package tinf13b4.forum.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static tinf13b4.forum.test.TestUtil.makeResultSet;
import static tinf13b4.forum.test.TestUtil.stubPostsCount;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.User;

import com.mysql.jdbc.ResultSet;

public class UserControllerTest {

	private QueryExecutor executor;
	private Date date;
	private Timestamp timestamp;
	private String picture;

	@Before
	public void setUp() throws Exception {
		date = new Date();
		timestamp = new Timestamp(date.getTime());
		picture = null;
		ResultSet resultSet = makeResultSet(
				Arrays.asList("User_ID", "Name", "Picture", "Email", "JoinedOn"),
				Arrays.asList(1, "username", picture, "email", timestamp)
				);
		executor = Mockito.mock(QueryExecutor.class);
		String query = "SELECT User_ID, Name, Picture, Email, JoinedOn "
									+ "FROM Users "
									+ "WHERE Confirmed='1';";
		when(executor.executeQuery(query)).thenReturn(resultSet);
		stubPostsCount(executor);
	}

	@Test
	public void testReturnsUsers() throws Exception {
		List<User> users = new UserController(executor).getUsers();
		assertEquals(1, users.get(0).getId());
		assertEquals("username", users.get(0).getName());
		assertEquals(picture, users.get(0).getPicture());
		assertEquals("email", users.get(0).getMail());
		assertEquals(date, users.get(0).getJoinedOn());
		assertEquals(1, users.get(0).getPostCount());
	}
}
