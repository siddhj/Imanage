package bean;

import java.sql.Date;

public class SortAndFilterBean {

private String filtername,filterproductid,assigneename,productid,comment;
private Date fromdate,todate,billdate;
private int challanid,issueitem,receiveitem,receivedueitem,amountpaid;

public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
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
public Date getFromdate() {
	return fromdate;
}
public void setFromdate(Date fromdate) {
	this.fromdate = fromdate;
}
public int pastreceive,pastpaid;

public int getPastreceive() {
	return pastreceive;
}
public void setPastreceive(int pastreceive) {
	this.pastreceive = pastreceive;
}
public int getPastpaid() {
	return pastpaid;
}
public void setPastpaid(int pastpaid) {
	this.pastpaid = pastpaid;
}

long aggregatechallanid;
public SortAndFilterBean(String assigneename, Date billdate, int challanid, String productid, int issueitem,
		int receiveitem, int receivedueitem, int amountpaid,int pastreceive,long aggregatechallanid,String comment) {
	super();
	this.assigneename = assigneename;
	this.billdate = billdate;
	this.challanid = challanid;
	this.productid = productid;
	this.issueitem = issueitem;
	this.receiveitem = receiveitem;
	this.receivedueitem = receivedueitem;
	this.amountpaid=amountpaid;
	this.pastreceive=pastreceive;
	this.aggregatechallanid=aggregatechallanid;
	this.comment=comment;
}
public long getAggregatechallanid() {
	return aggregatechallanid;
}
public void setAggregatechallanid(long aggregatechallanid) {
	this.aggregatechallanid = aggregatechallanid;
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
//public int getPaiditem() {
//	return paiditem;
//}
//public void setPaiditem(int paiditem) {
//	this.paiditem = paiditem;
//}
//public int getPaiditemdue() {
//	return paiditemdue;
//}
//public void setPaiditemdue(int paiditemdue) {
//	this.paiditemdue = paiditemdue;
//}
public int getAmountpaid() {
	return amountpaid;
}
public void setAmountpaid(int amountpaid) {
	this.amountpaid = amountpaid;
}

}
