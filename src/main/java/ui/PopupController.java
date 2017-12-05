package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import org.controlsfx.control.textfield.*;
import java.sql.SQLException;
import bean.Assignee;
import bean.Chalan;
import bean.PopUpChallan;
import dao.DChalan;
import dao.DLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import other.Utility;
import service.EditingCell;
import service.EditingPaid;
import service.MicroService;
import service.MultiScreen;
import utility.UTable;

public class PopupController {

	private TableColumn<PopUpChallan, Integer> currentreceivecolumn = new TableColumn<>("Receive Items");

	private TableColumn<PopUpChallan, Integer> currentpaidcolumn = new TableColumn<>("Paid Items");

	@FXML
	private TableColumn<PopUpChallan, Integer> past = new TableColumn<>("Past");

	@FXML
	private TableColumn<PopUpChallan, Integer> pastreceivecolumn = new TableColumn<>("Receive");

	@FXML
	private TableColumn<PopUpChallan, Integer> pastpaidcolumn = new TableColumn<>("Paid");

	@FXML
	private TableColumn<PopUpChallan, Integer> pastduecolumn = new TableColumn<>("Due");

	@FXML
	private TableView<PopUpChallan> receiveTable = new TableView<>();

	@FXML
	private TableColumn<PopUpChallan, Integer> issueitemcolumn = new TableColumn<>("Issue Items");

	@FXML
	private TableColumn<PopUpChallan, Date> billdatecolumn = new TableColumn<>("Bill Date");

	@FXML
	private Button savedata;

	@FXML
	public void initialize() throws SQLException, IOException {

		issueitemcolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("issue"));
		pastduecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastdue"));
		billdatecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Date>("billdate"));
		pastpaidcolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastpaid"));
		pastreceivecolumn.setCellValueFactory(new PropertyValueFactory<PopUpChallan, Integer>("pastreceive"));
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
		receiveTable.getColumns().addAll(currentreceivecolumn, currentpaidcolumn);

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
			System.out.println(c.getCurrentreceive() + "this sis the current reveive");
		});
		UTable.setChallanlist(chalan);
		MicroService service = new MicroService();

		// filling the main window controller
		int receive = service.getTotalReceiveFromPopUp(receiveTable.getItems());
		System.out.println(receive + "this is the receieve");
		int paid = service.getTotalPaidFromPopUp(receiveTable.getItems());
		UTable.setTotalpaid(paid);
		// int due = service.getTotalDueFromPopUp(receiveTable.getItems());

		// TextField paidtext = UTable.getPaidtextfield();
		TextField receivetext = UTable.getReceiveTextField();
		// TextField duetext = UTable.getDuetext();
		System.out.println(receivetext.getText());
		receivetext.setText(String.valueOf(receive));
		// paidtext.setText(String.valueOf(paid));
		// duetext.setText(String.valueOf(due));

		// saving the data in database
		// new DChalan().chalanDataUpdatePopUpWindow(chalan);
		UTable.setPopupchallantableviewdata(chalan);
		Stage stage = (Stage) savedata.getScene().getWindow();
		stage.close();
	}

}
