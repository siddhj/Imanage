package DataConnectionThread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ListTables;

public class TestDataInsertion {

	public static void main(String args[]) throws SQLException
	{
		String address = "C:\\Program Files\\IManage\\log4j-application.log";
			Connection connection = ListTables.returnConnection();
			connection.setAutoCommit(false);
			String query = "update license set FileStoreAddress=? where LicenseID=?";
			PreparedStatement prepare=null;
			prepare = connection.prepareStatement(query);
			prepare.setString(1,address);
			prepare.setInt(2, 2);
			prepare.execute();
			connection.commit();
	}

	
}
