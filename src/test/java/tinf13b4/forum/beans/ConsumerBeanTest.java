
package tinf13b4.forum.beans;

import org.junit.Before;
import org.junit.Test;

public class ConsumerBeanTest {

	private ConsumerBean consumerBean;

	@Before
	public void setUp() {
		consumerBean = new ConsumerBean();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFailsWithNegativeThreadId() {
		consumerBean.setThreadId(-1);;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFailsWithNegativeUserId() {
		consumerBean.setUserId(-1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFailsWithNullContent() {
		consumerBean.setPost(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFailsWithEmptyContent() {
		consumerBean.setPost("");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetPostFailsWithNonSetThreadId() {
		consumerBean.setUserId(1);
		consumerBean.setPost("foo");
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetPostFailsWithNonSetUserId() {
		consumerBean.setThreadId(1);
		consumerBean.setPost("foo");
	}

}
