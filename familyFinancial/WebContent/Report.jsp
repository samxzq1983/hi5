<!DOCTYPE html>
<html dir="ltr" lang="en-US">
<head>
	<meta charset="UTF-8" />
	<title>Family Financial - Report</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/datepicker3.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">

	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/moment.js"></script>
	<script type="text/javascript" src="js/daterangepicker.js"></script>
	<%@ page session="true" %>
	<%@ page import="java.util.List" %>
	<%@ page import="java.util.Locale" %>
	<%@ page import="java.util.Calendar" %>
	<%@ page import="java.text.DecimalFormat" %>
	<%@ page import="com.hi5.ff.entity.*" %>
	<%@ page import="com.hi5.ff.dao.*" %>
	<%
	User user = null;
	Calendar cal = Calendar.getInstance();
	DecimalFormat df = new DecimalFormat("#.00");
	double expenditure = 0.00;
	String currentMonth = "";
	ItemDao itemDao = null;
	CategoryDao categoryDao = null;
	List<Category> categoryList = null;
	String colourOptions[] = {"easypiechart-blue", "easypiechart-orange", "easypiechart-teal", "easypiechart-red"};

	UserDao userDao = new UserDao();
	List<User> userList = userDao.getAllUsers();

	try {
		user = (User) request.getSession().getAttribute("user");
		itemDao = new ItemDao();
		currentMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + cal.get(Calendar.YEAR);
		expenditure = itemDao.getUserExpenditureByMonth(user.getUserId(), cal.get(Calendar.MONTH) + 1);
		categoryDao = new CategoryDao();
		categoryList = categoryDao.getAllCategories();
	}

	catch(NullPointerException npe) {%>
		<jsp:forward page="Login.jsp" />
	<%
	}
	%>
</head>
<body>
<%@include file="./navbar.jsp" %>
<%@include file="./slidebar.jsp" %>
      <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">Report</li>
			</ol>
		</div><!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Report</h1>
			</div>
		</div><!--/.row-->

        <div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Select Report Type</div>
					<div class="panel-body">
						<div class="col-md-6">
							<form id="frmReportType" role="form" action="./ReportServlet?action=filterReport" method="post">
								<div class="form-group">
									<label>User</label>
									<% if(session.getAttribute("user")!=null && session.getAttribute("isAdminUser")!=null && (session.getAttribute("isAdminUser").equals("Y"))){%>
										<select class="form-control" name="ddlUserid">
											<option value="-1">-- [SELECT USER] --</option>
											<%if(userList!=null){
												for(User thisUser:userList){
											%>
											<option value="<%=thisUser.getUserId()%>"><%=thisUser.getUserName() %></option>
											<%	}
											} %>
											<option value="-999">Entire Family</option>
										</select>
									<%} else {%>
											<label><%= user.getUserName() %></label>
											<input type="hidden" name="ddlUserid" value="<%= user.getUserId() %>" />
									<%} %>
								</div>

								<!--
								<div class="form-group">
									<label>Report Interval</label>
									<select class="form-control" name="ddlReportInterval">
										<option value="-1">-- [SELECT REPORT INTERVAL] --</option>
										<option value="WEEKLY">Weekly</option>
										<option value="MONTHLY">Monthly</option>
										<option value="YEARLY">Yearly</option>
									</select>
								</div>
								 -->
								<div class="form-group">
									<label for="startDate">Start Date</label>
									<input type="text" name="strStartDate" class="form-control" id="startDate" placeholder="YYYY-MM-DD">
								</div>

								<div class="form-group">
									<label for="endDate">End Date</label>
									<input type="text" name="strEndDate" class="form-control" id="endDate" placeholder="YYYY-MM-DD">
								</div>

								<div class="form-group">
									<label for="endDate">Report Type</label>
									<select class="form-control" name="ddlReportType">
										<option value="-1">-- [SELECT REPORT TYPE] --</option>
										<option value="LINE">Line Chart</option>
										<option value="PIE">Pie Chart</option>
										<option value="BAR">BAR Chart</option>
									</select>
								</div>

								<button type="submit" class="btn btn-primary">Retrieve Report</button>
								<button type="reset" class="btn btn-default" >Reset</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div><!--/.row-->

		</div>
      <script type="text/javascript">
      $(document).ready(function() {
        $('#startDate').daterangepicker({
          singleDatePicker: true,
          dateFormat: 'dd-mm-yyyy',
          startDate: moment().subtract(7, 'days')
        });

        $('#endDate').daterangepicker({
          singleDatePicker: true,
          dateFormat: 'dd-mm-yyyy',
          startDate: moment()
        });

      });
      </script>

   </body>
</html>
