package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import com.itextpdf.text.DocumentException;

import bean.Chalan;
import bean.PopUpChallan;
import dao.DChalan;
import javafx.collections.ObservableList;
import utility.UTable;

public class DataManipulation {
	public void getPopUpWindowData(ObservableList<Chalan> mainpagechallanlist) throws SQLException, IOException, DocumentException {
		System.out.println("this is inside data manipulatiopn");
		long aggregatechallanid = new Date().getTime();
		UTable.setAggregatechallanid(aggregatechallanid);
		DChalan chalan = DChalan.getSingeletonInstance();
		String billdate ="";
		for (Chalan c : mainpagechallanlist) {
			billdate = c.getBilldate().toString();
			chalan.chalanDataInsert(c,aggregatechallanid);
			int challanid = chalan.getLastChallanID();
			ObservableList<PopUpChallan> popupwindowchallanlist = c.getPopupchallantableview();
			chalan.chalanDataUpdatePopUpWindow(popupwindowchallanlist);
			chalan.chalanLogDataInsert(popupwindowchallanlist, challanid);
		}
		
		MicroService.createPdfss(billdate);
		
	}
}
