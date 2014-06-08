
package tinf13b4.forum.beans;

import static com.google.common.base.Preconditions.checkArgument;
import tinf13b4.forum.controller.PostController;

public class ConsumerBean {

	private PostController postController;

	private int threadId;
	private int userId;

	public ConsumerBean() {
		postController = new PostController();
	}

	public void setPost(String content) {
		checkArgument(content!=null, "Content must not be null.");
		checkArgument(!content.isEmpty(), "Content must not be empty.");
		checkArgument(threadId!=0, "ThreadId must not be 0 when setting post.");
		checkArgument(userId!=0, "UserId must not be 0 when setting post.");
		postController.addPostToThread(threadId, userId, content);
	}

	public void setThreadId(int threadId) {
		checkArgument(threadId>0, "ThreadId must be > 0, but was " + threadId);
		this.threadId = threadId;
	}

	public void setUserId(int userId) {
		checkArgument(userId>0, "UserId must be > 0, but was " + userId);
		this.userId = userId;
	}

}