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
import tinf13b4.forum.util.UserDataValidatorUtil;

@WebServlet("/api/forgotten")
public class ForgottenServlet extends JsonServlet {

	public ForgottenServlet() {
		Connection connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
		userDataValidator = new UserDataValidatorUtil();
		passwordController = new PasswordController();
		sendMail = new SendMailController();
	}

	private static final long serialVersionUID = 1L;
	private final UserDataValidatorUtil userDataValidator;
	private final PasswordController passwordController;
	private final QueryExecutor queryExecutor;
	private final SendMailController sendMail;

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response, JsonObject postData) throws ServletException, IOException {
		JsonValue name = postData.get("name");
		JsonValue email = postData.get("email");
		
		
		if(name != null || email != null){
			
			List<String> errors = new ArrayList<String>(userDataValidator.forgottenDataValidator(name.asString(), "undefined"));

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
						+ "Name='" + name + "' "
						+ "OR Email='" + email + "';");
				
				// Email Message & Subject
				String subject = "Your new Password";				
				
				String message = "Hello " + name
						+ "\n \n Your new Password is: " + password;
				
				// Send email to User
				sendMail.emailBuilder(email.asString(), name.asString(), subject, message);

			}
		} else {
			response.sendError(HttpStatusCodes.BAD_REQUEST, "Bad Request");
		}
	}
}
