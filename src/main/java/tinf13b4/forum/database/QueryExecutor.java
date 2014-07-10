
package tinf13b4.forum.database;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.ibatis.common.jdbc.ScriptRunner;

public class QueryExecutor {

	private final Connection connection;

	public QueryExecutor(Connection connection) {
		checkArgument(connection != null, "Connection must not be null.");
		this.connection = connection;
	}

	public ResultSet executeQuery(String query) {
		checkArgument(query != null, "Query must not be null.");
		checkArgument(!query.isEmpty(), "Query must not be empty.");
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			return statement.executeQuery();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public void executeUpdate(String update) {
		checkArgument(update != null, "Update must not be null.");
		checkArgument(!update.isEmpty(), "Update must not be empty.");
		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public void executeSqlScript(InputStream inputStream) {
		checkArgument(inputStream != null, "InputStream must not be null.");
		ScriptRunner runner = new ScriptRunner(connection, false, false);
		try {
			runner.runScript(new BufferedReader(new InputStreamReader(inputStream)));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
