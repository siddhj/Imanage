package dao;

import java.io.IOException;
import java.sql.SQLException;

import bean.SortAndFilterBean;
import javafx.collections.ObservableList;
import other.SuppportDao;

public class RThread implements Runnable{

	@Override
	public void run() {
		DSort sort = new DSort();
		try {
			ObservableList<SortAndFilterBean> filterlist = sort.getFilterData("","","","");
			SuppportDao.setFilterlist(filterlist);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
