package service;

import java.time.LocalDate;

import javafx.collections.ObservableList;

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
	
	public static boolean parentListNameValidation(ObservableList<String> namelist,String assigneename)
	{
		if(namelist.contains(assigneename))
		{
			return true;
		}
		return false;
		
	}
	
	public static boolean parentListProductIDValidation(ObservableList<String> productlist,String productid)
	{
		if(productlist.contains(productid))
		{
			return true;
		}
		return false;
		
	}

	
}
