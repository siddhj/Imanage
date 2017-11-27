package ui;


//import dao.DataUtility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.MultiScreen;

/**
 * FXML Controller class
 *
 * @author siddhartha.jain
 */
public class LoginScreenController implements Initializable,MultiScreen {

    /**
     * Initializes the controller class.
     */
    
    MainScreenController mainscreen;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      @FXML
    private Button login;

    @FXML
    private TextField license;

   @FXML
   void loginUser(ActionEvent event) {
//            if(new DataUtility().getLicense(license.getText())){
           mainscreen.setScreen(MultiScreenFramework.mainpage);
//            }
//            System.out.println("Invalid License");
    }
//
    @Override
    public void setScreenParent(MainScreenController screencontroller) {
    mainscreen = screencontroller;
    }
}
