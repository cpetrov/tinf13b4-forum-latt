
package tinf13b4.forum.beans;

import java.util.List;

import tinf13b4.forum.controller.AdminPanelSettingsController;
import tinf13b4.forum.controller.CategoryController;
import tinf13b4.forum.controller.UserController;
import tinf13b4.forum.model.Category;
import tinf13b4.forum.model.User;

public class AdminPanelSettingsBean {

	private AdminPanelSettingsController adminPanelSettingsController;
	private List<Category> categories;
	private String forumName;

	public AdminPanelSettingsBean() {
		adminPanelSettingsController = new AdminPanelSettingsController();
	}

	public String getForumName() {
		return forumName;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	
	public void setPageDescription(String pageDescription) {
		adminPanelSettingsController.setExistingPageDescription(pageDescription);
	}

	public String getPageDescription() {
		return adminPanelSettingsController.getExistingPageDescription();
	}

	public void setPageImprint(String pageImprint) {
		adminPanelSettingsController.setExistingImprint(pageImprint);
	}

	public String getPageImprint() {
		return adminPanelSettingsController.getExistingImprint();
	}

	public void setTermsOfUse(String termsOfUse) {
		adminPanelSettingsController.setExistingTermsOfUse(termsOfUse);
	}

	public String getTermsOfUse() {
		return adminPanelSettingsController.getExistingTermsOfUse();
	}

	public void setServiceMode(boolean serviceMode) {
		adminPanelSettingsController.setServiceMode(serviceMode);
	}

	public boolean getServiceMode() {
		return adminPanelSettingsController.isServiceMode();
	}

	public String getServiceReason() {
		return adminPanelSettingsController.getServiceReason();
	}

	public void setServiceReason(String serviceReason) {
		adminPanelSettingsController.setServiceReason(serviceReason);
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
}
