package com.hi5.ff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hi5.ff.dao.CategoryDao;
import com.hi5.ff.entity.Category;

public class CategoryManagementServlet extends HttpServlet{

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
			String categoryName = req.getParameter("addCategoryName");

			if(categoryName!=null && categoryName.trim().length()>0){
				actionSuccess = doAddCategory(categoryName);
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Add Category Success";
			}else{
				actionMsg = "Add Category Fail";
			}

		}else if("edit".equalsIgnoreCase(action)){
			String categoryId = req.getParameter("editCategoryId");
			String categoryName = req.getParameter("editCategoryName");

			if(categoryId!=null && categoryName!=null && categoryName.trim().length()>0){
				actionSuccess = doEditCategory(Integer.parseInt(categoryId),categoryName);
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Edit Category Success";
			}else{
				actionMsg = "Edit Category Failed";
			}


		}else if("remove".equalsIgnoreCase(action)){


			String categoryId = req.getParameter("removeCategoryId");

			if(categoryId!=null){

				actionSuccess = doRemoveCategory(Integer.parseInt(categoryId));
			}else{
				actionSuccess =false;
			}

			if(actionSuccess){
				actionMsg = "Remove Category Success";
			}else{
				actionMsg = "Remove Category Failed";
			}





		}else{
			//
		}

		req.setAttribute("actionSuccess", (Boolean)actionSuccess);
		req.setAttribute("actionMsg", actionMsg);


		req.getRequestDispatcher("Category.jsp").forward(req, resp);


	}

	private boolean doRemoveCategory(int cateogryId) {
		// TODO Auto-generated method stub
		CategoryDao categoryDao = new CategoryDao();

		boolean success = categoryDao.removeCategory(cateogryId);
		return success;
	}

	private boolean doEditCategory(int categoryId, String categoryName) {
		// TODO Auto-generated method stub
		CategoryDao categoryDao = new CategoryDao();
		Category existCategory = new Category();

		existCategory.setCategoryId(categoryId);
		existCategory.setCategoryName(categoryName);


		boolean success = categoryDao.editCategory(existCategory);
		return success;
	}

	private boolean doAddCategory(String categoryName) {
		// TODO Auto-generated method stub
		CategoryDao categoryDao = new CategoryDao();
		Category newCategory = new Category();
		newCategory.setCategoryName(categoryName);

		boolean success = categoryDao.addCategory(newCategory);
		return success;
	}

}
