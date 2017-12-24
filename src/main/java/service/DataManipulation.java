package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import com.itextpdf.text.DocumentException;

import bean.Chalan;
import bean.PopUpChallan;
import dao.DChalan;
import javafx.collections.ObservableList;
import DataConnectionThread.ChallanInsertThread;
import DataConnectionThread.ChallanTableDataUpdateThread;
import utility.UTable;

public class DataManipulation {
	public void getPopUpWindowData(ObservableList<Chalan> mainpagechallanlist)
			throws SQLException, IOException, DocumentException, InterruptedException {
		long aggregatechallanid = new Date().getTime();
		UTable.setAggregatechallanid(aggregatechallanid);
		DChalan chalan = DChalan.getSingeletonInstance();
		String billdate = "";
		for (Chalan c : mainpagechallanlist) {
			billdate = c.getBilldate().toString();

			Runnable challandatainsertrunnable = new ChallanInsertThread(c, aggregatechallanid);
			Thread challandatainsertthread = new Thread(challandatainsertrunnable);
			challandatainsertthread.start();
			// chalan.chalanDataInsert(c,aggregatechallanid);
			// int challanid = chalan.getLastChallanID();

			ObservableList<PopUpChallan> popupwindowchallanlist = c.getPopupchallantableview();
			// chalan.chalanDataUpdatePopUpWindow(popupwindowchallanlist);

			Runnable challandataupdaterunnable = new ChallanTableDataUpdateThread(popupwindowchallanlist);
			Thread challandataupdatethread = new Thread(challandataupdaterunnable);
			challandataupdatethread.start();

			chalan.chalanLogDataInsert(popupwindowchallanlist, aggregatechallanid);

			challandatainsertthread.join();
			challandataupdatethread.join();
		}
		MicroService.createPdfss(billdate);

	}
}
