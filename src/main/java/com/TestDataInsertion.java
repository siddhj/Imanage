package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import dao.ListTables;

public class TestDataInsertion {

	public static void main(String args[]) throws SQLException
	{
		String address = "C:\\Program Files\\IManage\\log\\log4j-application.log";
			Connection connection = ListTables.returnConnection();
			connection.setAutoCommit(false);
			String query = "update license set LogStoreAddress=? where LicenseID=?";
			PreparedStatement prepare=null;
			prepare = connection.prepareStatement(query);
			prepare.setString(1,address);
			prepare.setInt(2, 17);
//			prepare.setInt(2, 3);
			prepare.execute();
			connection.commit();
//		insertLocalDateTest();
//		compareDateTest();
	}
	public static void insertLocalDateTest() throws SQLException{
		LocalDate localdate = LocalDate.now().minusDays(1);
		java.sql.Date date = java.sql.Date.valueOf(localdate);

		Connection connection = ListTables.returnConnection();
		connection.setAutoCommit(false);
		String query = "update license set LastLogDate=? where LicenseID IN(?,?)";
		PreparedStatement prepare=null;
		prepare = connection.prepareStatement(query);
		prepare.setDate(1,date);
		prepare.setInt(2, 17);
		prepare.setInt(3, 17);
		prepare.execute();
		connection.commit();
	}
	
	public static void compareDateTest() throws SQLException{
		LocalDate localdate = LocalDate.now();
		java.sql.Date sqldate = java.sql.Date.valueOf(localdate);
		Connection connection = ListTables.returnConnection();
		String query = "SELECT * FROM license where LicenseID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, 3);
		ResultSet resultset = stmt.executeQuery();
		resultset.beforeFirst();
		Date date = null;
		while (resultset.next()) {
			 date = resultset.getDate("LastLogDate");
		}
		System.out.println(date);
		System.out.println(sqldate);
		
		if(date.equals(sqldate))
		{
			System.out.println("same");
		}
	}
}
