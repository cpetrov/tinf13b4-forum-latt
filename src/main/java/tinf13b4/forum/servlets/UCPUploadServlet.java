
package tinf13b4.forum.servlets;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import tinf13b4.forum.beans.ConsumerBean;

@WebServlet("/api/ucp-upload")
@MultipartConfig
public class UCPUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPLOADS_DIRECTORY = "uploads";
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024;
	private ConsumerBean consumerBean;

	public UCPUploadServlet() {
		consumerBean = new ConsumerBean();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			return;
		}
		DiskFileItemFactory factory = createDiskFileItemFactory();
		String uploadFolder = getServletContext().getRealPath("") + File.separator + UPLOADS_DIRECTORY;
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		parseRequest(request, response, uploadFolder, upload);
	}

	private void parseRequest(HttpServletRequest request, HttpServletResponse response, String uploadFolder,
			ServletFileUpload upload) throws ServletException {
		try {
			List<?> items = upload.parseRequest(request);
			Iterator<?> iter = items.iterator();
			int userId = 0;
			String fileName = null;
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					String origFileName = new File(item.getName()).getName();
					String extension = FilenameUtils.getExtension(origFileName);
					SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy-hhmmss");
					fileName = sdf.format(new Date()) + "." + extension;
					String filePath = uploadFolder + File.separator + fileName;
					File uploadedFile = new File(filePath);
					item.write(uploadedFile);
				} else {
					String fieldname = item.getFieldName();
					String fieldvalue = item.getString();
					if (fieldname.equals("userId")) {
						userId = Integer.parseInt(fieldvalue);
					}
				}
			}
			consumerBean.setUserPicturePath(fileName);
			consumerBean.setUser(userId);
			response.sendRedirect("/UCP.jsp");
		} catch (FileUploadException ex) {
			throw new ServletException(ex);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private DiskFileItemFactory createDiskFileItemFactory() {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MAX_MEMORY_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		return factory;
	}
}
