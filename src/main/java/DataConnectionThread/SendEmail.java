package DataConnectionThread;

	import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
	import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import utility.LoginVariable;

	public class SendEmail {
		public static void main(String[] args) {
			final String username = "byteninza@gmail.com";
			final String password = "byteninza123";

			// setting gmail smtp properties
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			// check the authentication
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {

				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress("byteninza@gmail.com"));

				// recipients email address
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("byteninza@gmail.com"));

				// add the Subject of email
				message.setSubject("Log Received,Firmname: "+LoginVariable.getFirmname());

				// message body
				String body = "Firmname: "+LoginVariable.getFirmname()+"\n JavaVersion"+LoginVariable.getJarversion();
				message.setText("This is a test mail only jkhj");// message
				
				MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
				  
			    String filename = "C:\\Program Files\\IManage\\log4j-application.log";//change accordingly  
			    DataSource source = new FileDataSource(filename);  
			    messageBodyPart2.setDataHandler(new DataHandler(source));  
			    messageBodyPart2.setFileName(filename);  

			    Multipart multipart = new MimeMultipart();  
			    multipart.addBodyPart(messageBodyPart2);
			    
			    message.setContent(multipart ); 
				Transport.send(message);

				System.out.println("Email Sent Successfully");

			} catch (MessagingException e) {
				throw new RuntimeException(e);

			}
		}
}
