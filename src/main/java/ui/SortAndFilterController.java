package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import org.apache.log4j.Logger;

import com.ProgressDemo;

import bean.ChallanDetailBean;
import bean.SortAndFilterBean;
import dao.DChalan;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.Notification;
import service.Validation;
import utility.LoginVariable;
import utility.UTable;

public class SortAndFilterController {
	@FXML
	private Button filterdata = new Button();

	@FXML
	private Button clearbutton;

	@FXML
	private Button tabchallanbutton;

	@FXML
	private Button Imanagebutton;

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
	private TableColumn<SortAndFilterBean, LocalDate> billdatecolumn = new TableColumn<>("Bill Date");

	@FXML
	private TableColumn<SortAndFilterBean, SimpleStringProperty> namecolumn = new TableColumn<>("Assignee Name");

	@FXML
	private TableColumn<SortAndFilterBean, Integer> issuecolumn = new TableColumn<>("Qty Issued");

	@FXML
	private TableColumn<SortAndFilterBean, Integer> receivecolumn = new TableColumn<>("Qty Received");

	@FXML
	private TableColumn<SortAndFilterBean, Integer> receiveduecolumn = new TableColumn<>("Receive Due Qty");

	@FXML
	private TableColumn<SortAndFilterBean, SimpleIntegerProperty> pastreceivecolumn = new TableColumn<>(
			"Already Receive Qty");

	@FXML
	private TableColumn<SortAndFilterBean, SimpleIntegerProperty> amountpaidcolumn = new TableColumn<>("Amount Paid");

	@FXML
	private Button exploreselectchallan;

	@FXML
	public void initialize() throws SQLException, IOException {
		productidcolumn
				.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleStringProperty>("productid"));
		challanidcolumn.setCellValueFactory(
				new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("aggregatechallanid"));
		issuecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, Integer>("issueitem"));
		receivecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, Integer>("receiveitem"));
		receiveduecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, Integer>("receivedueitem"));
		billdatecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, LocalDate>("billdate"));
		namecolumn
				.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleStringProperty>("assigneename"));
		pastreceivecolumn
				.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("pastreceive"));
		amountpaidcolumn
				.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("amountpaid"));

		UTable.getIntialloaderassigneename().add("None");
		UTable.getIntialloaderproductid().add("None");
		assigneenamecombobox.getItems().addAll(UTable.getIntialloaderassigneename());
		productidcombobox.getItems().addAll(UTable.getIntialloaderproductid());
	}

	@FXML
	void getAssigneeNameForComboBox(ActionEvent event) {
		System.out.println(assigneenamecombobox.getValue() + "::");
	}

	@FXML
	void getFilterDataParameter(ActionEvent event) throws SQLException, IOException {
		new ProgressDemo().start();
		LocalDate fromdate = datefrom.getValue();
		LocalDate todate = dateto.getValue();
		String assigneename = assigneenamecombobox.getValue();
		String productid = productidcombobox.getValue();

		String sqlstringassigneename = Validation.nullVarifierStringForDao(assigneename);
		String sqlstringproductid = Validation.nullVarifierStringForDao(productid);
		String sqlstringfromdate = Validation.nullVarifierFromDateForDao(fromdate);
		String sqlstringtodate = Validation.nullVarifierToDateForDao(todate);

		System.out.println(sqlstringassigneename + "::" + sqlstringproductid + "::" + sqlstringfromdate + "::"
				+ sqlstringtodate + "::");
		DSort sort = new DSort();
		ObservableList<SortAndFilterBean> filterlist = sort.getFilterData(sqlstringassigneename.trim(),
				sqlstringproductid.trim(), sqlstringfromdate, sqlstringtodate);

		filterandsorttable.setItems(filterlist);

		UTable.getLoaderstage().close();
	}

	@FXML
	void clearFilterField(ActionEvent event) {
		datefrom.setValue(null);
		dateto.setValue(null);
		assigneenamecombobox.setValue(null);
		productidcombobox.setValue(null);
	}

	final static Logger logger = Logger.getLogger(MultiScreenFramework.class);

	// Explore Selection Button
	@FXML
	void getChallanDetail(ActionEvent event) {
		new ProgressDemo().start();
		SortAndFilterBean selectedchallan = filterandsorttable.getSelectionModel().getSelectedItem();
		long challanid = selectedchallan.getAggregatechallanid();
		ObservableList<ChallanDetailBean> challandetaillist = null;
		try {
			challandetaillist = DChalan.getSingeletonInstance().logChallanDataLoad(challanid);
		} catch (SQLException | IOException e) {
			logger.error("explore selection button challanid:" + challanid, e);
			e.printStackTrace();
		}
		UTable.setChallandetaillist(challandetaillist);
		UTable.getLoaderstage().close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ChallanDetail.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			logger.error("unable to load challandetail", e);
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		Stage window = new Stage();
		window.setScene(scene);
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		window.setX(bounds.getMinX() + 60);
		window.setY(bounds.getMinY() + 70);
		window.setWidth((bounds.getWidth() * 72) / 100);
		window.setHeight((bounds.getHeight() * 65) / 100);
		window.setAlwaysOnTop(true);
		window.initStyle(StageStyle.UNDECORATED);
		window.initOwner(UTable.getPrimarystage());
		window.initModality(Modality.WINDOW_MODAL);
		window.show();
	}

	@FXML
	void tabChallanButton(ActionEvent event) {
		logger.info("To open new challan window");
		if(LoginVariable.isNewchallanaccess()){
		new ProgressDemo().start();
		FXMLLoader myLoader = new FXMLLoader(getClass().getResource("UI_VER4.fxml"));
		try {
			Parent loadScreen = (Parent) myLoader.load();
			Stage primarystage = UTable.getPrimarystage();
			Scene scene = new Scene(loadScreen);
			primarystage.setScene(scene);
			UTable.getLoaderstage().close();
		} catch (IOException e) {
			e.printStackTrace();
		}}
		else{
    		Notification.errorOccuredNotification("Access Expired","You do not have right to access to create new challan. Contact System Admin");
		}
	}

	@FXML
	void openDashboardWindow(ActionEvent event) {
		new ProgressDemo().start();
		FXMLLoader myLoader = new FXMLLoader(getClass().getResource("DashboardWindow.fxml"));
		try {
			Parent loadScreen = (Parent) myLoader.load();
			Stage primarystage = UTable.getPrimarystage();
			Scene scene = new Scene(loadScreen);
			primarystage.setScene(scene);
			UTable.getLoaderstage().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
