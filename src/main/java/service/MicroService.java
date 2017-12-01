package service;

import bean.Assignee;
import bean.Chalan;
import dao.DLoader;
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
			System.out.println(c.getReceive());
			totalreceive+=c.getReceive();
		}
		System.out.println("total receive"+totalreceive);
		return totalreceive;
	}
}
