
package tinf13b4.forum.database;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import tinf13b4.forum.util.Base64Decryptor;
import tinf13b4.forum.util.ConnectionUtil;
import tinf13b4.forum.util.StringUtil;

import com.google.common.base.Preconditions;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionFactory {

	private final InputStream configurationInputStream;

	public ConnectionFactory() {
		this(Thread.currentThread().getContextClassLoader().getResourceAsStream("DBConfig.cfg"));
	}

	public ConnectionFactory(InputStream configurationInputStream) {
		Preconditions.checkArgument(configurationInputStream != null, "ConfigurationInputStream must not be null.");
		this.configurationInputStream = configurationInputStream;
	}

	public Connection createConnection() {
		List<String> dataList = readDataListFromFile();
		MysqlDataSource dataSource = ConnectionUtil.createDataSource(dataList);
		return ConnectionUtil.openConnection(dataSource);
	}

	private List<String> readDataListFromFile() {
		List<String> encryptedDataList = StringUtil.getStringListFromInputStream(configurationInputStream);
		return Base64Decryptor.getDecryptedStringList(encryptedDataList);
	}
}
