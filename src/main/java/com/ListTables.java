package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

/**
 * A sample app that connects to a Cloud SQL instance and lists all available tables in a database.
 */
public class ListTables {
  
	 static Connection connection;
//	 
//	public static void main(String[] args) throws IOException, SQLException {
//
//	  GoogleCredential credential = GoogleCredential.getApplicationDefault();
//    // TODO: fill this in
//    // The instance connection name can be obtained from the instance overview page in Cloud Console
//    // or by running "gcloud sql instances describe <instance> | grep connectionName".
//    String instanceConnectionName = "imanagement-187210:us-central1:imdata";
//
//    // TODO: fill this in
//    // The database from which to list tables.
//    String databaseName = "imdata1";
//
//    String username = "root";
//
//    // TODO: fill this in
//    // This is the password that was set via the Cloud Console or empty if never set
//    // (not recommended).
//    String password = "E81J1egqBHbxghCj";
//
//    if (instanceConnectionName.equals("<insert_connection_name>")) {
//      System.err.println("Please update the sample to specify the instance connection name.");
//      System.exit(1);
//    }
//
//    if (password.equals("<insert_password>")) {
//      System.err.println("Please update the sample to specify the mysql password.");
//      System.exit(1);
//    }
//
//    //[START doc-example]
//    String jdbcUrl = String.format(
//        "jdbc:mysql://google/%s?cloudSqlInstance=%s&"
//            + "socketFactory=com.google.cloud.sql.mysql.SocketFactory",
//        databaseName,
//        instanceConnectionName);
// 
//   connection = DriverManager.getConnection(jdbcUrl, username, password);
//   //[END doc-example]
//
//    try (Statement statement = connection.createStatement()) {
//      ResultSet resultSet = statement.executeQuery("SHOW TABLES");
//      while (resultSet.next()) {
//        System.out.println(resultSet.getString(1));
//      }
//    }
//  }
//public ListTables(){
//	try {
//		GoogleCredential credential = GoogleCredential.getApplicationDefault();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}
public static Connection returnConnection() throws SQLException, IOException{

	  GoogleCredential credential = GoogleCredential.getApplicationDefault();
	  String instanceConnectionName = "imanagement-187210:us-central1:imdata";
	  String databaseName = "imdata1";
	  String username = "root";
	  String password = "E81J1egqBHbxghCj";

	    if (instanceConnectionName.equals("<insert_connection_name>")) {
	      System.err.println("Please update the sample to specify the instance connection name.");
	      System.exit(1);
	    }

	    if (password.equals("<insert_password>")) {
	      System.err.println("Please update the sample to specify the mysql password.");
	      System.exit(1);
	    }

	    String jdbcUrl = String.format(
	        "jdbc:mysql://google/%s?cloudSqlInstance=%s&"
	            + "socketFactory=com.google.cloud.sql.mysql.SocketFactory",
	        databaseName,
	        instanceConnectionName);
	 
	   connection = DriverManager.getConnection(jdbcUrl, username, password);

	return connection;
  }
}