
package tinf13b4.forum.model;

import java.util.Date;

public class PostBuilder {

	private int postId;
	private int posterId;
	private int threadId;
	private String title;
	private String content;
	private Date date;

	public PostBuilder setPostId(int postId) {
		this.postId = postId;
		return this;
	}

	public PostBuilder setPosterId(int posterId) {
		this.posterId = posterId;
		return this;
	}

	public PostBuilder setThreadId(int threadId) {
		this.threadId = threadId;
		return this;
	}

	public PostBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public PostBuilder setContent(String content) {
		this.content = content;
		return this;
	}

	public PostBuilder setDate(Date date) {
		this.date = date;
		return this;
	}

	public Post build() {
		return new Post(postId, threadId, posterId, title, content, date);
	}
}
