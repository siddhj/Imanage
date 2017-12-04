package bean;

import java.util.Date;

public class PopUpChallan {
	private int assigneeid,issue,pastreceive,pastdue,pastpaid,challanid,currentreceive,currentpaid;
	private String productid;
	private Date billdate;
	public int getAssigneeid() {
		return assigneeid;
	}
	public void setAssigneeid(int assigneeid) {
		this.assigneeid = assigneeid;
	}
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	public int getPastreceive() {
		return pastreceive;
	}
	public void setPastreceive(int pastreceive) {
		this.pastreceive = pastreceive;
	}
	public int getPastdue() {
		return pastdue;
	}
	public void setPastdue(int pastdue) {
		this.pastdue = pastdue;
	}
	public int getPastpaid() {
		return pastpaid;
	}
	public void setPastpaid(int pastpaid) {
		this.pastpaid = pastpaid;
	}
	public int getChallanid() {
		return challanid;
	}
	public void setChallanid(int challanid) {
		this.challanid = challanid;
	}
	public int getCurrentreceive() {
		return currentreceive;
	}
	public void setCurrentreceive(int currentreceive) {
		this.currentreceive = currentreceive;
	}
	public int getCurrentpaid() {
		return currentpaid;
	}
	public void setCurrentpaid(int currentpaid) {
		this.currentpaid = currentpaid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public Date getBilldate() {
		return billdate;
	}
	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}
	public PopUpChallan(int assigneeid, int issue, int pastreceive, int pastdue, int pastpaid, int challanid,
			int currentreceive, int currentpaid, String productid, Date billdate) {
		super();
		this.assigneeid = assigneeid;
		this.issue = issue;
		this.pastreceive = pastreceive;
		this.pastdue = pastdue;
		this.pastpaid = pastpaid;
		this.challanid = challanid;
		this.currentreceive = currentreceive;
		this.currentpaid = currentpaid;
		this.productid = productid;
		this.billdate = billdate;
	}

}
