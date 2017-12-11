package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utility.UTable;

public class MultiScreenFramework extends Application {

	public static String screen1ID = "main";
    public static String screen1File = "Screen1.fxml";
    public static String loginscreen = "LoginScreen";
    public static String loginscreenfile = "LoginScreen.fxml"; 
    public static String homescreen = "HomeScreen";
    public static String homescreenfile = "/other/HomeScreen.fxml";
    public static String mainpage = "MainPage";
    public static String mainpagefile = "MainPage.fxml";
    public static String updatedmainpagefile = "UI_VER4.fxml";
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
    	MainScreenController mainController = new MainScreenController();
        mainController.loadScreen(MultiScreenFramework.loginscreen, MultiScreenFramework.loginscreenfile);
        mainController.loadScreen(MultiScreenFramework.homescreen, MultiScreenFramework.homescreenfile);
        mainController.loadScreen(MultiScreenFramework.mainpage,MultiScreenFramework.updatedmainpagefile);
        mainController.setScreen(MultiScreenFramework.loginscreen);
        
//        Group root = new Group();
//        root.getChildren().addAll(mainController);
//        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI_VER4_CSS.fxml"));
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
  //  System.out.println(MultiScreenFramework.class.getCanonicalName());
    }
}
