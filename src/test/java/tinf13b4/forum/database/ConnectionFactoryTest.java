
package tinf13b4.forum.database;

import org.junit.Test;

public class ConnectionFactoryTest {

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullPath() {
		new ConnectionFactory(null);
	}
}
