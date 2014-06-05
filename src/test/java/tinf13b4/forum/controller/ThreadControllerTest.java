
package tinf13b4.forum.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Thread;

import com.mysql.jdbc.Blob;
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
		Date date1 = new Timestamp( new Date().getTime() );
		Date date2 = new Timestamp( new Date().getTime() );
		ResultSet resultSet = makeResultSet(
				Arrays.asList("Thread_ID", "Title", "Content", "Date", "ReadOnly", "Category_ID", "User_ID", "Name", "Picture", "Email", "JoinedOn"),
				Arrays.asList(1, "title", "content", date1, false, 2, 3, "user", Mockito.mock(Blob.class), "email", date2)
				);
		String query="SELECT Thread_ID, Title, Content, Date, ReadOnly, Category_ID, U.User_ID, U.Name, U.Picture, U.Email, U.JoinedOn FROM Threads T, Users U WHERE Category_ID = 2 AND T.User_ID = U.User_ID AND U.Confirmed = 1;";
		when(executor.executeQuery(query)).thenReturn(resultSet);
		ThreadController controller = new ThreadController(executor);

		List<Thread> threads = controller.getThreadsWithCategory(2);

		assertTrue(threads.size() == 1);
		checkThread1(date1, threads);
	}

	private void checkThread1(Date date1, List<Thread> threads) {
		assertEquals(1, threads.get(0).getId());
		assertEquals("foo", threads.get(0).getTitle());
		assertEquals(2, threads.get(0).getCategoryId());
		assertEquals("bar", threads.get(0).getContent());
		assertEquals(date1, threads.get(0).getDate());
		assertEquals(true, threads.get(0).isReadonly());
		assertEquals(3, threads.get(0).getUser());
	}

	private ResultSet makeResultSet(final List<String> aColumns, final List<?>... rows) throws Exception {
		ResultSet result = Mockito.mock(ResultSet.class);
		final AtomicInteger currentIndex = new AtomicInteger(-1);
		when(result.next()).thenAnswer(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {
				return currentIndex.incrementAndGet() < rows.length;
			}
		});
		final ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		Answer<Object> rowLookupAnswer = new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {
				int columnIndex = aColumns.indexOf(argument.getValue());
				return rows[currentIndex.get()].get(columnIndex);
			}
		};
		when(result.getString(argument.capture())).thenAnswer(rowLookupAnswer);
		when(result.getShort(argument.capture())).thenAnswer(rowLookupAnswer);
		when(result.getDate(argument.capture())).thenAnswer(rowLookupAnswer);
		when(result.getInt(argument.capture())).thenAnswer(rowLookupAnswer);
		when(result.getTimestamp(argument.capture())).thenAnswer(rowLookupAnswer);
		when(result.getBoolean(argument.capture())).thenAnswer(rowLookupAnswer);
		return result;
	}
}
