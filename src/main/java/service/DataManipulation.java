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
		
		DChalan chalan = DChalan.getSingeletonInstance();
		for (Chalan c : mainpagechallanlist) {
			chalan.chalanDataInsert(c);
			int challanid = chalan.getLastChallanID();
			ObservableList<PopUpChallan> popupwindowchallanlist = c.getPopupchallantableview();
			chalan.chalanDataUpdatePopUpWindow(popupwindowchallanlist);
			chalan.chalanLogDataInsert(popupwindowchallanlist, challanid);
		}
	}
}
