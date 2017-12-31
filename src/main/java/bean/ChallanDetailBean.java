package bean;

import java.sql.Date;

public class ChallanDetailBean {
	public String assigneename, productid;
	public Date challanidbilldate,referchallanidbilldate;
	public int issueitem, receiveitem, amountpaid;
	private long referchallanid, prereceivedchallanid;

	public ChallanDetailBean(String assigneename, String productid, Date challanidbilldate, long referchallanid, long prereceivedchallanid,
			int issueitem, int receiveitem, int amountpaid,Date referchallanidbilldate) {
		super();
		this.assigneename = assigneename;
		this.productid = productid;
		this.challanidbilldate = challanidbilldate;
		this.referchallanid = referchallanid;
		this.prereceivedchallanid = prereceivedchallanid;
		this.issueitem = issueitem;
		this.receiveitem = receiveitem;
		this.amountpaid = amountpaid;
		this.referchallanidbilldate=referchallanidbilldate;
	}


	public Date getChallanidbilldate() {
		return challanidbilldate;
	}

	public void setChallanidbilldate(Date challanidbilldate) {
		this.challanidbilldate = challanidbilldate;
	}

	public Date getReferchallanidbilldate() {
		return referchallanidbilldate;
	}

	public void setReferchallanidbilldate(Date referchallanidbilldate) {
		this.referchallanidbilldate = referchallanidbilldate;
	}

	public long getPrereceivedchallanid() {
		return prereceivedchallanid;
	}

	public void setPrereceivedchallanid(long prereceivedchallanid) {
		this.prereceivedchallanid = prereceivedchallanid;
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

	public long getReferchallanid() {
		return referchallanid;
	}

	public void setReferchallanid(long referchallanid) {
		this.referchallanid = referchallanid;
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
