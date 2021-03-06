package bean;

public class Assignee {
	private String firstname, lastname, description,fullname,gstin;
	public Assignee(String fullname, String gstin, int phonenumber, int aadharnumber) {
		super();
		this.fullname = fullname;
		this.gstin = gstin;
		this.phonenumber = phonenumber;
		this.aadharnumber = aadharnumber;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	private int assigneeid, phonenumber, aadharnumber;

	public Assignee(String fullname, int assigneeid,String gstin) {
		super();
		this.assigneeid = assigneeid;
		this.fullname=fullname;
		this.gstin = gstin;
	}
	public Assignee() {

	}

	public Assignee(int phonenumber, int aadharnumber, String firstname, String lastname,String fullname,String description) {
		super();
		this.phonenumber = phonenumber;
		this.aadharnumber = aadharnumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.description = description;
		this.fullname=fullname;
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
