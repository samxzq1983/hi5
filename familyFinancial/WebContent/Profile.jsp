<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<%@ page import="java.util.List" %>
<%@ page import="com.hi5.ff.entity.*" %>
<%@ page import="com.hi5.ff.dao.*" %>
<%

Boolean actionSuccess = false;
if(request.getAttribute("actionSuccess")!=null){
	actionSuccess = (Boolean) request.getAttribute("actionSuccess");
}

String actionMsg = "";
if(request.getAttribute("actionMsg")!=null){
	actionMsg = (String) request.getAttribute("actionMsg");
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
				<li class="active">User Profile</li>
			</ol>
		</div><!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">User Profile</h1>
			</div>
			<div id="msgArea"></div>
		</div><!--/.row-->
			<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Add New Profile</div>
					<div class="panel-body">
						<div class="col-md-8">
							<form role="form" id="addProfileForm" action="./profileManagementServlet?action=add" method="post">

								<div class="form-group">
									<label>Profile Name</label>
									<input class="form-control" placeholder="Profile" name="addProfileName">
								</div>

								<div class="form-group">
									<label>Profile Description</label>
									<input class="form-control" name="addProfileDesc">
								</div>
								<button type="submit" class="btn btn-primary">Add</button>
								<button type="reset" class="btn btn-default">Cancel</button>

						</form>
					</div>
				</div>
			</div><!-- /.col-->

				<div class="panel panel-default">
					<div class="panel-heading">Profile Management</div>
					<div class="panel-body">
						<div class="col-md-8">
					<div class="panel-body">
						<table data-toggle="table" id="table-style" data-url="./reteriveProfileTable" data-row-style="rowStyle" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name" data-sort-order="desc">
						    <thead>
						    <tr>
						        <th data-field="id" data-align="right" >Profile ID</th>
						        <th data-field="name" >Profile Name</th>
						        <th data-field="desc" >Profile Description</th>
						        <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">Action</th>
						    </tr>
						    </thead>
						</table>
						<script>
						    $(function () {
						        $('#hover, #striped, #condensed').click(function () {
						            var classes = 'table';

						            if ($('#hover').prop('checked')) {
						                classes += ' table-hover';
						            }
						            if ($('#condensed').prop('checked')) {
						                classes += ' table-condensed';
						            }
						            $('#table-style').bootstrapTable('destroy')
						                .bootstrapTable({
						                    classes: classes,
						                    striped: $('#striped').prop('checked')
						                });
						        });
						    });

						    function rowStyle(row, index) {
						        var classes = ['active', 'success', 'info', 'warning', 'danger'];

						        if (index % 2 === 0 && index / 2 < classes.length) {
						            return {
						                classes: classes[index / 2]
						            };
						        }
						        return {};
						    }
						</script>
					</div>
				</div>
		</div>
		</div>
		</div>

		</div><!-- /.row -->
	</div><!--/.main-->



		<div id="popupModal1" class="modal fade"
			<table><tr><td></td></tr></table> tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>Title</h3>

					</div>
					<div class="modal-body">
						<div class="panel-heading">Edit Profile</div>
						<form id="editForm" role="form"
							action="./profileManagementServlet?action=edit" method="post">

							<div class="form-group">
								<label>Profile name</label> <input class="form-control"
									placeholder="Profile Name" name="editProfileName" id="editProfileName" />
							</div>

							<div class="form-group">
								<label>Profile Description</label> <input type="text"
									class="form-control" name="editProfileDesc" id="editProfileDesc" />
							</div>

							<input type="hidden" name="editProfileId" id="editProfileId" />
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

		<div id="popupModal2" class="modal fade"
			<table><tr><td></td></tr></table> tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>Title</h3>

					</div>
					<div class="modal-body">
						<div class="panel-heading">Remove Profile</div>
						<form id="removeForm" role="form"
							action="./profileManagementServlet?action=remove" method="post">

							<div class="form-group">
								<label>Profile Name</label> <input class="form-control"
									placeholder="Profile Name" name="removeProfileName" id="removeProfileName" readonly/>
							</div>

							<div class="form-group">
								<label>Profile Description</label> <input class="form-control"
									placeholder="Profile Description" name="removeProfileDesc" id="removeProfileDesc" readonly/>

							</div>
							<input type="hidden" name="removeProfileId" id="removeProfileId" />
						</form>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal"
							onclick="javascript:$('#removeForm').submit();">Remove</button>
						<button type="reset" class="btn btn-default" data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/bootstrap-table.js"></script>
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

		function operateFormatter(value, row, index) {
	        return [
	           ' <a class="btn bootpopup" title="">Edit</a>',
	            '<a id="remove" class="remove ml10" title="Remove">Remove</a>'
	        ].join('');
    	}

		window.operateEvents = {
		        'click .bootpopup': function (e, value, row, index) {
		        	var targetmodal = $(this).attr('target');
		        	targetmodal = '#popupModal1';
		        	if ($(this).attr('title') != undefined) {
		        	$(targetmodal+ ' .modal-header h3').html($(this).attr('title'));
		        	$(targetmodal+' .modal-header').show();
		        	} else {
		        	 $(targetmodal+' .modal-header h3').html('');
		        	$(targetmodal+' .modal-header').hide();
		        	}
		        	$(targetmodal).on('show', function () {
		        	    //$('iframe').attr("src", frametarget );

		        	});
		        	$("#editProfileName").val(row.name);
		        	$("#editProfileDesc").val(row.desc);
		        	$("#editProfileId").val(row.id);
		        	$(targetmodal).modal({show:true});
		        	return false;
		        },
		        'click .remove': function (e, value, row, index) {

		        	var targetmodal = $(this).attr('target');
		        	targetmodal = '#popupModal2';
		        	if ($(this).attr('title') != undefined) {
		        	$(targetmodal+ ' .modal-header h3').html($(this).attr('title'));
		        	$(targetmodal+' .modal-header').show();
		        	} else {
		        	 $(targetmodal+' .modal-header h3').html('');
		        	$(targetmodal+' .modal-header').hide();
		        	}
		        	$(targetmodal).on('show', function () {
		        	    //$('iframe').attr("src", frametarget );

		        	});
		        	$("#removeProfileName").val(row.name);
		        	$("#removeProfileDesc").val(row.desc);
		        	$("#removeProfileId").val(row.id);
		        	$(targetmodal).modal({show:true});
		        	return false;

		        }
		    };


		$(document).ready(function(){
			<%if(actionMsg!=null){
				if(actionSuccess){%>
			$("#msgArea").html("<font color='green'><%=actionMsg%></font>");
				<%}else{%>
			$("#msgArea").html("<font color='red'><%=actionMsg%></font>");
				<%}
			}%>
		});
	</script>
</body>
</html>