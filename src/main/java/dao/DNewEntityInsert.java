package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import com.ListTables;
import bean.Assignee;
import javafx.collections.ObservableList;

public class DNewEntityInsert {
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

}
