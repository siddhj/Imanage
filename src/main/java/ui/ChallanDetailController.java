package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import bean.ChallanDetailBean;
import bean.PopUpChallan;
import dao.DChalan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utility.UTable;

public class ChallanDetailController {
	  
		@FXML
	    private TableView<ChallanDetailBean> challandetailtableview = new TableView<>();

	    @FXML
	    private TableColumn<ChallanDetailBean, Integer> referchallanidcolumn = new TableColumn<>("ReferChallanID");

	    @FXML
	    private TableColumn<ChallanDetailBean, Integer> challanidcolumn = new TableColumn<>("ChallanID");

	    @FXML
	    private TableColumn<ChallanDetailBean,String> assigneenamecolumn = new TableColumn<>("Assignee Name");

	    @FXML
	    private TableColumn<ChallanDetailBean,String> productidcolumn = new TableColumn<>("Product ID");

	    @FXML
	    private TableColumn<ChallanDetailBean, Integer> issuecolumn = new TableColumn<>("Issue");

	    @FXML
	    private TableColumn<ChallanDetailBean, Integer> receivecolumn = new TableColumn<>("Receive");

	    @FXML
	    private TableColumn<ChallanDetailBean, Integer> paidcolumn = new TableColumn<>("Paid");

	    @FXML
	    private TableColumn<ChallanDetailBean, Date> billdatecolumn = new TableColumn<>("Bill Date");

	    @FXML
	    private Button closebutton = new Button();

	    @FXML
	    void closeScreen(ActionEvent event) {
	    	Stage stage = (Stage) closebutton.getScene().getWindow();
			stage.close();
	    }
	    
	    @FXML
		public void initialize() throws SQLException, IOException {
	    	issuecolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, Integer>("issueitem"));
	    	receivecolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, Integer>("receiveitem"));
	    	paidcolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, Integer>("paiditem"));
	    	challanidcolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, Integer>("challanid"));
	    	referchallanidcolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, Integer>("referchallanid"));
	    	assigneenamecolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, String>("assigneename"));
	    	productidcolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, String>("productid"));
	    	billdatecolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, Date>("billdate"));
	    	challandetailtableview.setItems(UTable.getChallandetaillist());
//	    	challandetailtableview
	    }
}
