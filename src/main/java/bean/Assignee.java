package bean;

public class Assignee {
	private String firstname, lastname, description;
	private int assigneeid, phonenumber, aadharnumber;

	public Assignee(String firstname, String lastname, int assigneeid) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.assigneeid = assigneeid;
	}
	public Assignee() {

	}

	public Assignee(int phonenumber, int aadharnumber, String firstname, String lastname, String description) {
		super();
		this.phonenumber = phonenumber;
		this.aadharnumber = aadharnumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.description = description;
	}

	public int getAssigneeid() {
		return assigneeid;
	}

	public void setAssigneeid(int assigneeid) {
		this.assigneeid = assigneeid;
	}

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getAadharnumber() {
		return aadharnumber;
	}

	public void setAadharnumber(int aadharnumber) {
		this.aadharnumber = aadharnumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
}
