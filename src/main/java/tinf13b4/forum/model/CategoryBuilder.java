package tinf13b4.forum.model;

public class CategoryBuilder {

	private int id;
	private String title;
	private String subtitle;

	public CategoryBuilder setId(int id) {
		this.id = id;
		return this;
	}

	public CategoryBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public CategoryBuilder setSubtitle(String subtitle) {
		this.subtitle = subtitle;
		return this;
	}

	public Category build() {
		return new Category(id, title, subtitle);
	}
}
