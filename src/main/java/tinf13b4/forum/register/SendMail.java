package tinf13b4.forum.register;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import tinf13b4.forum.util.EmailUtil;

public class SendMail {

	private static final String SEND_ADRESS = "<noreply>@gruppelatt.de";
	private static final String PUBLIC_ADRESS = "forumnoreplytinf13b4@gmail.com";
	private static final String PUBLIC_PASSWORD = "ForumNoreplyTinf14B412";
	
	// Build Email and send it to recipient
	public void emailBuilder(final String recipient, String username, String confirmationKey){
		
		// TODO - Genrate Link
		final String message = "Hallo " + username + "\n \n willkommen in unserem Forum zum abschlieﬂen der Registrierung bitte denm folgenden Link folgen: \n" + confirmationKey;
		final String subject = "Abschlieﬂen der Registrierung";

         
		// Send email with Gmail
        Properties props = new Properties();
        //SMTP Host
        props.put("mail.smtp.host", "smtp.gmail.com");
        //SSL Port
        props.put("mail.smtp.socketFactory.port", "465"); 
        //SSL Factory Class
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        //Enabling SMTP Authentication
        props.put("mail.smtp.auth", "true"); 
        //SMTP Port
        props.put("mail.smtp.port", "465"); 
         
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(PUBLIC_ADRESS, PUBLIC_PASSWORD);
            }
        };
         
        Session session = Session.getDefaultInstance(props, auth);
            EmailUtil.sendEmail(session, recipient, SEND_ADRESS, subject, message); 
    }
}
