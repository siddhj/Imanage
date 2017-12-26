package DataConnectionThread;

import java.sql.SQLException;

import dao.DLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.UTable;

public class AssigneeNameAndProductIDLoadThread implements Runnable {

	public AssigneeNameAndProductIDLoadThread() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ObservableList<ObservableList<String>> parentlist;
		try {
			parentlist = DLoader.getSingeletonInstanceOfLoader().intialLoader();
			UTable.setIntialloaderproductid(parentlist.get(0));
			UTable.setIntialloaderassigneename(parentlist.get(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
