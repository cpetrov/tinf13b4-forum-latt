package tinf13b4.forum.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

@WebServlet("/api/register")
public class RegisterServlet extends JsonServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response, JsonObject postData) throws ServletException, IOException {
		JsonValue name = postData.get("name");
		JsonValue email = postData.get("email");
		JsonValue password = postData.get("password");
		
		if(name != null && email != null && password != null){
			
		}else{
			response.sendError(HttpStatusCodes.BAD_REQUEST);
		}
	}

}
