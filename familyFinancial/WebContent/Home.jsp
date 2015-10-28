<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Family - Financial</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
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
				<li class="active">Home</li>
			</ol>
		</div><!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Home</h1>
				<h2>Total expenditure for <%= user.getUserName() %> in <%= currentMonth %>: S$<%= df.format(expenditure) %></h2>
			</div>
		</div><!--/.row-->
		
		<div class="row">
			<%
				double categoryExpenditure = 0.00;
					int i = 0;
					for(Category category:categoryList) {
						categoryExpenditure = itemDao.getUserExpenditureByCategoryAndMonth(user.getUserId(), category.getCategoryId(), cal.get(Calendar.MONTH) + 1);
			%>
					<div class="col-xs-6 col-md-3">
						<div class="panel panel-default">
							<div class="panel-body easypiechart-panel">
								<h4><%= category.getCategoryName() %></h4>
								<div class="easypiechart" id="<%= colourOptions[i] %>" data-percent="<%= categoryExpenditure %>" ><span class="percent"><%= df.format(categoryExpenditure/expenditure*100) %>%</span>
								</div>
							</div>
						</div>
					</div>
			<%
					i = (i + 1) % 3;
				}
			%>
			
			
		</div><!--/.row-->

		<div class="row">
			<!--<div class="col-md-8">

			</div>/.col-->

			<div class="col-md-8">

				<div class="panel panel-blue">
					<div class="panel-heading dark-overlay"><span class="glyphicon glyphicon-check"></span>plan list</div>
					<div class="panel-body">
						<ul class="todo-list">
						<li class="todo-list-item">
								<div class="checkbox">
									<input type="checkbox" id="checkbox" />
									<label for="checkbox">Make a plan for today</label>
								</div>
								<div class="pull-right action-buttons">
									<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
									<a href="#" class="flag"><span class="glyphicon glyphicon-flag"></span></a>
									<a href="#" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
								</div>
							</li>
							<li class="todo-list-item">
								<div class="checkbox">
									<input type="checkbox" id="checkbox" />
									<label for="checkbox">Update Basecamp</label>
								</div>
								<div class="pull-right action-buttons">
									<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
									<a href="#" class="flag"><span class="glyphicon glyphicon-flag"></span></a>
									<a href="#" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
								</div>
							</li>
							<li class="todo-list-item">
								<div class="checkbox">
									<input type="checkbox" id="checkbox" />
									<label for="checkbox">Send email to Jane</label>
								</div>
								<div class="pull-right action-buttons">
									<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
									<a href="#" class="flag"><span class="glyphicon glyphicon-flag"></span></a>
									<a href="#" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
								</div>
							</li>
							<li class="todo-list-item">
								<div class="checkbox">
									<input type="checkbox" id="checkbox" />
									<label for="checkbox">Drink coffee</label>
								</div>
								<div class="pull-right action-buttons">
									<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
									<a href="#" class="flag"><span class="glyphicon glyphicon-flag"></span></a>
									<a href="#" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
								</div>
							</li>
							<li class="todo-list-item">
								<div class="checkbox">
									<input type="checkbox" id="checkbox" />
									<label for="checkbox">Do some work</label>
								</div>
								<div class="pull-right action-buttons">
									<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
									<a href="#" class="flag"><span class="glyphicon glyphicon-flag"></span></a>
									<a href="#" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
								</div>
							</li>
							<li class="todo-list-item">
								<div class="checkbox">
									<input type="checkbox" id="checkbox" />
									<label for="checkbox">Tidy up workspace</label>
								</div>
								<div class="pull-right action-buttons">
									<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
									<a href="#" class="flag"><span class="glyphicon glyphicon-flag"></span></a>
									<a href="#" class="trash"><span class="glyphicon glyphicon-trash"></span></a>
								</div>
							</li>
						</ul>
					</div>
					<div class="panel-footer">
						<div class="input-group">
							<input id="btn-input" type="text" class="form-control input-md" placeholder="Add new task" />
							<span class="input-group-btn">
								<button class="btn btn-primary btn-md" id="btn-todo">Add</button>
							</span>
						</div>
					</div>
				</div>

			</div><!--/.col-->
		</div><!--/.row-->
	</div>	<!--/.main-->

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script>
		$('#calendar').datepicker({
		});

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