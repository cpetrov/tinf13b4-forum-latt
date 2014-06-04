package tinf13b4.forum.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import tinf13b4.forum.controller.CategoryController;
import tinf13b4.forum.controller.PostController;
import tinf13b4.forum.controller.ThreadController;
import tinf13b4.forum.controller.UserController;
import tinf13b4.forum.model.Category;
import tinf13b4.forum.model.User;
import tinf13b4.forum.model.Post;
import tinf13b4.forum.model.Thread;
import tinf13b4.forum.model.ThreadBuilder;

public class DummyProviderBean {

	private List<Category> categories;
	private List<Thread> threads;
	private List<User> users;
	private List<Post> posts;
	private String longText;
	private String heading;
	private int categoryId;
	private int threadId;
	private Category category;

	public DummyProviderBean() {
		createLongText();
		heading = "Just a heading for static pages";
	}

	private void createLongText() {
		longText = " <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Posuere nec mattis dapibus lacinia habitant"
				+ " - iaculis erat ligula diam enim suspendisse duis sociosqu consectetur.Lobortis posuere tempor mollis "
				+ "eleifend justo faucibus - aenean nibh.Venenatis taciti mus aliquam facilisis et eleifend netus; ac fri"
				+ "ngilla adipiscing velit risus.Ridiculus Sodales vitae?Aliquet nec venenatis quam feugiat ad consectetur"
				+ " nisl. </p><p>Pulvinar lacinia quisque sem fermentum tempus ac augue - ante habitasse?Lorem dictumst Turpis da"
				+ "pibus enim justo interdum.Hendrerit vehicula pellentesque varius condimentum?Ridiculus cras ipsum rhonc"
				+ "us mollis eu nisi quisque tincidunt nunc magna - conubia rutrum justo non?Hendrerit aliquet dictum cond"
				+ "imentum. Ridiculus Phasellus quis.Tristique dui nisi curabitur mi litora elit?Phasellus lobortis hendre"
				+ "rit dis mollis aliquam; facilisis urna </p><p>fringilla vitae; condimentum non interdum habitasse.Viverra dict"
				+ "umst Cum maecenas mollis odio curabitur - ad facilisis suscipit conubia dignissim volutpat himenaeos po"
				+ "tenti.Purus nulla nec porta aptent orci? Ipsum mattis et leo.Pharetra quam rhoncus dapibus dui eros a po"
				+ "rta quisque erat; netus conubia senectus.Tristique tellus Dolor elementum cum a primis fusce: blandit j"
				+ "usto lectus aptent ullamcorper nibh leo.Cras taciti Facilisis penatibus felis elit; ante est?Purus portt"
				+ "itor malesuada mollis nisi varius ultricies: platea augue consectetur.</p><p>fringilla vitae; condimentum non interdum habitasse.Viverra dict"
				+ "umst Cum maecenas mollis odio curabitur - ad facilisis suscipit conubia dignissim volutpat himenaeos po"
				+ "tenti.Purus nulla nec porta aptent orci? Ipsum mattis et leo.Pharetra quam rhoncus dapibus dui eros a po"
				+ "rta quisque erat; netus conubia senectus.Tristique tellus Dolor elementum cum a primis fusce: blandit j"
				+ "usto lectus aptent ullamcorper nibh leo.Cras taciti Facilisis penatibus felis elit; ante est?Purus portt"
				+ "itor malesuada mollis nisi varius ultricies: platea augue consectetur.</p><p>fringilla vitae; condimentum non interdum habitasse.Viverra dict"
				+ "umst Cum maecenas mollis odio curabitur - ad facilisis suscipit conubia dignissim volutpat himenaeos po"
				+ "tenti.Purus nulla nec porta aptent orci? Ipsum mattis et leo.Pharetra quam rhoncus dapibus dui eros a po"
				+ "rta quisque erat; netus conubia senectus.Tristique tellus Dolor elementum cum a primis fusce: blandit j"
				+ "usto lectus aptent ullamcorper nibh leo.Cras taciti Facilisis penatibus felis elit; ante est?Purus portt"
				+ "itor malesuada mollis nisi varius ultricies: platea augue consectetur.</p> ";
	}

	private void createPosts() {
		posts = new ArrayList<Post>();
		PostController controller = new PostController();
		posts = controller.getPosts(threadId);
	}

	private void createUsers() {
		users = new ArrayList<>();
		UserController controller = new UserController();
		users = controller.getUsers();
	}

	private void createThreads() {
		threads = new ArrayList<>();
		ThreadController controller = new ThreadController();
		threads = controller.getThreads(categoryId);
	}

	private void createCategories() {
		categories = new ArrayList<>();
		CategoryController controller = new CategoryController();
		categories = controller.getCategories();
	}

	public List<Category> getCategories() {
		createCategories();
		return categories;
	}

	public List<Thread> getThreads() {
		createThreads();
		return threads;
	}

	public List<User> getUsers() {
		createUsers();
		return users;
	}

	public List<Post> getPosts() {
		createPosts();
		return posts;
	}

	public String getLongText() {
		return longText;
	}

	public String getHeading() {
		return heading;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public Category getCategory() {
		setCategory(category);
		return category;
	}
	
	public void setCategory(Category category) {
		if(categories == null)
			createCategories();

		for (Category a : categories)
			if (a.getId() == categoryId) {
				this.category = a;
				return;
			}
	}
}
