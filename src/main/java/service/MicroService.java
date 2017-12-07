package service;

import java.time.LocalDate;

import bean.Assignee;
import bean.Chalan;
import bean.PopUpChallan;
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

	public int getTotalReceiveFromPopUp(ObservableList<PopUpChallan> challanlist) {
		int totalreceive = 0;
		for (PopUpChallan c : challanlist) {
			totalreceive += c.getCurrentreceive();
		}
		return totalreceive;
	}

	public int getTotalPaidFromPopUp(ObservableList<PopUpChallan> challanlist) {
		int totalpaid = 0;
		for (PopUpChallan c : challanlist) {
			totalpaid += c.getCurrentpaid();
		}
		return totalpaid;
	}

	public int getTotalDueFromPopUp(ObservableList<PopUpChallan> chalanlist) {
		int totaldue = 0;
		for (PopUpChallan c : chalanlist) {
			totaldue += c.getPastreceivedue();
		}
		return totaldue;
	}

	public static int sumReceiveFromPopUp(int pastreceive, int currentreceive) {
		return pastreceive + currentreceive;
	}

	public static int sumPaidFromPopUp(int pastpaid, int currentpaid) {
		return pastpaid + currentpaid;
	}

	public static int sumDueFromPopUp(int issue, int totalreceive) {
		return issue - totalreceive;
	}

	public static ObservableList<PopUpChallan> updatePopUpTableView(ObservableList<PopUpChallan> popuptable,
			int newreceive, int challanid) {
		ObservableList<PopUpChallan> newchalan = FXCollections.observableArrayList();
		for (PopUpChallan c : popuptable) {
			if (c.getChallanid() == challanid) {
				// int issue = c.getIssue();
				// int olddue = c.getDue();
				// int due = issue - newreceive;
				// if (newreceive > issue) {
				// Notification.popupWindowInvalidReceiveValueGreaterThenIssue();
				// due = c.getDue();
				// newreceive = c.getReceive();
				// } else if (due < 0) {
				// Notification.popupWindowInvalidValueLessThenZero();
				// due = c.getDue();
				// newreceive = c.getReceive();
				// }
				// c.setDue(due);
				// c.setReceive(newreceive);
				// newchalan.add(c);
			} else {
				// newchalan.add(c);
			}
		}
		return newchalan;
	}

	public static ObservableList<PopUpChallan> paidUpdatePopUpTableView(ObservableList<PopUpChallan> popuptable,
			int newpaid, int challanid) {
		ObservableList<PopUpChallan> newchalan = FXCollections.observableArrayList();
		for (PopUpChallan c : popuptable) {
			if (c.getChallanid() == challanid) {
				// int oldpaid = c.getPaid();
				// if(newpaid>c.getReceive())
				// {
				// newpaid = oldpaid;
				// Notification.popupWindowInvalidPaidValueGreaterThenReceive();
				// }
				// c.setPaid(newpaid);
				// newchalan.add(c);
			} else {
				// newchalan.add(c);
				// System.out.println(
				// "else" + c.getChallanid() + ":" + c.getIssue() + ":" +
				// c.getReceive() + ":" + c.getDue());
			}
		}
		return newchalan;
	}

	public static String nullVarifierStringForDao(String valuetocheck) {
		String defaultvalue = "";
		if (valuetocheck != null) {
			defaultvalue = defaultvalue + valuetocheck;
		}
		return defaultvalue;
	}
	
	public static String nullVarifierFromDateForDao(LocalDate fromdate) {
		String defaultvalue = "";
		if (fromdate != null) {
			defaultvalue = defaultvalue + fromdate;
		}
		return defaultvalue;
	}
	
	public static String nullVarifierToDateForDao(LocalDate todate) {
		String defaultvalue = "";
		if (todate != null) {
			defaultvalue = defaultvalue + todate;
		}else{
			defaultvalue="3099-12-12";
		}
		return defaultvalue;
	}

}
