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
	private static ObservableList<PopUpChallan> challanlist = FXCollections.observableArrayList();
	
	public static Stage primarystage;
	
	public static Stage getPrimarystage() {
		return primarystage;
	}

	public static void setPrimarystage(Stage primarystage) {
		UTable.primarystage = primarystage;
	}

	public static ObservableList<PopUpChallan> getChallanlist() {
		return challanlist;
	}

	public static void setChallanlist(ObservableList<PopUpChallan> challanlist) {
		UTable.challanlist = challanlist;
	}

	private static ObservableList<Assignee> assigneelist = FXCollections.observableArrayList();

	public static ObservableList<Assignee> getAssigneelist() {
		return assigneelist;
	}

	public static void setAssigneelist(ObservableList<Assignee> assigneelist) {
		UTable.assigneelist = assigneelist;
	}

	public static TextField textfield;

	public static void setReceiveTextField(TextField textfield) {
		UTable.textfield = textfield;
	}

	public static TextField getReceiveTextField() {
		return textfield;
	}

	public static TextField paidtextfield;

	public static TextField getPaidtextfield() {
		return paidtextfield;
	}

	public static void setPaidtextfield(TextField paidtextfield) {
		UTable.paidtextfield = paidtextfield;
	}

	public static TextField duetext;

	public static TextField getDuetext() {
		return duetext;
	}

	public static void setDuetext(TextField duetext) {
		UTable.duetext = duetext;
	}

	public static TableView<PopUpChallan> popuptableview;

	public static TableView<PopUpChallan> getPopuptableview() {
		return popuptableview;
	}

	public static void setPopuptableview(TableView<PopUpChallan> popuptableview) {
		UTable.popuptableview = popuptableview;
	}

	public static TableColumn paidcolumnpopuptable;

	public static TableColumn getPaidcolumnpopuptable() {
		return paidcolumnpopuptable;
	}

	public static void setPaidcolumnpopuptable(TableColumn paidcolumnpopuptable) {
		UTable.paidcolumnpopuptable = paidcolumnpopuptable;
	}

	public static ObservableList<PopUpChallan> popupchallantableviewdata = FXCollections.observableArrayList();

	public static ObservableList<PopUpChallan> getPopupchallantableviewdata() {
		return popupchallantableviewdata;
	}

	public static void setPopupchallantableviewdata(ObservableList<PopUpChallan> popupchallantableviewdata) {
		UTable.popupchallantableviewdata = popupchallantableviewdata;
	}

	public static int totalpaid;

	public static int getTotalpaid() {
		return totalpaid;
	}

	public static void setTotalpaid(int totalpaid) {
		UTable.totalpaid = totalpaid;
	}

	public static Stage stage;
	public static Stage getStage() {
		return stage;
	}


	public static void setStage(Stage stage) {
		UTable.stage = stage;
	}
	
	public static ObservableList<ChallanDetailBean> challandetaillist = FXCollections.observableArrayList();
	public static ObservableList<ChallanDetailBean> getChallandetaillist() {
		return challandetaillist;
	}

	public static void setChallandetaillist(ObservableList<ChallanDetailBean> challandetaillist) {
		UTable.challandetaillist = challandetaillist;
	}

}
