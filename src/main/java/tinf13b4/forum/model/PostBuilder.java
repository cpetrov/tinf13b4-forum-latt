package tinf13b4.forum.model;

import java.util.Date;

public class PostBuilder {

	private int postId;
	private User user;
	private int threadId;
	private String content;
	private Date date;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getThreadId() {
		return threadId;
	}
	
	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}
	
	public Post build() {
		return new Post(postId, user, threadId, content, date);
	}

}
