package ui;

import java.io.IOException;

import com.ProgressDemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.Notification;
import utility.LoginVariable;
import utility.UTable;

public class DashboardWindowController {
    @FXML
    private Button challanwindowbutton;

    @FXML
    private Button sortandfilterbutton;

    @FXML
    private Button newassigneebutton;

	@FXML
	public void initialize(){
			}
    
    @FXML
    void openChallanWindow(ActionEvent event) throws IOException {
    	System.out.println("New Challan Entry"+LoginVariable.isNewchallanaccess());
    	if(LoginVariable.isNewchallanaccess()){
    		new ProgressDemo().start();
    		FXMLLoader myLoader = new FXMLLoader(getClass().getResource("UI_VER4.fxml"));
			Parent loadScreen = (Parent) myLoader.load();
			Stage primarystage = UTable.getPrimarystage();
			Scene scene = new Scene(loadScreen);
			primarystage.setScene(scene);
			UTable.getLoaderstage().close();}else{
	    		Notification.errorOccuredNotification("Access Expired","You do not have right to access to create new challan. Contact System Admin");

			}

    }

    @FXML
    void openSortAndFilterWindow(ActionEvent event) throws IOException {
    	if(LoginVariable.isSortandfilteraccess()){
    	new ProgressDemo().start();
		FXMLLoader myLoader = new FXMLLoader(getClass().getResource("SortAndFilter.fxml"));
		Parent loadScreen = (Parent) myLoader.load();
		Stage primarystage = UTable.getPrimarystage();
		Scene scene = new Scene(loadScreen);
		primarystage.setScene(scene);
		UTable.getLoaderstage().close();}
    	else{
    		Notification.errorOccuredNotification("Access Expired","You do not have right to access sort and filter window. Contact System Admin");
    	}
    }
    

    @FXML
    void openAssigneeWindow(ActionEvent event) throws IOException {
    	if(LoginVariable.isNewassigneeaccess()){
    	new ProgressDemo().start();
		FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AssigneeWindow.fxml"));
		Parent loadScreen = (Parent) myLoader.load();
		Stage primarystage = UTable.getPrimarystage();
		Scene scene = new Scene(loadScreen);
		primarystage.setScene(scene);
		UTable.getLoaderstage().close();}
    	else{
    		Notification.errorOccuredNotification("Access Expired","You do not have right to access add new assignee window. Contact System Admin");
    	}
    }
}
