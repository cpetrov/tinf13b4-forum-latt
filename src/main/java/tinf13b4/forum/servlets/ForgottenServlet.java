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

import tinf13b4.forum.controller.PasswordController;
import tinf13b4.forum.controller.SendMailController;
import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.util.OverwriteVariablesWithResultUtil;
import tinf13b4.forum.util.UserDataValidatonUtil;

@WebServlet("/api/forgotten")
public class ForgottenServlet extends JsonServlet {

	public ForgottenServlet() {
		Connection connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
		userDataValidator = new UserDataValidatonUtil();
		passwordController = new PasswordController();
		sendMail = new SendMailController();
	}

	private static final long serialVersionUID = 1L;
	private final UserDataValidatonUtil userDataValidator;
	private final PasswordController passwordController;
	private final QueryExecutor queryExecutor;
	private final SendMailController sendMail;

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response, JsonObject postData) throws ServletException, IOException {
		JsonValue jsonUsername = postData.get("username");
		JsonValue jsonEmailAddress = postData.get("emailAddress");
		
		
		if(jsonUsername != null || jsonEmailAddress != null){
			
			OverwriteVariablesWithResultUtil result = userDataValidator.forgottenDatabaseDataValidator(jsonUsername.asString(), jsonEmailAddress.asString());
			List<String> errors = new ArrayList<String>(result.errors);

			if(errors.size() > 0){
				JsonArray json = new JsonArray();

				for(String error:errors){
					json.add(error);
				}

				JsonObject jsonObject = new JsonObject();

				jsonObject.add("errors", json);
				jsonObject.writeTo(response.getWriter());
				
			} else {
				
				// Get A Random Password
				String password = passwordController.generateNewPassword();

				// Get A Hashed Password
				String hashedPassword = passwordController.encryptPassword(password);
				
				// Send Data To Database
				queryExecutor.executeUpdate("UPDATE Users SET "
						+ "Password='" + hashedPassword + "' WHERE "
						+ "username='" + result.username + "' "
						+ "OR emailAddress='" + result.emailAddress + "';");
				
				// emailAddress Message & Subject
				String subject = "Your new Password";				
				
				String message = "Hello " + result.username
						+ "\n \n Your new Password is: @" + password;
				
				// Send emailAddress to User
				sendMail.emailBuilder(result.emailAddress, subject, message);

			}
		} else {
			response.sendError(HttpStatusCodes.BAD_REQUEST, "Bad Request");
		}
	}
}
