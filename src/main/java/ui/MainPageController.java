package ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.controlsfx.control.textfield.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.ProgressDemo;
import com.itextpdf.text.DocumentException;
import service.DataManipulation;
import service.MicroService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.Notification;
import service.Validation;
import utility.UTable;

public class MainPageController {

	@FXML
	private Button Imanagebutton;

	@FXML
	private Button addproductid;

	@FXML
	private Label assigneenamelabel;

	@FXML
	private Button exlporeselection;

	@FXML
	private Button tabproductidbutton;

    @FXML
    private Button addnewassigneebutton;

    @FXML
	private Label productidlabel;

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
	private TextField advancedpaidtext;

	@FXML
	private TableView<Chalan> newchalantable = new TableView<>();

	@FXML
	private TableColumn<Chalan, String> productidcolumn = new TableColumn<>("Product Id");

	@FXML
	private TableColumn<Chalan, String> namecolumn = new TableColumn<>("Name");

	@FXML
	private TableColumn<Chalan, String> issueitemcolumn = new TableColumn<>("Qty Issued");

	@FXML
	private TableColumn<Chalan, String> receiveitemcolumn = new TableColumn<>("Total Qty Received");

	// @FXML
	// private TableColumn<Chalan, String> amountpaidcolumn = new
	// TableColumn<>("Amount Paid");

	// not required in v0.9
	// @FXML
	// private TableColumn<Chalan, String> totalpaidcolumn = new
	// TableColumn<>("Total Qty Paid");
	//
	// @FXML
	// private TableColumn<Chalan, String> advancedpaidcolumn = new
	// TableColumn<>("Qty Advance Paid");

	@FXML
	private Button removebutton;

	@FXML
	private Button savebutton;

	@FXML
	private Button savechalandata;

	@FXML
	private Button clearbutton;

	@FXML
	private TextArea savechallandescription;

	@FXML
	private DatePicker billdate;

	@FXML
	void removeRow(ActionEvent event) {
		Chalan chalan = newchalantable.getSelectionModel().getSelectedItem();
		ObservableList<Chalan> allchalanfromtableview = newchalantable.getItems();
		allchalanfromtableview.forEach(c -> {
			if (c.getProductid() == chalan.getProductid()) {
				allchalanfromtableview.remove(c);
			}
		});
	}

	ObservableList<ObservableList<String>> parentlist = FXCollections.observableArrayList();

	// why to create instance table column instead of intializing in intialize()
	// method
	@FXML
	public void initialize() throws SQLException, IOException {

		productidcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("productid"));
		namecolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("assigneeid"));
		issueitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("issue"));
		receiveitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("totalreceive"));
		// amountpaidcolumn.setCellValueFactory(new PropertyValueFactory<Chalan,
		// String>("amountpaid"));

		// ** duecolumn.setCellValueFactory(new PropertyValueFactory<Chalan,
		// String>("due"));

		// not required in v0.9
		// advancedpaidcolumn.setCellValueFactory(new
		// PropertyValueFactory<Chalan, String>("advancepaid"));
		// totalpaidcolumn.setCellValueFactory(new PropertyValueFactory<Chalan,
		// String>("totalpaid"));

		// System.out.println("doen");
		// listFromDb = new DChalan().chalanDataLoad();
		// newchalantable.setItems(listFromDb);
		newchalantable.setEditable(true);
		// newchalantable.getColumns().addAll(receiveitem);

		receiveitemcolumn.setEditable(true);

		TextFields.bindAutoCompletion(productidtext, UTable.getIntialloaderproductid());
		TextFields.bindAutoCompletion(assigneename, UTable.getIntialloaderassigneename());

		/* Text Field Operations *****/
		issuetext.setDisable(true);
		advancedpaidtext.setDisable(true);

		productidtext.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {
				issuetext.setDisable(false);
				advancedpaidtext.setDisable(false);

			}
		});

		issuetext.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (!"0123456789".contains(keyEvent.getCharacter())) {
					keyEvent.consume();
				}
			}
		});
		advancedpaidtext.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (!"0123456789".contains(keyEvent.getCharacter())) {
					keyEvent.consume();
				}
			}
		});
		// scene.widthProperty().divide(3).subtract(2.1/3)
		/* Setting column size in the table */
		productidcolumn.prefWidthProperty()
				.bind(UTable.getPrimarystage().getScene().widthProperty().divide(3).subtract(2.1 / 3));
		issueitemcolumn.prefWidthProperty()
				.bind(UTable.getPrimarystage().getScene().widthProperty().divide(3).subtract(2.1 / 3));
		receiveitemcolumn.prefWidthProperty()
				.bind(UTable.getPrimarystage().getScene().widthProperty().divide(3).subtract(2.1 / 3));
		// amountpaidcolumn.prefWidthProperty().bind(UTable.getPrimarystage().getScene().widthProperty().divide(7).subtract(2.1/3));
		// totalpaidcolumn.prefWidthProperty().bind(UTable.getPrimarystage().getScene().widthProperty().divide(7).subtract(2.1/3));
		// advancedpaidcolumn.prefWidthProperty().bind(UTable.getPrimarystage().getScene().widthProperty().divide(7).subtract(2.1/3));

		productidcolumn.maxWidthProperty().bind(productidcolumn.prefWidthProperty());
		issueitemcolumn.maxWidthProperty().bind(issueitemcolumn.prefWidthProperty());
		receiveitemcolumn.maxWidthProperty().bind(receiveitemcolumn.prefWidthProperty());
		// amountpaidcolumn.maxWidthProperty().bind(amountpaidcolumn.prefWidthProperty());
		// totalpaidcolumn.maxWidthProperty().bind(totalpaidcolumn.prefWidthProperty());
		// advancedpaidcolumn.maxWidthProperty().bind(advancedpaidcolumn.prefWidthProperty());

		productidcolumn.setResizable(false);
		issueitemcolumn.setResizable(false);
		receiveitemcolumn.setResizable(false);
		// amountpaidcolumn.setResizable(false);
		// totalpaidcolumn.setResizable(false);
		// advancedpaidcolumn.setResizable(false);
		billdate.setValue(LocalDate.now());
		UTable.setMainpagetableview(newchalantable);
	}

	public ObservableList<Chalan> getData() {
		ObservableList<Chalan> list = FXCollections.observableArrayList();
		return list;
	}

	public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> forTableColumn() {
		return forTableColumn();
	}

	@FXML
	void saveChalan(ActionEvent event) throws SQLException, IOException {
		String name = assigneename.getText();

		if (billdate.getValue() == null) {
			Notification.invalidDateFromUser();
			return;
		}
		LocalDate dateofbill = billdate.getValue();

		if (!Validation.parentListNameValidation(UTable.getIntialloaderassigneename(), assigneename.getText())) {
			Notification.invalidInput("Invalid Name of Assignee",
					"Assignee Name Entered is invalid. Please check the assignee name again");
			return;
		}
		if (!Validation.parentListProductIDValidation(UTable.getIntialloaderproductid(), productidtext.getText())) {
			Notification.invalidInput("Invalid Product ID",
					"Product ID entered is invalid. Please check the product id again");
			return;
		}
		if (!Validation.checkProductIDAlreadyPresentInTable(productidtext.getText(), newchalantable.getItems())) {
			Notification.invalidInput("Product ID is already present in table",
					"Product ID you have entered is already present in the table. Edit the current exsiting entry for this product id from table");
			return;
		}
		if (!Validation.checkIfAdvancePaidTextBoxIsEmpty(advancedpaidtext.getText())) {
			Notification.invalidInput("Advance Paid Text Box is Empty", "Enter Some Value in Advance Paid Text Box");
			return;
		}

		if (dateofbill == null) {
			Notification.mainWindowInvalidBillDate();
			return;
		}
		try {
			ObservableList<String> assigneeandgstin = new MicroService()
					.assigneeIDRetrieveFullName(assigneename.getText());
			int AssigneeID = Integer.parseInt(assigneeandgstin.get(0));
			String gstin = assigneeandgstin.get(1);

			Chalan chalan = new Chalan(productidtext.getText(), Integer.parseInt(issuetext.getText()), 0,
					Integer.parseInt(issuetext.getText()), AssigneeID, UTable.getPopupchallantablelist(),
					Integer.parseInt(receivetext.getText()), dateofbill, savechallandescription.getText(),
					Integer.parseInt(advancedpaidtext.getText()));

			newchalantable.getItems().add(chalan);
			productidtext.setText("");
			issuetext.setText("");
			receivetext.setText("0");
			savechallandescription.setText("");
			// duetext.setText("");
			assigneenamelabel.setText(assigneename.getText());
			issuetext.setDisable(true);
			advancedpaidtext.setDisable(true);
			// billdate.setValue(null);
		} catch (NumberFormatException E) {
			Notification.invalidInput("Something is not right", "Please check your input values");
		}
		assigneename.setDisable(true);
		billdate.setDisable(true);
		billdate.setValue(LocalDate.now());
	}

	@FXML
	void exploreSelectionPopUpWindow(ActionEvent event) throws IOException {
		Chalan selectedchalan = newchalantable.getSelectionModel().getSelectedItem();
		int indexofselectedrow = newchalantable.getSelectionModel().getSelectedIndex();

		ObservableList<Chalan> mainpagechalanlist = FXCollections.observableArrayList();
		mainpagechalanlist.add(selectedchalan);
		ObservableList<PopUpChallan> popupchallantablelist = null;
		try {
			popupchallantablelist = selectedchalan.getPopupchallantableview();
		} catch (NullPointerException exception) {
			Notification.nothingIsSelectedNotification();
		}
		UTable.setPopupchallantablelist(popupchallantablelist);
		UTable.setSelectedchallanfrommainpage(selectedchalan);
		UTable.setIndexofselectedrow(indexofselectedrow);
		UTable.setMainpagechalanlist(newchalantable.getItems());
		// for loading receive data back
		// UTable.setReceiveTextField(receivetext);
		// UTable.setPaidtextfield(paidtext);
		// UTable.setDuetext(duetext);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUpWindowForEdit.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage window = new Stage();
		window.setScene(scene);
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		window.setX(bounds.getMinX() + 60);
		window.setY(bounds.getMinY() + 70);
		window.setWidth((bounds.getWidth() * 80) / 100);
		window.setHeight((bounds.getHeight() * 60) / 100);
		window.initStyle(StageStyle.UNDECORATED);

		window.initOwner(UTable.getPrimarystage());
		window.initModality(Modality.WINDOW_MODAL);
		window.show();
	}

	@FXML
	private Button newwindow;

	/* This is the action event for receive button */
	@FXML
	void popupWindow(ActionEvent event) throws IOException, SQLException {
		System.out.println("this is the receive button");

		ObservableList<Chalan> mainpagechalanlist = newchalantable.getItems();

		for (Chalan C : mainpagechalanlist) {
			if (C.getProductid().equals(productidtext.getText())) {
				Notification.mainWindowProductIDAlreadyExist();
				issuetext.setDisable(true);
				advancedpaidtext.setDisable(true);
				// newchalantable
				UTable.setSelectedchallanfrommainpage(C);
				UTable.setMainpagechalanlist(mainpagechalanlist);
				UTable.setPopupchallantablelist(C.getPopupchallantableview());
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUpWindowForEdit.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Stage window = new Stage();
				window.setScene(scene);
				Screen screen = Screen.getPrimary();
				Rectangle2D bounds = screen.getVisualBounds();
				window.setX(bounds.getMinX() + 60);
				window.setY(bounds.getMinY() + 70);
				window.setWidth((bounds.getWidth() * 80) / 100);
				window.setHeight((bounds.getHeight() * 60) / 100);
				window.initStyle(StageStyle.UNDECORATED);
				window.initOwner(UTable.getPrimarystage());
				window.initModality(Modality.WINDOW_MODAL);
				window.setAlwaysOnTop(true);
				window.show();

				savechallandescription.setText("");
				productidtext.setText("");
				issuetext.setText("");
				receivetext.setText("");
				advancedpaidtext.setText("");
//				billdate.setValue(null);
				return;
			}
		}
		new ProgressDemo().start();
		ObservableList<String> assigneeandgstin = new MicroService().assigneeIDRetrieveFullName(assigneename.getText());
		int assigneeid = Integer.parseInt(assigneeandgstin.get(0));
		String gstin = assigneeandgstin.get(1);
		String productid = productidtext.getText();
		ObservableList<PopUpChallan> chalanlist = DChalan.getSingeletonInstance().chalanDataLoad(productid, assigneeid);
		// setting chalanlist soon will be deprecated
		UTable.setPopupchallantablelist(chalanlist);
		UTable.getLoaderstage().close();
		// for loading receive data back
		UTable.setReceiveTextField(receivetext);
		UTable.setPaidtextfield(advancedpaidtext);
		UTable.setDuetext(duetext);
		System.out.println("Stage is about to set");
		Stage window = new Stage();
		UTable.setPopupstage(window);
		// When we call fxml loader then the intialize method is being called
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		window.setX(bounds.getMinX() + 100);
		window.setY(bounds.getMinY() + 90);
		window.setWidth((bounds.getWidth() * 90) / 100);
		window.setHeight((bounds.getHeight() * 70) / 100);
		window.setScene(scene);
		System.out.println("Utable set");
		window.initStyle(StageStyle.UNDECORATED);
		window.initOwner(UTable.getPrimarystage());
		window.initModality(Modality.WINDOW_MODAL);
		// UTable.setPopupstage(window);
		window.show();

	}

	@FXML
	void saveChalanData(ActionEvent event) throws SQLException, IOException, DocumentException, InterruptedException {
		new ProgressDemo().start();
		UTable.setAssigneename(assigneename.getText());
		UTable.setAmountpaid(advancedpaidtext.getText());
		ObservableList<Chalan> chalanlist = newchalantable.getItems();
		DataManipulation man = new DataManipulation();
		man.getPopUpWindowData(chalanlist);
		chalanlist.removeAll(chalanlist);
		UTable.getLoaderstage().close();
		Notification.dataSuccessfullySaved("Operation Successful",
				"Data Successfully Stored. Check PDF at C:\\Program Files\\IManage");
		assigneename.setText("");
		productidtext.setText("");
		issuetext.setText("");
		receivetext.setText("0");
		savechallandescription.setText("");
		advancedpaidtext.setText("");
		billdate.setValue(LocalDate.now());
		assigneename.setDisable(false);
		billdate.setDisable(false);
		assigneenamelabel.setText("");
		billdate.setValue(LocalDate.now());
	}

	@FXML
	void tabProductIDButton(ActionEvent event) {
		System.out.println("inside product id select button");
		new ProgressDemo().start();
		FXMLLoader myLoader = new FXMLLoader(getClass().getResource("SortAndFilter.fxml"));
		try {
			Parent loadScreen = (Parent) myLoader.load();
			Stage primarystage = UTable.getPrimarystage();
			Scene scene = new Scene(loadScreen);
			primarystage.setScene(scene);
			UTable.getLoaderstage().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			UTable.getLoaderstage().close();
			Notification.errorOccuredNotification();
			e.printStackTrace();
		}

	}

	@FXML
	private Button exportexcelbutton;

	public void saveExcelFile(File file) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet spreadsheet = workbook.createSheet("sample");

		Row row = spreadsheet.createRow(0);

		row.createCell(0).setCellValue("Name:");
		row.createCell(1).setCellValue(assigneenamelabel.getText());

		row.createCell(3).setCellValue("Bill Date:");
		row.createCell(4).setCellValue(new java.sql.Date(new Date().getTime()));

		row = spreadsheet.createRow(2);

		for (int j = 0; j < newchalantable.getColumns().size(); j++) {
			row.createCell(j).setCellValue(newchalantable.getColumns().get(j).getText());
		}

		for (int i = 3; i < newchalantable.getItems().size() + 3; i++) {
			row = spreadsheet.createRow(i + 1);
			for (int j = 0; j < newchalantable.getColumns().size(); j++) {
				if (newchalantable.getColumns().get(j).getCellData(i) != null) {
					row.createCell(j).setCellValue(newchalantable.getColumns().get(j).getCellData(i).toString());
				} else {
					row.createCell(j).setCellValue("");
				}
			}
		}

		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.close();

	}

	@FXML
	void clearMainPageDataButton(ActionEvent event) {
		assigneename.setDisable(false);
		billdate.setDisable(false);
		ObservableList<Chalan> chalanlist = newchalantable.getItems();
		chalanlist.removeAll(chalanlist);
		assigneename.setText("");
		productidtext.setText("");
		issuetext.setText("");
		receivetext.setText("");
		// duetext.setText("");
		advancedpaidtext.setText("");
		savechallandescription.setText("");
		billdate.setValue(null);

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
	

    @FXML
    void openNewAssigneeWindow(ActionEvent event) {
    	new ProgressDemo().start();
		FXMLLoader myLoader = new FXMLLoader(getClass().getResource("AssigneeWindow.fxml"));
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

	@FXML
	void addProductID(ActionEvent event) throws SQLException, IOException {
		String productid = productidtext.getText();
		new ProgressDemo().start();
		ObservableList<String> productidlist = DLoader.getSingeletonInstanceOfLoader().intialLoader().get(0);
		System.out.println("inside");
		if (productidlist.contains(productid)) {
			System.out.println("insideif");
			Notification.invalidInput("ProductId already present",
					"Product Id you are trying to add is already present");
			UTable.getLoaderstage().close();
		} else {
			System.out.println("insideelse");
			DChalan.getSingeletonInstance().insertNewProductID(productid);
			TextFields.bindAutoCompletion(productidtext, UTable.getIntialloaderproductid().add(productid));
			Notification.invalidInput("ProductId added", "Product Id has been added to database");
			UTable.getLoaderstage().close();
		}
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
		System.out.println("hello");

	}

	@FXML
	void productIdOnActionMethod(ActionEvent event) {
		System.out.println("this is the value" + productidtext.getText());
	}

	@FXML
	void receiveText(ActionEvent event) {

	}

	@FXML
	void exportExcel(ActionEvent event) {

	}
}
