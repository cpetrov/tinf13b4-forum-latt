
package tinf13b4.forum.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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

import tinf13b4.forum.controller.ThreadController;
import tinf13b4.forum.database.import tinf13b4.forum.model.Thread;

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

		controller.getThreads(-1);
	}

	@Test
	public void testReturnsEmptyListWhenResultSetNull() {
		Mockito.when(executor.executeQuery("SELECT * FROM Thread WHERE Category_ID = 2;")).thenReturn(null);
		ThreadController controller = new ThreadController(executor);

		List<Thread> threads = controller.getThreads(2);

		assertTrue(threads instanceof List);
	}

	@Test
	public void testReturnsThreadList() throws Exception {
		Date date1 = new java.sql.Date( new Date().getTime() );
		Date date2 = new java.sql.Date( new Date().getTime() );
		ResultSet resultSet = makeResultSet(
				Arrays.asList("Thread_ID", "Title", "Category_ID", "Content", "Date", "Readonly", "Thread_Starter_ID"),
				Arrays.asList(1, "foo", 2, "bar", date1, true, 3),
				Arrays.asList(4, "baz", 5, "bac", date2, false, 6)
				);
		when(executor.executeQuery("SELECT * FROM Thread WHERE Category_ID = 2;")).thenReturn(resultSet);
		ThreadController controller = new ThreadController(executor);

		List<Thread> threads = controller.getThreads(2);

		assertTrue(threads.size() == 2);
		checkThread1(date1, threads);
		checkThread2(date2, threads);
	}

	private void checkThread1(Date date1, List<Thread> threads) {
		assertEquals(1, threads.get(0).getId());
		assertEquals("foo", threads.get(0).getTitle());
		assertEquals(2, threads.get(0).getCategoryId());
		assertEquals("bar", threads.get(0).getContent());
		assertEquals(date1, threads.get(0).getDate());
		assertEquals(true, threads.get(0).isReadonly());
		assertEquals(3, threads.get(0).getThreadStarterId());
	}

	private void checkThread2(Date date2, List<Thread> threads) {
		assertEquals(4, threads.get(1).getId());
		assertEquals("baz", threads.get(1).getTitle());
		assertEquals(5, threads.get(1).getCategoryId());
		assertEquals("bac", threads.get(1).getContent());
		assertEquals(date2, threads.get(1).getDate());
		assertEquals(false, threads.get(1).isReadonly());
		assertEquals(6, threads.get(1).getThreadStarterId());
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
