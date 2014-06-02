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

import tinf13b4.forum.database.ConnectionFactory;
import tinf13b4.forum.database.QueryExecutor;
import tinf13b4.forum.register.ConfirmationKeyController;
import tinf13b4.forum.register.PasswordController;
import tinf13b4.forum.register.RegisterDataValidator;

@WebServlet("/api/register")
public class RegisterServlet extends JsonServlet {

	private final QueryExecutor queryExecutor;

	public RegisterServlet() {
		Connection connection = new ConnectionFactory().createConnection();
		queryExecutor = new QueryExecutor(connection);
		confirmationKeyController = new ConfirmationKeyController();
		passwordController = new PasswordController();
		registerDataValidator = new RegisterDataValidator();
	}

	private static final long serialVersionUID = 1L;
	private final ConfirmationKeyController confirmationKeyController;
	private final PasswordController passwordController;
	private final RegisterDataValidator registerDataValidator;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response, JsonObject postData) throws ServletException, IOException {
		JsonValue name = postData.get("name");
		JsonValue password = postData.get("password");
		JsonValue email = postData.get("email");

		if(name != null && email != null && password != null){

			List<String> errors = new ArrayList<String>(registerDataValidator.registerDataValidator(name.asString(), email.asString(), password.asString()));

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
				String hashedpassword = passwordController.encryptpassword(password.asString());

				// Get A Unique Confirmation Key With Alphabet
				String confirmationkey = confirmationKeyController.getUniqueConfirmationKey("0123456789AaBbCcDdEeFfGgHhIiJjKkLlMm0123456789NnOoPpQqRrSsTtUuVvWwXxYyZz0123456789");


				// Send Data To Database
				queryExecutor.executeUpdate("INSERT INTO Users (Name, Email, Password, Confirmation_Key) Values ('"
						+ name.asString() +"', '"
						+ email.asString() + "', '"
						+ hashedpassword +"', '"
						+ confirmationkey + "');");
			}
		} else {
			response.sendError(HttpStatusCodes.BAD_REQUEST, "Bad Request");
		}
	}
}
