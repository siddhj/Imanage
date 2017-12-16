package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import bean.Chalan;
import bean.PopUpChallan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.EditingCell;
import service.EditingPaid;
import service.MicroService;
import utility.UTable;

public class PopUpWindowForEditController {
	private TableColumn<PopUpChallan, Integer> paidcolumn = new TableColumn<>("Qty Paid");
	private TableColumn<PopUpChallan, Integer> currentpaidcolumn = new TableColumn<>("Current Paid Qty");
	private TableColumn<PopUpChallan, Integer> pastpaidcolumn = new TableColumn<>("Already Paid Qty");
	private TableColumn<PopUpChallan, Integer> pastpaidduecolumn = new TableColumn<>("Paid Qty Due");

	private TableColumn<PopUpChallan, Integer> receivecolumn = new TableColumn<>("Qty Receive");
	private TableColumn<PopUpChallan, Integer> pastreceivecolumn = new TableColumn<>("Already Receive Qty");
	private TableColumn<PopUpChallan, Integer> currentreceivecolumn = new TableColumn<>("Current Receive qty");
	private TableColumn<PopUpChallan, Integer> pastduecolumn = new TableColumn<>("Qty Due");

	@FXML
	private TableView<PopUpChallan> receiveTable = new TableView<>();

	@FXML
	private TableColumn<PopUpChallan, Integer> issueitemcolumn = new TableColumn<>("Qty Issued");

	@FXML
	private TableColumn<PopUpChallan, Date> billdatecolumn = new TableColumn<>("Bill Date");

	@FXML
	private Button savedata;

	@FXML
	private TextField issuetext;

	@FXML
	private TextField advancedpaidtext;

	@FXML
	private TextField receivetext;

	@FXML
	private TextField productidtext;

	@FXML
	public void initialize() throws SQLException, IOException {

		issueitemcolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("issue"));
		pastduecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastreceivedue"));
		billdatecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Date>("billdate"));
		// pastpaidcolumn.setCellValueFactory(new
		// PropertyValueFactory<PopUpChallan, Integer>("pastpaid"));
		pastreceivecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastreceive"));
		pastpaidcolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastpaid"));
		pastpaidduecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastpaiddue"));
		paidcolumn.getColumns().addAll(pastpaidcolumn, pastpaidduecolumn, currentpaidcolumn);
		receivecolumn.getColumns().addAll(pastreceivecolumn, pastduecolumn, currentreceivecolumn);
		// editable column
		Callback<TableColumn<PopUpChallan, Integer>, TableCell<PopUpChallan, Integer>> cellFactory = new Callback<TableColumn<PopUpChallan, Integer>, TableCell<PopUpChallan, Integer>>() {
			public TableCell<PopUpChallan, Integer> call(TableColumn<PopUpChallan, Integer> p) {
				return new EditingCell();
			}
		};
		Callback<TableColumn<PopUpChallan, Integer>, TableCell<PopUpChallan, Integer>> paidCellFactory = new Callback<TableColumn<PopUpChallan, Integer>, TableCell<PopUpChallan, Integer>>() {
			public TableCell<PopUpChallan, Integer> call(TableColumn<PopUpChallan, Integer> p) {
				return new EditingPaid();
			}
		};

		currentreceivecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("currentreceive"));
		currentpaidcolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("currentpaid"));

		currentreceivecolumn.setCellFactory(cellFactory);
		currentreceivecolumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PopUpChallan, Integer>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<PopUpChallan, Integer> t) {
				((PopUpChallan) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setCurrentreceive(t.getNewValue());
			}
		});

		currentpaidcolumn.setCellFactory(paidCellFactory);
		currentpaidcolumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PopUpChallan, Integer>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<PopUpChallan, Integer> t) {
				((PopUpChallan) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setCurrentpaid(t.getNewValue());
			}
		});

		currentreceivecolumn.setEditable(true);
		currentpaidcolumn.setEditable(true);
		receiveTable.setEditable(true);
		receiveTable.getColumns().addAll(receivecolumn, paidcolumn);

		chalanlist = UTable.getPopupchallantablelist();
		selectedchallanfrommainpage = UTable.getSelectedchallanfrommainpage();
		mainpagechalanlist = UTable.getMainpagechalanlist();
		//indexofselectedrow = UTable.getIndexofselectedrow();

		issueitemcolumn.prefWidthProperty().bind(receiveTable.widthProperty().divide(9).subtract(2.1/1));
		currentpaidcolumn.prefWidthProperty().bind(receiveTable.widthProperty().divide(9).subtract(2.1/1));
		pastpaidcolumn.prefWidthProperty().bind(receiveTable.widthProperty().divide(9).subtract(2.1/1));
		pastpaidduecolumn.prefWidthProperty().bind(receiveTable.widthProperty().divide(9).subtract(2.1/1));
		billdatecolumn.prefWidthProperty().bind(receiveTable.widthProperty().divide(9).subtract(2.1/1));
		pastreceivecolumn.prefWidthProperty().bind(receiveTable.widthProperty().divide(9).subtract(2.1/1));
		currentreceivecolumn.prefWidthProperty().bind(receiveTable.widthProperty().divide(9).subtract(2.1/1));
		pastduecolumn.prefWidthProperty().bind(receiveTable.widthProperty().divide(9).subtract(2.1/1));
		
		issueitemcolumn.maxWidthProperty().bind(issueitemcolumn.prefWidthProperty());
		currentpaidcolumn.maxWidthProperty().bind(currentpaidcolumn.prefWidthProperty());
		pastpaidcolumn.maxWidthProperty().bind(pastpaidcolumn.prefWidthProperty());
		pastpaidduecolumn.maxWidthProperty().bind(pastpaidduecolumn.prefWidthProperty());
		billdatecolumn.maxWidthProperty().bind(billdatecolumn.prefWidthProperty());
		pastreceivecolumn.maxWidthProperty().bind(pastreceivecolumn.prefWidthProperty());
		currentreceivecolumn.maxWidthProperty().bind(currentreceivecolumn.prefWidthProperty());
		pastduecolumn.maxWidthProperty().bind(pastduecolumn.prefWidthProperty());
		
		issueitemcolumn.setResizable(false);
		currentpaidcolumn.setResizable(false);
		pastpaidcolumn.setResizable(false);
		pastpaidduecolumn.setResizable(false);
		billdatecolumn.setResizable(false);
		pastreceivecolumn.setResizable(false);
		currentreceivecolumn.setResizable(false);
		pastduecolumn.setResizable(false);

		receiveTable.setItems(chalanlist);
		issuetext.setText(String.valueOf(selectedchallanfrommainpage.getIssue()));
		advancedpaidtext.setText(String.valueOf(selectedchallanfrommainpage.getAdvancepaid()));
		receivetext.setText(String.valueOf(selectedchallanfrommainpage.getTotalreceive()));
		productidtext.setText(selectedchallanfrommainpage.getProductid());
		UTable.setPopuptableview(receiveTable);
	}

	ObservableList<PopUpChallan> chalanlist;
	Chalan selectedchallanfrommainpage;
	ObservableList<Chalan> mainpagechalanlist;
//	int indexofselectedrow;

	
	@FXML
	void saveReceiveData(ActionEvent event) throws SQLException, IOException {
		ObservableList<PopUpChallan> popupchallantablelist = receiveTable.getItems();
		popupchallantablelist.forEach(c -> {
			System.out.println(c.getCurrentreceive() + "::" + c.getCurrentpaid() + "this sis the current reveive");
		});
	
		UTable.setPopupchallantablelist(popupchallantablelist);
		MicroService service = new MicroService();

		// filling the main window controller
		int receive = service.getTotalReceiveFromPopUp(receiveTable.getItems());
		int paid = service.getTotalPaidFromPopUp(receiveTable.getItems());

		System.out.println(receive+"::"+paid+"**"+selectedchallanfrommainpage);
		
		selectedchallanfrommainpage.setIssue(Integer.parseInt(issuetext.getText()));
		selectedchallanfrommainpage.setAdvancepaid(Integer.parseInt(advancedpaidtext.getText()));
		selectedchallanfrommainpage.setTotalreceive(receive);
		selectedchallanfrommainpage.setTotalpaid(paid);
		selectedchallanfrommainpage.setPopupchallantableview(receiveTable.getItems());
		selectedchallanfrommainpage.setDue(Integer.parseInt(issuetext.getText()));
		UTable.getMainpagetableview().getColumns().get(1).setVisible(false);
		UTable.getMainpagetableview().getColumns().get(1).setVisible(true);
		UTable.getMainpagetableview().getColumns().get(2).setVisible(false);
		UTable.getMainpagetableview().getColumns().get(2).setVisible(true);
		UTable.getMainpagetableview().getColumns().get(3).setVisible(false);
		UTable.getMainpagetableview().getColumns().get(3).setVisible(true);
		UTable.getMainpagetableview().getColumns().get(4).setVisible(false);
		UTable.getMainpagetableview().getColumns().get(4).setVisible(true);

		Stage stage = (Stage) savedata.getScene().getWindow();
		stage.close();
	}
	
}
