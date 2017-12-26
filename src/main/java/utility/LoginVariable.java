package utility;

public class LoginVariable {
	private static String jarversion,firmname,gstin,filestoreaddress;
	private static boolean newchallanaccess,sortandfilteraccess,newassigneeaccess;
	
	public static String getJarversion() {
		return jarversion;
	}
	public static void setJarversion(String jarversion) {
		LoginVariable.jarversion = jarversion;
	}
	public static String getFirmname() {
		return firmname;
	}
	public static void setFirmname(String firmname) {
		LoginVariable.firmname = firmname;
	}
	public static String getGstin() {
		return gstin;
	}
	public static void setGstin(String gstin) {
		LoginVariable.gstin = gstin;
	}
	public static String getFilestoreaddress() {
		return filestoreaddress;
	}
	public static void setFilestoreaddress(String filestoreaddress) {
		LoginVariable.filestoreaddress = filestoreaddress;
	}
	public static boolean isNewchallanaccess() {
		return newchallanaccess;
	}
	public static void setNewchallanaccess(boolean newchallanaccess) {
		LoginVariable.newchallanaccess = newchallanaccess;
	}
	public static boolean isSortandfilteraccess() {
		return sortandfilteraccess;
	}
	public static void setSortandfilteraccess(boolean sortandfilteraccess) {
		LoginVariable.sortandfilteraccess = sortandfilteraccess;
	}
	public static boolean isNewassigneeaccess() {
		return newassigneeaccess;
	}
	public static void setNewassigneeaccess(boolean newassigneeaccess) {
		LoginVariable.newassigneeaccess = newassigneeaccess;
	}
	
}
