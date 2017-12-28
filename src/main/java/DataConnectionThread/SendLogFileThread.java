package DataConnectionThread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
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

import org.apache.log4j.Logger;

import dao.ListTables;
import service.MicroService;
import ui.MultiScreenFramework;
import utility.LoginVariable;

public class SendLogFileThread implements Runnable {

	private int licenseid;
	public SendLogFileThread(int licenseid)
	{
		this.licenseid=licenseid;
	}
	
	final static Logger logger = Logger.getLogger(MultiScreenFramework.class);

	public void insertLocalDateTest() throws SQLException {
		LocalDate localdate = LocalDate.now();
		java.sql.Date date = java.sql.Date.valueOf(localdate);

		Connection connection = ListTables.returnConnection();
		connection.setAutoCommit(false);
		String query = "update license set LastLogDate=? where LicenseID IN(?)";
		PreparedStatement prepare = null;
		prepare = connection.prepareStatement(query);
		prepare.setDate(1, date);
		prepare.setInt(2, licenseid);
		prepare.execute();
		connection.commit();
	}

	@Override
	public void run() {
		LocalDate localdate = LocalDate.now();
		java.sql.Date sqldate = java.sql.Date.valueOf(localdate);
		Connection connection = ListTables.returnConnection();
		String query = "SELECT * FROM license where LicenseID = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(query);
		stmt.setInt(1, licenseid);
		ResultSet resultset = stmt.executeQuery();
		resultset.beforeFirst();
		Date date = null;
		while (resultset.next()) {
			date = resultset.getDate("LastLogDate");
		}
		System.out.println(date);
		System.out.println(sqldate);

		if (!date.equals(sqldate)) {
			sendMail();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMail() throws SQLException {
		logger.debug("Starting of log send thread");
		final String username = LoginVariable.getLogemailaddress();
		final String password = LoginVariable.getLogemailpassword();

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
			message.setFrom(new InternetAddress(LoginVariable.getLogemailaddress()));

			// recipients email address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(LoginVariable.getLogemailaddress()));

			// add the Subject of email
			message.setSubject("Log Received,Firmname: " + LoginVariable.getFirmname()+", LicenseID: "+LoginVariable.getLicenseid());

			// message body
			String body = "Firmname: " + LoginVariable.getFirmname() + "\n JavaVersion" + LoginVariable.getJarversion();
			message.setText("This is mail is used for sending log from customer side to system admin for analyses");// message

			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			String filename = LoginVariable.getLogstoreaddress();
			// String filename = "C:\\Program
			// Files\\IManage\\log4j-application.log";//change accordingly
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart2);

			message.setContent(multipart);
			Transport.send(message);

			System.out.println("Email Sent Successfully");
			insertLocalDateTest();
			MicroService.truncateLogFile();
			logger.info("Email Sent Successfully");
		} catch (MessagingException e) {
			logger.error("Mail Send", e);
		}

	}
}
