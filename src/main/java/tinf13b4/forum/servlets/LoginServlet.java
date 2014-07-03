package tinf13b4.forum.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.json.JsonValue;

import tinf13b4.forum.beans.SessionBean;
import tinf13b4.forum.controller.LoginController;

@WebServlet("/api/login")
public class LoginServlet extends JsonServlet {

	private LoginController loginController;


	public LoginServlet() {
		loginController = new LoginController();
	}

	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response, JsonObject postData) throws ServletException, IOException {

		List<String> errors = new ArrayList<String>();

		JsonValue jsonLogonData = postData.get("jsonLogonData");
		JsonValue jsonPassword = postData.get("password");

		// If Username is emtpy return error message
		if(jsonLogonData == null){
			errors.add("The Username should not be empty!");
		}

		// If Password is empty return error message
		if(jsonPassword == null){
			errors.add("The password must be at least 8 characters long!");
		}

		// Check error size
		if(errors.size() > 0){
			JsonObject json = errorListToJson(errors);

			json.writeTo(response.getWriter());
			
		} else {

			// Validate with Database
			errors = loginController.loginDataValidator(jsonLogonData.asString(), jsonPassword.asString());

			if(errors.size() > 0){
				JsonObject json = errorListToJson(errors);

				json.writeTo(response.getWriter());
			}else{
				HttpSession session = request.getSession();
				SessionBean userSession = (SessionBean)session.getAttribute("userSession");
				userSession.setId(session.getId());
				userSession.setUserName(postData.get("name").asString());
				userSession.setCreateTime(session.getCreationTime());
				userSession.setSession(session);
				userSession.login();
			}
		}
	}

	private JsonObject errorListToJson(List<String> errors){
		JsonArray json = new JsonArray();

		for(String error:errors){
			json.add(error);
		}

		JsonObject jsonObject = new JsonObject();

		jsonObject.add("errors", json);

		return jsonObject;
	}
}
