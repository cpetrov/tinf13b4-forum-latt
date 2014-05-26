
package tinf13b4.forum.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionUtilTest {

	private ArrayList<String> fakeDataList;

	@Before
	public void setUp() {
		fakeDataList = new ArrayList<>();
		fakeDataList.add("serverName");
		fakeDataList.add("1");
		fakeDataList.add("dbName");
		fakeDataList.add("user");
		fakeDataList.add("pass");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullDataSource() {
		ConnectionUtil.openConnection(null);
	}

	@Test
	public void testOpensConnection() throws SQLException {
		MysqlDataSource dataSource = Mockito.mock(MysqlDataSource.class);

		ConnectionUtil.openConnection(dataSource);

		Mockito.verify(dataSource).getConnection();
	}

	@Test(expected = IllegalStateException.class)
	public void testFailsOnOpenSQLError() throws Exception {
		MysqlDataSource dataSource = Mockito.mock(MysqlDataSource.class);
		Mockito.when(dataSource.getConnection()).thenThrow(new SQLException());

		ConnectionUtil.openConnection(dataSource);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateDataSourceFailsWithNullDataList() {
		ConnectionUtil.createDataSource(null);
	}

	@Test(expected = IllegalStateException.class)
	public void testCreateDataFailsWithIncompleteData() {
		ConnectionUtil.createDataSource(new ArrayList<String>());
	}

	@Test
	public void testCreatesDataSource() {
		MysqlDataSource dataSource = ConnectionUtil.createDataSource(fakeDataList);

		assertEquals("serverName", dataSource.getServerName());
		assertEquals(1, dataSource.getPort());
		assertEquals("dbName", dataSource.getDatabaseName());
		assertEquals("user", dataSource.getUser());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCloseFailsWithNullConnection() {
		ConnectionUtil.closeConnection(null);
	}

	@Test
	public void testClosesConnection() throws SQLException {
		Connection connection = mock(Connection.class);

		ConnectionUtil.closeConnection(connection);

		verify(connection).close();
	}

	@Test(expected = IllegalStateException.class)
	public void testFailsOnCloseSQLError() throws SQLException {
		Connection connection = mock(Connection.class);
		Mockito.doThrow(new SQLException()).when(connection).close();

		ConnectionUtil.closeConnection(connection);
	}
}
