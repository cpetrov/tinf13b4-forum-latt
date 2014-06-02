package tinf13b4.forum.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tinf13b4.forum.database.ConnectionManager;
import tinf13b4.forum.register.PasswordController;
import tinf13b4.forum.register.ConfirmationKeyController;
import tinf13b4.forum.register.RegisterDataValidator;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

@WebServlet("/api/register")
public class RegisterServlet extends JsonServlet {

	private static final long serialVersionUID = 1L;
	
	ConnectionManager connection = new ConnectionManager();
	ConfirmationKeyController ConfirmationKeyController = new ConfirmationKeyController();
	PasswordController PasswordController = new PasswordController();
	RegisterDataValidator RegisterDataValidator = new RegisterDataValidator();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response, JsonObject postData) throws ServletException, IOException {
		JsonValue name = postData.get("name");
		JsonValue password = postData.get("password");
		JsonValue email = postData.get("email");
		
		if(name != null && email != null && password != null){
			
			List<String> errors = new ArrayList<String>(RegisterDataValidator.registerDataValidator(name.asString(), email.asString(), password.asString()));			
		    
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
				String hashedpassword = PasswordController.encryptpassword(password.asString());
				
				// Get A Unique Confirmation Key With Alphabet
				String confirmationkey = ConfirmationKeyController.getUniqueConfirmationKey("0123456789AaBbCcDdEeFfGgHhIiJjKkLlMm0123456789NnOoPpQqRrSsTtUuVvWwXxYyZz0123456789");
				
				
				// Send Data To Database
				connection.insertCommand("INSERT INTO Users (Name, Email, Password, Confirmation_Key) Values ('" 
						+ name.asString() +"', '" 
						+ email.asString() + "', '" 
						+ hashedpassword +"', '"
						+ confirmationkey + "');");
			}
		}else{
			response.sendError(HttpStatusCodes.BAD_REQUEST,"Bad Request");
		}
	}
}
