package tinf13b4.forum.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Date;

public class Post {

	private int id;
	private int userId;
	private int threadId;
	private String content;
	private Date date;

	public Post(int id, int userId, int threadId, String content, Date date) {
		validateArguments(id, userId, threadId, content, date);
		this.id = id;
		this.setUserId(userId);
		this.threadId = threadId;
		this.content = content;
		this.date = date;
	}

	private void validateArguments(int postId, int userId, int threadId, String content,
			Date date) {
		checkArgument(postId > 0, "PostId must be positive.");
		checkArgument(userId > 0, "UserId must be positive.");
		checkArgument(threadId >0, "ThreadId must be positive.");
		checkArgument(content != null, "Content must not be null.");
		checkArgument(!content.isEmpty(), "Content must not be empty.");
		checkArgument(date != null, "Date must not be null.");
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	public int getUserId() {
		return userId;
	}
	
	public int getThreadId() {
		return threadId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		// TODO userID, threadId?
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
}
