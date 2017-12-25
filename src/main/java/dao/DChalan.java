package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.ListTables;

import bean.Chalan;
import bean.ChallanDetailBean;
import bean.PopUpChallan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.MicroService;
import service.Notification;

public class DChalan {

	private static final DChalan singletonchalan = new DChalan();

	private DChalan() {
	}

	public static DChalan getSingeletonInstance() {
		return singletonchalan;
	}

	public void chalanDataInsert(Chalan c, long aggregatechallanid) throws SQLException, IOException {
		Connection connection = ListTables.returnConnection();
		connection.setAutoCommit(false);
		String query = "insert into challan(ProductID,AssigneeID,Issue,Receive,Due,BillDateType,PastReceive,BillDate,Description,AmountPaid,AggregateChallanID) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prepare = connection.prepareStatement(query);
		Date date = new Date();
		Object param = new Timestamp(date.getTime());

		try {
			prepare.setString(1, c.getProductid());
			prepare.setInt(2, c.getAssigneeid());
			prepare.setInt(3, c.getIssue());
			prepare.setInt(4, c.getReceive());
			prepare.setInt(5, c.getDue());
			prepare.setDate(6, java.sql.Date.valueOf(c.getBilldate()));
			prepare.setInt(7, c.getTotalreceive());
			prepare.setTimestamp(8, new Timestamp(date.getTime()));
			prepare.setString(9, c.getComment());
			prepare.setInt(10, c.getAmountpaid());
			prepare.setLong(11, aggregatechallanid);
			prepare.addBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			Notification.errorOccuredNotification();
		}
		try {
			prepare.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			Notification.errorOccuredNotification();
		}
	}

	public ObservableList<PopUpChallan> chalanDataLoad(String productidtext, int assigneeid)
			throws SQLException, IOException {
		ListTables chalandata = new ListTables();
		Connection connection = chalandata.returnConnection();
		String query = "select ChallanID,ProductID,Receive,Issue,Due,AssigneeID,BillDate,AmountPaid,AggregateChallanID from challan where ProductID=?and AssigneeID=?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, productidtext);
		stmt.setInt(2, assigneeid);

		ResultSet resultset = stmt.executeQuery();

		ObservableList<PopUpChallan> list = FXCollections.observableArrayList();

		while (resultset.next()) {
			Instant instant = Instant.ofEpochMilli(resultset.getDate("BillDate").getTime());
			LocalDate dateofbill = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

			list.add(new PopUpChallan(resultset.getInt("AssigneeID"), resultset.getInt("Issue"),
					resultset.getInt("Receive"), resultset.getInt("Due"), resultset.getInt("ChallanID"), 0,
					resultset.getString("ProductID"), dateofbill, resultset.getInt("AmountPaid"),
					resultset.getLong("AggregateChallanID")));
		}

		return list;
	}

	public void chalanDataUpdatePopUpWindow(ObservableList<PopUpChallan> popupchallanlist)
			throws SQLException, IOException {
		Connection connection = ListTables.returnConnection();
		connection.setAutoCommit(false);
		String query = "update challan set Receive=?,Due=? where ChallanID=?";
		PreparedStatement prepare = connection.prepareStatement(query);
		for (PopUpChallan p : popupchallanlist) {
			int totalreceive = MicroService.sumReceiveFromPopUp(p.getPastreceive(), p.getCurrentreceive());
			// int totalpaid = MicroService.sumPaidFromPopUp(p.getPastpaid(),
			// p.getCurrentpaid());
			int totaldue = MicroService.sumDueFromPopUp(p.getIssue(), totalreceive);
			int challanid = p.getChallanid();
			try {
				prepare.setInt(1, totalreceive);
				prepare.setInt(2, totaldue);
				// prepare.setInt(3, totalpaid);
				prepare.setInt(3, challanid);
				prepare.addBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			prepare.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int getLastChallanID() throws SQLException, IOException {
		ListTables chalandata = new ListTables();
		Connection connection = chalandata.returnConnection();
		String query = "select ChallanID from challan order by ChallanID desc limit 1";
		PreparedStatement stmt = connection.prepareStatement(query);
		int challanid = 0;
		ResultSet resultset = stmt.executeQuery();
		while (resultset.next()) {
			challanid = resultset.getInt("ChallanID");
		}
		return challanid;
	}

	public void chalanLogDataInsert(ObservableList<PopUpChallan> chalanlist, long referchallanid)
			throws SQLException, IOException {
		Connection connection = ListTables.returnConnection();
		connection.setAutoCommit(false);
		PreparedStatement prepare = connection.prepareStatement(
				"insert into challanlog(BillDate,ChallanID,ReferChallanID,AssigneeID,ProductID,Issue,Receive,BillTimeStamp) "
						+ "values(?,?,?,?,?,?,?,?)");
		Date date = new Date();
		Object param = new Timestamp(date.getTime());

		for (PopUpChallan c : chalanlist) {
			if (!(c.getCurrentreceive() == 0)) {
				try {
					prepare.setDate(1, java.sql.Date.valueOf(c.getBilldate()));
					prepare.setLong(2, c.getAggregatechallanid());
					prepare.setLong(3, referchallanid);
					prepare.setInt(4, c.getAssigneeid());
					prepare.setString(5, c.getProductid());
					prepare.setInt(6, c.getIssue());
					prepare.setInt(7, c.getCurrentreceive());
					prepare.setTimestamp(8, new Timestamp(date.getTime()));
					prepare.addBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					prepare.executeBatch();
					connection.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("no update");
			}
		}
	}

	public ObservableList<ChallanDetailBean> logChallanDataLoad(long challanid) throws SQLException, IOException {
		ListTables chalandata = new ListTables();
		Connection connection = chalandata.returnConnection();
		String query = "Select l.ReferChallanID,l.ChallanID,l.AssigneeID,l.ProductID,l.Issue,l.Receive,l.AmountPaid,l.BillDate,l.BillTimeStamp,"
				+ "a.Full_Name from challanlog as l join assignee as a on l.AssigneeID = a.AssigneeID where l.ReferChallanID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setLong(1, challanid);

		ResultSet resultset = stmt.executeQuery();

		ObservableList<ChallanDetailBean> list = FXCollections.observableArrayList();
		// String assigneename, String productid, Date billdate, int
		// referchallanid, int challanid,
		// int issueitem, int receiveitem, int paiditem

		while (resultset.next()) {
			list.add(new ChallanDetailBean(resultset.getString("a.Full_Name"), resultset.getString("l.ProductID"),
					resultset.getDate("l.BillDate"), resultset.getLong("l.ReferChallanID"),
					resultset.getLong("l.ChallanID"), resultset.getInt("Issue"), resultset.getInt("Receive"),
					resultset.getInt("l.AmountPaid")));
		}
		return list;
	}

	public void insertNewProductID(String productid) throws SQLException, IOException {
		Connection connection = ListTables.returnConnection();
		connection.setAutoCommit(false);
		String query = "insert into product(ProductID) values(?)";
		PreparedStatement prepare = connection.prepareStatement(query);
		try {
			prepare.setString(1, productid);
			prepare.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
