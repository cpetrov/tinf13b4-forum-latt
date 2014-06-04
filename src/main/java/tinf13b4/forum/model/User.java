package tinf13b4.forum.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;


public class User {

	private final int id;
	private final String name;
	private Blob picture;
	private String eMail;
	private Date joinedOn;
	private ArrayList<Post> posts;

	// private final Date joinedOn; // TODO Noch nicht implementiert

	public User(int id, String name, Blob picture, String eMail, Date joinedOn, ArrayList<Post> posts) {
		checkArguments(id, name, picture, eMail, joinedOn, posts);
		this.id = id;
		this.name = name;
		this.setPicture(picture);
		this.eMail = eMail;
		this.joinedOn = joinedOn;
		this.posts = posts;
	}

	private void checkArguments(int id, String name, Blob picture, String eMail, Date joinedOn, ArrayList<Post> posts) {
		checkArgument(id > 0, "Id must be positive.");
		checkArgument(name != null, "Name must not be null.");
		checkArgument(!name.isEmpty(), "Name must not be empty.");
		// TODO picture
		checkArgument(eMail != null, "Mail must not be null.");
		checkArgument(!eMail.isEmpty(), "Mail must not be empty.");
		// TODO joinedOn
		checkArgument(posts != null, "Posts must not be null.");
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		checkArgument(eMail != null, "Mail must not be null.");
		checkArgument(!eMail.isEmpty(), "Mail must not be empty.");
		this.eMail = eMail;
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
