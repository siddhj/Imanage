package DataConnectionThread;

import java.io.IOException;
import java.sql.SQLException;

import dao.DLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.UTable;

public class AssigneeNameAndProductIDLoadThread implements Runnable{

	public AssigneeNameAndProductIDLoadThread(){
		
	}
	ObservableList<ObservableList<String>> parentlist = FXCollections.observableArrayList();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			parentlist = DLoader.getSingeletonInstanceOfLoader().intialLoader();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UTable.setIntialloaderproductid(parentlist.get(0));
		UTable.setIntialloaderassigneename(parentlist.get(1));
	}
	
}
