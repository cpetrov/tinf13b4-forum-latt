
package tinf13b4.forum.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import tinf13b4.forum.model.Category;
import tinf13b4.forum.model.Member;
import tinf13b4.forum.model.Post;
import tinf13b4.forum.model.Thread;
import tinf13b4.forum.model.ThreadBuilder;

public class DummyProviderBean {

	private List<Category> categories;
	private List<Thread> threads;
	private List<Member> members;
	private List<Post> posts;
	private String longText;
	private String heading;

	public DummyProviderBean() {
		createFakeCategories();
		createFakeMembers();
		createFakeThreads();
		createFakePosts();
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

	private void createFakePosts() {
		posts = new ArrayList<Post>();
		posts.add(new Post(getRandomInt(), getRandomInt(), getRandomInt(), "The post title.",
				"<p>Some post content.</p>", new Date()));
		posts.add(new Post(getRandomInt(), getRandomInt(), getRandomInt(), "Another post title.",
				"<p>And more post content.</p>", new Date()));
		posts.add(new Post(getRandomInt(), getRandomInt(), getRandomInt(), "Wow, third post",
				"<p>That's some cool content!</p>", new Date()));
		posts.add(new Post(getRandomInt(), getRandomInt(), getRandomInt(), "Wow, third post",
				"<p>That's some cool content!</p>", new Date()));
	}

	private void createFakeMembers() {
		members = new ArrayList<>();
		Member member = new Member(getRandomInt(), "Psychedelic Llama", getRandomInt(), new Date());
		member.setFacebook("/pages/Lama-Kamel/108027845885579?fref=ts&rf=108586525839174");
		member.setMail("llama@gmail.com");
		getMembers().add(member);
		getMembers().add(new Member(getRandomInt(), "Another Llama", getRandomInt(), new Date()));
		getMembers().add(new Member(getRandomInt(), "Just a Llama", getRandomInt(), new Date()));
		getMembers().add(new Member(getRandomInt(), "Another Llama", getRandomInt(), new Date()));
		getMembers().add(new Member(getRandomInt(), "Just a Llama", getRandomInt(), new Date()));
		getMembers().add(new Member(getRandomInt(), "Another Llama", getRandomInt(), new Date()));
	}

	private void createFakeThreads() {
		threads = new ArrayList<>();
		threads.add(buildThread1());
		threads.add(buildThread2());
		threads.add(buildThread1());
		threads.add(buildThread2());
		threads.add(buildThread1());
		threads.add(buildThread2());
	}

	private Thread buildThread1() {
		ThreadBuilder threadBuilder = new ThreadBuilder();
		threadBuilder.setCategoryId(getRandomInt());
		threadBuilder.setContent("<p>The thread content.</p><p>Donec tempor nibh ut quam sollicitudin, "
				+ "et mollis lacus dictum. Nullam ultrices id orci id luctus."
				+ " Praesent ut tellus interdum turpis tristique pellentesque "
				+ "at quis ipsum. Cras congue nec libero nec euismod. Ut felis"
				+ " turpis, vehicula quis posuere sit amet, placerat at quam."
				+ " Vestibulum convallis, risus quis laoreet consequat, neque "
				+ "nunc sollicitudin tellus, malesuada fermentum nibh nulla vitae"
				+ " diam. Cum sociis natoque penatibus et magnis dis parturient "
				+ "montes, nascetur ridiculus mus. Vivamus eu velit non neque "
				+ "blandit dignissim ac quis tellus. Phasellus posuere ligula id"
				+ " vehicula facilisis. Nullam eleifend condimentum venenatis. "
				+ "Duis et orci id turpis tincidunt ultricies.</p>");
		threadBuilder.setDate(new Date());
		threadBuilder.setId(getRandomInt());
		threadBuilder.setReadonly(false);
		threadBuilder.setThreadStarterId(getRandomInt());
		threadBuilder.setTitle("The thread title.");
		return threadBuilder.build();
	}

	private Thread buildThread2() {
		ThreadBuilder threadBuilder = new ThreadBuilder();
		threadBuilder.setCategoryId(getRandomInt());
		threadBuilder.setContent("The second thread content.");
		threadBuilder.setDate(new Date());
		threadBuilder.setId(getRandomInt());
		threadBuilder.setReadonly(false);
		threadBuilder.setThreadStarterId(getRandomInt());
		threadBuilder.setTitle("The second thread title.");
		return threadBuilder.build();
	}

	private void createFakeCategories() {
		categories = new ArrayList<>();
		Category category1 = new Category(getRandomInt(), "First category");
		category1.setDescription("Description of the first category");
		Category category2 = new Category(getRandomInt(), "Second category");
		category2.setDescription("Description of the second category");
		categories.add(category1);
		categories.add(category2);
		categories.add(category1);
		categories.add(category2);
		categories.add(category1);
		categories.add(category2);
	}

	private int getRandomInt() {
		Random random = new Random();
		return random.nextInt(100) + 1;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<Thread> getThreads() {
		return threads;
	}

	public List<Member> getMembers() {
		return members;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public String getLongText() {
		return longText;
	}

	public String getHeading() {
		return heading;
	}
}
