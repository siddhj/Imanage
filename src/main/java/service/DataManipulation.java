package service;

import java.io.IOException;
import java.sql.SQLException;

import bean.Chalan;
import bean.PopUpChallan;
import dao.DChalan;
import javafx.collections.ObservableList;

public class DataManipulation {

	public void getPopUpWindowData(ObservableList<Chalan> mainpagechallanlist) throws SQLException, IOException {
		DChalan chalan = new DChalan();
		for (Chalan c : mainpagechallanlist) {
			ObservableList<PopUpChallan> popupwindowchallanlist = c.getPopupchallantableview();
//			for (PopUpChallan p : popupwindowchallanlist) {
//				int totalreceive = MicroService.sumReceiveFromPopUp(p.getPastreceive(), p.getCurrentreceive());
//				int totalpaid = MicroService.sumPaidFromPopUp(p.getPastpaid(), p.getCurrentpaid());
//				int totaldue = MicroService.sumDueFromPopUp(p.getIssue(), totalreceive);
//				int challanid = p.getChallanid();
//				chalan.chalanDataUpdatePopUpWindow(totalreceive, totalpaid, totaldue, challanid);
//			}
				chalan.chalanDataUpdatePopUpWindow(popupwindowchallanlist);
		}
		chalan.chalanDataInsert(mainpagechallanlist);
	}
}
