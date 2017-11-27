package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ListTables;

import bean.Chalan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DChalan {

	public void chalanDataInsert(){
		
	}
	public static void main(String args[]) throws SQLException, IOException
	{
		new DChalan().chalanDataLoad();
	}
	
	public ObservableList<Chalan> chalanDataLoad() throws SQLException, IOException{
		ListTables chalandata = new ListTables();
		Connection connection = chalandata.returnConnection();
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery("select * from chalan");
		ObservableList<Chalan> list = FXCollections.observableArrayList();
				
		while(resultset.next())
		{
			list.add(new Chalan(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getString(5)));
		}
		return list;
	}
}
