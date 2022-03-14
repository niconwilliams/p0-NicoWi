package com.revature.utils;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton utility for creating and retrieving database connection
 */
public class ConnectionUtil {
	private static ConnectionUtil cu = null;
	private static Properties prop;
	private static Connection conn;
	private static final String url ="jdbc:mysql://localhost:3306/Revature";
	private static String username = null;
	private static String password = null;

	/**
	 * This method should read in the "database.properties" file and load the values
	 * into the Properties variable
	 */
	private ConnectionUtil() {
		Properties properties = new Properties();
        try {
            FileInputStream fileStream = new FileInputStream(".\\src\\main\\resources\\database.properties"); 
            properties.load(fileStream);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
		
	}

	public static synchronized ConnectionUtil getConnectionUtil() {
		if (cu == null)
			cu = new ConnectionUtil();
		return cu;
	}

	/**
	 * This method should create and return a Connection object
	 * 
	 * @return a Connection to the database
	 */
	public static Connection getConnection() {
		// Hint: use the Properties variable to setup your Connection object
	
		try { 
			getConnectionUtil();
			conn = DriverManager.getConnection(url, "root", "root123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
