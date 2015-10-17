package com.hi5.ff.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hi5.ff.dao.UserDao;
import com.hi5.ff.util.SQLUtil;
import com.hi5.ff.entity.User;

public class UserDao {
//	public boolean IsExist(User user){
//		int userId=user.getUserId();
//		String profileId = user.getProfileId();
//		String password = user.getPassword();
//		String sql = "select * from user where userid='"+userId+"' and password='"+password+"' and profileid='"+profileId+"'";
//		ResultSet rs = SQLUtil.executeQuery(sql);
//		try{
//		if(rs.next()){
//			userId = rs.getInt(userId);
//		}
//		rs.close();
//		}catch(SQLException e){
//			e.printStackTrace();
////			userId=0;
//			return false;
//		}
//		return true;
//	}

	public User getUser(String userName, String password){

		User user = null;

		String sql = "select * from user where user_name=? and user_password=?";
		String[] args = {userName,password};
		ResultSet rs = SQLUtil.executePreparedQuery(sql, args);
		try{
			if(rs.next()){
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setProfileId(rs.getInt("profile_id"));
				user.setUseName(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;


	}

	public List<User> getAllUsers(){

		List<User> userList = new ArrayList<User>();

		String sql = "select * from user";
		ResultSet rs = SQLUtil.executeQuery(sql);
		try{
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setProfileId(rs.getInt("profile_id"));
				user.setUseName(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));

				userList.add(user);
			}
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return userList;


	}

}
