package ui;

import java.io.IOException;
import java.sql.SQLException;

import bean.Chalan;
import dao.DChalan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.MultiScreen;


public class MainPageController implements MultiScreen {
		
		@FXML
	    private TextField assigneename;
	
	    @FXML
	    private TextField productidtext;
	
	    @FXML
	    private TextField issuetext;
	
	    @FXML
	    private TextField receivetext;
	
	    @FXML
	    private TextField duetext;
		
		@FXML
	    private TableView<Chalan> newchalantable = new TableView<>();

	    @FXML
	    private TableColumn<Chalan,String> productidcolumn = new TableColumn<>("Product Id");

	    @FXML
	    private TableColumn<Chalan,String> namecolumn = new TableColumn<>("Name");

	    @FXML
	    private TableColumn<Chalan,String> issueitemcolumn = new TableColumn<>("Issue Items");

	    @FXML
	    private TableColumn<Chalan,String> receiveitemcolumn = new TableColumn<>("Receive Items");

	    @FXML
	    private TableColumn<Chalan,String> duecolumn = new TableColumn<>("Due");

	    @FXML
	    private Button removebutton;
	    
	    @FXML
	    private Button savebutton;
	    
	    @FXML
	    private Button savechalandata;
	    
	    @FXML
	    void removeRow(ActionEvent event) {
	    Chalan chalan = newchalantable.getSelectionModel().getSelectedItem();
	    
	    listFromDb.forEach(c -> {
	    	if(c.getProductid()==chalan.getProductid())
	    	{
	    		listFromDb.remove(c);
	    	}
	    	else{
	    		System.out.println("there is no element selected");
	    	}
	    	
	    });
	    }
	    
	    ObservableList<Chalan> listFromDb =  FXCollections.observableArrayList();
	    //why to create instance table column instead of intializing in intialize() method 
	    @FXML
	    public void initialize() throws SQLException, IOException {
    	productidcolumn.setCellValueFactory(new PropertyValueFactory<Chalan,String>("productid"));
    	namecolumn.setCellValueFactory(new PropertyValueFactory<Chalan,String>("name"));
    	issueitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan,String>("issue"));
    	receiveitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan,String>("receive"));
    	duecolumn.setCellValueFactory(new PropertyValueFactory<Chalan,String>("due"));
    //	System.out.println("doen");
   // 	listFromDb = new DChalan().chalanDataLoad();
    //	newchalantable.setItems(listFromDb);
	    }   
    
    public ObservableList<Chalan> getData(){
    	ObservableList<Chalan> list = FXCollections.observableArrayList();
    	list.add(new Chalan("a","b","c","d","e"));
    	list.add(new Chalan("f","g","h","i","j"));
    	return list;
    }
  
    @FXML
    void saveChalan(ActionEvent event) {
    	System.out.println(assigneename.getText()+"::"+issuetext.getText());
    	Chalan chalan = new Chalan(assigneename.getText(),
    			productidtext.getText(),issuetext.getText(),receivetext.getText(),duetext.getText());
//    	Chalan chalan = new Chalan();
//    	chalan.setName(assigneename.getText());
//    	chalan.setName(productidtext.getText());
//    	chalan.setName(issuetext.getText());
//    	chalan.setName(receivetext.getText());
   // 	chalan.setName(duetext.getText());
    	newchalantable.getItems().add(chalan);
    	assigneename.setText("");
    	productidtext.setText("");
    	issuetext.setText("");
    	receivetext.setText("");
    	duetext.setText("");
    
    }
    
    @FXML
    void saveChalanData(ActionEvent event) throws SQLException, IOException {
    	ObservableList<Chalan> chalanlist = newchalantable.getItems();
    	chalanlist.forEach(c -> System.out.println(c.getName()));
    	DChalan chalan = new DChalan();
    	chalan.chalanDataInsert(chalanlist);
    }

    MainScreenController screencontroller = new MainScreenController();
	@Override
	public void setScreenParent(MainScreenController screencontroller) {
		// TODO Auto-generated method stub
		this.screencontroller = screencontroller;
	}

    @FXML
    void assigneeName(ActionEvent event) {

    }

    @FXML
    void dueText(ActionEvent event) {

    }

    @FXML
    void issueText(ActionEvent event) {

    }

    @FXML
    void productId(ActionEvent event) {

    }

    @FXML
    void receiveText(ActionEvent event) {

    }
}
