package service;

import java.util.Date;

import org.apache.log4j.Logger;

import bean.Chalan;
import bean.PopUpChallan;
import dao.DChalan;
import javafx.collections.ObservableList;
import ui.MultiScreenFramework;
import DataConnectionThread.ChallanInsertThread;
import DataConnectionThread.ChallanTableDataUpdateThread;
import utility.UTable;

public class DataManipulation {

	public String getPopUpWindowData(ObservableList<Chalan> mainpagechallanlist) {
		long aggregatechallanid = new Date().getTime();
		UTable.setAggregatechallanid(aggregatechallanid);
		DChalan chalan = DChalan.getSingeletonInstance();
		String billdate = "";
		for (Chalan c : mainpagechallanlist) {
			billdate = c.getBilldate().toString();
			logger.info("Current Challan Detail: " + c.toString());
			Runnable challandatainsertrunnable = new ChallanInsertThread(c, aggregatechallanid);
			Thread challandatainsertthread = new Thread(challandatainsertrunnable);
			challandatainsertthread.start();
			
			ObservableList<PopUpChallan> popupwindowchallanlist = c.getPopupchallantableview();
			
			Runnable challandataupdaterunnable = new ChallanTableDataUpdateThread(popupwindowchallanlist);
			Thread challandataupdatethread = new Thread(challandataupdaterunnable);
			challandataupdatethread.start();

			chalan.chalanLogDataInsert(popupwindowchallanlist, aggregatechallanid);
			try {
				challandatainsertthread.join();
			} catch (InterruptedException e) {
				logger.error("insertinto challan thread error", e);
			}
			try {
				challandataupdatethread.join();
			} catch (InterruptedException e) {
				logger.error("update challan thread error", e);

			}
		}
		return billdate;
	}

	final static Logger logger = Logger.getLogger(MultiScreenFramework.class);
}
