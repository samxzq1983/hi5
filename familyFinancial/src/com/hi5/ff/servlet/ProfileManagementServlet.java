package com.hi5.ff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hi5.ff.dao.ProfileDao;
import com.hi5.ff.dao.UserDao;
import com.hi5.ff.entity.Profile;
import com.hi5.ff.entity.User;
import com.hi5.ff.util.FFProperties;

public class ProfileManagementServlet extends HttpServlet{

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
			String profileName = req.getParameter("addProfileName");
			String profileDesc = req.getParameter("addProfileDesc");

			if(profileName!=null && profileName.trim().length()>0){
				actionSuccess = doAddProfile(profileName, profileDesc);
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Add Profile Success";
			}else{
				actionMsg = "Add Profile Fail";
			}

		}else if("edit".equalsIgnoreCase(action)){
			String profileId = req.getParameter("editProfileId");
			String profileName = req.getParameter("editProfileName");
			String profileDesc = req.getParameter("editProfileDesc");

			if(profileId!=null && profileName!=null && profileName.trim().length()>0){
				actionSuccess = doEditProfile(Integer.parseInt(profileId),profileName, profileDesc);
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Edit Profile Success";
			}else{
				actionMsg = "Edit Profile Failed";
			}


		}else if("remove".equalsIgnoreCase(action)){


			String profileId = req.getParameter("removeProfileId");

			if(profileId!=null){

				actionSuccess = doRemoveProfile(Integer.parseInt(profileId));
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Remove Profile Success";
			}else{
				actionMsg = "Remove Profile Failed";
			}





		}else{
			//
		}

		req.setAttribute("actionSuccess", (Boolean)actionSuccess);
		req.setAttribute("actionMsg", actionMsg);


		req.getRequestDispatcher("Profile.jsp").forward(req, resp);


	}

	private boolean doRemoveProfile(int profileId) {
		// TODO Auto-generated method stub
		ProfileDao profileDao = new ProfileDao();

		boolean success = profileDao.removeProfile(profileId);
		return success;
	}

	private boolean doEditProfile(int profileId, String profileName, String profileDesc) {
		// TODO Auto-generated method stub
		ProfileDao profileDao = new ProfileDao();
		Profile existProfile = new Profile();

		existProfile.setProfileId(profileId);
		existProfile.setProfileName(profileName);
		existProfile.setProfileDesc(profileDesc);


		boolean success = profileDao.editProfile(existProfile);
		return success;
	}

	private boolean doAddProfile(String profileName, String profileDesc) {
		// TODO Auto-generated method stub
		ProfileDao profileDao = new ProfileDao();
		Profile newProfile = new Profile();
		newProfile.setProfileName(profileName);
		newProfile.setProfileDesc(profileDesc);

		boolean success = profileDao.addProfile(newProfile);
		return success;
	}

}
