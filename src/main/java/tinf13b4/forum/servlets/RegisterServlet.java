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

import tinf13b4.forum.controller.PasswordController;
import tinf13b4.forum.controller.SendMailController;
import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.util.ConfirmationKeyUtil;
import tinf13b4.forum.util.UserDataValidatonUtil;

@WebServlet("/api/register")
public class RegisterServlet extends JsonServlet {

	public RegisterServlet() {
		Connection connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
		confirmationKeyController = new ConfirmationKeyUtil();
		passwordController = new PasswordController();
		userDataValidator = new UserDataValidatonUtil();
		sendMail = new SendMailController();
	}

	private static final long serialVersionUID = 1L;
	private final ConfirmationKeyUtil confirmationKeyController;
	private final PasswordController passwordController;
	private final UserDataValidatonUtil userDataValidator;
	private final QueryExecutor queryExecutor;
	private final SendMailController sendMail;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response, JsonObject postData) throws ServletException, IOException {
		JsonValue jsonUsername = postData.get("userjsonUsername");
		JsonValue jsonPassword = postData.get("password");
		JsonValue jsonEmailAddress = postData.get("emailAddress");

		if(jsonUsername != null && jsonEmailAddress != null && jsonPassword != null){

			List<String> errors = new ArrayList<String>(userDataValidator.registerDataValidation(jsonUsername.asString(), jsonEmailAddress.asString(), jsonPassword.asString()));

			if(errors.size() > 0){
				JsonArray json = new JsonArray();

				for(String error:errors){
					json.add(error);
				}

				JsonObject jsonObject = new JsonObject();

				jsonObject.add("errors", json);
				jsonObject.writeTo(response.getWriter());
			} else {

				// Get A Hashed jsonPassword
				String hashedjsonPassword = passwordController.encryptPassword(jsonPassword.asString());

				// Get A Unique Confirmation Key
				String confirmationKey = confirmationKeyController.getUniqueConfirmationKey();

				// Send Data To Database
				queryExecutor.executeUpdate("INSERT INTO Users (Name, Email, Password, JoinedOn, Confirmation_Key) Values ('"
						+ jsonUsername.asString() +"', '"
						+ jsonEmailAddress.asString() + "', '"
						+ hashedjsonPassword +"', '"
						+ new Date(new java.util.Date().getTime()) + "', '"
						+ confirmationKey + "');");
				
				// jsonEmailAddress Message & Subject
				String subject = "Complete your registration";				
				
				String message = "Hello " + jsonUsername.asString()
						+ "\n \n Welcome to our Forum! To complete the registration process, "
						+ "please visit the following link: \n" + new URL("http://localhost:8080/tinf13b4-forum-latt/confirmation.jsp?confirmationkey=" + confirmationKey).toString();
				
				// Send jsonEmailAddress to User
				sendMail.emailBuilder(jsonEmailAddress.asString(), subject, message);

			}
		} else {
			response.sendError(HttpStatusCodes.BAD_REQUEST, "Bad Request");
		}
	}
}
