
package tinf13b4.forum.test;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import tinf13b4.forum.database.QueryExecutor;

import com.mysql.jdbc.ResultSet;

public class TestUtil {

	public static ResultSet makeResultSet(final List<String> aColumns, final List<?>... rows) throws Exception {
		ResultSet result = Mockito.mock(ResultSet.class);
		final AtomicInteger currentIndex = new AtomicInteger(-1);
		Mockito.when(result.next()).thenAnswer(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {
				return currentIndex.incrementAndGet() < rows.length;
			}
		});
		final ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		Answer<?> rowLookupAnswer = new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock aInvocation) throws Throwable {
				int columnIndex = aColumns.indexOf(argument.getValue());
				return rows[currentIndex.get()].get(columnIndex);
			}
		};
		Mockito.when(result.getString(argument.capture())).thenAnswer(rowLookupAnswer);
		Mockito.when(result.getShort(argument.capture())).thenAnswer(rowLookupAnswer);
		Mockito.when(result.getDate(argument.capture())).thenAnswer(rowLookupAnswer);
		Mockito.when(result.getInt(argument.capture())).thenAnswer(rowLookupAnswer);
		Mockito.when(result.getTimestamp(argument.capture())).thenAnswer(rowLookupAnswer);
		Mockito.when(result.getBoolean(argument.capture())).thenAnswer(rowLookupAnswer);
		return result;
	}

	public static void stubPostsCount(QueryExecutor executor) throws Exception {
		ResultSet resultSet = makeResultSet(Arrays.asList("COUNT(*)"), Arrays.asList(1));
		when(executor.executeQuery("SELECT COUNT(*) FROM Posts WHERE User_ID = 1")).thenReturn(resultSet);
	}
}
