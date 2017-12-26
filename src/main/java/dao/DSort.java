package dao;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.SortAndFilterBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.UTable;

public class DSort {

	
	public ObservableList<SortAndFilterBean> getFilterData(String assigneename,String productid,String fromdate,String todate) throws SQLException, IOException
	{
	ObservableList<SortAndFilterBean> filterlist = FXCollections.observableArrayList();

	int totalissueitem=0,totalreceiveitem=0,totalreceivedueitem=0,totalamountpaid=0;
	
	String query = "select * from challan as c join assignee as a on c.AssigneeID = a.AssigneeID "
			+ "where a.Full_Name like ? and c.ProductID like ? and c.BillDateType Between ? and ?";
	
	Connection connection = ListTables.returnConnection();
	PreparedStatement stmt = connection.prepareStatement(query);
	stmt.setString(1, "%"+assigneename);
	stmt.setString(2, "%"+productid);
	stmt.setString(3, fromdate);
	stmt.setString(4, "3099-12-12");

	ResultSet resultset = stmt.executeQuery();

//FullName is assigned in FirstName as of now will be updated in later stage 
	while (resultset.next()) {
		filterlist.add(new SortAndFilterBean(resultset.getString("a.Full_Name"),resultset.getDate("c.BillDateType"),resultset.getInt("c.ChallanID"),
				resultset.getString("ProductID"),resultset.getInt("c.Issue"),resultset.getInt("c.Receive"),resultset.getInt("c.Due"),
				resultset.getInt("AmountPaid"),resultset.getInt("PastReceive"),resultset.getLong("AggregateChallanID")));

	totalissueitem +=resultset.getInt("c.Issue");
	totalreceiveitem+= resultset.getInt("c.Receive");
	totalreceivedueitem+=resultset.getInt("c.Due");
	totalamountpaid += resultset.getInt("AmountPaid");
	}
	UTable.setSortandfiltertotalissue(totalissueitem);
	UTable.setSortandfiltertotalreceive(totalreceiveitem);
	UTable.setSortandfiltertotalreceivedue(totalreceivedueitem);
	filterlist.add(new SortAndFilterBean("***TOTAL*** => ",null,0,"********",totalissueitem,totalreceiveitem,totalreceivedueitem,totalamountpaid,0000,0));
	
	return filterlist;
	}
	
}
