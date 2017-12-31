package bean;

import java.time.LocalDate;
import java.util.Date;

import javafx.collections.ObservableList;

public class Chalan{
	
	private int assigneeid,issue,receive,due,challanid,amountpaid,totalreceive;//totalpaid,advancepaid
	private String productid,comment;
	private LocalDate billdate;
	private ObservableList<PopUpChallan> popupchallantableview;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getTotalreceive() {
		return totalreceive;
	}
	public int getAmountpaid() {
		return amountpaid;
	}
	public void setAmountpaid(int amountpaid) {
		this.amountpaid = amountpaid;
	}
	public void setTotalreceive(int totalreceive) {
		this.totalreceive = totalreceive;
	}

	public ObservableList<PopUpChallan> getPopupchallantableview() {
		return popupchallantableview;
	}
	public void setPopupchallantableview(ObservableList<PopUpChallan> popupchallantableview) {
		this.popupchallantableview = popupchallantableview;
	}
	public int getChallanid() {
		return challanid;
	}
	public void setChallanid(int challanid) {
		this.challanid = challanid;
	}
	
	public LocalDate getBilldate() {
		return billdate;
	}
	public void setBilldate(LocalDate billdate) {
		this.billdate = billdate;
	}

	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	public int getReceive() {
		return receive;
	}
	public void setReceive(int receive) {
		this.receive = receive;
	}
	public int getDue() {
		return due;
	}
	public void setDue(int due) {
		this.due = due;
	}
	
	public Chalan(){
		
	}
	
	@Override
	public String toString() {
		return "Chalan [assigneeid=" + assigneeid + ", issue=" + issue + ", receive=" + receive + ", due=" + due
				+ ", challanid=" + challanid + ", amountpaid=" + amountpaid + ", totalreceive=" + totalreceive
				+ ", productid=" + productid + ", comment=" + comment + ", billdate=" + billdate
				+ ", popupchallantableview=" + popupchallantableview + "]";
	}
	public int getAssigneeid() {
		return assigneeid;
	}
	public void setAssigneeid(int assigneeid) {
		this.assigneeid = assigneeid;
	}
	public Chalan(String productid, int issue, int receive, int due, /*int advancepaid,*/ int assigneeid,ObservableList<PopUpChallan> popupchallantableview,int totalreceive,LocalDate billdate,String comment,int amountpaid) {
		super();
		this.productid = productid;
		this.issue = issue;
		this.receive = receive;
		this.due = due;
		this.assigneeid = assigneeid;
		this.popupchallantableview = popupchallantableview;
		this.amountpaid=amountpaid;
		this.totalreceive=totalreceive;
		this.billdate=billdate;
		this.comment = comment;
	}
	
	
  }
