package ui;

import java.io.IOException;
import java.sql.SQLException;

import com.ProgressDemo;

import dao.DNewEntityInsert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.Notification;
import utility.UTable;

public class AssigneeWindowController {
	@FXML
	private TextField assigneenametextbox;

	@FXML
	private Button Imanagebutton;

	@FXML
	private TextField mobilenotextbox;

	@FXML
	private TextField gstintextbox;

	@FXML
	private TextField aadharnotextbox;

	@FXML
	private Button clearassigneedetail;

	@FXML
	private Button saveassigneedetail;

	@FXML
	void clearAllData(ActionEvent event) {
		assigneenametextbox.setText("");
		mobilenotextbox.setText("");
		gstintextbox.setText("");
		aadharnotextbox.setText("");
	}

	@FXML
	void saveAssigneeData(ActionEvent event) throws SQLException, IOException {
		String assigneename = assigneenametextbox.getText();
		String mobileno = mobilenotextbox.getText();
		String gstin = gstintextbox.getText();
		String aadharno = aadharnotextbox.getText();

		if (assigneename == null || mobileno == null || gstin == null) {
			Notification.invalidInput("Invalid Entry", "Please check all the entry again");
			return;
		}
		if (aadharno == null) {
			aadharno = "";
		}
		new ProgressDemo().start();
		DNewEntityInsert.getGetSingeltonInstance().assigneeDataInsert(assigneename, mobileno, gstin, aadharno);
		UTable.loaderstage.close();
		Notification.dataSuccessfullySaved("Assignee Name added", "Assignee name has been successfully added");
		assigneenametextbox.setText("");
		mobilenotextbox.setText("");
		gstintextbox.setText("");
		aadharnotextbox.setText("");
	}

	@FXML
	void openDashboardWindow(ActionEvent event) {
		new ProgressDemo().start();
		FXMLLoader myLoader = new FXMLLoader(getClass().getResource("DashboardWindow.fxml"));
		try {
			Parent loadScreen = (Parent) myLoader.load();
			Stage primarystage = UTable.getPrimarystage();
			Scene scene = new Scene(loadScreen);
			primarystage.setScene(scene);
			UTable.getLoaderstage().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
