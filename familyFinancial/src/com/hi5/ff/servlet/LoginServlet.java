package com.hi5.ff.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hi5.ff.dao.UserDao;
import com.hi5.ff.entity.User;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		UserDao userDao = new UserDao();
		//boolean isExist = userDao.IsExist(new User(userName, password));
		User user = userDao.getUser(userName, password);
		if(user!=null){
			req.getSession().setAttribute("user",user);
			req.getRequestDispatcher("User.jsp").forward(req, resp);
		}else{
			resp.sendRedirect("Login.jsp");
		}
	}

}
