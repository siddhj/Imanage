package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ListTables;

import bean.PopUpChallan;
import bean.SortAndFilterBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DSort {

	
	public ObservableList<SortAndFilterBean> getFilterData(String assigneename,String productid,String fromdate,String todate) throws SQLException, IOException
	{
	ObservableList<SortAndFilterBean> filterlist = FXCollections.observableArrayList();
	
	String query = "select * from challan as c join assignee as a on c.AssigneeID = a.AssigneeID "
			+ "where a.First_Name like ? and c.ProductID like ? and c.BillDateType Between ? and ?";
	
	ListTables chalandata = new ListTables();
	Connection connection = chalandata.returnConnection();
	PreparedStatement stmt = connection.prepareStatement(query);
	stmt.setString(1, "%"+assigneename);
	stmt.setString(2, "%"+productid);
	stmt.setString(3, fromdate);
	stmt.setString(4, "3099-12-12");

	ResultSet resultset = stmt.executeQuery();


	while (resultset.next()) {

//		String assigneename, LocalDate billdate, int challanid, String productid, int issueitem,
//		int receiveitem, int receivedueitem, int paiditem, int paiditemdue
		System.out.println(resultset.getInt("c.ChallanID"));
		filterlist.add(new SortAndFilterBean(resultset.getString("a.First_Name"),resultset.getDate("c.BillDateType"),resultset.getInt("c.ChallanID"),
				resultset.getString("ProductID"),resultset.getInt("c.Issue"),resultset.getInt("c.Receive"),resultset.getInt("c.Due"),resultset.getInt("c.Paid"),
				resultset.getInt("c.Issue")-resultset.getInt("c.Paid"),resultset.getInt("PastReceive"),resultset.getInt("PastPaid")));

	}
	filterlist.forEach(c->{System.out.println(c);});
	return filterlist;
	}
	
}
