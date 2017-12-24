package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utility.UTable;

public class MultiScreenFramework extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        

        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginCSS.fxml"));
        Parent root = (Parent)loader.load();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(root);
        primaryStage.setMaximized(true);
        UTable.setPrimarystage(primaryStage);
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
  
    public static void main(String[] args) {
        launch(args);
    }
}
