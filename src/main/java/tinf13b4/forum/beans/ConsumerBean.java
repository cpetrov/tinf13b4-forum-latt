
package tinf13b4.forum.beans;

import static com.google.common.base.Preconditions.checkArgument;
import tinf13b4.forum.controller.PostController;
import tinf13b4.forum.controller.ThreadController;
import tinf13b4.forum.controller.UserController;
import tinf13b4.forum.database.QueryExecutor;

public class ConsumerBean {

	private PostController postController;
	private UserController userController;
	private int postThreadId;
	private int postUserId;
	private String userName;
	private String userPicturePath;
	private String userMail;
	private boolean userConfirmed;
	private ThreadController threadController;
	private String threadTitle;
	private int threadUserId;
	private int threadCategoryId;
	private boolean threadReadOnly;

	public ConsumerBean() {
		postController = new PostController();
		userController = new UserController();
		threadController = new ThreadController();
	}

	protected ConsumerBean(QueryExecutor queryExecutor) {
		postController = new PostController(queryExecutor);
		userController = new UserController(queryExecutor);
		threadController = new ThreadController(queryExecutor);
	}

	public void setThread(String content) {
		checkThreadArguments(content);
		threadController.createThread(threadTitle, content, threadUserId, threadCategoryId, threadReadOnly);
	}

	private void checkThreadArguments(String content) {
		checkArgument(content!=null, "ThreadContent must not be null.");
		checkArgument(!content.isEmpty(), "ThreadContent must not be empty.");
		checkArgument(threadTitle != null, "ThreadTitle must not be null when setting thread.");
		checkArgument(threadUserId != 0, "ThreadUserId must not be 0 when setting thread.");
		checkArgument(threadCategoryId != 0, "ThreadCategoryId must not be 0 when setting thread.");
	}

	public void setThreadTitle(String title) {
		checkArgument(title != null, "ThreadTitle must not be null.");
		checkArgument(!title.isEmpty(), "ThreadTitle must not be empty.");
		threadTitle = title;
	}

	public void setThreadUserId(int id) {
		checkArgument(id > 0, "ThreadUserId must be > 0, but was " + id);
		threadUserId = id;
	}

	public void setThreadCategoryId(int id) {
		checkArgument(id > 0, "ThreadCategoryId must be > 0, but was " + id);
		threadCategoryId = id;
	}

	public void setThreadReadOnly(boolean readOnly) {
		threadReadOnly = readOnly;
	}

	public void setPost(String content) {
		checkPostArguments(content);
		postController.addPostToThread(postThreadId, postUserId, content);
	}

	private void checkPostArguments(String content) {
		checkArgument(content != null, "Content must not be null.");
		checkArgument(!content.isEmpty(), "Content must not be empty.");
		checkArgument(postThreadId != 0, "ThreadId must not be 0 when setting post.");
		checkArgument(postUserId != 0, "UserId must not be 0 when setting post.");
	}

	public void setUser(int userId) {
		checkUserArguments(userId);
		userController.updateUser(userId, userName, userPicturePath, userMail, userConfirmed);
	}

	private void checkUserArguments(int userId) {
		checkArgument(userId > 0, "UserId must be > 0, but was " + userId);
		checkArgument(userMail != null, "UserMail must not be null when setting user.");
		checkArgument(userName != null, "UserName must not be null when setting user.");
		checkArgument(userPicturePath != null, "UserPicturePath must not be null when setting user.");
	}

	public void setPostThreadId(int threadId) {
		checkArgument(threadId > 0, "ThreadId must be > 0, but was " + threadId);
		this.postThreadId = threadId;
	}

	public void setPostUserId(int userId) {
		checkArgument(userId > 0, "UserId must be > 0, but was " + userId);
		this.postUserId = userId;
	}

	public void setUserName(String userName) {
		checkArgument(userName != null, "UserName must not be null.");
		checkArgument(!userName.isEmpty(), "UserName must not be empty.");
		this.userName = userName;
	}

	public void setUserPicturePath(String userPicturePath) {
		checkArgument(userPicturePath != null, "UserPicturePath must not be null.");
		checkArgument(!userPicturePath.isEmpty(), "UserPicturePath must not be empty.");
		this.userPicturePath = userPicturePath;
	}

	public void setUserMail(String userMail) {
		checkArgument(userMail != null, "UserMail must not be null.");
		checkArgument(!userMail.isEmpty(), "UserMail must not be empty.");
		this.userMail = userMail;
	}

	public int getLastInsertId() {
		return threadController.getLastInsertId();
	}
}
