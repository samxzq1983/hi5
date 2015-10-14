package com.hi5.ff.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLUtil {
	public static Connection conn=null;
	public SQLUtil(){
		if(conn!=null)
			return ;
		getConnection();
	}
	public static Connection getConnection(){
		if(conn!=null)
			return conn;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			//e.printStackTrace();
			System.out.println(" ");
			return null;
		}
		try{


			String url=FFProperties.getProperties().getProperty("URL");
			String user=FFProperties.getProperties().getProperty("USER");;
			String paw=FFProperties.getProperties().getProperty("PASSWORD");;
			conn=DriverManager.getConnection(url,user,paw);
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println(" ");
			return null;
		}
		return conn;
	}



	public static ResultSet executeQuery(String sql){
		getConnection();
		if(conn==null){
			System.out.println(" ");
			return null;
		}
		Statement stmt = null;
		ResultSet rs;

		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		}catch(SQLException e){
			System.out.println(" ");
			//e.printStackTrace();
			return null;
		}
		return rs;
	}
	public static void executeUpdate(String sql){
		getConnection();
		if(conn==null){
			System.out.println(" ");
			return ;
		}
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}catch(SQLException e){
			System.out.println(" ");
			//e.printStackTrace();
			return;
		}
	}

	//test main
	public static void main(String args[]){
		String sql = "select * from user";
		SQLUtil su = new SQLUtil();
		ResultSet rs = su.executeQuery(sql);
		try{
		if(rs.next()){
			System.out.println(rs.getString("username"));
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
