package tinf13b4.forum.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;

public class AdminPanelSettingsController {
	private String existingPageDescription;
	private String existingPageInprint;
	private String existingTermsOfUse;
	private int orderNumber;

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

	public void setExistingInprint(String pageInprint) {
		queryExecutor.executeUpdate("UPDATE Settings SET Inprint='"+pageInprint+"';");
	}

	public String getExistingInprint() {
		ResultSet rs = queryExecutor.executeQuery("SELECT Inprint FROM Settings;"); // Marius darauf aufmerksam machen, in der Settings Table in der DB steht "Inprint", nicht "Imprint", momentan würd es hier noch krachen!
		if (rs == null)
			return "";
		try {
			while (rs.next()) {
				existingPageInprint = rs.getString("Inprint");
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
		return existingPageInprint;
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
	
//	public void setOrderNumber(int orderNumber, int categoryID) {
//		queryExecutor.executeUpdate("UPDATE Categories SET orderNumber='" + orderNumber + "' WHERE categoryID LIKE '" + categoryID + "';");
//	}
	
	
}