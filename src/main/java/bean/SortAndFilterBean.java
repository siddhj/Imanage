package bean;

import java.sql.Date;

public class SortAndFilterBean {
@Override
	public String toString() {
		return "SortAndFilterBean [filtername=" + filtername + ", filterproductid=" + filterproductid
				+ ", assigneename=" + assigneename + ", productid=" + productid + ", fromdate=" + fromdate + ", todate="
				+ todate + ", billdate=" + billdate + ", challanid=" + challanid + ", issueitem=" + issueitem
				+ ", receiveitem=" + receiveitem + ", receivedueitem=" + receivedueitem + ", paiditem=" + paiditem
				+ ", paiditemdue=" + paiditemdue + "]";
	}
public String filtername,filterproductid,assigneename,productid;
public Date fromdate,todate,billdate;
public int challanid,issueitem,receiveitem,receivedueitem,paiditem,paiditemdue;
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
public Date getFromdate() {
	return fromdate;
}
public void setFromdate(Date fromdate) {
	this.fromdate = fromdate;
}
public SortAndFilterBean(String assigneename, Date billdate, int challanid, String productid, int issueitem,
		int receiveitem, int receivedueitem, int paiditem, int paiditemdue) {
	super();
	this.assigneename = assigneename;
	this.billdate = billdate;
	this.challanid = challanid;
	this.productid = productid;
	this.issueitem = issueitem;
	this.receiveitem = receiveitem;
	this.receivedueitem = receivedueitem;
	this.paiditem = paiditem;
	this.paiditemdue = paiditemdue;
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
public String getProductid() {
	return productid;
}
public void setProductid(String productid) {
	this.productid = productid;
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
