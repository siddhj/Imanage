package dao;

import bean.Assignee;
import bean.LoginVerification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.MultiScreenFramework;

public class DLoader {
	public static ObservableList<Assignee> assigneelist;

	private static final DLoader singletonloader = new DLoader();
	final static Logger logger = Logger.getLogger(MultiScreenFramework.class);

	private DLoader() {
	}

	public static DLoader getSingeletonInstanceOfLoader() {
		return singletonloader;
	}

	public ObservableList<Assignee> getAssigneeList() throws SQLException, IOException {
		Connection connection = ListTables.returnConnection();
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery("SELECT AssigneeID,Full_Name,GSTIN FROM assignee");
		ObservableList<Assignee> assigneelist = FXCollections.observableArrayList();
		while (result.next()) {
			assigneelist.add(new Assignee(result.getString("Full_Name"), result.getInt("AssigneeID"),
					result.getString("GSTIN")));
		}
		return assigneelist;
	}

	public ObservableList<ObservableList<String>> intialLoader() throws SQLException {
		Connection connection = ListTables.returnConnection();
		ObservableList<ObservableList<String>> parentlist = FXCollections.observableArrayList();
		ObservableList<ObservableList<?>> assigneeloaderlist = assigneeLoader(connection);
		ObservableList<String> namelist = (ObservableList<String>) assigneeloaderlist.get(0);
		// assigneelist = (ObservableList<Assignee>) assigneeloaderlist.get(1);
		ObservableList<String> productlist = productIDLoader(connection);
		parentlist.add(productlist);
		parentlist.add(namelist);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parentlist;
	}

	public ObservableList<String> productIDLoader(Connection connection) throws SQLException {
		ResultSet result = null;
		Statement stmt = connection.createStatement();
		result = stmt.executeQuery("SELECT ProductID FROM product");
		ObservableList<String> productlist = FXCollections.observableArrayList();
		while (result.next()) {
			productlist.add(result.getString("ProductID"));
		}

		return productlist;
	}

	public ObservableList<ObservableList<?>> assigneeLoader(Connection connection) throws SQLException {

		Statement stmt = null;
		ResultSet result = null;
		try {
			stmt = connection.createStatement();
			result = stmt.executeQuery("SELECT AssigneeID,Full_Name,GSTIN FROM assignee");
		} catch (SQLException e) {

		}
		ObservableList<String> namelist = FXCollections.observableArrayList();
		ObservableList<Assignee> assigneelist = FXCollections.observableArrayList();

		while (result.next()) {

			int assigneeid = result.getInt("AssigneeID");
			String fullname = result.getString("Full_Name");
			String gstin = result.getString("GSTIN");
			Assignee assign = new Assignee(fullname, assigneeid, gstin);
			assigneelist.add(assign);
			namelist.add(fullname);
		}
		ObservableList<ObservableList<?>> parentlist = FXCollections.observableArrayList();
		parentlist.add(namelist);
		parentlist.add(assigneelist);
		return parentlist;
	}

	public boolean licenseVerfication(String macaddress, String username, String password)
			throws SQLException, IOException {
		Connection connection = ListTables.returnConnection();
		String query = "SELECT * FROM license where LicenseKey = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, macaddress);
		ResultSet resultset = stmt.executeQuery();
		resultset.beforeFirst();
		while (resultset.next()) {
			return true;
		}
		return false;
	}

	public ObservableList<LoginVerification> usernamepasswordVerfication(String macaddress, String username,
			String password) throws SQLException {
		ObservableList<LoginVerification> loginverificationlist = FXCollections.observableArrayList();
		Connection connection = ListTables.returnConnection();
		String query = "SELECT * FROM license where LicenseKey = ? and username=? and password =?";
		PreparedStatement stmt;
		stmt = connection.prepareStatement(query);
		stmt.setString(1, macaddress);
		stmt.setString(2, username);
		stmt.setString(3, password);

		ResultSet resultset = stmt.executeQuery();
		resultset.beforeFirst();
		while (resultset.next()) {
			loginverificationlist
					.add(new LoginVerification(resultset.getString("ActiveJarVersion"), resultset.getString("FirmName"),
							resultset.getString("GSTIN"), resultset.getString("FileStoreAddress"),
							resultset.getBoolean("NewChallanAccess"), resultset.getBoolean("SortAndFilterAccess"),
							resultset.getBoolean("NewAssigneeAddAccess"), resultset.getString("LogStoreAddress"),
							resultset.getDate("LastLogDate").toLocalDate(), resultset.getInt("LicenseID")));
		}
		return loginverificationlist;
	}
}
