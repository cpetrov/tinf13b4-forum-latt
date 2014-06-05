
package tinf13b4.forum.beans;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import tinf13b4.forum.database.QueryExecutor;

public class ConsumerBeanTest {

	private ConsumerBean consumerBean;
	private QueryExecutor executor;

	@Before
	public void setUp() {
		executor = mock(QueryExecutor.class);
		consumerBean = new ConsumerBean(executor);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativeThreadId() {
		consumerBean.setPostThreadId(-1);;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNegativeUserId() {
		consumerBean.setPostUserId(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullContent() {
		consumerBean.setPost(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyContent() {
		consumerBean.setPost("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPostFailsWithNonSetThreadId() {
		consumerBean.setPostUserId(1);
		consumerBean.setPost("foo");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPostFailsWithNonSetUserId() {
		consumerBean.setPostThreadId(1);
		consumerBean.setPost("foo");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullUserName() {
		consumerBean.setUserName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyUserName() {
		consumerBean.setUserName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullUserPicturePath() {
		consumerBean.setUserPicturePath(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyUserPicturePath() {
		consumerBean.setUserPicturePath("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithNullUserMail() {
		consumerBean.setUserMail(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailsWithEmptyUserMail() {
		consumerBean.setUserMail("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetUserFailsWithNegativeUserId() {
		consumerBean.setUserMail("foo");
		consumerBean.setUserName("bar");
		consumerBean.setUserPicturePath("baz");
		consumerBean.setUser(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetUserFailsWithNonSetUserMail() throws Exception {
		consumerBean.setUserName("bar");
		consumerBean.setUserPicturePath("baz");
		consumerBean.setUser(2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetUserFailsWithNonSetUserName() throws Exception {
		consumerBean.setUserMail("foo");
		consumerBean.setUserPicturePath("baz");
		consumerBean.setUser(2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetUserFailsWithNonSetUserPicturePath() throws Exception {
		consumerBean.setUserName("bar");
		consumerBean.setUserMail("foo");
		consumerBean.setUser(2);
	}
}
