package bean;

import java.util.Date;

import javafx.collections.ObservableList;

public class Chalan{
	
	private int assigneeid,issue,receive,due,paid,challanid,totalpaid,totalreceive;
	public int getTotalpaid() {
		return totalpaid;
	}
	public void setTotalpaid(int totalpaid) {
		this.totalpaid = totalpaid;
	}
	public int getTotalreceive() {
		return totalreceive;
	}
	public void setTotalreceive(int totalreceive) {
		this.totalreceive = totalreceive;
	}
	private String productid;
	private Date billdate;
	private ObservableList<PopUpChallan> popupchallantableview;
	
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
public Chalan(String productid, int issue, int receive, int due, int paid, int assigneeid, Date billdate) {
		super();
		this.assigneeid = assigneeid;
		this.issue = issue;
		this.receive = receive;
		this.due = due;
		this.paid = paid;
		this.productid = productid;
		this.billdate = billdate;
	}

public Chalan(int challanid,String productid, int issue, int receive, int due, int paid, int assigneeid, Date billdate) {
	super();
	this.challanid = challanid;
	this.assigneeid = assigneeid;
	this.issue = issue;
	this.receive = receive;
	this.due = due;
	this.paid = paid;
	this.productid = productid;
	this.billdate = billdate;
}

public Date getBilldate() {
		return billdate;
	}
	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}
	//	private Date
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
	public int getPaid() {
		return paid;
	}
	public void setPaid(int paid) {
		this.paid = paid;
	}
	
	public Chalan(){
		
	}
	
	public Chalan(String productid, int issue, int receive, int due, int paid, int assigneeid,ObservableList<PopUpChallan> popupchallantableview,int totalpaid,int totalreceive) {
		super();
		this.productid = productid;
		this.issue = issue;
		this.receive = receive;
		this.due = due;
		this.paid = paid;
		this.assigneeid = assigneeid;
		this.popupchallantableview = popupchallantableview;
		this.totalpaid=totalpaid;
		this.totalreceive=totalreceive;
	}
	@Override
	public String toString() {
		return "Chalan [productid=" + productid + ", issue=" + issue + ", receive=" + receive + ", due=" + due
				+ ", paid=" + paid + ", name=" + assigneeid + "]";
	}
	public int getAssigneeid() {
		return assigneeid;
	}
	public void setAssigneeid(int assigneeid) {
		this.assigneeid = assigneeid;
	}
	
  }
