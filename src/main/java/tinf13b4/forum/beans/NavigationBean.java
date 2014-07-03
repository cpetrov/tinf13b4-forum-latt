
package tinf13b4.forum.beans;

public class NavigationBean {

	public NavigationBean() {
		acpNavigation = "";
	}

	private String category;
	private String page;
	private String acpNavigation;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getAcpNavigation() {
		return acpNavigation;
	}

	public void setAcpNavigation(String acpNavigation) {
		this.acpNavigation = acpNavigation;
	}
}
