package com.hi5.ff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hi5.ff.dao.UserDao;
import com.hi5.ff.util.SQLUtil;
import com.hi5.ff.entity.User;

public class UserDao {

	public User getUser(String userName, String password) {

		User user = null;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from user where user_name=? and user_password=?";
		String[] args = { userName, password };
		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setProfileId(rs.getInt("profile_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
			}

		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return user;

	}

	public User getUser(int userId) {

		User user = null;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from user where user_Id=?";
		Object[] args = { userId };
		try {
			pstmt = conn.prepareStatement(sql);
			rs = SQLUtil.executePreparedQuery(pstmt, args);
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setProfileId(rs.getInt("profile_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
			}

		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return user;

	}

	public List<User> getAllUsers() {

		List<User> userList = new ArrayList<User>();
		Connection conn = ConnectionFactory.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM USER order by USER_NAME";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setProfileId(rs.getInt("profile_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));

				userList.add(user);
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			SQLUtil.closeResultSet(rs);
			SQLUtil.closeStatement(stmt);
			SQLUtil.closeConnection(conn);
		}

		return userList;

	}

	public boolean addUser(User newUser) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "insert into user (user_name, profile_id, user_password) values (?,?,?)";
		Object[] args = { newUser.getUserName(), (Integer) newUser.getProfileId(), newUser.getPassword() };

		try {
			pstmt = conn.prepareStatement(sql);
			int r = SQLUtil.executeUpd(pstmt, args);

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		}

		finally {
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return success;

	}

	public boolean editUser(User existUser) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "update user set user_name=?, user_password=?,profile_id=? where user_id=?";
		Object[] args = { existUser.getUserName(), existUser.getPassword(), (Integer) existUser.getProfileId(), existUser.getUserId() };

		try {
			pstmt = conn.prepareStatement(sql);
			int r = SQLUtil.executeUpd(pstmt, args);
			if (r != 1) {
				success = false;
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		}
		finally {
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return success;

	}

	public boolean removeUser(int userId) {
		// TODO Auto-generated method stub
		boolean success = true;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;

		String sql = "delete from user where user_id=?";
		Object[] args = { (Integer) userId };

		try {
			pstmt = conn.prepareStatement(sql);
			int r = SQLUtil.executeUpd(pstmt, args);
			if (r != 1) {
				success = false;
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		}
		finally {
			SQLUtil.closeStatement(pstmt);
			SQLUtil.closeConnection(conn);
		}

		return success;

	}

}
