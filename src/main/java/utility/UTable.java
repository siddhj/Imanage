package utility;

import bean.Assignee;

import bean.Chalan;
import bean.ChallanDetailBean;
import bean.PopUpChallan;
import bean.SortAndFilterBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public final class UTable {

	public static Stage loaderstage;
	public static Stage primarystage;
	public static Stage applicationloaderstage;

	private static ObservableList<Assignee> assigneelist = FXCollections.observableArrayList();
	public static ObservableList<PopUpChallan> popupchallantablelist = FXCollections.observableArrayList();
	public static ObservableList<ChallanDetailBean> challandetaillist = FXCollections.observableArrayList();
	public static ObservableList<Chalan> mainpagechalanlist = FXCollections.observableArrayList();
	public static ObservableList<SortAndFilterBean> sortandfilterwindowlist = FXCollections.observableArrayList();

	public static ObservableList<String> intialloaderproductid = FXCollections.observableArrayList();
	public static ObservableList<String> intialloaderassigneename = FXCollections.observableArrayList();

	public static TableView<PopUpChallan> popuptableview;
	public static TableView<Chalan> mainpagetableview;

	public static Chalan selectedchallanfrommainpage;

	public static TextField duetext;
	public static TextField paidtextfield;
	public static TextField textfield;

	public static String sortandfilterassigneename;
	public static String sortandfilterproductid;
	public static String sortandfilterfromdate;
	public static String assigneename;
	public static String amountpaid;

	public static int sortandfiltertotalissue,sortandfiltertotalreceive,sortandfiltertotalreceivedue;

	public static Stage getApplicationloaderstage() {
		return applicationloaderstage;
	}

	public static void setApplicationloaderstage(Stage applicationloaderstage) {
		UTable.applicationloaderstage = applicationloaderstage;
	}

	public static String getAssigneename() {
		return assigneename;
	}

	public static void setAssigneename(String assigneename) {
		UTable.assigneename = assigneename;
	}

	public static ObservableList<String> getIntialloaderproductid() {
		return intialloaderproductid;
	}

	public static void setIntialloaderproductid(ObservableList<String> intialloaderproductid) {
		UTable.intialloaderproductid = intialloaderproductid;
	}

	public static ObservableList<String> getIntialloaderassigneename() {
		return intialloaderassigneename;
	}

	public static void setIntialloaderassigneename(ObservableList<String> intialloaderassigneename) {
		UTable.intialloaderassigneename = intialloaderassigneename;
	}

	public static String getAmountpaid() {
		return amountpaid;
	}

	public static void setAmountpaid(String amountpaid) {
		UTable.amountpaid = amountpaid;
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

	public static ObservableList<PopUpChallan> getPopupchallantablelist() {
		return popupchallantablelist;
	}

	public static void setPopupchallantablelist(ObservableList<PopUpChallan> popupchallantablelist) {
		UTable.popupchallantablelist = popupchallantablelist;
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

	static long aggregatechallanid;

	public static long getAggregatechallanid() {
		return aggregatechallanid;
	}

	public static void setAggregatechallanid(long aggregatechallanid) {
		UTable.aggregatechallanid = aggregatechallanid;
	}

}
