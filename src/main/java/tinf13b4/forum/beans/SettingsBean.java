
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
	private String pageName;

	public SettingsBean() {
		settingsController = new SettingsController();
	}

	public String getPageName() {
		return settingsController.getPageName();
	}
	public void setPageName(String pageName) {
		settingsController.setPageName(pageName);
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
}
