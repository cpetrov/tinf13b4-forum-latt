package tinf13b4.forum.beans;

import java.util.Date;

import javax.servlet.http.HttpSession;

import tinf13b4.forum.controller.UserController;
import tinf13b4.forum.model.User;

public class SessionBean {
	
	private HttpSession session;
	private String id;
	private User user;
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

	// Get and Set for boolean, because of use in JSP!
	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	public void setIsLoggedIn(boolean isLoggedIn) {
		if(!isLoggedIn)
			logout();
	}
	
	public void logout(){
		this.isLoggedIn = false;
		this.id = null;
		this.user = null;
		this.createTime = null;
		this.session.setAttribute("id", null);
		this.session.invalidate();
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(String userName) {
		UserController controller = new UserController();
		this.user = controller.getUser(userName);
	}
	
	public void setSession(HttpSession session) {
		this.session = session;
	}
}
