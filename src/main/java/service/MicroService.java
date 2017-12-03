package service;

import bean.Assignee;
import bean.Chalan;
import dao.DLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.UTable;

public class MicroService {

	public int assigneeIDRetrieve(String name) {
		ObservableList<Assignee> assigneelist = DLoader.assigneelist;
		int assigneeid = 0;
		for (Assignee al : assigneelist) {
			if (al.getFirstname().equals(name)) {
				assigneeid = al.getAssigneeid();
				break;
			}
		}
		return assigneeid;
	}

	public int getTotalReceiveFromPopUp(ObservableList<Chalan> chalanlist) {
		int totalreceive = 0;
		for (Chalan c : chalanlist) {
			totalreceive += c.getReceive();
		}
		return totalreceive;
	}

	public static ObservableList<Chalan> updatePopUpTableView(ObservableList<Chalan> popuptable, int newreceive,
			int challanid) {
		ObservableList<Chalan> newchalan = FXCollections.observableArrayList();
		for (Chalan c : popuptable) {
			if (c.getChallanid() == challanid) {
				int issue = c.getIssue();
				int olddue = c.getDue();
				int due = issue - newreceive;
				if (newreceive > issue) {
					Notification.popupWindowInvalidReceiveValueGreaterThenIssue();
					due = c.getDue();
					newreceive = c.getReceive();
				} else if (due < 0) {
					Notification.popupWindowInvalidValueLessThenZero();
					due = c.getDue();
					newreceive = c.getReceive();
				}
				c.setDue(due);
				c.setReceive(newreceive);
				newchalan.add(c);
			} else {
				newchalan.add(c);
			}
		}
		return newchalan;
	}

	public static ObservableList<Chalan> paidUpdatePopUpTableView(ObservableList<Chalan> popuptable, int newpaid,
			int challanid) {
		ObservableList<Chalan> newchalan = FXCollections.observableArrayList();
		for (Chalan c : popuptable) {
			if (c.getChallanid() == challanid) {
				int oldpaid = c.getPaid();
				if(newpaid>c.getReceive())
				{
				newpaid = oldpaid;
				Notification.popupWindowInvalidPaidValueGreaterThenReceive();
				}
				c.setPaid(newpaid);
				newchalan.add(c);
			} else {
				newchalan.add(c);
				// System.out.println(
				// "else" + c.getChallanid() + ":" + c.getIssue() + ":" +
				// c.getReceive() + ":" + c.getDue());
			}
		}
		return newchalan;
	}

}
