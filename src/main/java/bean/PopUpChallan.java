package bean;

import java.time.LocalDate;
import java.util.Date;

public class PopUpChallan {
	private int assigneeid,issue,pastreceive,pastreceivedue,challanid,currentreceive,amountpaid;
//	public int getPastpaiddue() {
//		return pastpaiddue;
//	}
//	public void setPastpaiddue(int pastpaiddue) {
//		this.pastpaiddue = pastpaiddue;
//	}
	private String productid;
	private LocalDate billdate;
	private long aggregatechallanid;
	public long getAggregatechallanid() {
		return aggregatechallanid;
	}
	public void setAggregatechallanid(long aggregatechallanid) {
		this.aggregatechallanid = aggregatechallanid;
	}
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
	
	public int getPastreceivedue() {
		return pastreceivedue;
	}
	public void setPastreceivedue(int pastreceivedue) {
		this.pastreceivedue = pastreceivedue;
	}
//	public int getPastpaid() {
//		return pastpaid;
//	}
//	public void setPastpaid(int pastpaid) {
//		this.pastpaid = pastpaid;
//	}
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
//	public int getCurrentpaid() {
//		return currentpaid;
//	}
//	public void setCurrentpaid(int currentpaid) {
//		this.currentpaid = currentpaid;
//	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public LocalDate getBilldate() {
		return billdate;
	}
	public void setBilldate(LocalDate billdate) {
		this.billdate = billdate;
	}
//	public PopUpChallan(int assigneeid, int issue, int pastreceive, int pastreceivedue, int pastpaid, int challanid,
//			int currentreceive, int currentpaid, String productid, LocalDate billdate,int pastpaiddue) {
//		super();
//		this.assigneeid = assigneeid;
//		this.issue = issue;
//		this.pastreceive = pastreceive;
//		this.pastreceivedue = pastreceivedue;
//		this.pastpaid = pastpaid;
//		this.challanid = challanid;
//		this.currentreceive = currentreceive;
//		this.currentpaid = currentpaid;
//		this.productid = productid;
//		this.billdate = billdate;
//		this.pastpaiddue=pastpaiddue;
//	}
	
	public PopUpChallan(int assigneeid, int issue, int pastreceive, int pastreceivedue, int challanid,
			int currentreceive, String productid, LocalDate billdate,int amountpaid,long aggregatechallanid) {
		super();
		this.assigneeid = assigneeid;
		this.issue = issue;
		this.pastreceive = pastreceive;
		this.pastreceivedue = pastreceivedue;
		this.challanid = challanid;
		this.currentreceive = currentreceive;
		this.productid = productid;
		this.billdate = billdate;
		this.amountpaid=amountpaid;
		this.aggregatechallanid=aggregatechallanid;
	}

}
