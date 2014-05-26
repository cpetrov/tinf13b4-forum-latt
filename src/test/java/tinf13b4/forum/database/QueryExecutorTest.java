
package tinf13b4.forum.database;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

public class QueryExecutorTest {

	private Connection connection;
	private Statement statement;

	@Before
	public void setUp() throws SQLException {
		connection = mock(Connection.class);
		statement = mock(Statement.class);
		when(connection.createStatement()).thenReturn(statement);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullConnection() {
		new QueryExecutor(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullQuery() {
		new QueryExecutor(connection).executeQuery(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyQuery() {
		new QueryExecutor(connection).executeQuery("");
	}

	@Test
	public void testExecutesQuery() throws SQLException {
		QueryExecutor executor = new QueryExecutor(connection);

		executor.executeQuery("foo");

		verify(connection).createStatement();
		verify(statement).executeQuery("foo");
	}

	@Test(expected = IllegalStateException.class)
	public void testFailsWithInvalidQuery() throws Exception {
		when(connection.createStatement()).thenReturn(statement);
		when(statement.executeQuery("someInvalidSql")).thenThrow(new SQLException());

		new QueryExecutor(connection).executeQuery("someInvalidSql");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExecuteSqlScriptFailsWithNullInputStream() {
		new QueryExecutor(connection).executeSqlScript(null);
	}
}
