package com.hi5.ff.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hi5.ff.dao.*;
import com.hi5.ff.entity.*;
import com.hi5.ff.util.FFConstants;
import com.hi5.ff.util.FFProperties;

import java.util.*;

public class ReportServlet extends HttpServlet {

	ItemDao itemDao = new ItemDao();
	CategoryDao categoryDao = new CategoryDao();
	UserDao userDao = new UserDao();
	String reportPage = "";
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action") == null ? "" : (String) req.getParameter("action");
		boolean actionSuccess = true;
		String userName = "";

		if(action.equalsIgnoreCase("filterReport")) {
			int userid = Integer.parseInt(req.getParameter("ddlUserid"));
			System.out.println("Userid = " + userid + " " + ((userid == FFConstants.EVERYBODY) ? "Everybody" : userDao.getUser(userid).getUserName()));

			String strStartDate = req.getParameter("strStartDate");
			System.out.println("strStartDate = " + strStartDate);

			String strEndDate = req.getParameter("strEndDate");
			System.out.println("strEndDate = " + strEndDate);

			String reportType = req.getParameter("ddlReportType");
			System.out.println("reportType = " + reportType);

			String startDateTokens[] = strStartDate.split("/");
			String sqlStartDate = startDateTokens[2] + "-" + startDateTokens[0] + "-" + startDateTokens[1];

			String endDateTokens[] = strEndDate.split("/");
			String sqlEndDate = endDateTokens[2] + "-" + endDateTokens[0] + "-" + endDateTokens[1];


			if(userid == FFConstants.EVERYBODY)
				userName = "Entire Family";
			else
				userName = userDao.getUser(userid).getUserName();

			req.setAttribute("titleMsg", "Report for " + userName + " between " + startDateTokens[1] + " " + FFConstants.MONTH[Integer.parseInt(startDateTokens[0])] + " " + startDateTokens[2] + " and " + endDateTokens[1] + " " + FFConstants.MONTH[Integer.parseInt(endDateTokens[0])] + " " + endDateTokens[2]);
           /*
			switch(reportType) {
				case("LINE"):
					actionSuccess = doLineChart(userid, sqlStartDate, sqlEndDate, req);
					reportPage = "LineChart.jsp";
					break;

				case("PIE"):
					actionSuccess = doPieChart(userid, sqlStartDate, sqlEndDate, req);
					reportPage = "PieChart.jsp";
					break;
				case("BAR"):
					actionSuccess = doBarChart(userid, sqlStartDate, sqlEndDate, req);
					reportPage = "BarChart.jsp";
					break;
			}
			*/
			//new start
			if(reportType.equals("LINE")){
				actionSuccess = doLineChart(userid, sqlStartDate, sqlEndDate, req);
				reportPage = "LineChart.jsp";
			}else if(reportType.equals("PIE")){
				actionSuccess = doPieChart(userid, sqlStartDate, sqlEndDate, req);
				reportPage = "PieChart.jsp";
			}else if(reportType.equals("BAR")){
				actionSuccess = doBarChart(userid, sqlStartDate, sqlEndDate, req);
				reportPage = "BarChart.jsp";
			}
			
			//new end
			 

		}

		req.setAttribute("actionSuccess", (Boolean) actionSuccess);

		//if(actionSuccess)
			//req.getRequestDispatcher("ReportOri.jsp").forward(req, resp);
			req.getRequestDispatcher(reportPage).forward(req, resp);
	}

	private boolean doLineChart(int userid, String startDate, String endDdte, HttpServletRequest req) {
		List<DayExpenditure> itemList = itemDao.getAllUserItemsByDurationForReporting(userid, startDate, endDdte);
		String xAxis = "";
		String yAxis = "";
		System.out.println("Size = " + itemList.size());

		if(itemList ==null || itemList.isEmpty()){
			req.setAttribute("actionMsg", "No Records found");
			return false;
		}

		for(DayExpenditure item:itemList) {
			xAxis += "" + dateFormat.format(item.getDate()) + ",";
			yAxis += item.getAmount() + ",";
		}

		xAxis = xAxis.substring(0, xAxis.length() - 1);
		yAxis = yAxis.substring(0,  yAxis.length() - 1);

		req.setAttribute("xAxis", xAxis);
		req.setAttribute("yAxis", yAxis);
		return true;
	}

	private boolean doPieChart(int userid, String startDate, String endDate, HttpServletRequest req) {
		List<CategoryExpenditure> itemList = itemDao.getUserExpenditureByCategoryAndDurationForReporting(userid, startDate, endDate);
		String labelNames = "";
		String values = "";
		System.out.println("Size = " + itemList.size());

		if(itemList ==null || itemList.isEmpty()){
			req.setAttribute("actionMsg", "No Records found");
			return false;
		}

		for(CategoryExpenditure item:itemList) {
			labelNames += categoryDao.getCategoryNameById(item.getCategoryID()) + ",";
			values += item.getExpenditure() + ",";
		}

		labelNames = labelNames.substring(0, labelNames.length() - 1);
		values = values.substring(0,  values.length() - 1);

		System.out.println("Labels = " + labelNames);
		System.out.println("Values = " + values);
		req.setAttribute("labelNames", labelNames);
		req.setAttribute("values", values);
		return true;
	}


	private boolean doBarChart(int userid, String startDate, String endDate,  HttpServletRequest req) {

		List<CategoryExpenditure> itemList = itemDao.getUserExpenditureForBarChart(userid, startDate, endDate);

		if(itemList ==null || itemList.isEmpty()){
			req.setAttribute("actionMsg", "No Records found");
			return false;
		}

		String labelNames = "";
		String values = "";

		for(CategoryExpenditure item:itemList) {
			labelNames += categoryDao.getCategoryNameById(item.getCategoryID()) + ",";
			values += item.getExpenditure() + ",";
		}

		req.setAttribute("labelNames", labelNames);
		req.setAttribute("values", values);


		return true;
	}



}
