package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import com.ListTables;

import bean.Chalan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DChalan {
	public static void main(String args[]) throws SQLException, IOException
	{
		new DChalan().chalanDataLoad();
	}
	
	public void chalanDataInsert(ObservableList<Chalan> chalanlist) throws SQLException, IOException{
		ListTables chalandata = new ListTables();
		Connection connection = chalandata.returnConnection();
		connection.setAutoCommit(false);
		Statement statement = connection.createStatement();
		PreparedStatement prepare = connection.prepareStatement("insert into challan(ProductID,AssigneeID,Issue,Receive,Due,Billdate,Paid) "
				+ "values(?,?,?,?,?,?,?)");
		// what about throw clause at the top
		Date date = new Date();
		Object param = new Timestamp(date.getTime());
		chalanlist.forEach(c ->{
		try {
			prepare.setString(1, c.getProductid());
			prepare.setInt(2, c.getAssigneeid());
			prepare.setInt(3, c.getIssue());
			prepare.setInt(4, c.getReceive());
			prepare.setInt(5, c.getDue());
		//Timestamp is not what we need
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
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	public ObservableList<Chalan> chalanDataLoad() throws SQLException, IOException{
		ListTables chalandata = new ListTables();
		Connection connection = chalandata.returnConnection();
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery("select * from chalan");
		ObservableList<Chalan> list = FXCollections.observableArrayList();
				
		while(resultset.next())
		{
		// to be changed according to new chalan bean
//			list.add(new Chalan(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getString(5)));

		}
		return list;
	}
}
