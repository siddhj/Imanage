package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public boolean isAssigneeDeatilAlreadyPresent(String gstin,String mobileno) throws SQLException{
		Connection connection = ListTables.returnConnection();
		connection.setAutoCommit(false);
		Date date = new Date();
		date.getTime();
		PreparedStatement prepare = connection
				.prepareStatement("select * from assignee where GSTIN=? OR PhoneNo=?");
		prepare.setString(1, gstin);
		prepare.setString(2, mobileno);
		ResultSet result = prepare.executeQuery();
		while(result.next())
		{
			return false;
		}
		return true;
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
