package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import bean.SortAndFilterBean;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SortAndFilterController {

    @FXML
    private TextField assigneenametextbox = new TextField();

    @FXML
    private TextField productidtextbox = new TextField();

    @FXML
    private DatePicker datefrom = new DatePicker();

    @FXML
    private DatePicker dateto = new DatePicker();

    @FXML
    private TableView<SortAndFilterBean> filterandsorttable = new TableView<>();

    @FXML
    private TableColumn<SortAndFilterBean, SimpleIntegerProperty> challanidcolumn = new TableColumn<>("Challan ID");

    @FXML
    private TableColumn<SortAndFilterBean, SimpleIntegerProperty> productidcolumn = new TableColumn<>("Product ID");
    
    @FXML
    private TableColumn<SortAndFilterBean,Date> billdatecolumn = new TableColumn<>("BillDate");

    @FXML
    private TableColumn<SortAndFilterBean, Date> namecolumn = new TableColumn<>("Assignee Name");

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
	public void initialize() throws SQLException, IOException {
		
	}
}
