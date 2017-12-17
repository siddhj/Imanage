package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.ProgressDemo;
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
import service.MultiScreen;
import service.Notification;
import utility.UTable;

public class LoginScreenController implements Initializable, MultiScreen {

	MainScreenController mainscreen;

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

	@FXML
	void loginUser(ActionEvent event) throws SQLException, IOException {
		System.out.println("inside login button");
		// mainscreen.setScreen(MultiScreenFramework.mainpage);
		new ProgressDemo().start();
		LicenseAuthentication auth = new LicenseAuthentication();
		boolean licensevalid = auth.macAddressAuthentication(usernametextfield.getText(), passwordtextfield.getText());
		if (licensevalid == true) {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("UI_VER4.fxml"));
			try {
				Parent loadScreen = (Parent) myLoader.load();
				Stage primarystage = UTable.getPrimarystage();
				Scene scene = new Scene(loadScreen);
				primarystage.setScene(scene);
				UTable.getLoaderstage().close();
			} catch (IOException e) {
				e.printStackTrace();
				UTable.getLoaderstage().close();
			}
		} else {

			UTable.getLoaderstage().close();
			Notification.licenseValidation();
		}
	}

	@Override
	public void setScreenParent(MainScreenController screencontroller) {
		mainscreen = screencontroller;
	}
}
