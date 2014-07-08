
package tinf13b4.forum.beans;

import java.util.List;

import tinf13b4.forum.controller.SettingsController;
import tinf13b4.forum.controller.CategoryController;
import tinf13b4.forum.controller.UserController;
import tinf13b4.forum.model.Category;
import tinf13b4.forum.model.User;

public class SettingsBean {

	private SettingsController settingsController;
	private List<Category> categories;
	private String forumName;
	// update Category
	private int categoryId;
	private String categoryTitle;
	private String categorySubtitle;
	private int categorySortNumber;
	private boolean updateCategory;

	public SettingsBean() {
		settingsController = new SettingsController();
	}

	public String getForumName() {
		return forumName;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public void setPageDescription(String pageDescription) {
		settingsController.setExistingPageDescription(pageDescription);
	}

	public String getPageDescription() {
		return settingsController.getExistingPageDescription();
	}

	public void setPageImprint(String pageImprint) {
		settingsController.setExistingImprint(pageImprint);
	}

	public String getPageImprint() {
		return settingsController.getExistingImprint();
	}

	public void setTermsOfUse(String termsOfUse) {
		settingsController.setExistingTermsOfUse(termsOfUse);
	}

	public String getTermsOfUse() {
		return settingsController.getExistingTermsOfUse();
	}

	public void setServiceMode(boolean serviceMode) {
		settingsController.setServiceMode(serviceMode);
	}

	public boolean getServiceMode() {
		return settingsController.isServiceMode();
	}

	public String getServiceReason() {
		return settingsController.getServiceReason();
	}

	public void setServiceReason(String serviceReason) {
		settingsController.setServiceReason(serviceReason);
	}

	public List<User> getUsers() {
		UserController controller = new UserController();
		return controller.getAllUsers();
	}

	public void setUsers(List<User> users) {
	}

	public List<Category> getCategories() {
		CategoryController controller = new CategoryController();
		categories = controller.getCategories();
		return categories;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategorySubtitle() {
		return categorySubtitle;
	}

	public void setCategorySubtitle(String categorySubtitle) {
		this.categorySubtitle = categorySubtitle;
	}

	public int getCategorySortNumber() {
		return categorySortNumber;
	}

	public void setCategorySortNumber(int categorySortNumber) {
		this.categorySortNumber = categorySortNumber;
	}

	public void setUpdateCategory(boolean updateCategory) {
		settingsController.updateCategory(categoryId, categoryTitle, categorySubtitle, categorySortNumber);
	}
}
