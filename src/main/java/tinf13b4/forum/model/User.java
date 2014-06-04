package tinf13b4.forum.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;


public class User {

	private final int id;
	private final String name;
	private Blob picture;
	private String mail;
	private Date joinedOn;
	private ArrayList<Post> posts;

	// private final Date joinedOn; // TODO Noch nicht implementiert

	public User(int id, String name, Blob picture, String email, Date joinedOn, ArrayList<Post> posts) {
		checkArguments(id, name, picture, email, joinedOn, posts);
		this.id = id;
		this.name = name;
		this.setPicture(picture);
		this.mail = email;
		this.joinedOn = joinedOn;
		this.posts = posts;
	}

	private void checkArguments(int id, String name, Blob picture, String email, Date joinedOn, ArrayList<Post> posts) {
		checkArgument(id > 0, "Id must be positive.");
		checkArgument(name != null, "Name must not be null.");
		checkArgument(!name.isEmpty(), "Name must not be empty.");
		// TODO picture
		checkArgument(email != null, "Email must not be null.");
		checkArgument(!email.isEmpty(), "Email must not be empty.");
		// TODO joinedOn
		checkArgument(posts != null, "Posts must not be null.");
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		checkArgument(mail != null, "Mail must not be null.");
		checkArgument(!mail.isEmpty(), "Mail must not be empty.");
		this.mail = mail;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}
	
	public Date getJoinedOn() {
		return joinedOn;
	}
	
	public void setJoinedOn(Date joinedOn) {
		this.joinedOn = joinedOn;
	}
	
	public ArrayList<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}
}
