
package tinf13b4.forum.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Category;

public class CategoryController {

	private ArrayList<Category> categories;
	QueryExecutor queryExecutor;

	public CategoryController() {
		Connection connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
	}

	public ArrayList<Category> getCategorys() {
		return categories;
	}

	public void setCategorys(ArrayList<Category> categorys) {
		this.categories = new ArrayList<Category>();
		getCategoriesFromDB();
	}

	private void getCategoriesFromDB() {
		ResultSet rs = queryExecutor.executeQuery("SELECT Category_ID, Name, Subtitle" + " FROM Category" + "ORDER BY Name ASC;");
		if (rs == null)
			return;
		try {
			while (rs.next()) {
				Category category = new Category(rs.getInt("Category_ID"), rs.getString("Name"));
				category.setDescription("Subtitle");
				categories.add(category);
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
