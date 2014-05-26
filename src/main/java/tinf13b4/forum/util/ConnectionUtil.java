package tinf13b4.forum.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionUtil {

	public static Connection openConnection(MysqlDataSource dataSource) {
		checkArgument(dataSource!=null, "DataSource must not be null.");
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public static MysqlDataSource createDataSource(List<String> data) {
		checkArgument(data!=null, "Data must not be null.");
		checkState(data.size()==5, "Data incomplete.");
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(data.get(0));
		dataSource.setPort(Integer.parseInt(data.get(1)));
		dataSource.setDatabaseName(data.get(2));
		dataSource.setUser(data.get(3));
		dataSource.setPassword(data.get(4));
		return dataSource;
	}

	public static void closeConnection(Connection connection) {
		checkArgument(connection!=null, "Connection must not be null.");
		try {
			connection.close();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

}
