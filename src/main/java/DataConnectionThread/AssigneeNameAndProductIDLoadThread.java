package DataConnectionThread;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import dao.DLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.UTable;
import utility.Logging;

public class AssigneeNameAndProductIDLoadThread implements Runnable {
	final static Logger logger = Logger.getLogger(AssigneeNameAndProductIDLoadThread.class);

	public AssigneeNameAndProductIDLoadThread() {
		logger.addAppender(Logging.getAppender());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		logger.info("Thread started for loading productid and assignee name");
		ObservableList<ObservableList<String>> parentlist;
		try {
			parentlist = DLoader.getSingeletonInstanceOfLoader().intialLoader();
			UTable.setIntialloaderproductid(parentlist.get(0));
			UTable.setIntialloaderassigneename(parentlist.get(1));
		} catch (SQLException e) {
			logger.error("Error while intial loading", e);
			e.printStackTrace();
		}

	}

}
