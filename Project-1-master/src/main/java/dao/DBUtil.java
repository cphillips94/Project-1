package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.SystemException;

public class DBUtil {
	
	public static final Logger LOG = LogManager.getLogger(DBUtil.class);
	
	static Connection conn;
	// Step 1 - Load the driver
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Step 2 - Establish the connection to the database
	// DBUtil must return the same connection again and again
	// MUST NOT return new connection every time the class is called
	
	static Connection obtainConnection() throws SystemException{
		LOG.info("Entering obtainConnection() in DBUtil");

		String URL = "jdbc:postgresql://ip-172-31-80-121.ec2.internal:8888/ers";	
		String CONNECTION_PASSWORD = "mysecretpassword"; 
		String CONNECTION_USERNAME = "postgres"; 
		
		// DESIGN PATTERN - singleton design pattern
		if(conn == null) {
			try {
				conn = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
			} catch (SQLException e) {
				throw new SystemException();
			}
		}
		LOG.info("Exiting obtainConnection() in DBUtil");
		return conn;
	}
	
	static void closeConnection() throws SystemException {
		if(conn != null) {
			try {
				conn.close();

			} catch (SQLException e) {
				throw new SystemException();
			}
		}
		
	}
}