package com.hi5.ff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hi5.ff.dao.UserDao;
import com.hi5.ff.entity.User;
import com.hi5.ff.util.FFProperties;

public class UserManagementServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String action = req.getParameter("action") == null ? "": (String) req.getParameter("action");
		boolean actionSuccess = true;
		String actionMsg = "";
		if("add".equalsIgnoreCase(action)){
			String userName = req.getParameter("addUserName");
			String password = req.getParameter("addPassword");
			String profileId = req.getParameter("addProfile");

			if(userName!=null && userName.trim().length()>0 && profileId!=null && Integer.parseInt(profileId) > 0){
				if(password==null || password.trim().length()==0){
					//give default password
					password = FFProperties.getProperties().getProperty("USER_DEFAULT_PASSWORD");
				}
				actionSuccess = doAddUser(userName, password, Integer.parseInt(profileId));
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Add User Success";
			}else{
				actionMsg = "Add User Fail";
			}

		}else if("edit".equalsIgnoreCase(action)){
			String userName = req.getParameter("editUserName");
			String password = req.getParameter("editPassword");
			String profileId = req.getParameter("editProfile");
			String userId = req.getParameter("editUserId");

			if(userName!=null && userName.trim().length()>0 && profileId!=null && Integer.parseInt(profileId) > 0){
				if(password==null || password.trim().length()==0){
					//give default password
					password = FFProperties.getProperties().getProperty("USER_DEFAULT_PASSWORD");
				}
				actionSuccess = doEditUser(Integer.parseInt(userId),userName, password, Integer.parseInt(profileId));
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Edit User Success";
			}else{
				actionMsg = "Edit User Failed";
			}


		}else if("remove".equalsIgnoreCase(action)){


			String userId = req.getParameter("removeUserId");

			if(userId!=null){

				actionSuccess = doRemoveUser(Integer.parseInt(userId));
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Remove User Success";
			}else{
				actionMsg = "Remove User Failed";
			}





		}else{
			//
		}

		req.setAttribute("actionSuccess", (Boolean)actionSuccess);
		req.setAttribute("actionMsg", actionMsg);


		req.getRequestDispatcher("User.jsp").forward(req, resp);


	}

	private boolean doRemoveUser(int userId) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();

		boolean success = userDao.removeUser(userId);
		return success;
	}

	private boolean doEditUser(int userId, String userName, String password, int profileId) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		User existUser = new User();

		existUser.setUserId(userId);
		existUser.setUseName(userName);
		existUser.setProfileId(profileId);
		existUser.setPassword(password);

		boolean success = userDao.editUser(existUser);
		return success;
	}

	private boolean doAddUser(String userName, String password, int profileId) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		User newUser = new User();
		newUser.setUseName(userName);
		newUser.setProfileId(profileId);
		newUser.setPassword(password);

		boolean success = userDao.addUser(newUser);
		return success;
	}

}
