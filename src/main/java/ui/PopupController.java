package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import bean.ChallanDetailBean;
import bean.PopUpChallan;
import dao.DChalan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.EditingCell;
import service.EditingPaid;
import service.MicroService;
import utility.UTable;

public class PopupController {

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
	public void initialize() throws SQLException, IOException {

		issueitemcolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("issue"));
		pastduecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastreceivedue"));
		billdatecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Date>("billdate"));
//		pastpaidcolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastpaid"));
		pastreceivecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastreceive"));
		pastpaidcolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastpaid"));
		pastpaidduecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastpaiddue"));
		paidcolumn.getColumns().addAll(pastpaidcolumn,pastpaidduecolumn,currentpaidcolumn);
		receivecolumn.getColumns().addAll(pastreceivecolumn,pastduecolumn,currentreceivecolumn);
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

		ObservableList<PopUpChallan> chalanlist = UTable.getChallanlist();
		receiveTable.setItems(chalanlist);
		UTable.setPopuptableview(receiveTable);
	}

	public ObservableList<PopUpChallan> getData() {
		ObservableList<PopUpChallan> list = FXCollections.observableArrayList();
		return list;
	}

	@FXML
	void saveReceiveData(ActionEvent event) throws SQLException, IOException {
		ObservableList<PopUpChallan> chalan = receiveTable.getItems();
		chalan.forEach(c -> {
			System.out.println(c.getCurrentreceive()+"::"+c.getCurrentpaid() + "this sis the current reveive");
		});
		UTable.setChallanlist(chalan);
		MicroService service = new MicroService();

		// filling the main window controller
		int receive = service.getTotalReceiveFromPopUp(receiveTable.getItems());
		System.out.println(receive + "this is the receieve");
		int paid = service.getTotalPaidFromPopUp(receiveTable.getItems());
		UTable.setTotalpaid(paid);
		TextField receivetext = UTable.getReceiveTextField();
		receivetext.setText(String.valueOf(receive));
		UTable.setPopupchallantableviewdata(chalan);
		Stage stage = (Stage) savedata.getScene().getWindow();
		stage.close();
	}
	
	  
}
