package com.hi5.ff.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLUtil {

	public static void closeConnection(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public static void closeStatement(Statement statement) {
		if (statement != null)
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public static ResultSet executePreparedQuery(PreparedStatement pstmt, Object[] args) throws SQLException {

		ResultSet rs = null;

		for (int i = 1; i <= args.length; i++) {
			Object arg = args[i - 1];
			if (arg instanceof Integer) {
				pstmt.setInt(i, (Integer) arg);
			} else {
				pstmt.setString(i, (String) arg);
			}
		}
		rs = pstmt.executeQuery();

		return rs;

	}

	public static int executeUpd(PreparedStatement pstmt, Object[] args) throws SQLException {
		// TODO Auto-generated method stub

		for (int i = 1; i <= args.length; i++) {
			Object arg = args[i - 1];
			if (arg instanceof Integer) {
				pstmt.setInt(i, (Integer) arg);
			}else if(arg instanceof Double){
				pstmt.setDouble(i, (Double) arg);
			}
			else {
				pstmt.setString(i, (String) arg);
			}

		}
		return pstmt.executeUpdate();


	}

}
