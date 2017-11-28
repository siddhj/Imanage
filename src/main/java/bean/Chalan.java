package bean;

public class Chalan{
	
	private int productid,issue,receive,due,paid;
	private String name;
//	private Date
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
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
	
	public Chalan(int productid, int issue, int receive, int due, int paid, String name) {
		super();
		this.productid = productid;
		this.issue = issue;
		this.receive = receive;
		this.due = due;
		this.paid = paid;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Chalan [productid=" + productid + ", issue=" + issue + ", receive=" + receive + ", due=" + due
				+ ", paid=" + paid + ", name=" + name + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
  }
