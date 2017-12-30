package ui;

import java.io.IOException;
import java.util.Enumeration;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

import utility.ProgressDemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.Notification;
import utility.Logging;
import utility.LoginVariable;
import utility.UTable;

public class DashboardWindowController {
	@FXML
	private Button challanwindowbutton;

	@FXML
	private Button sortandfilterbutton;

	@FXML
	private Button newassigneebutton;

	final static Logger logger = Logger.getLogger(DashboardWindowController.class);

	@FXML
	public void initialize() {
		logger.addAppender(Logging.getAppender());
		logger.debug("This is intialized");
		Enumeration e = Logger.getRootLogger().getAllAppenders();
	    while ( e.hasMoreElements() ){
	      Appender app = (Appender)e.nextElement();
	      if ( app instanceof FileAppender ){
	        System.out.println("File: " + ((FileAppender)app).getFile());
	      }
	    }
	}

	@FXML
	void openChallanWindow(ActionEvent event) throws IOException {
		logger.info("This is intialized'lqehafhjawdlk/akfklahwdgejlfbwh.fgwfoik");
		System.out.println("New Challan Entry" + LoginVariable.isNewchallanaccess());
		if (LoginVariable.isNewchallanaccess()) {
			new ProgressDemo().start();
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("UI_VER4.fxml"));
			Parent loadScreen = (Parent) myLoader.load();
			Stage primarystage = UTable.getPrimarystage();
			Scene scene = new Scene(loadScreen);
			primarystage.setScene(scene);
			UTable.getLoaderstage().close();
		} else {
			Notification.errorOccuredNotification("Access Expired",
					"You do not have right to access to create new challan. Contact System Admin");

		}

	}

	@FXML
	void openSortAndFilterWindow(ActionEvent event) throws IOException {
		if (LoginVariable.isSortandfilteraccess()) {
			new ProgressDemo().start();
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("SortAndFilter.fxml"));
			Parent loadScreen = (Parent) myLoader.load();
			Stage primarystage = UTable.getPrimarystage();
			Scene scene = new Scene(loadScreen);
			primarystage.setScene(scene);
			UTable.getLoaderstage().close();
		} else {
			Notification.errorOccuredNotification("Access Expired",
					"You do not have right to access sort and filter window. Contact System Admin");
		}
	}

	@FXML
	void openAssigneeWindow(ActionEvent event) throws IOException {
		if (LoginVariable.isNewassigneeaccess()) {
			new ProgressDemo().start();
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AssigneeWindow.fxml"));
			Parent loadScreen = (Parent) myLoader.load();
			Stage primarystage = UTable.getPrimarystage();
			Scene scene = new Scene(loadScreen);
			primarystage.setScene(scene);
			UTable.getLoaderstage().close();
		} else {
			Notification.errorOccuredNotification("Access Expired",
					"You do not have right to access add new assignee window. Contact System Admin");
		}
	}
}
