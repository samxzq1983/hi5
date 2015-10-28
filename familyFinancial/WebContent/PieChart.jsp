<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

		String strLabelNames = request.getAttribute("labelNames")!=null? request.getAttribute("labelNames").toString() : "";
		String strValues = request.getAttribute("values")!=null?request.getAttribute("values").toString():"";
    %>

    <script>
	    var values;
	    var strValues = '<%=strValues%>';
	    values = strValues.split(",");
	    var colorArray=["#F7464A","#46BFBD","#FDB45C","#949FB1","#4D5360"];
	    var colorHighlightArray= ["#FF5A5E","#5AD3D1","#FFC870","#A8B3C5","#616774"];
	    var labelNames;
	    var strNames = '<%=strLabelNames%>';
	    labelNames = strNames.split(",");

	    var pieData = [];

		window.onload = function(){
			var ctx = document.getElementById("pie-chart").getContext("2d");
			//window.myPie = new Chart(ctx).Pie(pieData);
			var myPie = new Chart(ctx).Pie(pieData);

			for(var i=0;i<values.length;i++){
				//window.myPie.addData({
				myPie.addData({
				    value: parseFloat(values[i]),
				    color: colorArray[i%5],
				    highlight: colorHighlightArray[i%5],
				    label: labelNames[i]
				});
			}
		};

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
						<div class="panel-heading">Pie Chart</div>
						<div class="panel-body">
							<div class="canvas-wrapper">
								<canvas class="main-chart" id="pie-chart" height="200" width="600"></canvas>
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
