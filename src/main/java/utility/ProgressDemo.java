package utility;

import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import utility.UTable;

//public class ProgressDemo extends Application{
public class ProgressDemo //implements Runnable 
{
	public void start() {
		Stage loaderstage = new Stage();
		
		
	//	Label label = new Label("Loading....");
		FlowPane root = new FlowPane();
		 ProgressIndicator p1 = new ProgressIndicator();
		root.getChildren().addAll(p1);
		
		Scene scene = new Scene(root);
		loaderstage.setTitle("Please Wait While Processing");
		loaderstage.setScene(scene);
		loaderstage.show();	
		UTable.setLoaderstage(loaderstage);
	}

	public void applicaionstart() {
		Stage loaderstage = new Stage();
		FlowPane root = new FlowPane();
		 ProgressIndicator p1 = new ProgressIndicator();
		root.getChildren().addAll(p1);
		
		Scene scene = new Scene(root);
		loaderstage.setTitle("Please Wait this will take few minutes");
		loaderstage.setScene(scene);
		loaderstage.show();	
		UTable.setApplicationloaderstage(loaderstage);
	}

}
