package dao;

import com.ListTables;
import bean.Assignee;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DLoader {
public static ObservableList<Assignee> assigneelist;

private static final DLoader singletonloader = new DLoader();

private DLoader(){
}

public static DLoader getSingeletonInstanceOfLoader(){
return singletonloader;
}

public ObservableList<Assignee> getAssigneeList() throws SQLException, IOException{
	Connection connection = ListTables.returnConnection();
	Statement stmt = connection.createStatement();
	ResultSet result = stmt.executeQuery("SELECT AssigneeID,Full_Name FROM assignee");
	ObservableList<Assignee> assigneelist  = FXCollections.observableArrayList();
	while(result.next())
	{
		assigneelist.add(new Assignee(result.getString("Full_Name"),result.getInt("AssigneeID")));
	}
	return assigneelist;
}


public ObservableList<ObservableList<String>> intialLoader() throws SQLException, IOException{
		Connection connection = ListTables.returnConnection();
		ObservableList<ObservableList<String>> parentlist = FXCollections.observableArrayList();
		ObservableList<ObservableList<?>>	assigneeloaderlist = assigneeLoader(connection);
		ObservableList<String> namelist = (ObservableList<String>) assigneeloaderlist.get(0);
		//assigneelist = (ObservableList<Assignee>) assigneeloaderlist.get(1);
		ObservableList<String> productlist = productIDLoader(connection);	
		parentlist.add(productlist);parentlist.add(namelist);
		connection.close();
		return parentlist;
		}
	
	public ObservableList<String> productIDLoader(Connection connection) throws SQLException, IOException{
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery("SELECT ProductID FROM product");
		ObservableList <String> productlist = FXCollections.observableArrayList();
		while(result.next())
		{
			productlist.add(result.getString("ProductID"));
		}
		return productlist;
	}
	
	public ObservableList<ObservableList<?>> assigneeLoader(Connection connection) throws SQLException, IOException{
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery("SELECT AssigneeID,Full_Name FROM assignee");
		ObservableList <String> namelist = FXCollections.observableArrayList();  
		ObservableList<Assignee> assigneelist = FXCollections.observableArrayList();
		
		while(result.next())
		{

		int assigneeid = result.getInt("AssigneeID");	
		String fullname = result.getString("Full_Name");

		Assignee assign = new Assignee(fullname,assigneeid);
		assigneelist.add(assign);
		namelist.add(fullname);
		}
		
		ObservableList<ObservableList<?>> parentlist = FXCollections.observableArrayList();
		parentlist.add(namelist);
		parentlist.add(assigneelist);
		return parentlist;
	}
	
	public boolean licenseVerfication(String macaddress,String username,String password) throws SQLException, IOException{
		Connection connection = ListTables.returnConnection();
		String query = "SELECT * FROM license where LicenseKey = ? and username = ? and password = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, macaddress);
		stmt.setString(2, username);
		stmt.setString(3, password);
		ResultSet resultset = stmt.executeQuery();
		resultset.beforeFirst();
		while(resultset.next())
		{
			return true;
		}
		return false;
	}
}
