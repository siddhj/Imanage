package service;

import java.io.IOException;
import java.sql.SQLException;
import bean.Chalan;
import bean.PopUpChallan;
import dao.DChalan;
import javafx.collections.ObservableList;

public class DataManipulation {
	public void getPopUpWindowData(ObservableList<Chalan> mainpagechallanlist) throws SQLException, IOException {
		System.out.println("this is inside data manipulatiopn");
		
		mainpagechallanlist.forEach(c->{
			System.out.println(c.getIssue()+"This is the issue"+c.getAdvancepaid()+"this is the advanced paid");
			c.getPopupchallantableview().forEach(pop->{
				System.out.println(pop.getChallanid()+"this is the challanlisy");
			});
		});
		
		System.out.println("this is outside data manipualtion");
		DChalan chalan = DChalan.getSingeletonInstance();
		chalan.chalanDataInsert(mainpagechallanlist);
		int challanid = chalan.getLastChallanID();
		for (Chalan c : mainpagechallanlist) {
			ObservableList<PopUpChallan> popupwindowchallanlist = c.getPopupchallantableview();
			chalan.chalanDataUpdatePopUpWindow(popupwindowchallanlist);
			chalan.chalanLogDataInsert(popupwindowchallanlist, challanid);
		}
	
	}
}
