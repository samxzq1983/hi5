package com.hi5.ff.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hi5.ff.dao.UserDao;
import com.hi5.ff.util.SQLUtil;
import com.hi5.ff.entity.User;

public class UserDao {
	public boolean IsExist(User user){
		String userId=user.getUserId();
		String profileId = user.getProfileId();
		String password = user.getPassword();
		String sql = "select * from user where userid='"+userId+"' and password='"+password+"' and profileid='"+profileId+"'";
		ResultSet rs = SQLUtil.executeQuery(sql);
		try{
		if(rs.next()){
			userId = rs.getString(userId);
		}
		rs.close();
		}catch(SQLException e){
			//e.printStackTrace();
			userId="";
			return false;
		}
		return true;
	}
	
	//test
	public static void main(String args[]){
		User user = new User("tom","f");
		UserDao userDao = new UserDao();
		boolean flag = userDao.IsExist(user);
		System.out.println(flag);
	}
}