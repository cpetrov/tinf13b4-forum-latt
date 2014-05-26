
package tinf13b4.forum.model;

import static com.google.common.base.Preconditions.checkArgument;

public class Category {

	private int id;
	private String name;
	private String description;

	public Category(int id, String name) {
		checkArgument(id>=0, "Id must be >=0, but was: " + id);
		checkArgument(name!= null, "Category Name must not be null.");
		checkArgument(!name.isEmpty(), "Category Name must not be empty.");
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		checkArgument(description!= null, "Description must not be null.");
		checkArgument(!description.isEmpty(), "Description must not be empty.");
		this.description = description;
	}
}
