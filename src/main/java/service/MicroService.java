package service;

import bean.Assignee;
import dao.DLoader;
import javafx.collections.ObservableList;

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
		return 0;
	}
}
