package com.hi5.ff.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.hi5.ff.dao.ProfileDao;
import com.hi5.ff.dao.UserDao;
import com.hi5.ff.entity.Profile;
import com.hi5.ff.entity.User;
import com.hi5.ff.util.JSONUtil;

public class ProfileTableServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ProfileDao profileDao = new ProfileDao();
		List<Profile> profileList = profileDao.getAllProfiles();

		JSONArray userJArray = new JSONArray();
		try {
			userJArray = JSONUtil.genJSONArray(profileList);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setContentType("application/Json");
		resp.getWriter().println(userJArray);

	}

}
