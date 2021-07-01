package com.email.sender.application;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




/**
 * 
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Trying to develop Email using Java Programming Language" );
        String message ="Hi Charan, Hope you are doing good! I am sending you this email using Java Programming language.";
        String subject = "First Email using Java";
        String to = "a.saicharan2502@gmail.com";
        String from = "monstersmech@gmail.com";
        
       // sendEmail(message,subject,to,from);
        sendAttachment(message,subject,to,from);
    }
    
    
    private static void sendAttachment(String message, String subject, String to, String from) {
		
     	//variable for gmail
    	String host ="smtp.gmail.com";
    	
    	//Getting the system properties
    	Properties properties = System.getProperties();
    	System.out.println("Properties "+properties);
    	
    	//setting important information to properties Object
    	//host set
    	properties.put("mail.smtp.host", host);
    	properties.put("mail.smtp.port", "465");
    	properties.put("mail.smtp.ssl.enable", "true");
    	properties.put("mail.smtp.auth", "true");
    	
    	//Step 1: Getting the session object
    	Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("monstersmech@gmail.com", "Javatechdeveloper@25");
			}
    		
		});
    	session.setDebug(true);
    	
    	//Step 2: Compose the message [text, multi media]
    	MimeMessage mime = new MimeMessage(session);
    	
    	try {
    		mime.setFrom(from);
    		mime.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		mime.setSubject(subject);
    		
    		String path ="C:\\Users\\Lenovo\\Downloads\\Profile.png";
    		MimeMultipart mimeMultipart = new MimeMultipart();
    		
    		
    		MimeBodyPart textMime = new MimeBodyPart();
    		
    		
    		MimeBodyPart fileMime = new MimeBodyPart();
    		
    		try {
				textMime.setText("Hi Charan, "
						+ "Hope you are doing good! This email consists of attached png file");
				
				File file = new File(path);
				fileMime.attachFile(file);
				
				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		mime.setContent(mimeMultipart);
    		
    		//Step 3:sending the message using using transport class
    		Transport.send(mime);
    		System.out.println("Email has been sent successfully!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//Responsible for sending the text as a email
    public static void sendEmail(String message,String subject,String to,String from) {
    	
    	//variable for gmail
    	String host ="smtp.gmail.com";
    	
    	//Getting the system properties
    	Properties properties = System.getProperties();
    	System.out.println("Properties "+properties);
    	
    	//setting important information to properties Object
    	//host set
    	properties.put("mail.smtp.host", host);
    	properties.put("mail.smtp.port", "465");
    	properties.put("mail.smtp.ssl.enable", "true");
    	properties.put("mail.smtp.auth", "true");
    	
    	//Step 1: Getting the session object
    	Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("monstersmech@gmail.com", "Javatechdeveloper@25");
			}
    		
		});
    	session.setDebug(true);
    	
    	//Step 2: Compose the message [text, multi media]
    	MimeMessage mime = new MimeMessage(session);
    	
    	try {
    		mime.setFrom(from);
    		mime.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		mime.setSubject(subject);
    		mime.setText(message);
    		
    		//Step 3:sending the message using using transport class
    		Transport.send(mime);
    		System.out.println("Email has been sent successfully!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
