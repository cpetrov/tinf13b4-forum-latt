package tinf13b4.forum.content;

public class CategoryModel {
	private int category_ID;
	private String name;
	private String subtitel;
	
	public CategoryModel(int category_ID, String name, String subtitle) {
		this.category_ID = category_ID;
		this.name = name;
		this.subtitel = subtitle;
	}
	
	public int getCategory_ID() {
		return category_ID;
	}
	public void setCategory_ID(int category_ID) {
		this.category_ID = category_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubtitel() {
		return subtitel;
	}
	public void setSubtitel(String subtitel) {
		this.subtitel = subtitel;
	}

}
