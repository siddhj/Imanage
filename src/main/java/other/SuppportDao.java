package other;

import bean.SortAndFilterBean;
import javafx.collections.ObservableList;

public class SuppportDao {
	static ObservableList<SortAndFilterBean> filterlist;

	public static ObservableList<SortAndFilterBean> getFilterlist() {
		return filterlist;
	}

	public static void setFilterlist(ObservableList<SortAndFilterBean> filterlist) {
		SuppportDao.filterlist = filterlist;
	}
}
