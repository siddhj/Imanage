package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import bean.Chalan;
import bean.ChallanDetailBean;
import bean.PopUpChallan;
import bean.SortAndFilterBean;
import dao.DChalan;
import dao.DLoader;
import dao.DSort;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.MicroService;
import service.Validation;
import utility.UTable;

public class SortAndFilterController {
    @FXML
    private Button filterdata= new Button();

    @FXML
    private Button clearbutton;


    @FXML
    private ComboBox<String> assigneenamecombobox = new ComboBox<>();

    @FXML
    private ComboBox<String> productidcombobox = new ComboBox<>();

    @FXML
    private DatePicker datefrom = new DatePicker();

    @FXML
    private DatePicker dateto = new DatePicker();

    @FXML
    private TableView<SortAndFilterBean> filterandsorttable = new TableView<>();

    @FXML
    private TableColumn<SortAndFilterBean, SimpleIntegerProperty> challanidcolumn = new TableColumn<>("Challan ID");

    @FXML
    private TableColumn<SortAndFilterBean, SimpleStringProperty> productidcolumn = new TableColumn<>("Product ID");
    
    @FXML
    private TableColumn<SortAndFilterBean,LocalDate> billdatecolumn = new TableColumn<>("BillDate");

    @FXML
    private TableColumn<SortAndFilterBean, SimpleStringProperty> namecolumn = new TableColumn<>("Assignee Name");

    @FXML
    private TableColumn<SortAndFilterBean, SimpleIntegerProperty> issuecolumn = new TableColumn<>("Issue Qty");

    @FXML
    private TableColumn<SortAndFilterBean, SimpleIntegerProperty> receivecolumn  = new TableColumn<>("Receive Qty");

    @FXML
    private TableColumn<SortAndFilterBean, SimpleIntegerProperty> receiveduecolumn = new TableColumn<>("Receive Due");

    @FXML
    private TableColumn<SortAndFilterBean, SimpleIntegerProperty> paidcolumn  = new TableColumn<>("Paid Qty");

    @FXML
    private TableColumn<SortAndFilterBean, SimpleIntegerProperty> paidduecolumn  = new TableColumn<>("Paid Due");
    
    @FXML
    private TableColumn<SortAndFilterBean, SimpleIntegerProperty> pastreceivecolumn  = new TableColumn<>("Past Receive");

    @FXML
    private TableColumn<SortAndFilterBean, SimpleIntegerProperty> pastpaidcolumn  = new TableColumn<>("Past Paid");
//    public String filtername,filterproductid,assigneename;
//    public Date fromdate,todate,billdate;
//    public int challanid,productid,issueitem,receiveitem,receivedueitem,paiditem,paiditemdue;

    @FXML
	private Button exploreselectchallan;


	@FXML
	public void initialize() throws SQLException, IOException {
	productidcolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleStringProperty>("productid"));
	challanidcolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("challanid"));
	issuecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("issueitem"));
	receivecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("receiveitem"));
	receiveduecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("receivedueitem"));
	paidcolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("paiditem"));	
	paidduecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("paiditemdue"));
	billdatecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, LocalDate>("billdate"));
	namecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleStringProperty>("assigneename"));
	pastreceivecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("pastreceive"));
	pastpaidcolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("pastpaid"));
	ObservableList<ObservableList<String>> parentlist = new DLoader().intialLoader();
	
	assigneenamecombobox.getItems().addAll(parentlist.get(1));
	productidcombobox.getItems().addAll(parentlist.get(0));
	
	}
	
    @FXML
    void getAssigneeNameForComboBox(ActionEvent event) {
    	System.out.println(assigneenamecombobox.getValue()+"::");
    }
    
    @FXML
    void getFilterDataParameter(ActionEvent event) throws SQLException, IOException {
    	LocalDate fromdate = datefrom.getValue();
    	LocalDate todate = dateto.getValue();
    	String assigneename = assigneenamecombobox.getValue();
    	String productid = productidcombobox.getValue();
    	
    	String sqlstringassigneename = Validation.nullVarifierStringForDao(assigneename);
    	String sqlstringproductid = Validation.nullVarifierStringForDao(productid);
    	String sqlstringfromdate = Validation.nullVarifierFromDateForDao(fromdate);
    	String sqlstringtodate = Validation.nullVarifierToDateForDao(todate);
    	
    	System.out.println(sqlstringassigneename+"::"+sqlstringproductid+"::"+sqlstringfromdate+"::"+sqlstringtodate+"::");
    DSort sort = new DSort();
    ObservableList<SortAndFilterBean> filterlist = sort.getFilterData(sqlstringassigneename.trim(), sqlstringproductid.trim(), sqlstringfromdate, sqlstringtodate);
    filterandsorttable.setItems(filterlist);
    }
    
    @FXML
    void clearFilterField(ActionEvent event) {
    	datefrom.setValue(null);
    	dateto.setValue(null);
    	assigneenamecombobox.setValue(null);
    	productidcombobox.setValue(null);
    }
    @FXML
    void getChallanDetail(ActionEvent event) throws SQLException, IOException {
    	SortAndFilterBean selectedchallan = filterandsorttable.getSelectionModel().getSelectedItem();
    	int challanid = selectedchallan.getChallanid();
    	ObservableList<ChallanDetailBean> challandetaillist = new DChalan().logChallanDataLoad(challanid);
    	UTable.setChallandetaillist(challandetaillist);
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ChallanDetail.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage window = new Stage();
		window.setScene(scene);
		Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        window.setX(bounds.getMinX()+60); 
        window.setY(bounds.getMinY()+70);
        window.setWidth((bounds.getWidth()*70)/100);
        window.setHeight((bounds.getHeight()*90)/100);
		window.initStyle(StageStyle.UNDECORATED);
		window.show();
    }
}
