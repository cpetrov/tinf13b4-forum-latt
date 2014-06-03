
package tinf13b4.forum.model;

import static com.google.common.base.Preconditions.checkArgument;

public class Category {

	private int id;
	private String title;
	private String subtitle;

	public Category(int id, String title, String subtitle) {
		checkArgument(id>=0, "Id must be >=0, but was: " + id);
		checkArgument(title!= null, "Category Title must not be null.");
		checkArgument(!title.isEmpty(), "Category Title must not be empty.");
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setDescription(String subtitle) {
		checkArgument(subtitle!= null, "Subtitle must not be null.");
		checkArgument(!subtitle.isEmpty(), "Subtitle must not be empty.");
		this.subtitle = subtitle;
	}
}
