package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import com.CreateFileAndFolder;

import utility.ProgressDemo;

import DataConnectionThread.AssigneeNameAndProductIDLoadThread;
import DataConnectionThread.FilePathSetThread;
import DataConnectionThread.SendLogFileThread;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.LicenseAuthentication;
import service.Notification;
import utility.Logging;
import utility.LoginVariable;
import utility.UTable;

public class LoginScreenController implements Initializable {
	@FXML
	private Button login;

	@FXML
	private TextField license;

	@FXML
	private TextField usernametextfield;
	 static Logger logger;
	@FXML
	private PasswordField passwordtextfield;
	private static String logfilepath,challanpdfpath;
	static {
		try {
			CreateFileAndFolder.checkAndCreateNewLogFile();
			logfilepath = CreateFileAndFolder.getLogfilepath();
			challanpdfpath = CreateFileAndFolder.getPdffilepath();
			PatternLayout layout = new PatternLayout();
			layout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
			logger = Logger.getLogger(LoginScreenController.class);
			RollingFileAppender appender;
			appender = new RollingFileAppender(layout, logfilepath, true);
			Logging.setAppender(appender);
			logger.addAppender(appender);
			} catch (Exception e) {
			logger.error("logfilepath: "+logfilepath, e);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		logger.info("Application is intialized. Log File Path are set.");
		logger.info("path of log file: "+logfilepath);
		LoginVariable.setLogstoreaddress(logfilepath);
		LoginVariable.setFilestoreaddress(challanpdfpath);
	}

	ObservableList<ObservableList<String>> parentlist = FXCollections.observableArrayList();

	@FXML
	void loginUser(ActionEvent event) throws SQLException, IOException, InterruptedException {
		new ProgressDemo().applicaionstart();
		logger.info("Login Button is clicked");
	
		Runnable nameproductrunnable = new AssigneeNameAndProductIDLoadThread();
		Thread nameproductthread = new Thread(nameproductrunnable);
		nameproductthread.start();

		LicenseAuthentication auth = new LicenseAuthentication();
		boolean licensevalid = auth.macAddressAuthentication(usernametextfield.getText(), passwordtextfield.getText());
		if (licensevalid == true) {
			Runnable filepathsetrunnable = new FilePathSetThread(logfilepath,challanpdfpath,LoginVariable.getLicenseid());
			Thread filepathsetthread = new Thread(filepathsetrunnable);
			filepathsetthread.start();
			logger.info("Log File Address Updated in Database");

			Runnable sendlogrunnable = new SendLogFileThread(LoginVariable.getLicenseid());
			Thread sendlogfilethread = new Thread(sendlogrunnable);
			sendlogfilethread.run();
		}
		if (licensevalid == true) {
			logger.info("Login Credential are valid. Ready To Go!!!!");
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("DashboardWindow.fxml"));
			try {
				Parent loadScreen = (Parent) myLoader.load();
				Stage primarystage = UTable.getPrimarystage();
				Scene scene = new Scene(loadScreen);
				primarystage.setScene(scene);
				nameproductthread.join();
				UTable.getApplicationloaderstage().close();
			} catch (IOException e) {
				UTable.getApplicationloaderstage().close();
				logger.error("Error in license authentication", e);
				e.printStackTrace();
				UTable.getApplicationloaderstage().close();
			}
		} else {

			// UTable.getLoaderstage().close();
			Notification.authenticationValidation("License ID or username/password is invalid",
					"Please make sure you enter correct username/password. If still unable to login contact system admin. Your license may be invalid");
		}
	}

}
