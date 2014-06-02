package tinf13b4.forum.content;

import java.sql.ResultSet;
import java.util.ArrayList;

import tinf13b4.forum.database.ConnectionManager;

public class CategoryController {
	private ArrayList<CategoryModel> categorys;
	private ConnectionManager conn;

	public ArrayList<CategoryModel> getCategorys() {
		return categorys;
	}

	public void setCategorys(ArrayList<CategoryModel> categorys) {
		this.categorys = new ArrayList<CategoryModel>();
		getCategorysFromDB();
	}

	private void getCategorysFromDB() {
		conn = new ConnectionManager();
		ResultSet rs = conn.executeCommand("SELECT Category_ID, Name, Subtitle"
				+ " FROM Category"
				+ "ORDER BY Name ASC;");
		if(rs == null)
			return;
		while(rs.next())
		{
			categorys.add(new CategoryModel(rs.getInt("Category_ID"), rs.getString("Name"), rs.getString("Subtitle")));
		}
	}
}
