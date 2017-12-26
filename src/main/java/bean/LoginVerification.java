package bean;

public class LoginVerification {
private String jarversion,firmname,gstin,filestoreaddress;
private boolean newchallanaccess,sortandfilteraccess,newassigneeaccess;
public String getJarversion() {
	return jarversion;
}
public void setJarversion(String jarversion) {
	this.jarversion = jarversion;
}
public String getFirmname() {
	return firmname;
}
public void setFirmname(String firmname) {
	this.firmname = firmname;
}
public String getGstin() {
	return gstin;
}
public void setGstin(String gstin) {
	this.gstin = gstin;
}
public String getFilestoreaddress() {
	return filestoreaddress;
}
public void setFilestoreaddress(String filestoreaddress) {
	this.filestoreaddress = filestoreaddress;
}
public boolean isNewchallanaccess() {
	return newchallanaccess;
}
public void setNewchallanaccess(boolean newchallanaccess) {
	this.newchallanaccess = newchallanaccess;
}
public boolean isSortandfilteraccess() {
	return sortandfilteraccess;
}
public void setSortandfilteraccess(boolean sortandfilteraccess) {
	this.sortandfilteraccess = sortandfilteraccess;
}
public boolean isNewassigneeaccess() {
	return newassigneeaccess;
}
public void setNewassigneeaccess(boolean newassigneeaccess) {
	this.newassigneeaccess = newassigneeaccess;
}
public LoginVerification(String jarversion, String firmname, String gstin, String filestoreaddress,
		boolean newchallanaccess, boolean sortandfilteraccess, boolean newassigneeaccess) {
	super();
	this.jarversion = jarversion;
	this.firmname = firmname;
	this.gstin = gstin;
	this.filestoreaddress = filestoreaddress;
	this.newchallanaccess = newchallanaccess;
	this.sortandfilteraccess = sortandfilteraccess;
	this.newassigneeaccess = newassigneeaccess;
}
@Override
public String toString() {
	return "LoginVerification [jarversion=" + jarversion + ", firmname=" + firmname + ", gstin=" + gstin
			+ ", filestoreaddress=" + filestoreaddress + ", newchallanaccess=" + newchallanaccess
			+ ", sortandfilteraccess=" + sortandfilteraccess + ", newassigneeaccess=" + newassigneeaccess + "]";
}

}
