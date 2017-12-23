package bean;

import java.sql.Date;

public class ChallanDetailBean {
	public String assigneename,productid;
	public Date billdate;
	public int referchallanid,challanid,issueitem,receiveitem,amountpaid;

	public ChallanDetailBean(String assigneename, String productid, Date billdate, int referchallanid, int challanid,
			int issueitem, int receiveitem, int amountpaid /*int paiditem*/ ) {
		super();
		this.assigneename = assigneename;
		this.productid = productid;
		this.billdate = billdate;
		this.referchallanid = referchallanid;
		this.challanid = challanid;
		this.issueitem = issueitem;
		this.receiveitem = receiveitem;
//		this.paiditem = paiditem;
		this.amountpaid = amountpaid;
	}
	public String getAssigneename() {
		return assigneename;
	}
	public void setAssigneename(String assigneename) {
		this.assigneename = assigneename;
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
	public int getReferchallanid() {
		return referchallanid;
	}
	public void setReferchallanid(int referchallanid) {
		this.referchallanid = referchallanid;
	}
	public int getChallanid() {
		return challanid;
	}
	public void setChallanid(int challanid) {
		this.challanid = challanid;
	}
	public int getIssueitem() {
		return issueitem;
	}
	public void setIssueitem(int issueitem) {
		this.issueitem = issueitem;
	}
	public int getReceiveitem() {
		return receiveitem;
	}
	public void setReceiveitem(int receiveitem) {
		this.receiveitem = receiveitem;
	}
	public int getAmountpaid() {
		return amountpaid;
	}
	public void setAmountpaid(int amountpaid) {
		this.amountpaid = amountpaid;
	}
	
}
