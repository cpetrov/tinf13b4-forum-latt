package tinf13b4.forum.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Category;

public class SettingsController {
	private QueryExecutor queryExecutor;;
	private ResultSet rs;
	private String existingPageDescription;
	private String existingPageImprint;
	private String existingTermsOfUse;
	private boolean serviceMode;
	private String serviceReason;

	public SettingsController() {
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
		ResultSet rs = queryExecutor.executeQuery("SELECT Imprint FROM Settings;");
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

	public boolean isServiceMode() {
		rs = queryExecutor.executeQuery("SELECT ServiceMode FROM Settings;");
		try {
			while (rs.next())
			{
				serviceMode = rs.getBoolean("ServiceMode");
			}
		} catch (SQLException e) {
			new IllegalStateException("SQL Error: " + e);
		}
		return serviceMode;
	}

	public void setServiceMode(boolean serviceMode) {
		this.serviceMode = serviceMode;
		if(serviceMode)
			queryExecutor.executeUpdate("UPDATE `pmforum`.`Settings` SET `ServiceMode`=1 WHERE  `Settings_ID`=1;");
		else
			queryExecutor.executeUpdate("UPDATE `pmforum`.`Settings` SET `ServiceMode`=0 WHERE  `Settings_ID`=1;");

	}

	public String getServiceReason() {
		rs = queryExecutor.executeQuery("SELECT ServiceReason FROM Settings;");
		try {
			while (rs.next())
			{
				serviceReason = rs.getString("ServiceReason");
			}
		} catch (SQLException e) {
			new IllegalStateException("SQL Error: " + e);
		}
		return serviceReason;
	}

	public void setServiceReason(String serviceReason) {
		this.serviceReason = serviceReason;
		queryExecutor.executeUpdate("UPDATE `pmforum`.`Settings` SET `ServiceReason`='" + serviceReason +"' WHERE  `Settings_ID`=1;");
	}

	public void setOrderNumber(int orderNumber, int categoryID) {
		queryExecutor.executeUpdate("UPDATE Categories SET orderNumber = " + orderNumber
				+ " WHERE Category_ID = " + categoryID + ";");
	}

	public void updateCategory(int categoryId, String categoryTitle,
			String categorySubtitle, int categoryOrderNumber) {
		
		String query = "UPDATE Categories SET ";
		boolean comma = false;
		
		if(categoryTitle != null && !categoryTitle.isEmpty()) {
			query += "Title = '" + categoryTitle + "' ";
			comma = true;
		}
		if(categorySubtitle != null && !categorySubtitle.isEmpty()) {
			if(comma)
				query += ", ";
			query += "Subtitle = '" + categorySubtitle + "' ";
		}
		if(categoryOrderNumber > 0) {
			if(comma)
				query += ", ";
			query += "OrderNumber = " + categoryOrderNumber + " ";
		}
		
		testAndUpdateSortNumber(categoryId, categoryOrderNumber);
		
		query += " WHERE Category_ID = "+ categoryId +";";
		
		queryExecutor.executeUpdate(query);
	}

	private void testAndUpdateSortNumber(int categoryId, int categoryOrderNumber) {
		rs = queryExecutor.executeQuery("SELECT OrderNumber FROM Categories WHERE Category_ID = "+categoryId+";");
		int orderNumber = 0;
		if(rs == null)
			return;
		else{
			try {
				while(rs.next()){
					orderNumber = rs.getInt("OrderNumber");
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		queryExecutor.executeUpdate("UPDATE Categories SET OrderNumber = "+orderNumber+" WHERE OrderNumber = 2;");
	}

	public void updateUser(int userId, String userName, String userMail,
			String userPicture, boolean userConfirmed) {
		
		String query = "UPDATE Users SET ";
		boolean comma = false;
		
		if(userName != null && !userName.isEmpty()) {
			query += "Name = '" + userName + "' ";
			comma = true;
		}
		if(userMail != null && !userMail.isEmpty()) {
			if(comma)
				query += ", ";
			query += "Mail = '" + userMail + "' ";
			comma = true;
		}
		if(userPicture != null && !userPicture.isEmpty()) {
			if(comma)
				query += ", ";
			query += "Picture = '" + userPicture + "' ";
			comma = true;
		}
		
		if(comma)
			query += ", ";
			query += "Confirmed = '" + (userConfirmed ? 1 : 0) + "' ";
		
			query += " WHERE User_ID = "+ userId +";";
			queryExecutor.executeUpdate(query);
	}

	public String getForumName() {
		rs = queryExecutor.executeQuery("SELECT Forumname FROM Settings;");
		if(rs == null)
			return "Forum name";
		else{
			try {
				while(rs.next()){
					return rs.getString("Forumname");
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return "Forum name";
	}

	public void setForumName(String forumName) {
		queryExecutor.executeUpdate("UPDATE Settings SET Forumname = '" + forumName +"';");
	}
}
