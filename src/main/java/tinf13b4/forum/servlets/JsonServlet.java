package tinf13b4.forum.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rap.json.JsonObject;

public abstract class JsonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject postData = parseJsonPostData(request);

		doPost(request, response, postData);
	}

	protected abstract void doPost(HttpServletRequest request, HttpServletResponse response, JsonObject postData) throws ServletException, IOException;

	private JsonObject parseJsonPostData(HttpServletRequest request) throws IOException {
		return JsonObject.readFrom(request.getReader());
	}

}
