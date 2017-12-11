package service;

import java.io.IOException;
import java.sql.SQLException;
import bean.Chalan;
import bean.PopUpChallan;
import dao.DChalan;
import javafx.collections.ObservableList;

public class DataManipulation {
	public void getPopUpWindowData(ObservableList<Chalan> mainpagechallanlist) throws SQLException, IOException {

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
