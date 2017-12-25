package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.itextpdf.text.DocumentException;

import bean.Chalan;
import bean.PopUpChallan;
import dao.DChalan;
import javafx.collections.ObservableList;
import ui.MultiScreenFramework;
import DataConnectionThread.ChallanInsertThread;
import DataConnectionThread.ChallanTableDataUpdateThread;
import utility.UTable;

public class DataManipulation {
	public void getPopUpWindowData(ObservableList<Chalan> mainpagechallanlist){
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

			try {
				challandatainsertthread.join();
			} catch (InterruptedException e) {
			logger.error("insertinto challan thread error",e);
			}
			try {
				challandataupdatethread.join();
			} catch (InterruptedException e) {
				logger.error("update challan thread error",e);
				
			}
		}
		
		MicroService.createPdfss(billdate);

	}
	final static Logger logger = Logger.getLogger(MultiScreenFramework.class);
}
