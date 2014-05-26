
package tinf13b4.forum.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Date;

public class Post {

	private int postId;
	private int posterId;
	private int threadId;
	private String title;
	private String content;
	private Date date;

	public Post(int postId, int threadId, int posterId, String title, String content, Date date) {
		validateArguments(postId, threadId, posterId, title, content, date);
		this.postId = postId;
		this.threadId = threadId;
		this.posterId = posterId;
		this.title = title;
		this.content = content;
		this.date = date;
	}

	private void validateArguments(int postId, int threadId, int posterId, String title, String content, Date date) {
		checkArgument(postId > 0, "PostId must be positive.");
		checkArgument(threadId > 0, "ThreadId must be positive.");
		checkArgument(posterId > 0, "PosterId must be positive.");
		checkArgument(title != null, "Title must not be null.");
		checkArgument(!title.isEmpty(), "Title must not be empty.");
		checkArgument(content != null, "Content must not be null.");
		checkArgument(!content.isEmpty(), "Content must not be empty.");
		checkArgument(date != null, "Date must not be null.");
	}

	public int getPostId() {
		return postId;
	}

	public int getThreadId() {
		return threadId;
	}

	public int getPosterId() {
		return posterId;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + postId;
		result = prime * result + posterId;
		result = prime * result + threadId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (postId != other.postId)
			return false;
		if (posterId != other.posterId)
			return false;
		if (threadId != other.threadId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
