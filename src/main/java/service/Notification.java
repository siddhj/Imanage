package service;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;

public class Notification {

	public static void popupWindowInvalidValueLessThenZero(String title,String message){
		Notifications note = Notifications.create()
				.title(title)
				.text(message)
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT)
				.onAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.darkStyle();
		note.showError();
	}
	
	public static void popupWindowInvalidGreaterValue(String title,String message){
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
		note.darkStyle();
		note.showError();
	}
	
//	public static void popupWindowInvalidPaidValueGreaterThenReceive(){
//		Notifications note = Notifications.create()
//				.title("Invalid Input")
//				.text("Paid Value Cannot Be Greater Then Receive Value")
//				.graphic(null)
//				.hideAfter(Duration.seconds(5))
//				.position(Pos.BOTTOM_RIGHT)
//				.onAction(new EventHandler<ActionEvent>(){
//					@Override
//					public void handle(ActionEvent event) {
//				System.out.println("called");		
//					}
//				});
//		note.showError();
//	}
	
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
		note.darkStyle();
		note.showInformation();
	}
	
	public static void invalidInput(String title,String message){
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
		note.darkStyle();
		note.showInformation();
	}
	
//	public static void invalidInputName(){
//		Notifications note = Notifications.create()
//				.title("Invalid Input")
//				.text("Invalid Assignee Name Entered")
//				.graphic(null)
//				.hideAfter(Duration.seconds(5))
//				.position(Pos.CENTER)
//				.onAction(new EventHandler<ActionEvent>(){
//					@Override
//					public void handle(ActionEvent event) {
//				System.out.println("called");		
//					}
//				});
//		note.darkStyle();
//		note.showInformation();
//	}
//	
//	public static void invalidInputProductID(){
//		Notifications note = Notifications.create()
//				.title("Invalid Input")
//				.text("Invalid ProductID value entered")
//				.graphic(null)
//				.hideAfter(Duration.seconds(5))
//				.position(Pos.CENTER)
//				.onAction(new EventHandler<ActionEvent>(){
//					@Override
//					public void handle(ActionEvent event) {
//				System.out.println("called");		
//					}
//				});
//		note.darkStyle();
//		note.showInformation();
//	}
	
	public static void authenticationValidation(String title,String message){
		Notifications note = Notifications.create()
				.title(title)
				.text(message)
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.CENTER)
				.onAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.darkStyle();
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
		note.darkStyle();
		note.showError();
	}
	
	public static void mainWindowInvalidBillDate(){
		Notifications note = Notifications.create()
				.title("Select Date")
				.text("Bill Date Selected Is not Valid")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT)
				.onAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.darkStyle();
		note.showError();
	}

	public static void invalidDateFromUser(){
		Notifications note = Notifications.create()
				.title("Invalid Date")
				.text("Please Input the correct date in right format")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT)
				.onAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.darkStyle();
		note.showError();
	}

	public static void nothingIsSelectedNotification(){
		Notifications note = Notifications.create()
				.title("No Row is Selected")
				.text("Please Select a Row before deleteing and editing anything")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT)
				.onAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.darkStyle();
		note.showError();
	}
	
	public static void errorOccuredNotification(){
		Notifications note = Notifications.create()
				.title("Some Error Occured")
				.text("Please Check Your Internet Connection. If error still exsist contact system admin")
				.graphic(null)
				.hideAfter(Duration.seconds(5))
				.position(Pos.BOTTOM_RIGHT)
				.onAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
				System.out.println("called");		
					}
				});
		note.darkStyle();
		note.showError();
	}
}
