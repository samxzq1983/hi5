package com.hi5.ff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hi5.ff.dao.ItemDao;
import com.hi5.ff.dao.UserDao;
import com.hi5.ff.entity.Item;
import com.hi5.ff.entity.User;

public class DataInputServlet extends HttpServlet{
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
			String itemName = req.getParameter("addItemName");
			double itemPrice = req.getParameter("addItemPrice")!=null && req.getParameter("addItemPrice").trim().length()>0 ? Double.parseDouble(req.getParameter("addItemPrice")) : 0.00;
			String itemRemark = req.getParameter("addItemRemark");
			int categoryId = Integer.parseInt(req.getParameter("addCategroyId"));
		
			int userId = 0;
			if(req.getParameter("addUserId")!=null)
			{
				userId= Integer.parseInt(req.getParameter("addUserId"));
			}
			else
			{
				UserDao userdao = new UserDao();
				User user = new User();
				user = (User)req.getSession(true).getAttribute("user");
				userId = user.getUserId();
			}
			 

			if(itemName!=null && itemName.trim().length()>0){
				actionSuccess = doAddItem(itemName, itemPrice, itemRemark, categoryId, userId);
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Add Data Success";
			}else{
				actionMsg = "Add Data Fail";
			}

		}else if("edit".equalsIgnoreCase(action)){
			String itemName = req.getParameter("editItemName");
			double itemPrice = req.getParameter("editItemPrice")!=null && req.getParameter("editItemPrice").trim().length()>0 ? Double.parseDouble(req.getParameter("editItemPrice")) : 0.00;
			String itemRemark = req.getParameter("editItemRemark");
			int categoryId = Integer.parseInt(req.getParameter("editCategoryId"));
			int userId = Integer.parseInt(req.getParameter("editUserId"));
			int itemId = Integer.parseInt(req.getParameter("editItemId"));

			if(itemName!=null && itemName.trim().length()>0){
				actionSuccess = doEditItem(itemId, itemName, itemPrice, itemRemark, categoryId, userId);
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Edit Item Success";
			}else{
				actionMsg = "Edit Item Failed";
			}


		}else if("remove".equalsIgnoreCase(action)){


			String itemId = req.getParameter("removeItemId");

			if(itemId!=null){

				actionSuccess = doRemoveItem(Integer.parseInt(itemId));
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Remove Item Success";
			}else{
				actionMsg = "Remove Item Failed";
			}

		}else{
			//
		}


		req.setAttribute("actionSuccess", (Boolean)actionSuccess);
		req.setAttribute("actionMsg", actionMsg);


		req.getRequestDispatcher("DataInput.jsp").forward(req, resp);


	}
	
	private boolean doRemoveItem(int itemId) {
		// TODO Auto-generated method stub
		ItemDao itemDao = new ItemDao();
		return itemDao.removeItem(itemId);
	}

	private boolean doEditItem(int itemId, String itemName, double itemPrice, String itemRemark, int categoryId,
			int userId) {
		// TODO Auto-generated method stub
		Item editItem = new Item();
		editItem.setItemId(itemId);
		editItem.setItemName(itemName);
		editItem.setItemPrice(itemPrice);
		editItem.setItemRemark(itemRemark);
		editItem.setCategoryId(categoryId);
		editItem.setUserId(userId);
		ItemDao itemDao = new ItemDao();
		return itemDao.editItem(editItem);
	}
	
	private boolean doAddItem(String itemName, double itemPrice, String itemRemark, int categoryId, int userId) {
		// TODO Auto-generated method stub
		Item addItem = new Item();
		addItem.setItemName(itemName);
		addItem.setItemPrice(itemPrice);
		addItem.setItemRemark(itemRemark);
		addItem.setCategoryId(categoryId);
		addItem.setUserId(userId);
		ItemDao itemDao = new ItemDao();
		return itemDao.addItem(addItem);
	}

	 

}
