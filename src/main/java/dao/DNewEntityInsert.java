package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import bean.Assignee;
import javafx.collections.ObservableList;

public class DNewEntityInsert {
	
	private DNewEntityInsert(){
		
	}
	
	private static DNewEntityInsert getSingeltonInstance =  new DNewEntityInsert();
	
	
	public static DNewEntityInsert getGetSingeltonInstance() {
		return getSingeltonInstance;
	}

	public static void setGetSingeltonInstance(DNewEntityInsert getSingeltonInstance) {
		DNewEntityInsert.getSingeltonInstance = getSingeltonInstance;
	}

	public void chalanDataInsert(ObservableList<Assignee> assigneelist) throws SQLException, IOException {
		Connection connection = ListTables.returnConnection();
		connection.setAutoCommit(false);
		Statement statement = connection.createStatement();
		PreparedStatement prepare = connection
				.prepareStatement("insert into assignee(First_Name,Last_Name,Phone_Number,Aadhar_Number,Description) "
						+ "values(?,?,?,?,?)");
		Date date = new Date();

		for (Assignee a : assigneelist) {
			try {
				prepare.setString(1, a.getFirstname());
				prepare.setString(2, a.getLastname());
				prepare.setInt(3, a.getPhonenumber());
				prepare.setInt(4, a.getAadharnumber());
				prepare.setString(5, a.getDescription());
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
	
	public void assigneeDataInsert(String assigneename,String mobileno,String gstin,String aadharno) throws SQLException, IOException {
		Connection connection = ListTables.returnConnection();
		connection.setAutoCommit(false);
		Statement statement = connection.createStatement();
		Date date = new Date();
		date.getTime();
		PreparedStatement prepare = connection
				.prepareStatement("insert into assignee(PhoneNo,AadharNo,Full_Name,GSTIN,BillDate) "
						+ "values(?,?,?,?,?)");
		try {
				prepare.setString(1, mobileno);
				prepare.setString(2, aadharno);
				prepare.setString(3, assigneename);
				prepare.setString(4, gstin);
				prepare.setTimestamp(5, new Timestamp(date.getTime()));
				prepare.execute();
				connection.commit();
				} catch (SQLException e) {
				e.printStackTrace();
				connection.commit();
		}
	}
}
