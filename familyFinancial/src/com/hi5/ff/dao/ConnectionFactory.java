package com.hi5.ff.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hi5.ff.util.FFProperties;

public class ConnectionFactory {


	private static ConnectionFactory instance = new ConnectionFactory();

	public static final String URL = FFProperties.getProperties().getProperty("URL");
	public static final String USER = FFProperties.getProperties().getProperty("USER");
	public static final String PASSWORD = FFProperties.getProperties().getProperty("PASSWORD");
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private ConnectionFactory() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection getConnection() {
		return instance.createConnection();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionFactory.getConnection();
		String sql = "select * from user";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs!=null && rs.next()){
				System.out.println(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
