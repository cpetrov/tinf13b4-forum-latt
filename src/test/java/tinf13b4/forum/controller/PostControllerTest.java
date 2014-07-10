package tinf13b4.forum.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
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
import tinf13b4.forum.model.Post;
import tinf13b4.forum.model.User;

import com.mysql.jdbc.ResultSet;



public class PostControllerTest {
	private QueryExecutor executor;

	@Before
	public void setUp() throws Exception {
		executor = Mockito.mock(QueryExecutor.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNonPositiveThreadId() {
		new PostController(executor).getPostsForThread(0);
	}

	@Test
	public void testInsertsNewPost() {
		Timestamp datePosted = new Timestamp(new Date().getTime());

		new PostController(executor).addPostToThread(2, 1, "content");

		verify(executor).executeUpdate("INSERT INTO Posts (Content, Date, Thread_ID, User_ID) VALUES ('content', '" + datePosted+ "', '2', '1')");
	}

	@Test
	public void testReturnsPosts() throws Exception {
		Timestamp dateJoined = new Timestamp(new Date().getTime());
		Timestamp timestamp = new Timestamp(new Date().getTime());
		ResultSet resultSet = makeResultSet(
				Arrays.asList("Post_ID", "Content", "Date", "User_ID", "Name", "Picture", "Email", "JoinedOn", "Confirmed", "Permission"),
				Arrays.asList(1, "content", timestamp, 1, "username", "pathToPicture", "email", dateJoined, true, 1)
				);
		when(executor.executeQuery("SELECT P.Post_ID, P.Content, P.Date, P.User_ID, U.Name, U.Picture, U.Email, U.JoinedOn, U.Confirmed, U.Permission "
				+ "FROM Posts P, Threads T, Users U "
				+ "WHERE P.Thread_ID = T.Thread_ID "
				+ "AND T.Thread_ID = 1 "
				+ "AND P.User_ID = U.User_ID "
				+ "AND U.Confirmed = 1 "
				+ "ORDER BY Date ASC;")).thenReturn(resultSet);
		stubPostsCount(executor);

		List<Post> posts = new PostController(executor).getPostsForThread(1);

		checkPosts(timestamp, posts);
		checkUser(dateJoined, posts);
	}

	private void checkPosts(Timestamp timestamp, List<Post> posts) {
		assertTrue(posts.size() == 1);
		assertEquals("content", posts.get(0).getContent());
		assertEquals(1, posts.get(0).getId());
		assertEquals(new Date(timestamp.getTime()), posts.get(0).getDate());
	}

	private void checkUser(Timestamp dateJoined, List<Post> posts) {
		User user = posts.get(0).getUser();
		assertEquals(1, user.getId());
		assertEquals("username", user.getName());
		assertEquals("pathToPicture", user.getPicture());
		assertEquals("email", user.getMail());
		assertEquals(new Date(dateJoined.getTime()), user.getJoinedOn());
	}
}
