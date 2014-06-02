
package tinf13b4.forum.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import tinf13b4.forum.util.Base64Decryptor;
import tinf13b4.forum.util.ConnectionUtil;
import tinf13b4.forum.util.StringUtil;

import com.google.common.base.Preconditions;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionFactory {

	private final File dbConfig;

	public ConnectionFactory() {
		this(new File("src/main/config/DBConfig.cfg"));
	}

	public ConnectionFactory(File dbConfig) {
		Preconditions.checkArgument(dbConfig != null, "DbConfig must not be null.");
		this.dbConfig = dbConfig;
	}

	public Connection createConnection() {
		List<String> dataList = readDataListFromFile();
		MysqlDataSource dataSource = ConnectionUtil.createDataSource(dataList);
		return ConnectionUtil.openConnection(dataSource);
	}

	private List<String> readDataListFromFile() {
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(dbConfig);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("File not found.", e);
		}
		List<String> encryptedDataList = StringUtil.getStringListFromInputStream(inputStream);
		return Base64Decryptor.getDecryptedStringList(encryptedDataList);
	}
}
