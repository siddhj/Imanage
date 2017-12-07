package bean;

import java.sql.Date;

public class SortAndFilterBean {
public String filtername,filterproductid,assigneename;
public Date fromdate,todate,billdate;
public int challanid,productid,issueitem,receiveitem,receivedueitem,paiditem,paiditemdue;
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
public int getProductid() {
	return productid;
}
public void setProductid(int productid) {
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
