
package tinf13b4.forum.controller;

import static tinf13b4.forum.controller.ResultSetUtil.buildCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.model.Category;

public class CategoryController {

	private ResultSet resultSet;
	private QueryExecutor executor;

	public CategoryController() {
		this(new QueryExecutor(new ConnectionFactory().createConnection()));
	}

	public CategoryController(QueryExecutor executor) {
		this.executor = executor;
	}

	public void setRs(ResultSet rs) {
		this.resultSet = rs;
	}

	public List<Category> getCategoryForThread(int threadId) {
		resultSet = executor.executeQuery("SELECT C.Category_ID, C.Title, C.Subtitle, C.OrderNumber "
										+ "FROM Categories C, Threads T "
										+ "WHERE T.Thread_ID = " + threadId + " "
										+ "AND C.Category_ID = T.Category_ID "
										+ "ORDER BY C.Title ASC;");
		List<Category> categories = new ArrayList<Category>();
		if (resultSet == null)
			return new ArrayList<Category>();
		else {
			try {
				while (resultSet.next()) {
					categories.add(buildCategory(resultSet));
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return categories;
	}

	public List<Category> getCategories() {
		resultSet = executor.executeQuery("SELECT Category_ID, Title, Subtitle, OrderNumber FROM Categories ORDER BY OrderNumber ASC;");
		List<Category> categories = new ArrayList<Category>();
		if (resultSet == null)
			return new ArrayList<Category>();
		else {
			try {
				while (resultSet.next()) {
					categories.add(buildCategory(resultSet));
				}
			} catch (SQLException e) {
				new IllegalStateException("SQL Error: " + e);
			}
		}
		return categories;
	}
}
