package utility;

import java.time.LocalDate;
import java.util.Date;

import bean.Assignee;
import bean.Chalan;
import bean.ChallanDetailBean;
import bean.PopUpChallan;
import bean.SortAndFilterBean;
import dao.DChalan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public final class UTable {
	
	public static Stage loaderstage;
	public static Stage primarystage;
	public static Stage popupstage;

	private static ObservableList<Assignee> assigneelist = FXCollections.observableArrayList();
	public static ObservableList<PopUpChallan> popupchallantablelist = FXCollections.observableArrayList();
	public static ObservableList<ChallanDetailBean> challandetaillist = FXCollections.observableArrayList();
	public static ObservableList<Chalan> mainpagechalanlist = FXCollections.observableArrayList();
	public static ObservableList<SortAndFilterBean> sortandfilterwindowlist = FXCollections.observableArrayList();

	public static TableView<PopUpChallan> popuptableview;
	public static TableView<Chalan> mainpagetableview;

	public static Chalan selectedchallanfrommainpage;

	public static TextField duetext;
	public static TextField paidtextfield;
	public static TextField textfield;

//	public static TextField sortandfilterassigneename;
//	public static TextField sortandfilterproductid;
//	public static Date sortandfilterdatefrom;
//	public static Date sortandfilterdateto;
//	
	public static String sortandfilterassigneename;
	public static String sortandfilterproductid;
	public static String sortandfilterfromdate;
	
	public static TableColumn paidcolumnpopuptable;

	public static int indexofselectedrow,totalpaid;
//	totallissueitem,totalreceiveitem,totalreceivedueitem,totalpaiditem,totalpaiditemdue
	public static int sortandfiltertotalissue,sortandfiltertotalpaid,sortandfiltertotalreceive,sortandfiltertotalreceivedue,sortandfilterpaiddue;

	
	public static int getSortandfiltertotalpaid() {
		return sortandfiltertotalpaid;
	}

	public static void setSortandfiltertotalpaid(int sortandfiltertotalpaid) {
		UTable.sortandfiltertotalpaid = sortandfiltertotalpaid;
	}

	public static int getSortandfiltertotalreceive() {
		return sortandfiltertotalreceive;
	}

	public static void setSortandfiltertotalreceive(int sortandfiltertotalreceive) {
		UTable.sortandfiltertotalreceive = sortandfiltertotalreceive;
	}

	public static int getSortandfiltertotalreceivedue() {
		return sortandfiltertotalreceivedue;
	}

	public static void setSortandfiltertotalreceivedue(int sortandfiltertotalreceivedue) {
		UTable.sortandfiltertotalreceivedue = sortandfiltertotalreceivedue;
	}

	public static int getSortandfilterpaiddue() {
		return sortandfilterpaiddue;
	}

	public static void setSortandfilterpaiddue(int sortandfilterpaiddue) {
		UTable.sortandfilterpaiddue = sortandfilterpaiddue;
	}

	public static int getSortandfiltertotalissue() {
		return sortandfiltertotalissue;
	}

	public static void setSortandfiltertotalissue(int sortandfiltertotalissue) {
		UTable.sortandfiltertotalissue = sortandfiltertotalissue;
	}

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
	
	public static Stage getLoaderstage() {
		return loaderstage;
	}

	public static void setLoaderstage(Stage loaderstage) {
		UTable.loaderstage = loaderstage;
	}

	public static ObservableList<ChallanDetailBean> getChallandetaillist() {
		return challandetaillist;
	}

	public static void setChallandetaillist(ObservableList<ChallanDetailBean> challandetaillist) {
		UTable.challandetaillist = challandetaillist;
	}
	
	public static Stage getPopupstage() {
		return popupstage;
	}

	public static void setPopupstage(Stage popupstage) {
		UTable.popupstage = popupstage;
	}

	public static String getSortandfilterassigneename() {
		return sortandfilterassigneename;
	}

	public static void setSortandfilterassigneename(String sortandfilterassigneename) {
		UTable.sortandfilterassigneename = sortandfilterassigneename;
	}

	public static String getSortandfilterproductid() {
		return sortandfilterproductid;
	}

	public static void setSortandfilterproductid(String sortandfilterproductid) {
		UTable.sortandfilterproductid = sortandfilterproductid;
	}

	public static String getSortandfilterfromdate() {
		return sortandfilterfromdate;
	}

	public static void setSortandfilterfromdate(String sortandfilterfromdate) {
		UTable.sortandfilterfromdate = sortandfilterfromdate;
	}

	public static ObservableList<SortAndFilterBean> getSortandfilterwindowlist() {
		return sortandfilterwindowlist;
	}

	public static void setSortandfilterwindowlist(ObservableList<SortAndFilterBean> sortandfilterwindowlist) {
		UTable.sortandfilterwindowlist = sortandfilterwindowlist;
	}


}
