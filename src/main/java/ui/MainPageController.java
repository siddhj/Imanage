package ui;

import java.io.IOException;
import java.sql.SQLException;

import bean.Chalan;
import dao.DChalan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MainPageController {
	
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

	    //why to create instance table column instead of intializing in intialize() method 
	    @FXML
	    public void initialize() throws SQLException, IOException {
    	productidcolumn.setCellValueFactory(new PropertyValueFactory<Chalan,String>("productid"));
    	namecolumn.setCellValueFactory(new PropertyValueFactory<Chalan,String>("name"));
    	issueitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan,String>("issue"));
    	receiveitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan,String>("receive"));
    	duecolumn.setCellValueFactory(new PropertyValueFactory<Chalan,String>("due"));
    	System.out.println("doen");
    	newchalantable.setItems(new DChalan().chalanDataLoad());
	    }   
    
    public ObservableList<Chalan> getData(){
    	ObservableList<Chalan> list = FXCollections.observableArrayList();
    	list.add(new Chalan("a","b","c","d","e"));
    	list.add(new Chalan("f","g","h","i","j"));
    	return list;
    }
}
