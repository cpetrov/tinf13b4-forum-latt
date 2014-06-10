package tinf13b4.forum.beans;

import tinf13b4.forum.controller.AdminPanelSettingsController;

public class AdminPanelSettingsBean {
	private AdminPanelSettingsController adminPanelSettingsController;

	public AdminPanelSettingsBean() {
		adminPanelSettingsController = new AdminPanelSettingsController();
	}

	public void setPageDescription(String pageDescription) {
		adminPanelSettingsController.setExistingPageDescription(pageDescription);
	}

	public String getPageDescription() {
		return adminPanelSettingsController.getExistingPageDescription();
	}

	public void setPageInprint(String pageInprint) {
		adminPanelSettingsController.setExistingInprint(pageInprint);
	}

	public String getPageInprint() {
		return adminPanelSettingsController.getExistingInprint();
	}

	public void setTermsOfUse(String termsOfUse) {
		adminPanelSettingsController.setExistingTermsOfUse(termsOfUse);
	}

	public String getTermsOfUse() {
		return adminPanelSettingsController.getExistingTermsOfUse();
	}
}