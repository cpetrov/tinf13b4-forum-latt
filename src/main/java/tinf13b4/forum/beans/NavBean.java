
package tinf13b4.forum.beans;

public class NavBean {

	private final Nav[] navs = new Nav[]{
			new Nav("Startseite", "home.jsp"),
			new Nav("Mitglieder", "users.jsp"),
			new Nav("Anmelden", "login.jsp"),
			new Nav("Registrieren", "register.jsp")
	};

	public Nav[] getNavs() {
		return navs;
	}

	public String getClassIfActive(String navName, String viewName) {
		return navName.equals(viewName) ? "active" : "";
	}

	public static class Nav {

		public Nav(String name, String href) {
			this.name = name;
			this.href = href;
		}

		private final String name;
		private final String href;

		public String getName() {
			return name;
		}

		public String getHref() {
			return href;
		}
	}
}