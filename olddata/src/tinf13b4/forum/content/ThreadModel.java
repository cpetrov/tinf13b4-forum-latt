package tinf13b4.forum.content;

import java.util.ArrayList;
import java.util.Date;

public class ThreadModel 
{
	private int id;
	private String title;
	private String content;
	private int user_ID;
	private Date date;
	private boolean readonly;
	private int category_ID;
	private ArrayList<PostModel> posts;
	
	public ThreadModel(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isReadonly() {
		return readonly;
	}
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	public int getCategory_ID() {
		return category_ID;
	}
	public void setCategory_ID(int category_ID) {
		this.category_ID = category_ID;
	}
	public ArrayList<PostModel> getPosts() {
		return posts;
	}
	public void addPost(int post_ID) {
		posts.add(new PostModel(post_ID));
	}
}
