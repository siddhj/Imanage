package bean;

import java.sql.Date;

public class ChallanDetailBean {
	public String assigneename, productid;
	public Date billdate;
	public int issueitem, receiveitem, amountpaid;
	private long referchallanid, challanid;

	public ChallanDetailBean(String assigneename, String productid, Date billdate, long referchallanid, long challanid,
			int issueitem, int receiveitem, int amountpaid /* int paiditem */ ) {
		super();
		this.assigneename = assigneename;
		this.productid = productid;
		this.billdate = billdate;
		this.referchallanid = referchallanid;
		this.challanid = challanid;
		this.issueitem = issueitem;
		this.receiveitem = receiveitem;
		// this.paiditem = paiditem;
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

	public long getReferchallanid() {
		return referchallanid;
	}

	public void setReferchallanid(long referchallanid) {
		this.referchallanid = referchallanid;
	}

	public long getChallanid() {
		return challanid;
	}

	public void setChallanid(long challanid) {
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
