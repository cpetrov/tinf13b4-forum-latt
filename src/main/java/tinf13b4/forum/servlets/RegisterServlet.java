package tinf13b4.forum.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	private final ExecutorService executorService = Executors.newSingleThreadExecutor();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response, JsonObject postData) throws ServletException, IOException {
		final JsonValue username = postData.get("username");
		JsonValue password = postData.get("password");
		final JsonValue emailAddress = postData.get("emailAddress");

		if(username != null && emailAddress != null && password != null){

			List<String> errors = new ArrayList<String>(userDataValidator.registerDataValidator(username.asString(), emailAddress.asString(), password.asString()));

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
				final String confirmationKey = confirmationKeyController.getUniqueConfirmationKey();

				// Send Data To Database
				queryExecutor.executeUpdate("INSERT INTO Users (Name, Email, Password, JoinedOn, Confirmation_Key) Values ('"
						+ username.asString() +"', '"
						+ emailAddress.asString() + "', '"
						+ hashedPassword +"', '"
						+ new Date(new java.util.Date().getTime()) + "', '"
						+ confirmationKey + "');");

				// send email async
				executorService.execute(new Runnable(){
					@Override
					public void run(){
						// Email Message & Subject
						String subject = "Complete your registration";
						String message = "Hello " + username.asString()
									+ "\n \n Welcome to our Forum! To complete the registration process, "
									+ "please visit the following link: \n http://5.45.110.9:8080/tinf13b4-forum-latt/confirmation.jsp?confirmationkey=" + confirmationKey;
						
						// Send email to User
						sendMail.emailBuilder(emailAddress.asString(), subject, message);
					}
				});
			}
		} else {
			response.sendError(HttpStatusCodes.BAD_REQUEST, "Bad Request");
		}
	}
}
