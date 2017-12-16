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
import bean.SummaryBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.UTable;

public class DSort {

	
	public ObservableList<SortAndFilterBean> getFilterData(String assigneename,String productid,String fromdate,String todate) throws SQLException, IOException
	{
//	ObservableList<ObservableList<?>> filter = FXCollections.observableArrayList();
	ObservableList<SortAndFilterBean> filterlist = FXCollections.observableArrayList();
//	ObservableList<SummaryBean> summarylist = FXCollections.observableArrayList();	
	
	int totalissueitem=0,totalreceiveitem=0,totalreceivedueitem=0,totalpaiditem=0,totalpaiditemdue=0;
	
	String query = "select * from challan as c join assignee as a on c.AssigneeID = a.AssigneeID "
			+ "where a.Full_Name like ? and c.ProductID like ? and c.BillDateType Between ? and ?";
	
	ListTables chalandata = new ListTables();
	Connection connection = chalandata.returnConnection();
	PreparedStatement stmt = connection.prepareStatement(query);
	stmt.setString(1, "%"+assigneename);
	stmt.setString(2, "%"+productid);
	stmt.setString(3, fromdate);
	stmt.setString(4, "3099-12-12");

	ResultSet resultset = stmt.executeQuery();

//FullName is assigned in FirstName as of now will be updated in later stage 
	while (resultset.next()) {
		filterlist.add(new SortAndFilterBean(resultset.getString("a.Full_Name"),resultset.getDate("c.BillDateType"),resultset.getInt("c.ChallanID"),
				resultset.getString("ProductID"),resultset.getInt("c.Issue"),resultset.getInt("c.Receive"),resultset.getInt("c.Due"),resultset.getInt("c.Paid"),
				resultset.getInt("c.Issue")-resultset.getInt("c.Paid"),resultset.getInt("PastReceive"),resultset.getInt("PastPaid")));

	totalissueitem +=resultset.getInt("c.Issue");
	totalreceiveitem+= resultset.getInt("c.Receive");
	totalreceivedueitem+=resultset.getInt("c.Due");
	totalpaiditem+=resultset.getInt("c.Paid");
	totalpaiditemdue+=(resultset.getInt("c.Issue")-resultset.getInt("c.Paid"));
	
	}
	UTable.setSortandfiltertotalissue(totalissueitem);
	UTable.setSortandfiltertotalreceive(totalreceiveitem);
	UTable.setSortandfiltertotalpaid(totalpaiditem);
	UTable.setSortandfiltertotalreceivedue(totalreceivedueitem);
	UTable.setSortandfilterpaiddue(totalpaiditemdue);

	filterlist.add(new SortAndFilterBean("***TOTAL*** => ",null,0,"********",totalissueitem,totalreceiveitem,totalreceivedueitem,totalpaiditem,totalpaiditemdue,0000,0000));
	
//	SummaryBean summary = new SummaryBean(totalissueitem,totalreceiveitem,totalreceivedueitem,totalpaiditem,totalpaiditemdue);
//	summarylist.add(summary);
//	filter.add(filterlist);
//	filter.add(summarylist);
	
	return filterlist;
	}
	
}
