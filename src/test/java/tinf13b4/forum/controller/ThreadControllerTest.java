
package tinf13b4.forum.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static tinf13b4.forum.test.TestUtil.makeResultSet;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Thread;
import tinf13b4.forum.model.User;
import tinf13b4.forum.test.TestUtil;

import com.mysql.jdbc.ResultSet;

public class ThreadControllerTest {

	private QueryExecutor executor;

	@Before
	public void setUp() {
		executor = Mockito.mock(QueryExecutor.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativeCategoryId() {
		ThreadController controller = new ThreadController(executor);

		controller.getThreadsWithCategory(-1);
	}

	@Test
	public void testReturnsEmptyListWhenResultSetNull() {
		Mockito.when(executor.executeQuery("SELECT * FROM Thread WHERE Category_ID = 2;")).thenReturn(null);
		ThreadController controller = new ThreadController(executor);

		List<Thread> threads = controller.getThreadsWithCategory(2);

		assertTrue(threads instanceof List);
	}

	@Test
	public void testReturnsThreadList() throws Exception { //TODO broken
		Date date = new Timestamp( new Date().getTime() );
		Date joinedOn = new Timestamp( new Date().getTime() );
		ResultSet resultSet = makeResultSet(
				Arrays.asList("Thread_ID", "Title", "Content", "Date", "ReadOnly", "Category_ID", "User_ID", "Name", "Picture", "Email", "JoinedOn"),
				Arrays.asList(1, "title", "content", date, false, 2, 1, "user", "pathToPicture", "email", joinedOn)
				);
		String query="SELECT Thread_ID, Title, Content, Date, ReadOnly, Category_ID, U.User_ID, U.Name, U.Picture, U.Email, U.JoinedOn "
					+ "FROM Threads T, Users U "
					+ "WHERE Category_ID = 2 AND T.User_ID = U.User_ID AND U.Confirmed = 1;";
		when(executor.executeQuery(query)).thenReturn(resultSet);
		TestUtil.stubPostsCount(executor);
		ThreadController controller = new ThreadController(executor);

		List<Thread> threads = controller.getThreadsWithCategory(2);

		assertTrue(threads.size() == 1);
		checkThread1(date, threads, joinedOn);
	}

	private void checkThread1(Date date, List<Thread> threads, Date joinedOn) {
		assertEquals(1, threads.get(0).getId());
		assertEquals("title", threads.get(0).getTitle());
		assertEquals(2, threads.get(0).getCategoryId());
		assertEquals("content", threads.get(0).getContent());
		assertEquals(new Date(date.getTime()), threads.get(0).getDate());
		assertEquals(false, threads.get(0).isReadonly());
		User user = new User(1, "user", 1, "pathToPicture", "email", new Date(joinedOn.getTime()));
		assertEquals(user, threads.get(0).getUser());
	}

}
