package tinf13b4.forum.beans;

import java.util.ArrayList;
import java.util.List;

import tinf13b4.forum.controller.CategoryController;
import tinf13b4.forum.controller.PostController;
import tinf13b4.forum.controller.ThreadController;
import tinf13b4.forum.controller.UserController;
import tinf13b4.forum.model.Category;
import tinf13b4.forum.model.Post;
import tinf13b4.forum.model.Thread;
import tinf13b4.forum.model.User;

public class ProviderBean {

	private List<Category> categories;
	private List<Thread> threads;
	private List<User> users;
	private List<Post> posts;
	private String longText;
	private String heading;
	private int categoryId;
	private int threadId;
	private int userId;
	private Category category;
	private Thread thread;
	private User user;
	private String userName;

	public ProviderBean() {
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
		if (threadId != 0) {
			posts = controller.getPostsForThread(threadId);
		} else if (userId != 0) {
			posts = controller.getPostsForUser(userId);
		}
	}

	private void createUsers() {
		users = new ArrayList<>();
		UserController controller = new UserController();
		users = controller.getUsers();
	}

	private void createThreads() {
		threads = new ArrayList<>();
		ThreadController controller = new ThreadController();
		if (categoryId != 0) {
			threads = controller.getThreadsWithCategory(categoryId);
		} else if (threadId != 0) {
			threads = controller.getThreadsWithId(threadId);
		} else if (userId != 0) {
			threads = controller.getThreadsWithUser(userId);
		}
	}

	private void createCategories() {
		categories = new ArrayList<>();
		CategoryController controller = new CategoryController();
		if (threadId != 0) {
			categories = controller.getCategoryForThread(threadId);
		} else {
			categories = controller.getCategories();
		}
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

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		setUser(user);
	}

	public Category getCategory() {
		setCategory(category);
		return category;
	}

	public void setCategory(Category category) {
		if (categories == null)
			createCategories();

		for (Category a : categories)
			if (a.getId() == categoryId) {
				this.category = a;
				return;
			}
	}

	public Thread getThread() {
		setThread(thread);
		return thread;
	}

	public void setThread(Thread thread) {
		if (thread == null)
			createThreads();
		for (Thread a : threads)
			if (a.getId() == threadId) {
				this.thread = a;
				return;
			}
	}

	public User getUser() {
		setUser(user);
		return user;
	}

	public void setUser(User user) {
		if (users == null)
			createUsers();
		if (userName != null && !userName.isEmpty()) {
			for(User a : users) {
				if(a.getName().equals(userName)) {
					this.user = a;
					return;
				}
			}
		}
		else {
			for(User a: users) {
				if(a.getId() == userId) {
					this.user = a;
					return;
				}
			}
		}
	}
}
