package tinf13b4.forum.util;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class EmailUtil {
 
    public static void sendEmail(Session session, String recipient, String sendAdress, String subject, String message){
    	
        try
        {
          MimeMessage MimeMessage = new MimeMessage(session);
          
          //set message headers
          MimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
          MimeMessage.addHeader("format", "flowed");
          MimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
 
          MimeMessage.setFrom(new InternetAddress(sendAdress, sendAdress));
 
          MimeMessage.setReplyTo(InternetAddress.parse(sendAdress, false));
 
          MimeMessage.setSubject(subject, "UTF-8");
 
          MimeMessage.setText(message, "UTF-8");
 
          MimeMessage.setSentDate(new Date());
 
          MimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));

          Transport.send(MimeMessage); 

        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
}
