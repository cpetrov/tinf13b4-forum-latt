package tinf13b4.forum.servlets;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import tinf13b4.forum.controller.SendMailController;
import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.util.UserDataValidatorUtil;

@WebServlet("/api/register")
public class RegisterServlet extends JsonServlet {

	public RegisterServlet() {
		Connection connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
		confirmationKeyController = new ConfirmationKeyController();
		passwordController = new PasswordController();
		userDataValidator = new UserDataValidatorUtil();
		sendMail = new SendMailController();
	}

	private static final long serialVersionUID = 1L;
	private final ConfirmationKeyController confirmationKeyController;
	private final PasswordController passwordController;
	private final UserDataValidatorUtil userDataValidator;
	private final QueryExecutor queryExecutor;
	private final SendMailController sendMail;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response, JsonObject postData) throws ServletException, IOException {
		JsonValue name = postData.get("name");
		JsonValue password = postData.get("password");
		JsonValue email = postData.get("email");

		if(name != null && email != null && password != null){

			List<String> errors = new ArrayList<String>(userDataValidator.registerDataValidator(name.asString(), email.asString(), password.asString()));

			if(errors.size() > 0){
				JsonArray json = new JsonArray();

				for(String error:errors){
					json.add(error);
				}

				JsonObject jsonObject = new JsonObject();

				jsonObject.add("errors", json);
				jsonObject.writeTo(response.getWriter());
			} else {

				// Get A Hashed Password
				String hashedPassword = passwordController.encryptPassword(password.asString());

				// Get A Unique Confirmation Key
				String confirmationKey = confirmationKeyController.getUniqueConfirmationKey();

				// Send Data To Database
				queryExecutor.executeUpdate("INSERT INTO Users (Name, Email, Password, JoinedOn, Confirmation_Key) Values ('"
						+ name.asString() +"', '"
						+ email.asString() + "', '"
						+ hashedPassword +"', '"
						+ new Date(new java.util.Date().getTime()) + "', '"
						+ confirmationKey + "');");
				
				// Email Message & Subject
				String subject = "Complete your registration";				
				
				String message = "Hello " + name
						+ "\n \n Welcome to our Forum! To complete the registration process, "
						+ "please visit the following link: \n" + new URL("http://localhost:8080/tinf13b4-forum-latt/confirmation.jsp?confirmationkey=" + confirmationKey).toString();
				
				// Send email to User
				sendMail.emailBuilder(email.asString(), name.asString(), subject, message);

			}
		} else {
			response.sendError(HttpStatusCodes.BAD_REQUEST, "Bad Request");
		}
	}
}
