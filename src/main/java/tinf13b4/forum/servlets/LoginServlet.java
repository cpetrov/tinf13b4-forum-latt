package tinf13b4.forum.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.json.JsonValue;

import tinf13b4.forum.controller.ConfirmationKeyController;
import tinf13b4.forum.controller.PasswordController;
import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.register.RegisterDataValidator;
import tinf13b4.forum.register.SendMail;

@WebServlet("/api/login")
public class LoginServlet extends JsonServlet {

	public LoginServlet() {
		passwordController = new PasswordController();
	}

	private static final long serialVersionUID = 1L;
	private final PasswordController passwordController;

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response, JsonObject postData) throws ServletException, IOException {
		
		List<String> errors = new ArrayList<String>();
		
		JsonValue logonCredential = postData.get("logonCredential");
		JsonValue password = postData.get("password");

		if(logonCredential != null){
			errors.add("Name darf nicht leer sein");
		} else {
			response.sendError(HttpStatusCodes.BAD_REQUEST, "Bad Request");
		}
		
		if(password != null){			
			errors.add("Passwort darf nicht leer sein");
		} else {
			response.sendError(HttpStatusCodes.BAD_REQUEST, "Bad Request");
		}
		
		if(errors.size() > 0){
			JsonArray json = new JsonArray();

			for(String error:errors){
				json.add(error);
			}

			JsonObject jsonObject = new JsonObject();

			jsonObject.add("errors", json);
			jsonObject.writeTo(response.getWriter());
		} else {

						
		}
	}
}