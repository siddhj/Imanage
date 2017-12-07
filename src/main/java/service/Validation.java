package service;

import java.time.LocalDate;

public class Validation {
	public static String nullVarifierStringForDao(String valuetocheck) {
		String defaultvalue = "";
		if (valuetocheck != null) {
			defaultvalue = defaultvalue + valuetocheck;
		}
		return defaultvalue;
	}
	
	public static String nullVarifierFromDateForDao(LocalDate fromdate) {
		String defaultvalue = "";
		if (fromdate != null) {
			defaultvalue = defaultvalue + fromdate;
		}
		return defaultvalue;
	}
	
	public static String nullVarifierToDateForDao(LocalDate todate) {
		String defaultvalue = "";
		if (todate != null) {
			defaultvalue = defaultvalue + todate;
		}else{
			defaultvalue="3099-12-12";
		}
		return defaultvalue;
	}

	
}
