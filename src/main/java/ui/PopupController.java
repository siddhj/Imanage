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
	// @FXML
	// void sendNukes(ActionEvent event) {
	// Node source = (Node) event.getSource();
	// Stage stage = (Stage) source.getScene().getWindow();
	// stage.close();
	// Utility.setVartwo("gone");
	// System.out.println(Utility.getVartwo());
	//
	// }

	@FXML
	private TableView<Chalan> receiveTable = new TableView<>();


	private TableColumn<Chalan, Integer> paidcolumn = new TableColumn<>("Paid");

	private TableColumn<Chalan, Integer> receiveitemcolumn = new TableColumn<>("Receive Items");

	@FXML
	private TableColumn<Chalan, Integer> duecolumn = new TableColumn<>("Due");

	@FXML
	private TableColumn<Chalan, Integer> issueitemcolumn = new TableColumn<>("Issue Items");

	@FXML
	private TableColumn<Chalan, Date> billdatecolumn = new TableColumn<>("Bill Date");

	@FXML
	private Button savedata;

	@FXML
	public void initialize() throws SQLException, IOException {

		issueitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, Integer>("issue"));
		duecolumn.setCellValueFactory(new PropertyValueFactory<Chalan, Integer>("due"));
		billdatecolumn.setCellValueFactory(new PropertyValueFactory<Chalan, Date>("billdate"));

		// editable column
		Callback<TableColumn<Chalan, Integer>, TableCell<Chalan, Integer>> cellFactory = new Callback<TableColumn<Chalan, Integer>, TableCell<Chalan, Integer>>() {
			public TableCell<Chalan, Integer> call(TableColumn<Chalan, Integer> p) {
				return new EditingCell();
			}
		};
		Callback<TableColumn<Chalan, Integer>, TableCell<Chalan, Integer>> paidCellFactory = new Callback<TableColumn<Chalan, Integer>, TableCell<Chalan, Integer>>() {
			public TableCell<Chalan, Integer> call(TableColumn<Chalan, Integer> p) {
				return new EditingPaid();
			}
		};

		receiveitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, Integer>("receive"));
		paidcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, Integer>("paid"));

		receiveitemcolumn.setCellFactory(cellFactory);
		receiveitemcolumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Chalan, Integer>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Chalan, Integer> t) {
				((Chalan) t.getTableView().getItems().get(t.getTablePosition().getRow())).setReceive(t.getNewValue());
			}
		});

		paidcolumn.setCellFactory(paidCellFactory);
		paidcolumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Chalan, Integer>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Chalan, Integer> t) {
				((Chalan) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPaid(t.getNewValue());
			}
		});

		receiveitemcolumn.setEditable(true);
		paidcolumn.setEditable(true);
		receiveTable.setEditable(true);
		receiveTable.getColumns().addAll(receiveitemcolumn, paidcolumn);
		ObservableList<Chalan> chalanlist = UTable.getChallanlist();
		
		receiveTable.setItems(chalanlist);
		UTable.setPopuptableview(receiveTable);
	}

	public ObservableList<Chalan> getData() {
		ObservableList<Chalan> list = FXCollections.observableArrayList();
		// list.add(new Chalan("a","b","c","d","e"));
		// list.add(new Chalan("f","g","h","i","j"));
		return list;
	}

	@FXML
	void saveReceiveData(ActionEvent event) {
		ObservableList<Chalan> chalan = receiveTable.getItems();
		UTable.setChallanlist(chalan);
		MicroService service = new MicroService();
		int receive  = service.getTotalReceiveFromPopUp(receiveTable.getItems());
		System.out.println(receive+"amount");
		TextField receivefield = UTable.getReceiveTextField();
		receivefield.setText(String.valueOf(receive));
		Stage stage = (Stage) savedata.getScene().getWindow();
		stage.close();
	}
	
	
}
