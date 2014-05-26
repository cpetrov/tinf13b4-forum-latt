package tinf13b4.forum.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tinf13b4.forum.security.DecryptBase64;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionManager {
	private ArrayList<String> data;

	private Connection conn;
	private Statement stat;

	public static void main(String[] args) {
		new ConnectionManager();
	}

	public ConnectionManager() {
		// TODO relative path zu DBConfig.cfg
		String path = "/tinf13b4-forum-lat/src/tinf13b4/forum/databaseConfigurator/DBConfig.cfg";
		data = new DecryptBase64().DecryptBase64(path);
	}

	private void openConnection() {
		try {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName(data.get(0));
			dataSource.setPort(Integer.parseInt(data.get(1)));
			dataSource.setDatabaseName(data.get(2));
			dataSource.setUser(data.get(3));
			dataSource.setPassword(data.get(4));

			conn = dataSource.getConnection();
			stat = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeConnection() {
		try {
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ResultSet executeCommand(String command) {
		openConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
		return rs;
	}

	public void executeSqlScript(String filename) {
		ScriptRunner runner = new ScriptRunner(conn, false, false);
		try {
			runner.runScript(new BufferedReader(new FileReader(filename)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
