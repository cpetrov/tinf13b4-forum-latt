package tinf13b4.forum.content;

import java.util.Date;

public class ThreadModel 
{
	private int id;
	private String title;
	private String content;
	private String threadStarter;
	private Date date;
	private boolean readonly;
	private int categorie_ID;
	
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
	public String getThreadStarter() {
		return threadStarter;
	}
	public void setThreadStarter(String threadStarter) {
		this.threadStarter = threadStarter;
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
	public int getCategorie_ID() {
		return categorie_ID;
	}
	public void setCategorie_ID(int categorie_ID) {
		this.categorie_ID = categorie_ID;
	}
}
