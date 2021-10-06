package com.mem.model;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public String sendPassword(String mb_email) {
		String to = mb_email;
		String subject = "---·New Passwrod Reminder---";
		SendEmail se = new SendEmail();
		String randomPwd = se.getStringRandom(8);
		String url = "http://localhost:8081/HOMII/front-end/mem/MemLogin.jsp";
		String messageText = ("Your new password: " + randomPwd + ". Please click here to login <a href=\"" + url + "\">Click Here</a>");
		
//		 \"  ->"
//		System.out.println(messageText);

		
		try {
			//link to Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));


			message.setSubject(subject);

			message.setText(messageText);
			 message.setContent(messageText, "text/html;charset=UTF-8");
			

			Transport.send(message);
			System.out.println("Sent");
		} catch (MessagingException e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
		return randomPwd;
	}

	
	
	// Email for activating account
	public void sendMail (String mb_email) {
		String to = mb_email;
		String subject = "---Activate for your account---";
		
		String url = "http://localhost:8081/HOMII/mem/accountactivate.do?key1=" + to;
		String messageText = ("Please click link to activate your account <a href=\"" + url + "\">Click Here</a>");

		
		try {

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));


			message.setSubject(subject);

			message.setText(messageText);

			 message.setContent(messageText, "text/html;charset=UTF-8");
			

			Transport.send(message);
			System.out.println("Sent");
		} catch (MessagingException e) {
			System.out.println("Fail");
			e.printStackTrace();
		}
	}
	
	
	// produce random number
		public String getStringRandom(int length) {
        
        String val = "";
        Random random = new Random();
        
        //length stand for how many digit you produce
        for(int i = 0; i < length; i++) {
            
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";

            if( "char".equalsIgnoreCase(charOrNum) ) {

                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
