package service;

import bean.Assignee;
import bean.Chalan;
import dao.DLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.UTable;

public class MicroService {

	public int assigneeIDRetrieve(String name)
	{
		ObservableList<Assignee> assigneelist = DLoader.assigneelist;
		int assigneeid =0;
		for (Assignee al : assigneelist) {
			if (al.getFirstname().equals(name)) {
				assigneeid = al.getAssigneeid();
				break;
			}
		}
		return assigneeid;
	}
	
	public int getTotalReceiveFromPopUp(ObservableList<Chalan> chalanlist){
			int totalreceive=0;
		for(Chalan c:chalanlist)
		{
			totalreceive+=c.getReceive();
		}
		return totalreceive;
	}
	
	public static ObservableList<Chalan> updatePopUpTableView(ObservableList<Chalan> popuptable, int newreceive){
		ObservableList<Chalan> newchalan = FXCollections.observableArrayList();
		for(Chalan c:popuptable)
		{	
			int issue = c.getIssue();
			int due =issue-newreceive;
			c.setDue(due);
			c.setReceive(newreceive);
			newchalan.add(c);
		}
		
		return newchalan;
	}
}
