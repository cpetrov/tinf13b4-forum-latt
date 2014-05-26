
package tinf13b4.forum.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Date;

public class Thread {

	private int id;
	private int threadStarterId;
	private int categoryId;
	private String title;
	private String content;
	private Date date;
	private boolean readonly;

	public Thread(int id, int threadStarterId, int categoryId, String title, String content, Date date, boolean readonly) {
		validateArguments(id, threadStarterId, categoryId, title, content, date);
		this.id = id;
		this.threadStarterId = threadStarterId;
		this.categoryId = categoryId;
		this.title = title;
		this.content = content;
		this.date = date;
		this.readonly = readonly;
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
}
