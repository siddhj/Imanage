package utility;

import bean.Assignee;
import bean.Chalan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public final class UTable {
private static ObservableList<Chalan> challanlist = FXCollections.observableArrayList();
//private static ObservableList<Product> productlist = FXCollections.observableArrayList();
private static ObservableList<Assignee> assigneelist = FXCollections.observableArrayList();

public static ObservableList<Chalan> getChallanlist() {
	return challanlist;
}
public static void setChallanlist(ObservableList<Chalan> challanlist) {
	challanlist.forEach(c->{System.out.println(c.getIssue()+c.getDue());});
	UTable.challanlist = challanlist;
}
public static ObservableList<Assignee> getAssigneelist() {
	return assigneelist;
}
public static void setAssigneelist(ObservableList<Assignee> assigneelist) {
	UTable.assigneelist = assigneelist;
}

public static TextField textfield;
public static void setReceiveTextField(TextField textfield)
{
	UTable.textfield = textfield;
}
public static TextField getReceiveTextField(){
	return textfield;
}

public static TextField paidtextfield;
public static TextField getPaidtextfield() {
	return paidtextfield;
}
public static void setPaidtextfield(TextField paidtextfield) {
	UTable.paidtextfield = paidtextfield;
}

public static TableView <Chalan> popuptableview;
public static TableView<Chalan> getPopuptableview() {
	return popuptableview;
}
public static void setPopuptableview(TableView<Chalan> popuptableview) {
	System.out.println("inside UTable");
	UTable.popuptableview = popuptableview;
}

public static TableColumn paidcolumnpopuptable;

public static TableColumn getPaidcolumnpopuptable() {
	return paidcolumnpopuptable;
}
public static void setPaidcolumnpopuptable(TableColumn paidcolumnpopuptable) {
	UTable.paidcolumnpopuptable = paidcolumnpopuptable;
}


}
