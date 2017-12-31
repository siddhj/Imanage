package ui;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import bean.ChallanDetailBean;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.PdfGenerate;
import utility.SortAndFilterUtility;
import utility.UTable;

public class ChallanDetailController {

	@FXML
	private TableView<ChallanDetailBean> challandetailtableview = new TableView<>();

	@FXML
	private TableColumn<ChallanDetailBean, Integer> referchallanidcolumn = new TableColumn<>("Parent Challan ID");

	@FXML
	private TableColumn<ChallanDetailBean, Integer> challanidcolumn = new TableColumn<>("Challan ID");

	@FXML
	private TableColumn<ChallanDetailBean, String> assigneenamecolumn = new TableColumn<>("Assignee Name");

	@FXML
	private TableColumn<ChallanDetailBean, String> productidcolumn = new TableColumn<>("Product ID");

	@FXML
	private TableColumn<ChallanDetailBean, Integer> issuecolumn = new TableColumn<>("Qty Issue");

	@FXML
	private TableColumn<ChallanDetailBean, Integer> receivecolumn = new TableColumn<>("Qty Receive");

	// @FXML
	// private TableColumn<ChallanDetailBean, Integer> paidcolumn = new
	// TableColumn<>("Qty Paid");

	// @FXML
	// private TableColumn<ChallanDetailBean, Integer> amountpaidcolumn = new
	// TableColumn<>("Amount Paid");
	//
	@FXML
	private TableColumn<ChallanDetailBean, Date> billdatecolumn = new TableColumn<>("Bill Date");

	@FXML
	private Button closebutton = new Button();

	@FXML
	private Label referchallanidholderlabel = new Label();

	@FXML
	private Label assigneenameholderlabel = new Label();

	@FXML
	private Label billdateholderlabel = new Label();

	@FXML
	private Button exportpdfbutton;

	@FXML
	public void initialize() throws SQLException, IOException {
		issuecolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, Integer>("issueitem"));
		receivecolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, Integer>("receiveitem"));
		challanidcolumn
				.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, Integer>("prereceivedchallanid"));
		referchallanidcolumn
				.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, Integer>("referchallanid"));
		assigneenamecolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, String>("assigneename"));
		productidcolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, String>("productid"));
		billdatecolumn.setCellValueFactory(new PropertyValueFactory<ChallanDetailBean, Date>("challanidbilldate"));
		ObservableList<ChallanDetailBean> challandetaillist = UTable.getChallandetaillist();

		referchallanidholderlabel.setText(String.valueOf(challandetaillist.get(0).getReferchallanid()));
		assigneenameholderlabel.setText(challandetaillist.get(0).getAssigneename());
		billdateholderlabel.setText(String.valueOf(challandetaillist.get(0).getReferchallanidbilldate()));

		challandetailtableview.setItems(challandetaillist);
		// challandetailtableview
	}


    @FXML
    void exportChallanPdf(ActionEvent event) {
    	String billdateforpdf = billdateholderlabel.getText();
    	String referchallanid = referchallanidholderlabel.getText();
    	String assigneename = assigneenameholderlabel.getText();
    	SortAndFilterUtility.getChallandetailstage().setAlwaysOnTop(false);
    	closebutton.setDisable(true);
    	File pdffilelocation =  PdfGenerate.pdfFileLocationForSave(billdateforpdf);
    	PdfGenerate.createChallanDetailPdfss(billdateforpdf,referchallanid,assigneename,challandetailtableview,pdffilelocation);
    	SortAndFilterUtility.getChallandetailstage().setAlwaysOnTop(false);
    	closebutton.setDisable(false);
    }
	
	@FXML
	void closeScreen(ActionEvent event) {
		Stage stage = (Stage) closebutton.getScene().getWindow();
		stage.close();
	}

}
