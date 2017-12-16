package bean;

import java.sql.Date;

public class SummaryBean {
	public String filtername,filterproductid,assigneename,productid;
	public Date fromdate,todate,billdate;
	public int challanid,issueitem,receiveitem,receivedueitem,paiditem,paiditemdue;

	
	private int totallissueitem,totalreceiveitem,totalreceivedueitem,totalpaiditem,totalpaiditemdue;
	
	public SummaryBean(int totallissueitem, int totalreceiveitem, int totalreceivedueitem, int totalpaiditem,
			int totalpaiditemdue) {
		super();
		this.totallissueitem = totallissueitem;
		this.totalreceiveitem = totalreceiveitem;
		this.totalreceivedueitem = totalreceivedueitem;
		this.totalpaiditem = totalpaiditem;
		this.totalpaiditemdue = totalpaiditemdue;
	}
	public int getTotallissueitem() {
		return totallissueitem;
	}
	public void setTotallissueitem(int totallissueitem) {
		this.totallissueitem = totallissueitem;
	}
	public int getTotalreceiveitem() {
		return totalreceiveitem;
	}
	public void setTotalreceiveitem(int totalreceiveitem) {
		this.totalreceiveitem = totalreceiveitem;
	}
	public int getTotalreceivedueitem() {
		return totalreceivedueitem;
	}
	public void setTotalreceivedueitem(int totalreceivedueitem) {
		this.totalreceivedueitem = totalreceivedueitem;
	}
	public int getTotalpaiditem() {
		return totalpaiditem;
	}
	public void setTotalpaiditem(int totalpaiditem) {
		this.totalpaiditem = totalpaiditem;
	}
	public int getTotalpaiditemdue() {
		return totalpaiditemdue;
	}
	public void setTotalpaiditemdue(int totalpaiditemdue) {
		this.totalpaiditemdue = totalpaiditemdue;
	}
	public String getFiltername() {
		return filtername;
	}
	public void setFiltername(String filtername) {
		this.filtername = filtername;
	}
	public String getFilterproductid() {
		return filterproductid;
	}
	public void setFilterproductid(String filterproductid) {
		this.filterproductid = filterproductid;
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
	public Date getFromdate() {
		return fromdate;
	}
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}
	public Date getTodate() {
		return todate;
	}
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	public Date getBilldate() {
		return billdate;
	}
	public void setBilldate(Date billdate) {
		this.billdate = billdate;
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
	public int getReceivedueitem() {
		return receivedueitem;
	}
	public void setReceivedueitem(int receivedueitem) {
		this.receivedueitem = receivedueitem;
	}
	public int getPaiditem() {
		return paiditem;
	}
	public void setPaiditem(int paiditem) {
		this.paiditem = paiditem;
	}
	public int getPaiditemdue() {
		return paiditemdue;
	}
	public void setPaiditemdue(int paiditemdue) {
		this.paiditemdue = paiditemdue;
	}

}
