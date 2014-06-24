package tinf13b4.forum.beans;

import java.util.Date;

import javax.servlet.http.HttpSession;

import tinf13b4.forum.controller.UserController;

public class SessionBean {
	
	private HttpSession session;
	private String id;
	private int userId;
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

	public void setIsLoggedIn(boolean isLoggedIn) {
		if(!isLoggedIn)
			logout();
	}
	
	public void logout(){
		this.isLoggedIn = false;
		this.id = null;
		this.userName = null;
		this.createTime = null;
		this.session.setAttribute("id", null);
		this.session.invalidate();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		UserController controller = new UserController();
		this.userId = controller.getUserId(userName);
	}
	
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
