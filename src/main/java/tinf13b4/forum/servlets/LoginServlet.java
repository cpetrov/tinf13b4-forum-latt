package tinf13b4.forum.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.json.JsonValue;

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
		
		JsonValue logonCredential = postData.get("logonCredential");
		JsonValue password = postData.get("password");
		
		// 
		if(logonCredential != null){
			errors.add("Name can not be empty");
		} else {
			response.sendError(HttpStatusCodes.BAD_REQUEST, "Bad Request");
		}
		
		// If Password is empty return error message
		if(password != null){			
			errors.add("Password can not be empty");
		} else {
			response.sendError(HttpStatusCodes.BAD_REQUEST, "Bad Request");
		}
		
		// Check error size
		if(errors.size() > 0){
			JsonArray json = new JsonArray();

			for(String error:errors){
				json.add(error);
			}

			JsonObject jsonObject = new JsonObject();

			jsonObject.add("errors", json);
			jsonObject.writeTo(response.getWriter());
		} else {

			// Validate with Database
			loginController.loginDataValidator(logonCredential.asString(), password.asString());
		}
	}
}