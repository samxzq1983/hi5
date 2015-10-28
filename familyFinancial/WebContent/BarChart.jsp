<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Family Financial - Report</title>

	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/datepicker3.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<%

		Boolean actionSuccess = false;
		if(request.getAttribute("actionSuccess")!=null){
			actionSuccess = (Boolean) request.getAttribute("actionSuccess");
		}

		String actionMsg = "";
		if(request.getAttribute("actionMsg")!=null){
			actionMsg = (String) request.getAttribute("actionMsg");
		}

		String strLabelNames = request.getAttribute("labelNames").toString();
		String strValues = request.getAttribute("values").toString();
		String[] labels = strLabelNames.split(",");
		String[] values = strValues.split(",");

    %>

    <script>


	    var data = {
	    		labels:[
		    	    <%if(labels!=null){
		    	    	for(String lableStr:labels){
		    	    %>
						'<%=lableStr%>',

		    		<%}
		    		}%>
				],
	    	    datasets: [
	    	        {
	    	            label: "",
	    	            fillColor: "rgba(220,220,220,0.5)",
	    	            strokeColor: "rgba(220,220,220,0.8)",
	    	            highlightFill: "rgba(220,220,220,0.75)",
	    	            highlightStroke: "rgba(220,220,220,1)",
	    	            data: [
    	                  <%for(String value:values){%>
    	                  parseFloat('<%=value%>'),
    	                  <%}%>
	    	             ]
	    	        },
	    	    ]
	    	};




		window.onload = function(){
			var ctx = document.getElementById("bar-chart").getContext("2d");
			var myBarChart = new Chart(ctx).Bar(data);
		}

	</script>
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
		<div id="msgArea"></div>
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Report Details</h1>
				<h2><%= request.getAttribute("titleMsg") %></h2>
			</div>
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
		</div>	<!--/.main-->



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



		$(document).ready(function(){
			<%if(actionMsg!=null){
				if(actionSuccess){%>
			$("#msgArea").html("<div class='alert bg-success' role='alert'><span class='glyphicon glyphicon-check'></span><%=actionMsg%><a href='javascript:clearMsgArea()' class='pull-right'><span class='glyphicon glyphicon-remove'></span></a></div>");
				<%}else if(actionMsg!=""){%>
			$("#msgArea").html("<div class='alert bg-danger' role='alert'><span class='glyphicon glyphicon-exclamation-sign'></span><%=actionMsg%><a href='javascript:clearMsgArea()' class='pull-right'><span class='glyphicon glyphicon-remove'></span></a></div>");
				<%}
			}%>
		});

		function clearMsgArea(){
			$('#msgArea').html("");
		}

	</script>
</body>

</html>
