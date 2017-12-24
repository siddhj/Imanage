package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.ProgressDemo;

import DataConnectionThread.AssigneeNameAndProductIDLoadThread;
import dao.DLoader;
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
import utility.UTable;

public class LoginScreenController implements Initializable{
//
//	MainScreenController mainscreen;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private Button login;

	@FXML
	private TextField license;

	@FXML
	private TextField usernametextfield;

	@FXML
	private PasswordField passwordtextfield;
	ObservableList<ObservableList<String>> parentlist = FXCollections.observableArrayList();
	@FXML
	void loginUser(ActionEvent event) throws SQLException, IOException, InterruptedException {
		// mainscreen.setScreen(MultiScreenFramework.mainpage);
		new ProgressDemo().start();

		Runnable nameproductrunnable = new AssigneeNameAndProductIDLoadThread();
		Thread nameproductthread = new Thread(nameproductrunnable);
		nameproductthread.start();
		
		LicenseAuthentication auth = new LicenseAuthentication();
		boolean licensevalid = auth.macAddressAuthentication(usernametextfield.getText(), passwordtextfield.getText());
		if (licensevalid == true) {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("DashboardWindow.fxml"));
			try {
				Parent loadScreen = (Parent) myLoader.load();
				Stage primarystage = UTable.getPrimarystage();
				Scene scene = new Scene(loadScreen);
				primarystage.setScene(scene);
				nameproductthread.join();
				UTable.getLoaderstage().close();
			} catch (IOException e) {
				e.printStackTrace();
				UTable.getLoaderstage().close();
			}
		} else {

			UTable.getLoaderstage().close();
			Notification.authenticationValidation("License ID or username/password is invalid", "Please make sure you enter correct username/password. If still unable to login contact system admin. Your license may be invalid");
		}
	}


}
