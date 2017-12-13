package com;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import utility.UTable;

//public class ProgressDemo extends Application{
public class ProgressDemo //implements Runnable 
{
	public void start() {
		Stage loaderstage = new Stage();
		BackgroundImage myBI= new BackgroundImage(new Image("file:///C:/Users/siddhartha.jain/workspace/byte/src/main/java/Images/chalan.png",32,32,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		
	//	Label label = new Label("Loading....");
		FlowPane root = new FlowPane();
		root.setBackground(new Background(myBI));
		root.setPadding(new Insets(10));
		root.setHgap(10);
		//root.getChildren().addAll(label);
		Scene scene = new Scene(root);
		loaderstage.setTitle("Please Wait While Processing");
		loaderstage.setScene(scene);
		loaderstage.show();	
		UTable.setLoaderstage(loaderstage);
	}

//	@Override
//	public void run() {
//		System.out.println("inside the multithread");
//		Stage stage = new Stage();
//		Label label = new Label("Loading....");
//		FlowPane root = new FlowPane();
//		root.setPadding(new Insets(10));
//		root.setHgap(10);
//		root.getChildren().addAll(label);
//		Scene scene = new Scene(root);
//		stage.setTitle("Please Wait While Processing");
//		stage.setScene(scene);
//		stage.show();	
//		UTable.setStage(stage);
//	}

//	public static void main(String[] args) {
//		 new ProgressDemo().start();
//	}

}
