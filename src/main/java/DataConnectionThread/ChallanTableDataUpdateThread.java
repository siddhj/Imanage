package DataConnectionThread;

import java.io.IOException;
import java.sql.SQLException;

import bean.PopUpChallan;
import dao.DChalan;
import javafx.collections.ObservableList;

public class ChallanTableDataUpdateThread implements Runnable {

	private ObservableList<PopUpChallan> popupchallanlist;

	public ChallanTableDataUpdateThread(ObservableList<PopUpChallan> popupchallanlist) {
		this.popupchallanlist = popupchallanlist;
	}

	@Override
	public void run() {
		try {
			DChalan.getSingeletonInstance().chalanDataUpdatePopUpWindow(popupchallanlist);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
