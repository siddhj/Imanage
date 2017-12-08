package ui;

import java.io.IOException;
//import dao.DataUtility;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.LicenseAuthentication;
import service.MultiScreen;
import service.Notification;
import utility.UTable;

/**
 * FXML Controller class
 *
 * @author siddhartha.jain
 */
public class LoginScreenController implements Initializable, MultiScreen {

	/**
	 * Initializes the controller class.
	 */

	MainScreenController mainscreen;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

	@FXML
	private Button login;

	@FXML
	private TextField license;

	@FXML
	void loginUser(ActionEvent event) throws SQLException, IOException {
		// System.out.println("inside login button");
		// mainscreen.setScreen(MultiScreenFramework.mainpage);
		LicenseAuthentication auth = new LicenseAuthentication();
		boolean licensevalid = auth.macAddressAuthentication();
		if(licensevalid==true){
		 FXMLLoader myLoader = new FXMLLoader(getClass().getResource("UI_VER4.fxml"));
         try {
			Parent loadScreen = (Parent) myLoader.load();
			Stage primarystage = UTable.getPrimarystage();
			Scene scene = new Scene(loadScreen);
			primarystage.setScene(scene);
         } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}else{
			Notification.licenseValidation();
		}
	}

	//
	@Override
	public void setScreenParent(MainScreenController screencontroller) {
		mainscreen = screencontroller;
	}
}
