package bean;

import java.time.LocalDate;

public class LoginVerification {
private String jarversion,firmname,gstin,filestoreaddress,logstoreaddress,logemailaddress,logemailpassword;
public String getLogemailaddress() {
	return logemailaddress;
}
public void setLogemailaddress(String logemailaddress) {
	this.logemailaddress = logemailaddress;
}
public String getLogemailpassword() {
	return logemailpassword;
}
public void setLogemailpassword(String logemailpassword) {
	this.logemailpassword = logemailpassword;
}

private boolean newchallanaccess,sortandfilteraccess,newassigneeaccess;
private LocalDate logdate;
private int licenseid;
public int getLicenseid() {
	return licenseid;
}
public void setLicenseid(int licenseid) {
	this.licenseid = licenseid;
}
public LocalDate getLogdate() {
	return logdate;
}
public void setLogdate(LocalDate logdate) {
	this.logdate = logdate;
}
public String getLogstoreaddress() {
	return logstoreaddress;
}
public void setLogstoreaddress(String logstoreaddress) {
	this.logstoreaddress = logstoreaddress;
}

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
		boolean newchallanaccess, boolean sortandfilteraccess, boolean newassigneeaccess,String logstoreaddress
		,LocalDate logdate, int licenseid,String logemailaddress,String logemailpassword) {
	super();
	this.jarversion = jarversion;
	this.firmname = firmname;
	this.gstin = gstin;
	this.filestoreaddress = filestoreaddress;
	this.newchallanaccess = newchallanaccess;
	this.sortandfilteraccess = sortandfilteraccess;
	this.newassigneeaccess = newassigneeaccess;
	this.logstoreaddress=logstoreaddress;
	this.logdate=logdate;
	this.licenseid=licenseid;
	this.logemailaddress=logemailaddress;
	this.logemailpassword=logemailpassword;
}
@Override
public String toString() {
	return "LoginVerification [jarversion=" + jarversion + ", firmname=" + firmname + ", gstin=" + gstin
			+ ", filestoreaddress=" + filestoreaddress + ", logstoreaddress=" + logstoreaddress + ", logemailaddress="
			+ logemailaddress + ", logemailpassword=" + logemailpassword + ", newchallanaccess=" + newchallanaccess
			+ ", sortandfilteraccess=" + sortandfilteraccess + ", newassigneeaccess=" + newassigneeaccess + ", logdate="
			+ logdate + ", licenseid=" + licenseid + "]";
}


}
