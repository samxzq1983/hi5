package com.hi5.ff.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.hi5.ff.dao.ItemDao;
import com.hi5.ff.entity.Item;
import com.hi5.ff.entity.User;
import com.hi5.ff.util.JSONUtil;

public class DataInputTableServlet extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			doPost(req,resp);
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {

			ItemDao itemDao = new ItemDao();
			Object userObj = req.getSession().getAttribute("user");
			if(userObj == null){
				throw new ServletException("User Object not exist in session");
			}
			User user = (User) userObj;
			List<Item> itemList = itemDao.getAllItemsByUser(user.getUserId());

			JSONArray itemJArray = new JSONArray();
			try {
				itemJArray = JSONUtil.genJSONArray(itemList);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.setContentType("application/Json");
			resp.getWriter().println(itemJArray);

		}
}
