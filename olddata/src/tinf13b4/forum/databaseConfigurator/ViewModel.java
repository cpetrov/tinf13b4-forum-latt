package tinf13b4.forum.databaseConfigurator;

import java.nio.file.Path;

public class ViewModel 
{
	private String hostname;
	private int port;
	private String databasename;
	private String username;
	private String password;
	private Path path;
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDatabasename() {
		return databasename;
	}
	public void setDatabasename(String databasename) {
		this.databasename = databasename;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
}
