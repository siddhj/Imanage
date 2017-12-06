package com;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import utility.UTable;

//public class ProgressDemo extends Application{
public class ProgressDemo {
	public void start() {
		Stage stage = new Stage();
		Label label = new Label("Loading....");
		FlowPane root = new FlowPane();
		root.setPadding(new Insets(10));
		root.setHgap(10);
		root.getChildren().addAll(label);
		Scene scene = new Scene(root);
		stage.setTitle("Data is Loading");
		stage.setScene(scene);
		stage.show();	
		UTable.setStage(stage);
	}

//	public static void main(String[] args) {
//		 new ProgressDemo().start();
//	}

}
