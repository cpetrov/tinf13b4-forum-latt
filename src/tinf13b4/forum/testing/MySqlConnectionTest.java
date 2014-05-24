package tinf13b4.forum.testing;

import tinf13b4.forum.database.ConnectionManager;

public class MySqlConnectionTest 
{
	public static void main(String[] args) 
	{
		ConnectionManager manager = new ConnectionManager("C:\\Users\\marius.schroeder\\Documents\\GitHub\\tinf13b4\\src\\tinf13b4\\forum\\databaseConfigurator\\DBConfig.cfg");
		manager.executeCommand("Testing Command!;");
	}
}
