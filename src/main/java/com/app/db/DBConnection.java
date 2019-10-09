package com.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String DB_URL = "jdbc:mysql://127.0.0.1/vishaldb";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		if (connection == null)
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		return connection;
	}

}
