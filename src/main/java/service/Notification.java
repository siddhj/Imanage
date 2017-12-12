package service;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;

public class Notification {

	public static void popupWindowInvalidValueLessThenZero(){
		Notifications note = Notifications.create()
				.title("Invalid Input")
				.text("Value Entered In Column Is Less Then 0")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT)
				.onAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.showError();
	}
	
	public static void popupWindowInvalidReceiveValueGreaterThenIssue(){
		Notifications note = Notifications.create()
				.title("Invalid Input")
				.text("Receive Value Cannot Be Greater Then Issue Value")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT)
				.onAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.showError();
	}
	
	public static void popupWindowInvalidPaidValueGreaterThenReceive(){
		Notifications note = Notifications.create()
				.title("Invalid Input")
				.text("Paid Value Cannot Be Greater Then Receive Value")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT)
				.onAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.showError();
	}
	
	public static void dataSuccessfullySaved(){
		Notifications note = Notifications.create()
				.title("Operation Successful")
				.text("Data Successfully Stored.")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.CENTER)
				.onAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.showInformation();
	}
	
	public static void invalidInput(){
		Notifications note = Notifications.create()
				.title("Invalid Input")
				.text("Value Entered is Invalid")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.CENTER)
				.onAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.showInformation();
	}
	
	public static void invalidInputName(){
		Notifications note = Notifications.create()
				.title("Invalid Input")
				.text("Invalid Assignee Name Entered")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.CENTER)
				.onAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.showInformation();
	}
	
	public static void invalidInputProductID(){
		Notifications note = Notifications.create()
				.title("Invalid Input")
				.text("Invalid ProductID value entered")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.CENTER)
				.onAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.showInformation();
	}
	
	public static void licenseValidation(){
		Notifications note = Notifications.create()
				.title("Invalid License")
				.text("Your do not have valid license. Contact System Admin")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.CENTER)
				.onAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.showInformation();
	}
	
	public static void mainWindowProductIDAlreadyExist(){
		Notifications note = Notifications.create()
				.title("Product ID alread Present")
				.text("Product ID is already added. Click on Edit Selection Button for Changes")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT)
				.onAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.showError();
	}
}
