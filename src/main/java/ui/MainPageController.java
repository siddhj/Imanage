package ui;

import java.io.IOException;
import org.controlsfx.control.textfield.*;

import service.EditingCell;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import other.Utility;
import service.MultiScreen;
import utility.UTable;

public class MainPageController implements MultiScreen {

	// @FXML
	// private DatePicker billdate;
	@FXML
	private TextField assigneename;

	@FXML
	private TextField productidtext;

	@FXML
	private TextField issuetext;

	@FXML
	private TextField receivetext;

	@FXML
	private TextField duetext;

	@FXML
	private TextField paidtext;

	@FXML
	private TableView<Chalan> newchalantable = new TableView<>();

	@FXML
	private TableColumn<Chalan, String> productidcolumn = new TableColumn<>("Product Id");

	@FXML
	private TableColumn<Chalan, String> namecolumn = new TableColumn<>("Name");

	@FXML
	private TableColumn<Chalan, String> issueitemcolumn = new TableColumn<>("Issue Items");

	@FXML
	private TableColumn<Chalan, String> receiveitemcolumn = new TableColumn<>("Receive Items");

	@FXML
	private TableColumn<Chalan, String> duecolumn = new TableColumn<>("Due");

	@FXML
	private Button removebutton;

	@FXML
	private Button savebutton;

	@FXML
	private Button savechalandata;

	@FXML
	private TableColumn<Chalan, String> paidcolumn = new TableColumn<>("Paid");

	@FXML
	void removeRow(ActionEvent event) {
		Chalan chalan = newchalantable.getSelectionModel().getSelectedItem();

		listFromDb.forEach(c -> {
			if (c.getProductid() == chalan.getProductid()) {
				listFromDb.remove(c);
			} else {
				System.out.println("there is no element selected");
			}

		});
	}

//	TableColumn<Chalan, Integer> receiveitem = new TableColumn<Chalan, Integer>("Product");
	ObservableList<Chalan> listFromDb = FXCollections.observableArrayList();

	// why to create instance table column instead of intializing in intialize()
	// method
	@FXML
	public void initialize() throws SQLException, IOException {
//		receiveitem.setCellValueFactory(new PropertyValueFactory<Chalan,Integer>("receive"));
//
//		// callback functions are defined on the basis of the column and its datatype
//		Callback<TableColumn<Chalan,Integer>, TableCell<Chalan,Integer>> cellFactory
//		= new Callback<TableColumn<Chalan,Integer>, TableCell<Chalan,Integer>>() {
//			public TableCell<Chalan,Integer> call(TableColumn<Chalan,Integer> p) {
//				return new EditingCell();
//			}
//		};
//
//		receiveitem.setCellFactory(cellFactory);
//		receiveitem.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Chalan, Integer>>() {
//			@Override
//			public void handle(TableColumn.CellEditEvent<Chalan, Integer> t) {
//				((Chalan) t.getTableView().getItems().get(t.getTablePosition().getRow()))
//						.setReceive(t.getNewValue());
//			}
//		});

		productidcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("productid"));
		namecolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("assigneeid"));
		issueitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("issue"));
		receiveitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("receive"));

		duecolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("due"));
		paidcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("paid"));
		// System.out.println("doen");
		// listFromDb = new DChalan().chalanDataLoad();
		// newchalantable.setItems(listFromDb);
		newchalantable.setEditable(true);
		//newchalantable.getColumns().addAll(receiveitem);

		receiveitemcolumn.setEditable(true);
//		ObservableList<ObservableList<String>> parentlist = new DLoader().intialLoader();
//		TextFields.bindAutoCompletion(assigneename, parentlist.get(1));
//		TextFields.bindAutoCompletion(productidtext, parentlist.get(0));
	
	}

	public ObservableList<Chalan> getData() {
		ObservableList<Chalan> list = FXCollections.observableArrayList();
		// list.add(new Chalan("a","b","c","d","e"));
		// list.add(new Chalan("f","g","h","i","j"));
		return list;
	}

	public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> forTableColumn() {
		return forTableColumn();
	}

	@FXML
	void saveChalan(ActionEvent event) {
		System.out.println(assigneename.getText() + "::" + issuetext.getText());
		ObservableList<Assignee> assigneelist = DLoader.assigneelist;
		System.out.println("assigneelist details" + assigneelist.size());
		String name = assigneename.getText();
		int AssigneeID = 0;
		for (Assignee al : assigneelist) {
			if (al.getFirstname().equals(name)) {
				AssigneeID = al.getAssigneeid();
				System.out.println(AssigneeID);
				break;
			}
		}
		Chalan chalan = new Chalan(productidtext.getText(), Integer.parseInt(issuetext.getText()),
				Integer.parseInt(receivetext.getText()), Integer.parseInt(duetext.getText()),
				Integer.parseInt(paidtext.getText()), AssigneeID);
		// Chalan chalan = new Chalan();
		// chalan.setName(assigneename.getText());
		// chalan.setName(productidtext.getText());
		// chalan.setName(issuetext.getText());
		// chalan.setName(receivetext.getText());
		// chalan.setName(duetext.getText());
		newchalantable.getItems().add(chalan);
		// assigneename.setText("");
		productidtext.setText("");
		issuetext.setText("");
		receivetext.setText("");
		duetext.setText("");
		paidtext.setText("");

	}

	@FXML
	void saveChalanData(ActionEvent event) throws SQLException, IOException {
		ObservableList<Chalan> chalanlist = newchalantable.getItems();
		DChalan chalan = new DChalan();
		chalan.chalanDataInsert(chalanlist);
	}

	MainScreenController screencontroller = new MainScreenController();

	@Override
	public void setScreenParent(MainScreenController screencontroller) {
		// TODO Auto-generated method stub
		this.screencontroller = screencontroller;
	}

	@FXML
	void selectDateValue(ActionEvent event) {

	}

	@FXML
	void assigneeName(ActionEvent event) {

	}

	@FXML
	void dueText(ActionEvent event) {

	}

	@FXML
	void issueText(ActionEvent event) {

	}

	@FXML
	void productId(ActionEvent event) {

	}

	@FXML
	void receiveText(ActionEvent event) {

	}

	@FXML
	private Button newwindow;

	@FXML
	void popupWindow(ActionEvent event) throws IOException, SQLException {
		Utility.setVarone("daddy is home");

		UTable.setChallanlist(new DChalan().chalanDataLoad());

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage window = new Stage();
		window.setScene(scene);
		window.setMinHeight(500);
		window.setMinWidth(500);
		window.show();
	}
}
