<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Records Management</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span>Family</span>Financial</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> User <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>

		</div><!-- /.container-fluid -->
	</nav>

<%@include file="./slidebar.jsp" %>

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">Item</li>
			</ol>
		</div><!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Item</h1>
			</div>
		</div><!--/.row-->


		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Item Category Management</div>
					<div class="panel-body">
						<table data-toggle="table" data-url="tables/data1.json"  data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name" data-sort-order="desc">
						    <thead>
						    <tr>
						        <th data-field="state" data-checkbox="true" >Item ID</th>
						        <th data-field="id" data-sortable="true">Item ID</th>
						        <th data-field="name"  data-sortable="true">Item Name</th>
						        <th data-field="price" data-sortable="true">Item Price</th>
						        <th data-field="remark" data-sortable="true">Item Remarks</th>
						        <th data-field="date" data-sortable="true">Add Date</th>
						        <th data-field="category_id" data-sortable="true">Item category</th>
						        <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">Action</th>
						     </tr>
						    </thead>
						</table>
					</div>
				</div>
			</div>

  		</div><!--/.row-->


<div id="popupModal2" class="modal fade"<table><tr><td></td></tr></table>
tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                	<h3>Title</h3>

            </div>
            <div class="modal-body">
                <iframe src="" style="zoom:0.60" frameborder="0" height="250" width="99.6%"></iframe>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" data-dismiss="modal">OK</button>
            </div>
        </div>
    </div>
</div>


	</div><!--/.main-->

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/bootstrap-table.js"></script>
	<script>
    function operateFormatter(value, row, index) {
        return [
           ' <a class="btn bootpopup" title="">Edit</a>',
            '<a id="remove" class="remove ml10" title="Remove">Remove</a>'
        ].join('');
    }

    window.operateEvents = {
        'click .bootpopup': function (e, value, row, index) {
        	var frametarget = $(this).attr('href');
        	var targetmodal = $(this).attr('target');
        	if (targetmodal == undefined) {
        	targetmodal = '#popupModal2';
        	} else {
        	targetmodal = '#'+targetmodal;
        	}
        	if ($(this).attr('title') != undefined) {
        	$(targetmodal+ ' .modal-header h3').html($(this).attr('title'));
        	$(targetmodal+' .modal-header').show();
        	} else {
        	 $(targetmodal+' .modal-header h3').html('');
        	$(targetmodal+' .modal-header').hide();
        	}
        	$(targetmodal).on('show', function () {
        	    $('iframe').attr("src", frametarget );
        	});
        	$(targetmodal).modal({show:true});
        	return false;
        },
        'click .remove': function (e, value, row, index) {

        	alert(JSON.stringify(row.id));

        }
    };

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