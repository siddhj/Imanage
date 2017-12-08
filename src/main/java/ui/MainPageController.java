package ui;

import java.io.FileOutputStream;
import java.io.IOException;

import org.controlsfx.control.textfield.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.ProgressDemo;

import service.DataManipulation;
import service.MicroService;
import java.sql.SQLException;
import java.util.Date;

import bean.Assignee;
import bean.Chalan;
import bean.PopUpChallan;
import dao.DChalan;
import dao.DLoader;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.MultiScreen;
import service.Notification;
import service.Validation;
import utility.UTable;

public class MainPageController implements MultiScreen {

	@FXML
	private Label assigneenamelabel;

	@FXML
	private Button exlporeselection;
	
    @FXML
    private Button tabproductidbutton;

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
	private TableColumn<Chalan, String> totalpaidcolumn = new TableColumn<>("Total Paid");

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
		ObservableList<Chalan> allchalanfromtableview = newchalantable.getItems();
		System.out.println("inside remove row");
		allchalanfromtableview.forEach(c -> {
			System.out.println(c.getTotalpaid()+""+c.getTotalreceive()+"::"+chalan.getProductid());
			if (c.getProductid() == chalan.getProductid()) {
				allchalanfromtableview.remove(c);
			} else {
				System.out.println("there is no element selected");
			}

		});
	}
	ObservableList<ObservableList<String>> parentlist = FXCollections.observableArrayList();
	// TableColumn<Chalan, Integer> receiveitem = new TableColumn<Chalan,
	// Integer>("Product");
	//ObservableList<Chalan> listFromDb = FXCollections.observableArrayList();

	// why to create instance table column instead of intializing in intialize()
	// method
	@FXML
	public void initialize() throws SQLException, IOException {
		// receiveitem.setCellValueFactory(new
		// PropertyValueFactory<Chalan,Integer>("receive"));
		//
		// // callback functions are defined on the basis of the column and its
		// datatype
		// Callback<TableColumn<Chalan,Integer>, TableCell<Chalan,Integer>>
		// cellFactory
		// = new Callback<TableColumn<Chalan,Integer>,
		// TableCell<Chalan,Integer>>() {
		// public TableCell<Chalan,Integer> call(TableColumn<Chalan,Integer> p)
		// {
		// return new EditingCell();
		// }
		// };
		//
		// receiveitem.setCellFactory(cellFactory);
		// receiveitem.setOnEditCommit(new
		// EventHandler<TableColumn.CellEditEvent<Chalan, Integer>>() {
		// @Override
		// public void handle(TableColumn.CellEditEvent<Chalan, Integer> t) {
		// ((Chalan)
		// t.getTableView().getItems().get(t.getTablePosition().getRow()))
		// .setReceive(t.getNewValue());
		// }
		// });

		productidcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("productid"));
		namecolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("assigneeid"));
		issueitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("issue"));
		receiveitemcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("totalreceive"));
		duecolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("due"));
		paidcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("paid"));
		totalpaidcolumn.setCellValueFactory(new PropertyValueFactory<Chalan, String>("totalpaid"));
		// System.out.println("doen");
		// listFromDb = new DChalan().chalanDataLoad();
		// newchalantable.setItems(listFromDb);
		newchalantable.setEditable(true);
		// newchalantable.getColumns().addAll(receiveitem);

		receiveitemcolumn.setEditable(true);
		 parentlist = new DLoader().intialLoader();
		TextFields.bindAutoCompletion(assigneename, parentlist.get(1));
		TextFields.bindAutoCompletion(productidtext, parentlist.get(0));

		
		issuetext.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			  @Override public void handle(KeyEvent keyEvent) {
			    if (!"0123456789".contains(keyEvent.getCharacter())) {
			      keyEvent.consume();
			    }
			  }
			});
		paidtext.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			  @Override public void handle(KeyEvent keyEvent) {
			    if (!"0123456789".contains(keyEvent.getCharacter())) {
			      keyEvent.consume();
			    }
			  }
			});
		
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
		String name = assigneename.getText();
		int AssigneeID = new MicroService().assigneeIDRetrieve(name);
	
		if(!Validation.parentListNameValidation(parentlist.get(1), assigneename.getText()))
		{
			Notification.invalidInputName();
			return;
		}
		if(!Validation.parentListProductIDValidation(parentlist.get(0),productidtext.getText()))
		{
			Notification.invalidInputProductID();
			return;
		}
		try{
		Chalan chalan = new Chalan(productidtext.getText(), Integer.parseInt(issuetext.getText()),0,
				Integer.parseInt(issuetext.getText()), Integer.parseInt(paidtext.getText()), AssigneeID,
				UTable.getPopupchallantableviewdata(), UTable.getTotalpaid(), Integer.parseInt(receivetext.getText()));
		
		newchalantable.getItems().add(chalan);
		productidtext.setText("");
		issuetext.setText("");
		receivetext.setText("");
		// duetext.setText("");
		paidtext.setText("");
		assigneenamelabel.setText(assigneename.getText());
		}
		catch(NumberFormatException E){
		Notification.invalidInput();	
		}
		
	}

	MainScreenController screencontroller = new MainScreenController();

	@Override
	public void setScreenParent(MainScreenController screencontroller) {
		// TODO Auto-generated method stub
		this.screencontroller = screencontroller;
	}
	@FXML
	void exploreSelectionPopUpWindow(ActionEvent event) throws IOException {
		Chalan selectedchalan = newchalantable.getSelectionModel().getSelectedItem();
		ObservableList<PopUpChallan> popupchalan = selectedchalan.getPopupchallantableview();
		// setting chalanlist soon will be deprecated
		UTable.setChallanlist(popupchalan);

		// for loading receive data back
		UTable.setReceiveTextField(receivetext);
		UTable.setPaidtextfield(paidtext);
		UTable.setDuetext(duetext);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage window = new Stage();
		window.setScene(scene);
		Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        window.setX(bounds.getMinX()+60); 
        window.setY(bounds.getMinY()+70);
        window.setWidth((bounds.getWidth()*80)/100);
        window.setHeight((bounds.getHeight()*60)/100);
		window.initStyle(StageStyle.UNDECORATED);
		window.show();
	}

	@FXML
	private Button newwindow;

	@FXML
	void popupWindow(ActionEvent event) throws IOException, SQLException {
		System.out.println("this is the receive button");
		new ProgressDemo().start();
		int assigneeid = new MicroService().assigneeIDRetrieve(assigneename.getText());
		String productid = productidtext.getText();
		ObservableList<PopUpChallan> chalanlist = new DChalan().chalanDataLoad(productid, assigneeid);
		// setting chalanlist soon will be deprecated
		UTable.setChallanlist(chalanlist);
		// for loading receive data back
		UTable.setReceiveTextField(receivetext);
		UTable.setPaidtextfield(paidtext);
		UTable.setDuetext(duetext);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Popup.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage window = new Stage();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        window.setX(bounds.getMinX()+60); 
        window.setY(bounds.getMinY()+70);
        window.setWidth((bounds.getWidth()*80)/100);
        window.setHeight((bounds.getHeight()*60)/100);
		window.setScene(scene);
		window.initStyle(StageStyle.UNDECORATED);
		window.show();

	}

	@FXML
	void saveChalanData(ActionEvent event) throws SQLException, IOException {
		new ProgressDemo().start();
		ObservableList<Chalan> chalanlist = newchalantable.getItems();
		DataManipulation man = new DataManipulation();
		man.getPopUpWindowData(chalanlist);
		chalanlist.removeAll(chalanlist);
		UTable.getStage().close();
		Notification.dataSuccessfullySaved();

		assigneename.setText("");
		productidtext.setText("");
		issuetext.setText("");
		receivetext.setText("");
		//duetext.setText("");
		paidtext.setText("");
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
         } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
    @FXML
    private Button exportexcelbutton;
    @FXML
    public void exportExcel() throws IOException{
    	System.out.println("export excel"); 
    	Workbook workbook = new HSSFWorkbook();
         Sheet spreadsheet = workbook.createSheet("sample");
                                             
          // Create cell style 
//         CellStyle style=workbook.createCellStyle();;
       //  style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//         style.setAlignment(CellStyle.ALIGN_CENTER);
//         style.setFillBackgroundColor(new HSSFColor.RED().getIndex());
         // Setting font to style
        
         
         Row row = spreadsheet.createRow(0);
         
         row.createCell(0).setCellValue("Name:");
//         row.getCell(0).setCellStyle(style);
         row.createCell(1).setCellValue(assigneenamelabel.getText());
     
         row.createCell(3).setCellValue("Bill Date:");
         row.createCell(4).setCellValue(new java.sql.Date(new Date().getTime()));
         
         row = spreadsheet.createRow(1);

         for (int j = 0; j < newchalantable.getColumns().size(); j++) {
             row.createCell(j).setCellValue(newchalantable.getColumns().get(j).getText());
         }

         for (int i = 0; i < newchalantable.getItems().size(); i++) {
             row = spreadsheet.createRow(i + 1);
             for (int j = 0; j < newchalantable.getColumns().size(); j++) {
                 if(newchalantable.getColumns().get(j).getCellData(i) != null) { 
                     row.createCell(j).setCellValue(newchalantable.getColumns().get(j).getCellData(i).toString()); 
                 }
                 else {
                     row.createCell(j).setCellValue("");
                 }   
             }
         }

         FileOutputStream fileOut = new FileOutputStream("E:\\workbook.xls");
         workbook.write(fileOut);
         fileOut.close();

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
	void productId(ActionEvent event) {

	}

	@FXML
	void receiveText(ActionEvent event) {

	}
}
