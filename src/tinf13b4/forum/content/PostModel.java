package tinf13b4.forum.content;

import java.util.Date;

public class PostModel {
	private int post_ID;
	private String content;
	private String poster;
	private Date date;
	private String thread_ID;
	public int getPost_ID() {
		return post_ID;
	}
	public void setPost_ID(int post_ID) {
		this.post_ID = post_ID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getThread_ID() {
		return thread_ID;
	}
	public void setThread_ID(String thread_ID) {
		this.thread_ID = thread_ID;
	}
	

}
