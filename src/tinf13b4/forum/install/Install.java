package tinf13b4.forum.install;

import tinf13b4.forum.database.ConnectionManager;

public class Install {

	private ConnectionManager mConnectionManager;

	public Install() {
		mConnectionManager = new ConnectionManager();
		mConnectionManager.executeSqlScript("/ExecuteTables.sql");
	}
}
