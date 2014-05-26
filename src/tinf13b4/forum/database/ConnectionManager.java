package tinf13b4.forum.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import tinf13b4.forum.util.Base64Decryptor;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class ConnectionManager {
	private Connection connection;
	private Statement statement;
	private final MysqlDataSource dataSource;

	public ConnectionManager(String filepath) {
		List<String> data = new Base64Decryptor().getDecryptedDataList(filepath);
		dataSource = createDataSource(data);
	}

	private Connection openConnection() {
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO deal with the exception
			e.printStackTrace();
		}
		return connection;
	}

	private MysqlDataSource createDataSource(List<String> data) {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(data.get(0));
		dataSource.setPort(Integer.parseInt(data.get(1)));
		dataSource.setDatabaseName(data.get(2));
		dataSource.setUser(data.get(3));
		dataSource.setPassword(data.get(4));
		return dataSource;
	}

	private void closeConnection() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO deal with the exception
			e.printStackTrace();
		}
	}

	public ResultSet executeCommand(String command) {
		openConnection();
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(command);
		} catch (SQLException e) {
			// TODO deal with the exception
			e.printStackTrace();
		}
		closeConnection();
		return resultSet;
	}
}
