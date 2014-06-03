
package tinf13b4.forum.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Category;
import tinf13b4.forum.model.CategoryBuilder;

public class CategoryController {

	private ResultSet rs;
	private QueryExecutor executor;

	public CategoryController() {
		ConnectionFactory factory = new ConnectionFactory();
		executor = new QueryExecutor(factory.createConnection());
	}

	protected CategoryController(QueryExecutor executor) {
		this.executor = executor;
	}

	public List<Category> getCategories() {
		rs = executor.executeQuery("SELECT Category_ID, Title, Subtitle" + " FROM Category" + " ORDER BY Title ASC;");
		List<Category> categories = new ArrayList<Category>();
		if (rs == null)
			return new ArrayList<Category>();
		else {
			try {
				while (rs.next()) {
					categories.add(buildCategory());
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return categories;
	}
	
	private Category buildCategory() throws SQLException {
		CategoryBuilder categoryBuilder = new CategoryBuilder();
		categoryBuilder.setId(rs.getInt("Category_ID"));
		categoryBuilder.setTitle(rs.getString("Title"));
		categoryBuilder.setSubtitle(rs.getString("Subtitle"));
		return categoryBuilder.build();
	}
}
