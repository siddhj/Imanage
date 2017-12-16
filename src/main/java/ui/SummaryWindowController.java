package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import bean.PopUpChallan;
import bean.SortAndFilterBean;
import bean.SummaryBean;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import utility.UTable;

public class SummaryWindowController {

	@FXML
	private TableView<SortAndFilterBean> summarywindowtable = new TableView<>();

	private TableColumn<SortAndFilterBean, String> assigneenamecolumn = new TableColumn<>("Name");
	private TableColumn<SortAndFilterBean, String> productidcolumn = new TableColumn<>("ProductID");
	private TableColumn<SortAndFilterBean, LocalDate> billdatecolumn = new TableColumn<>("Bill Date");

	private TableColumn<SortAndFilterBean, SimpleIntegerProperty> issuecolumn = new TableColumn<>("Qty Paid");
	private TableColumn<SortAndFilterBean, SimpleIntegerProperty> receivecolumn = new TableColumn<>("Receive Qty");
	private TableColumn<SortAndFilterBean, SimpleIntegerProperty> receiveduecolumn = new TableColumn<>("Receive Qty Due");
	private TableColumn<SortAndFilterBean, SimpleIntegerProperty> paidcolumn = new TableColumn<>("Paid Qty");
	private TableColumn<SortAndFilterBean, SimpleIntegerProperty> paidduecolumn = new TableColumn<>("Paid Qty Due");

	@FXML
	private HBox VBox_HBox_First = new HBox();

	@FXML
	private Label DateLabel = new Label();

	@FXML
	private Label DateLabelDateHolder = new Label();

	@FXML
	private HBox VBox_HBox_Second = new HBox();

	private HBox VBox_HBox_Second_AssigneeName = new HBox();
	private HBox VBox_HBox_Second_ProductID = new HBox();
	private HBox VBox_HBox_Second_DateFrom = new HBox();
	private HBox VBox_HBox_Second_DateUpto = new HBox();

	@FXML
	public void initialize() throws SQLException, IOException {

		// condition to find all the variable on the basis of which data is
		// filtered out.

		productidcolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, String>("productid"));
		assigneenamecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, String>("assigneename"));
		billdatecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, LocalDate>("billdate"));
		
		/* Code for Second HBox in VBox in FXML */
		// if AssigneeName if present in filter
		if (!UTable.getSortandfilterassigneename().equals("")) {
			Label AssigneeNameLabel = new Label("Name");
			Label AssigneeNameLabelNameHolder = new Label(UTable.getSortandfilterassigneename());
			VBox_HBox_Second_AssigneeName.getChildren().addAll(AssigneeNameLabel, AssigneeNameLabelNameHolder);
			VBox_HBox_Second.getChildren().add(VBox_HBox_Second_AssigneeName);
		} else {
			summarywindowtable.getColumns().add(assigneenamecolumn);
		}

		// if ProductId is present in filter
		if (!UTable.getSortandfilterproductid().equals("")) {
			Label ProductIDLabel = new Label("Product ID");
			Label ProductIDLabelProductIDHolder = new Label(UTable.getSortandfilterproductid());
			VBox_HBox_Second_ProductID.getChildren().addAll(ProductIDLabel, ProductIDLabelProductIDHolder);
			VBox_HBox_Second.getChildren().add(VBox_HBox_Second_ProductID);
		} else {
			summarywindowtable.getColumns().add(productidcolumn);
		}
		
		//if DateFrom is present in filter
		if (!UTable.getSortandfilterfromdate().equals("")) {
			Label DateFromLabel = new Label("Date From");
			Label DateFromLabelDateFromHolder = new Label(UTable.getSortandfilterfromdate());
			VBox_HBox_Second_DateFrom.getChildren().addAll(DateFromLabel, DateFromLabelDateFromHolder);
			VBox_HBox_Second.getChildren().add(VBox_HBox_Second_DateFrom);
		} else {
			summarywindowtable.getColumns().add(billdatecolumn);
		}

		//challanidcolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("challanid"));
		issuecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("issueitem"));
		receivecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("receiveitem"));
		receiveduecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("receivedueitem"));
		paidcolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("paiditem"));	
		paidduecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, SimpleIntegerProperty>("paiditemdue"));
		billdatecolumn.setCellValueFactory(new PropertyValueFactory<SortAndFilterBean, LocalDate>("billdate"));
	
		summarywindowtable.getColumns().addAll(issuecolumn, receivecolumn, receiveduecolumn, paidcolumn, paidduecolumn);
		summarywindowtable.setItems(UTable.getSortandfilterwindowlist());
	
	}
}
