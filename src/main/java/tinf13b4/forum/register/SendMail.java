package tinf13b4.forum.register;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import tinf13b4.forum.util.EmailUtil;

public class SendMail {
	
	// Build Email and send it to recipient
	public void emailBuilder(final String recipient, String username, String confirmationKey){
		
		final String sendAdress = "<noreply>@gruppelatt.de";
		final String publicAddress = "forumnoreplytinf13b4@gmail.com";
		final String publicPassword = "ForumNoreplyTinf14B412";
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
                return new PasswordAuthentication(publicAddress, publicPassword);
            }
        };
         
        Session session = Session.getDefaultInstance(props, auth);
            EmailUtil.sendEmail(session, recipient, sendAdress, subject, message); 
    }
}
