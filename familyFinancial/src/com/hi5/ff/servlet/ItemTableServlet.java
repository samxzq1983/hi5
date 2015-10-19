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
import com.hi5.ff.dao.UserDao;
import com.hi5.ff.entity.Item;
import com.hi5.ff.entity.User;
import com.hi5.ff.util.JSONUtil;

public class ItemTableServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ItemDao itemDao = new ItemDao();
		List<Item> itemList = itemDao.getAllItems();

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
