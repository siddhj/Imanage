package dao;

import com.ListTables;
import bean.Assignee;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DLoader {
public static 	ObservableList<Assignee> assigneelist;
	public ObservableList<ObservableList<String>> intialLoader() throws SQLException, IOException{
		ListTables table = new ListTables();
		Connection connection = table.returnConnection();
		ObservableList<ObservableList<String>> parentlist = FXCollections.observableArrayList();
		ObservableList<ObservableList<?>>	assigneeloaderlist = assigneeLoader(connection);
		ObservableList<String> namelist = (ObservableList<String>) assigneeloaderlist.get(0);
		assigneelist = (ObservableList<Assignee>) assigneeloaderlist.get(1);
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
		ResultSet result = stmt.executeQuery("SELECT AssigneeID,First_Name,Last_Name FROM assignee");
		ObservableList <String> namelist = FXCollections.observableArrayList();  
		ObservableList<Assignee> assigneelist = FXCollections.observableArrayList();
		
		while(result.next())
		{

		int assigneeid = result.getInt("AssigneeID");	
		String firstname = result.getString("First_Name");	
		String lastname = result.getString("Last_Name");
		System.out.println(firstname);
		Assignee assign = new Assignee(firstname,lastname,assigneeid);
		assigneelist.add(assign);
		namelist.add(firstname);
		}
		ObservableList<ObservableList<?>> parentlist = FXCollections.observableArrayList();
		parentlist.add(namelist);
		parentlist.add(assigneelist);
		return parentlist;
	}
}
