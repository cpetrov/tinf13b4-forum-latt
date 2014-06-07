
package tinf13b4.forum.model;

import java.util.Date;

public class UserBuilder {

	private int id;
	private String name;
	private String picture;
	private String email;
	private Date joinedOn;
	private int postsCount;

	public UserBuilder setId(int id) {
		this.id = id;
		return this;
	}

	public UserBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public UserBuilder setPicture(String picture) {
		this.picture = picture;
		return this;
	}

	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserBuilder setJoinedOn(Date joinedOn) {
		this.joinedOn = joinedOn;
		return this;
	}

	public UserBuilder setPostsCount(int postsCount) {
		this.postsCount = postsCount;
		return this;
	}

	public User build() {
		return new User(id, name, postsCount, picture, email, joinedOn);
	}
}
