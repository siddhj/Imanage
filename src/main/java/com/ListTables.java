package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

/**
 * A sample app that connects to a Cloud SQL instance and lists all available tables in a database.
 */
public class ListTables {
  
	 static Connection connection;
	 final static Logger logger = Logger.getLogger(ListTables.class);	 
	 public static Connection returnConnection(){

	  try {
		GoogleCredential credential = GoogleCredential.getApplicationDefault();
	} catch (IOException e) {
		logger.fatal("Unable to read credential for authentication", e);
	}
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
	 
	   try {
		connection = DriverManager.getConnection(jdbcUrl, username, password);
	} catch (SQLException e) {
		logger.fatal("Not Able to get sql connection", e);
	}

	return connection;
  }
}