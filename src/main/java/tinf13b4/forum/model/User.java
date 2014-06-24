package tinf13b4.forum.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Date;


public class User {

	private final int id;
	private final String name;
	private String picture;
	private String mail;
	private Date joinedOn;
	private int postCount;
	private boolean confirmed;

	public User(int id, String name, int postsCount, String picture, String email, Date joinedOn, boolean confirmed) {
		checkArguments(id, name, postsCount, picture, email, joinedOn);
		this.id = id;
		this.name = name;
		this.postCount = postsCount;
		this.setPicture(picture);
		this.mail = email;
		this.joinedOn = joinedOn;
		this.confirmed = confirmed;
	}

	private void checkArguments(int id, String name, int postsCount, String picture, String email, Date joinedOn) {
		checkArgument(id > 0, "Id must be positive.");
		checkArgument(name != null, "Name must not be null.");
		checkArgument(postsCount >= 0, "PostsCount must be >= 0, but was " + postsCount);
		checkArgument(!name.isEmpty(), "Name must not be empty.");
		checkArgument(email != null, "Email must not be null.");
		checkArgument(!email.isEmpty(), "Email must not be empty.");
		checkArgument(joinedOn!=null, "JoinedOn must not be null.");
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getJoinedOn() {
		return joinedOn;
	}

	public void setJoinedOn(Date joinedOn) {
		this.joinedOn = joinedOn;
	}

	public int getPostCount() {
		return postCount;
	}
	
	public boolean getConfirmed() {
		return confirmed;
	}
	
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((joinedOn == null) ? 0 : joinedOn.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + postCount;
		result = prime * result + (confirmed ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (joinedOn == null) {
			if (other.joinedOn != null)
				return false;
		} else if (!joinedOn.equals(other.joinedOn))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (postCount != other.postCount)
			return false;
		if (confirmed != other.confirmed)
			return false;
		return true;
	}
}
