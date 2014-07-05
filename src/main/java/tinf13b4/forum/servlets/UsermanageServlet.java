package tinf13b4.forum.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;

import tinf13b4.forum.controller.UserController;
import tinf13b4.forum.model.User;

@WebServlet("/api/usermanage")
public class UsermanageServlet extends JsonServlet {

	public UsermanageServlet() {
		
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response, JsonObject postData)
			throws ServletException, IOException {
		UserController controller = new UserController();
		List<User> users = controller.getAllUsers();
		
		JsonArray json = userListToJson(users);
		json.writeTo(response.getWriter());
	}

	private JsonArray userListToJson(List<User> users) {
		JsonArray json = new JsonArray();
		for(User user: users){
			JsonObject jsonObject = new JsonObject();
			
			jsonObject.add("id", user.getId());
			jsonObject.add("name", user.getName());
			jsonObject.add("mail", user.getMail());
			jsonObject.add("picture", user.getPicture());
			jsonObject.add("permission", user.getPermission());
			jsonObject.add("confirmed", user.getConfirmed());
			
			json.add(jsonObject);
		}
		return json;
	}
}
