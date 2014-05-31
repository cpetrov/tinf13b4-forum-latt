
package tinf13b4.forum.install;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;

public class Installer {

	private File dbConfig;

	public Installer(File dbConfig) {
		this.dbConfig = dbConfig;
	}

	public void install() {
		ConnectionFactory factory = new ConnectionFactory(dbConfig);
		Connection connection = factory.createConnection();
		QueryExecutor queryExecutor = new QueryExecutor(connection);
		queryExecutor.executeSqlScript(getSqlInputStream());
	}

	private InputStream getSqlInputStream() {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File("src/main/resources/tinf13b4/forum/install/InitializeTables.sql"));
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("File not found.");
		}
		return fileInputStream;
	}
}
