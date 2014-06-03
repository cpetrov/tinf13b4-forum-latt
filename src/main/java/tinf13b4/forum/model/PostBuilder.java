package tinf13b4.forum.model;

import java.util.Date;

public class PostBuilder {

	private int postId;
	private int userId;
	private String content;
	private Date date;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public Post build() {
		return new Post(postId, userId, content, date);
	}
}
