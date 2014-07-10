
package tinf13b4.forum.database;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class QueryExecutorTest {

	private Connection connection;
	private PreparedStatement statement;
	private String query;

	@Before
	public void setUp() throws SQLException {
		connection = mock(Connection.class);
		statement = mock(PreparedStatement.class);
		query = "query";
		when(connection.prepareStatement(query)).thenReturn(statement);
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

		executor.executeQuery("query");

		verify(connection).prepareStatement("query");
		verify(statement).executeQuery();
	}

	@Test(expected = IllegalStateException.class)
	public void testFailsWithInvalidQuery() throws Exception {
		when(connection.prepareStatement("someInvalidSql")).thenReturn(statement);
		when(statement.executeQuery()).thenThrow(new SQLException());

		new QueryExecutor(connection).executeQuery("someInvalidSql");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExecuteSqlScriptFailsWithNullInputStream() {
		new QueryExecutor(connection).executeSqlScript(null);
	}
}
