package other;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import service.MultiScreen;
import ui.MainScreenController;

/**
 * FXML Controller class
 *
 * @author siddhartha.jain
 */
public class HomeScreenController implements Initializable,MultiScreen {
    MainScreenController mainscreen;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setScreenParent(MainScreenController screencontroller) {
       mainscreen = screencontroller;
    }
    @FXML
    private Button newchalan;

    @FXML
    private Button sortbydate;

    @FXML
    void newChalan(ActionEvent event) {

    }

    @FXML
    void sortByDate(ActionEvent event) {

    }
    
}
