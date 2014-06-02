
package tinf13b4.forum.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Thread {

	private final int id;
	private final int threadStarterId;
	private final int categoryId;
	private final String title;
	private final String content;
	private final Date date;
	private final boolean readonly;
	private final List<Post> posts;

	public Thread(int id, int threadStarterId, int categoryId, String title, String content, Date date, boolean readonly) {
		validateArguments(id, threadStarterId, categoryId, title, content, date);
		this.id = id;
		this.threadStarterId = threadStarterId;
		this.categoryId = categoryId;
		this.title = title;
		this.content = content;
		this.date = date;
		this.readonly = readonly;
		posts = new ArrayList<>();
	}

	private void validateArguments(int id, int threadStarterId, int categoryId, String title, String content, Date date) {
		checkArgument(id > 0, "Id must be positive.");
		checkArgument(threadStarterId > 0, "ThreadStarterId must be positive.");
		checkArgument(categoryId > 0, "CategoryId must be positive.");
		checkArgument(title != null, "Title must not be null.");
		checkArgument(!title.isEmpty(), "Title must not be empty.");
		checkArgument(content != null, "Content must not be null.");
		checkArgument(!content.isEmpty(), "Content must not be empty.");
		checkArgument(date != null, "Date must not be null.");
	}

	public int getId() {
		return id;
	}

	public int getThreadStarterId() {
		return threadStarterId;
	}

	public int getCategoryId() {
		return categoryId;
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

	public boolean isReadonly() {
		return readonly;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + (readonly ? 1231 : 1237);
		result = prime * result + threadStarterId;
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
		Thread other = (Thread) obj;
		if (categoryId != other.categoryId)
			return false;
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
		if (readonly != other.readonly)
			return false;
		if (threadStarterId != other.threadStarterId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void addPost(Post post) {
		posts.add(post);
	}
}
