
package tinf13b4.forum.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;

public class UserBuilder {

	private int id;
	private String name;
	private Blob picture;
	private String eMail;
	private Date joinedOn;
	private ArrayList<Post> posts;

	public UserBuilder setId(int id) {
		this.id = id;
		return this;
	}

	public UserBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public UserBuilder setPicture(Blob picture) {
		this.picture = picture;
		return this;
	}

	public UserBuilder setEMail(String eMail) {
		this.eMail = eMail;
		return this;
	}
	
	public UserBuilder setJoinedOn(Date joinedOn) {
		this.joinedOn = joinedOn;
		return this;
	}
	
	public UserBuilder setPosts(ArrayList<Post> posts) {
		this.posts = posts;
		return this;
	}

	public User build() {
		return new User(id, name, picture, eMail, joinedOn, posts);
	}
}
