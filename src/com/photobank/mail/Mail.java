package com.photobank.mail;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Mail {

	public static void sendMail(String mailTo, String secretKey) {
		// String uuid = UUID.randomUUID().toString();
		 
		// System.out.println(("uuid = " + uuid));
		 
		 
		 final String userName = "alexkhym@gmail.com";
		 final String password = "alexkhym1";
		 
		 Properties props = new Properties();
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.startles.enable", true);
		 props.put("mail.smtp.host", "smtp.gmail.com");
		 props.put("mail.smtp.port", "587");
		 
		 Session  session = Session.getInstance(props, new javax.mail.Authenticator() {
		  protected PasswordAuthentication getPasswordAuthentication(){
		   return new PasswordAuthentication (userName, password);
		  }
		 });
		 try{ Message message = new MimeMessage(session);
		 message.setFrom(new InternetAddress(userName, "PhotoBank"));
		 message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
		 message.setSubject("Email verification");
		 message.setContent("<h:uuid<br/><br/>"
		   + "</body>", "text/html; charset=utf-8" );
		 
		 message.setText( "Please submit your verification by link if its not u just ignore the message "+"http://localhost:8080/Bank1/rest/jsonServices/activateAccount?secretKey="+secretKey);
		 Transport.send(message);
		 System.out.println("Email sent");
		 
		 } catch (MessagingException e) {
		  throw new RuntimeException(e);
		 } catch (UnsupportedEncodingException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		 }


	}

}
