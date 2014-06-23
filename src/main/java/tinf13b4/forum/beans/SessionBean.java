package tinf13b4.forum.beans;

import java.util.Date;

public class SessionBean {

	private String id;
	private String userName;
	private Date createTime;
	private boolean isLoggedIn = false;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = new Date(createTime);
	}

	public void login(){
		this.isLoggedIn = true;
	}

	public void logout(){
		this.isLoggedIn = false;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
