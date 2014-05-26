
package tinf13b4.forum.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Date;

public class Member {

	private int id;
	private String name;
	private int posts;
	private String mail;
	private String facebook;
	private Date joinedOn;

	public Member(int id, String name, int posts, Date joinedOn) {
		checkArguments(id, name, posts, joinedOn);
		this.id = id;
		this.name = name;
		this.posts = posts;
		this.joinedOn = joinedOn;
	}

	private void checkArguments(int id, String name, int posts, Date joinedOn) {
		checkArgument(id > 0, "Id must be positive.");
		checkArgument(name != null, "Name must not be null.");
		checkArgument(!name.isEmpty(), "Name must not be empty.");
		checkArgument(posts >= 0, "Posts must not be negative.");
		checkArgument(joinedOn != null, "JoinedOn must not be null.");
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPosts() {
		return posts;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		checkArgument(mail != null, "Mail must not be null.");
		checkArgument(!mail.isEmpty(), "Mail must not be empty.");
		this.mail = mail;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		checkArgument(facebook != null, "Facebook must not be null.");
		checkArgument(!facebook.isEmpty(), "Facebook must not be empty.");
		this.facebook = facebook;
	}
	
	public Date getJoinedOn() {
		return joinedOn;
	}
}
