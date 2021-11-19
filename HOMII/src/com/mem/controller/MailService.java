package com.mem.controller;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	public void sendMail(String to, String subject, String messageText) {
			
	   try {
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

	     final String myGmail = "homiiwisc506@gmail.com";
	     final String myGmail_password = "homii506";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //設定信中的主旨  
		   message.setSubject(subject);
		   //設定信中的內容 
		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("Send");
     }catch (MessagingException e){
	     System.out.println("Fail");
	     e.printStackTrace();
     }
   }
	
	 public static void main (String args[]){

      String to = "ilai3@wisc.edu";
      
      String subject = "New Password";
      
      String ch_name = "peter1";
      String passRandom = "111";
      String messageText = "Hello! " + ch_name + " Please remember this password: " + passRandom + "\n" +" (Activated)"; 
       
      MailService mailService = new MailService();
      mailService.sendMail(to, subject, messageText);

   }



}
