<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Family Financial- Report Details</title>

	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/datepicker3.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">

<%@ page import="com.hi5.ff.entity.*" %>
<%@ page import="com.hi5.ff.dao.*" %>
<%@ page import="java.util.*" %>

<%
User user = (User) request.getSession().getAttribute("user");
String reportType = (String) request.getSession().getAttribute("reportType");
List<Category> categoryList = (List<Category>) request.getSession().getAttribute("categoryList");
List<Item> itemList = (List<Item>) request.getSession().getAttribute("itemList");
List<User> userList = (List<User>) request.getSession().getAttribute("userList"); %>

</head>

<body>
<%@include file="./navbar.jsp" %>

<%@include file="./slidebar.jsp" %>
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">Report Details</li>
			</ol>
		</div><!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Report Details</h1>
				<h2><%= request.getSession().getAttribute("titleMsg") %></h2>
			</div>
		</div><!--/.row-->

		<% if(reportType.equalsIgnoreCase("LINE")) { %>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Line Chart</div>
						<div class="panel-body">
							<div class="canvas-wrapper">
								<canvas class="main-chart" id="line-chart" height="200" width="600"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div><!--/.row-->
		<% } %>

		<% if(reportType.equalsIgnoreCase("BAR")) { %>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Bar Chart</div>
					<div class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="main-chart" id="bar-chart" height="200" width="600"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div><!--/.row-->
		<% } %>

		<% if(reportType.equalsIgnoreCase("PIE")) { %>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">Pie Chart</div>
					<div class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="chart" id="pie-chart" ></canvas>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">Doughnut Chart</div>
					<div class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="chart" id="doughnut-chart" ></canvas>
						</div>
					</div>
				</div>
			</div>
		</div><!--/.row-->
		<% } %>

		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">Items Management</div>
				<div class="panel-body">
					<div id="popupModal1" class="modal fade"
			<table><tr><td></td></tr></table> tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>Title</h3>

					</div>
					<div class="modal-body">
						<div class="panel-heading">Edit Item</div>
						<form id="editForm" role="form"
							action="./itemManagementServlet?action=edit" method="post">

							<div class="form-group">
								<label>Item Name</label> <input class="form-control"
									placeholder="Item Name" name="editItemName" id="editItemName" />
							</div>

							<div class="form-group">
									<label>Item Price</label>
									<input class="form-control" placeholder="Item Price" name="editItemPrice" id="editItemPrice"/>
								</div>

								<div class="form-group">
									<label>Item Remark</label>
									<input class="form-control" placeholder="Item Remark" name="editItemRemark" id="editItemRemark" />
								</div>

								<div class="form-group">
									<label>Category</label>
									<select class="form-control" name="editCategoryId" id="editCategoryId">
										<%if(categoryList!=null){
											for(Category category:categoryList){
										%>
										<option value="<%=category.getCategoryId()%>"><%=category.getCategoryName() %></option>
										<%	}
										} %>
									</select>
								</div>

								<div class="form-group">
									<label>Added By</label>
									<select class="form-control" name="editUserId" id="editUserId">
										<%if(userList!=null){
											for(User thisUser:userList){
										%>
										<option value="<%=thisUser.getUserId()%>"><%=thisUser.getUserName() %></option>
										<%	}
										} %>
									</select>
								</div>
							<input type="hidden" name="editItemId" id="editItemId" />
						</form>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal"
							onclick="javascript:$('#editForm').submit();">Edit</button>
						<button type="reset" class="btn btn-default" data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>
				</div>
			</div>
		</div>

	</div>	<!--/.main-->


	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>

	<script>
		!function ($) {
		    $(document).on("click","ul.nav li.parent > a > span.icon", function(){
		        $(this).find('em:first').toggleClass("glyphicon-minus");
		    });
		    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
	</script>
</body>

</html>
