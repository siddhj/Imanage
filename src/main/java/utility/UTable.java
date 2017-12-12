package utility;

import bean.Assignee;
import bean.Chalan;
import bean.ChallanDetailBean;
import bean.PopUpChallan;
import dao.DChalan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public final class UTable {
	
	public static Stage stage;
	public static Stage primarystage;
	
	private static ObservableList<Assignee> assigneelist = FXCollections.observableArrayList();
	public static ObservableList<PopUpChallan> popupchallantablelist = FXCollections.observableArrayList();
	public static ObservableList<ChallanDetailBean> challandetaillist = FXCollections.observableArrayList();
	public static ObservableList<Chalan> mainpagechalanlist = FXCollections.observableArrayList();
//	private static ObservableList<PopUpChallan> challanlist = FXCollections.observableArrayList();

	public static TableView<PopUpChallan> popuptableview;
	public static TableView<Chalan> mainpagetableview;

	public static Chalan selectedchallanfrommainpage;

	public static TextField duetext;
	public static TextField paidtextfield;
	public static TextField textfield;
	
	public static TableColumn paidcolumnpopuptable;

	public static int indexofselectedrow;
	public static int totalpaid;

	
	public static TableView<Chalan> getMainpagetableview() {
		return mainpagetableview;
	}

	public static void setMainpagetableview(TableView<Chalan> mainpagetableview) {
		UTable.mainpagetableview = mainpagetableview;
	}
	
	public static Chalan getSelectedchallanfrommainpage() {
		return selectedchallanfrommainpage;
	}

	public static void setSelectedchallanfrommainpage(Chalan selectedchallanfrommainpage) {
		UTable.selectedchallanfrommainpage = selectedchallanfrommainpage;
	}

	public static int getIndexofselectedrow() {
		return indexofselectedrow;
	}

	public static void setIndexofselectedrow(int indexofselectedrow) {
		UTable.indexofselectedrow = indexofselectedrow;
	}

	public static ObservableList<Chalan> getMainpagechalanlist() {
		return mainpagechalanlist;
	}

	public static void setMainpagechalanlist(ObservableList<Chalan> mainpagechalanlist) {
		UTable.mainpagechalanlist = mainpagechalanlist;
	}

	public static Stage getPrimarystage() {
		return primarystage;
	}

	public static void setPrimarystage(Stage primarystage) {
		UTable.primarystage = primarystage;
	}

//	public static ObservableList<PopUpChallan> getChallanlist() {
//		return challanlist;
//	}
//
//	public static void setChallanlist(ObservableList<PopUpChallan> challanlist) {
//		UTable.challanlist = challanlist;
//	}


	public static ObservableList<Assignee> getAssigneelist() {
		return assigneelist;
	}

	public static void setAssigneelist(ObservableList<Assignee> assigneelist) {
		UTable.assigneelist = assigneelist;
	}

	public static void setReceiveTextField(TextField textfield) {
		UTable.textfield = textfield;
	}

	public static TextField getReceiveTextField() {
		return textfield;
	}


	public static TextField getPaidtextfield() {
		return paidtextfield;
	}

	public static void setPaidtextfield(TextField paidtextfield) {
		UTable.paidtextfield = paidtextfield;
	}


	public static TextField getDuetext() {
		return duetext;
	}

	public static void setDuetext(TextField duetext) {
		UTable.duetext = duetext;
	}


	public static TableView<PopUpChallan> getPopuptableview() {
		return popuptableview;
	}

	public static void setPopuptableview(TableView<PopUpChallan> popuptableview) {
		UTable.popuptableview = popuptableview;
	}


	public static TableColumn getPaidcolumnpopuptable() {
		return paidcolumnpopuptable;
	}

	public static void setPaidcolumnpopuptable(TableColumn paidcolumnpopuptable) {
		UTable.paidcolumnpopuptable = paidcolumnpopuptable;
	}

	public static ObservableList<PopUpChallan> getPopupchallantablelist() {
		return popupchallantablelist;
	}

	public static void setPopupchallantablelist(ObservableList<PopUpChallan> popupchallantablelist) {
		UTable.popupchallantablelist = popupchallantablelist;
	}

	public static int getTotalpaid() {
		return totalpaid;
	}

	public static void setTotalpaid(int totalpaid) {
		UTable.totalpaid = totalpaid;
	}

	public static Stage getStage() {
		return stage;
	}


	public static void setStage(Stage stage) {
		UTable.stage = stage;
	}
	

	
	public static ObservableList<ChallanDetailBean> getChallandetaillist() {
		return challandetaillist;
	}

	public static void setChallandetaillist(ObservableList<ChallanDetailBean> challandetaillist) {
		UTable.challandetaillist = challandetaillist;
	}

}
