
package tinf13b4.forum.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import org.apache.tomcat.util.http.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import tinf13b4.forum.beans.ConsumerBean;
import tinf13b4.forum.util.UserDataValidatorUtil;

@WebServlet("/api/ucp")
@MultipartConfig
public class UCPServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPLOADS_DIRECTORY = "uploads";
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024;
	private static final String[] ALLOWED_EXTENSIONS = {"png", "jpg"};
	private ConsumerBean consumerBean;
	private String mail;
	private String password;
	private String passwordConfirmation;
	private int userId;

	public UCPServlet() {
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
			FileItem fileItemFile = null;
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					fileItemFile = item;
				} else {
					String fieldname = item.getFieldName();
					String fieldvalue = item.getString();
					if (fieldname.equals("userId")) {
						userId = Integer.parseInt(fieldvalue);
					} else if (fieldname.equals("mail")) {
						mail = fieldvalue;
					} else if (fieldname.equals("password")) {
						password = fieldvalue;
					} else if (fieldname.equals("passwordConfirmation")) {
						passwordConfirmation = fieldvalue;
					}
				}
			}
			if (fileItemFile != null) {
				handleFileUpload(request, response, uploadFolder, userId, fileItemFile);
			}
			if (notNullAndEmpty(password)) {
				handlePassword(request, response);
			}
			if (notNullAndEmpty(mail)) {
				handleMail(request, response);
			}
			boolean mailOrPasswordNotEmptyAndNull = notNullAndEmpty(consumerBean.getUserMail()) || notNullAndEmpty(consumerBean.getUserPassword());
			if (mailOrPasswordNotEmptyAndNull) {
				consumerBean.setUser(userId);
			}
			if (!(notNullAndEmpty(consumerBean.getUserPicturePath()) || mailOrPasswordNotEmptyAndNull)) {
				handleError("Nothing to change.", request, response);
			}
			consumerBean.resetUserData();
			if (request.getAttribute("error") == null) {
				request.setAttribute("success", true);
				request.getRequestDispatcher("/UCP.jsp").forward(request, response);
			}
		} catch (SizeLimitExceededException ex) {
			handleError("File size too big. Maximal allowed size: 1 MB.", request, response);
		} catch (FileUploadException ex) {
			handleError("Error while processing the request.", request, response);
		} catch (Exception ex) {
			handleError("Something went wrong :(", request, response);
		}
	}

	private void handlePassword(HttpServletRequest request, HttpServletResponse response) {
		if(checkPasswordValid(request, response)) {
			consumerBean.setUserPassword(password);
		}
	}

	private void handleMail(HttpServletRequest request, HttpServletResponse response) {
		if(checkMailValid(request, response)) {
			consumerBean.setUserMail(mail);
		}
	}

	private void handleFileUpload(HttpServletRequest request, HttpServletResponse response, String uploadFolder,
			int userId, FileItem fileItemFile) throws Exception {
		String extension = getFileExtension(fileItemFile, request, response);
		if (request.getAttribute("error") == null) {
			uploadFile(uploadFolder, userId, fileItemFile, extension, request, response);
		}
	}

	private boolean checkPasswordValid(HttpServletRequest request, HttpServletResponse response) {
		UserDataValidatorUtil userDataValidator = new UserDataValidatorUtil();
		if(!userDataValidator.checkPassword(password)) {
			handleError("Please use a big letter, a small letter, a number and one of the special characters @, #, $, %", request, response);
			return false;
		} else {
			if ((notNullAndEmpty(passwordConfirmation)) & !(checkConfirmation(request, response))) {
				handleError("Please, repeat your new password in the 'Password confirmation' field.", request, response);
				return false;
			}
		}
		return true;
	}

	private boolean checkMailValid(HttpServletRequest request, HttpServletResponse response) {
		UserDataValidatorUtil userDataValidator = new UserDataValidatorUtil();
		if(!userDataValidator.checkMail(mail)) {
			handleError("The email address is not valid", request, response);
			return false;
		} else {
			ResultSet rs = userDataValidator.validateData("", mail);
			try {
				while(rs.next()) {
					handleError("Sorry, this mail address is already in use.", request, response);
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private boolean checkConfirmation(HttpServletRequest request, HttpServletResponse response) {
		if (!password.equals(passwordConfirmation)) {
			handleError("Password confirmation wrong.", request, response);
			return false;
		}
		return true;
	}

	private void uploadFile(String uploadFolder, int userId, FileItem fileItemFile, String extension,
			HttpServletRequest request, HttpServletResponse response) {
		String fileName;
		fileName = createFileName(userId, extension);
		writeFile(uploadFolder, fileName, fileItemFile, request, response);
		if (request.getAttribute("error") == null) {
			consumerBean.setUserPicturePath(fileName);
			consumerBean.setUser(userId);
		}
	}

	private void handleError(String error, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("error", error);
		try {
			request.getRequestDispatcher("/UCP.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private void writeFile(String uploadFolder, String fileName, FileItem item, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath = uploadFolder + File.separator + fileName;
		try {
			File uploadedFile = new File(filePath);
			item.write(uploadedFile);
		} catch (Exception e) {
			handleError("Something went wrong while uploading the picture.", request, response);
		}
	}

	private String getFileExtension(FileItem item, HttpServletRequest request, HttpServletResponse response) {
		String origFileName = new File(item.getName()).getName();
		String extension = FilenameUtils.getExtension(origFileName);
		if (!containsCaseInsensitive(extension, Arrays.asList(ALLOWED_EXTENSIONS))) {
			handleError("File extension not allowed. Allowed extensions: .png, .jpg", request, response);
		}
		return extension;
	}

	public boolean containsCaseInsensitive(String s, List<String> list) {
		for (String string : list) {
			if (string.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}

	private String createFileName(int userId, String extension) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy-hhmmss");
		return userId + "-" + sdf.format(new Date()) + "." + extension;
	}

	private DiskFileItemFactory createDiskFileItemFactory() {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MAX_MEMORY_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		return factory;
	}

	private boolean notNullAndEmpty(String string) {
		return string != null && !string.isEmpty();
	}
}
