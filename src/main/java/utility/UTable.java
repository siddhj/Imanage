package utility;

import bean.Assignee;
import bean.Chalan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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



}
