
package tinf13b4.forum.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import tinf13b4.forum.util.EmailUtil;

public class SendMailController {

	// SEND INFORMATIONS
	private static final String SEND_ADRESS = "noreply@gruppelatt.de";
	private static final String PUBLIC_ADRESS = "forumnoreplytinf13b4@gmail.com";
	private static final String PUBLIC_PASSWORD = "ForumNoreplyTinf14B412";
	
	// SMTP INFORMATIONS
	private static final String SMTP_HOST = "smtp.gmail.com";
	private static final String ENCRYPTION_PORT = "465";
	private static final String ENCRYPTION_CLASS = "javax.net.ssl.SSLSocketFactory";
	private static final String SMTP_SERVER_AUTH = "true";
	private static final String SMTP_PORT = "465";
	

	// Build Email and send it to recipient
	public void emailBuilder(final String recipient, String subject, String message) {
		
		// Send email
		Properties props = new Properties();
		// SMTP Host
		props.put("mail.smtp.host", SMTP_HOST);
		// SSL Port
		props.put("mail.smtp.socketFactory.port", ENCRYPTION_PORT);
		// SSL Factory Class
		props.put("mail.smtp.socketFactory.class", ENCRYPTION_CLASS);
		// Enabling SMTP Authentication
		props.put("mail.smtp.auth", SMTP_SERVER_AUTH);
		// SMTP Port
		props.put("mail.smtp.port", SMTP_PORT);
		Authenticator auth = new Authenticator() {

			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(PUBLIC_ADRESS, PUBLIC_PASSWORD);
			}
		};
		Session session = Session.getDefaultInstance(props, auth);
		EmailUtil.sendEmail(session, recipient, SEND_ADRESS, subject, message);
	}
}
