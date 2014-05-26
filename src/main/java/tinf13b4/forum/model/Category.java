
package tinf13b4.forum.model;

import static com.google.common.base.Preconditions.checkArgument;

public class Category {

	private int categoryId;
	private String name;
	private String description;

	public Category(int categoryId, String name) {
		checkArgument(categoryId>=0, "CategoryId must be >=0, but was: " + categoryId);
		checkArgument(name!= null, "Category Name must not be null.");
		checkArgument(!name.isEmpty(), "Category Name must not be empty.");
		this.categoryId = categoryId;
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
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
