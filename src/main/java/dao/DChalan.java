package dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import com.ListTables;

import bean.Chalan;
import bean.PopUpChallan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DChalan {
	public static void main(String args[]) throws SQLException, IOException {
//		new DChalan().chalanDataLoad("21D", 1).forEach(c -> System.out.println(c.getDue()));
	}

	public void chalanDataInsert(ObservableList<Chalan> chalanlist) throws SQLException, IOException {
		Connection connection = ListTables.returnConnection();
		connection.setAutoCommit(false);
		Statement statement = connection.createStatement();
		PreparedStatement prepare = connection.prepareStatement(
				"insert into challan(ProductID,AssigneeID,Issue,Receive,Due,Billdate,Paid) " + "values(?,?,?,?,?,?,?)");
		// what about throw clause at the top
		Date date = new Date();
		Object param = new Timestamp(date.getTime());

		for (Chalan c : chalanlist) {
			try {
				prepare.setString(1, c.getProductid());
				prepare.setInt(2, c.getAssigneeid());
				prepare.setInt(3, c.getIssue());
				prepare.setInt(4, c.getReceive());
				prepare.setInt(5, c.getDue());
				// Timestamp is not what we need
				prepare.setTimestamp(6, new Timestamp(date.getTime()));
				prepare.setInt(7, c.getPaid());
				prepare.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				prepare.executeBatch();
				connection.commit();
				// statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// not working why
		// chalanlist.forEach(c ->{
		// try {
		// prepare.setString(1, c.getProductid());
		// prepare.setInt(2, c.getAssigneeid());
		// prepare.setInt(3, c.getIssue());
		// prepare.setInt(4, c.getReceive());
		// prepare.setInt(5, c.getDue());
		// //Timestamp is not what we need
		// prepare.setTimestamp(6, new Timestamp(date.getTime()));
		// prepare.setInt(7, c.getPaid());
		// prepare.addBatch();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// try {
		// prepare.executeBatch();
		// connection.commit();
		// statement.close();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// });
	}

	public ObservableList<PopUpChallan> chalanDataLoad(String productidtext, int assigneeid)
			throws SQLException, IOException {
		ListTables chalandata = new ListTables();
		Connection connection = chalandata.returnConnection();
		String query = "select ChallanID,ProductID,Receive,Issue,Due,Paid,AssigneeID,BillDate from challan where ProductID=?and AssigneeID=?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, productidtext);
		stmt.setInt(2, assigneeid);

		ResultSet resultset = stmt.executeQuery();

		ObservableList<PopUpChallan> list = FXCollections.observableArrayList();

		while (resultset.next()) {
			// int assigneeid, int issue, int pastreceive, int pastdue, int
			// pastpaid, int challanid,
			// int currentreceive, int currentpaid, String productid, Date
			// billdate
			list.add(new PopUpChallan( resultset.getInt("AssigneeID"),resultset.getInt("Issue"),resultset.getInt("Receive"), resultset.getInt("Due")
					,resultset.getInt("Paid"),resultset.getInt("ChallanID"),0,0,resultset.getString("ProductID"),resultset.getDate("BillDate")));
		}
		return list;
	}

	public void chalanDataUpdatePopUpWindow(ObservableList<PopUpChallan> chalanlist) throws SQLException, IOException {
		ListTables chalandata = new ListTables();
		Connection connection = chalandata.returnConnection();
		connection.setAutoCommit(false);
		Statement statement = connection.createStatement();
		PreparedStatement prepare = connection
				.prepareStatement("update challan set Receive=?,Due=?,Paid=? where ChallanID=?");
		Date date = new Date();
		Object param = new Timestamp(date.getTime());
		for (PopUpChallan c : chalanlist) {
			try {
//				prepare.setInt(1, c.getReceive());
//				prepare.setInt(2, c.getDue());
//				prepare.setInt(3, c.getPaid());
				prepare.setInt(4, c.getChallanid());
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

		}
	}

}
