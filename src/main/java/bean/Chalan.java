package bean;

public class Chalan{
	
	private int assigneeid,issue,receive,due,paid;
	private String productid;
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
	
	public Chalan(String productid, int issue, int receive, int due, int paid, int assigneeid) {
		super();
		this.productid = productid;
		this.issue = issue;
		this.receive = receive;
		this.due = due;
		this.paid = paid;
		this.assigneeid = assigneeid;
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
