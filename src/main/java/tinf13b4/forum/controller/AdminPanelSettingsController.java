package tinf13b4.forum.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;

public class AdminPanelSettingsController {
	private String existingPageDescription;
	private String existingPageImprint;
	private String existingTermsOfUse;

	QueryExecutor queryExecutor;
	public AdminPanelSettingsController() {
		Connection connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
	}

	public void setExistingPageDescription(String pageDescription) {
		queryExecutor.executeUpdate("UPDATE Settings SET Pagedescription='"+pageDescription+"';");
	}

	public String getExistingPageDescription() {
		ResultSet rs = queryExecutor.executeQuery("SELECT Pagedescription FROM Settings;"); // hier eventuell die komplette Methode rausziehen
		if (rs == null)
			return "";
		try {
			while (rs.next()) {
				existingPageDescription = rs.getString("Pagedescription");
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
		return existingPageDescription;
	}

	public void setExistingImprint(String pageImprint) {
		queryExecutor.executeUpdate("UPDATE Settings SET Imprint='"+pageImprint+"';");
	}

	public String getExistingImprint() {
		ResultSet rs = queryExecutor.executeQuery("SELECT Imprint FROM Settings;"); // Marius darauf aufmerksam machen, in der Settings Table in der DB steht "Inprint", nicht "Imprint", momentan würd es hier noch krachen!
		if (rs == null)
			return "";
		try {
			while (rs.next()) {
				existingPageImprint = rs.getString("Imprint");
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
		return existingPageImprint;
	}
	public void setExistingTermsOfUse(String termsOfUse) {
		queryExecutor.executeUpdate("UPDATE Settings SET TermsOfUse='"+termsOfUse+"';");
	}

	public String getExistingTermsOfUse() {
		ResultSet rs = queryExecutor.executeQuery("SELECT TermsOfUse FROM Settings;");
		if (rs == null)
			return "";
		try {
			while (rs.next()) {
				existingTermsOfUse = rs.getString("TermsOfUse");
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
		return existingTermsOfUse;
	}	
}