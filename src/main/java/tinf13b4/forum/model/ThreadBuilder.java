
package tinf13b4.forum.model;

import java.util.Date;

public class ThreadBuilder {

	private int id;
	private User user;
	private int categoryId;
	private String title;
	private String content;
	private Date date;
	private boolean readOnly;

	public ThreadBuilder setId(int id) {
		this.id = id;
		return this;
	}

	public ThreadBuilder setUser(User user) {
		this.user = user;
		return this;
	}

	public ThreadBuilder setCategoryId(int categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public ThreadBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public ThreadBuilder setContent(String content) {
		this.content = content;
		return this;
	}

	public ThreadBuilder setDate(Date date) {
		this.date = date;
		return this;
	}

	public ThreadBuilder setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		return this;
	}

	public Thread build() {
		return new Thread(id, user, categoryId, title, content, date, readOnly);
	}
}
