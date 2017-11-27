package bean;

public class Chalan{
	
	private String productid,name,issue,receive,due;
	
	
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public Chalan(String productid, String name, String issue, String receive, String due) {
		super();
		this.productid = productid;
		this.name = name;
		this.issue = issue;
		this.receive = receive;
		this.due = due;
	}
  }
