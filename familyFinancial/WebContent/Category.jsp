<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

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


	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/bootstrap-table.js"></script>

</head>
<body>
<%@include file="./navbar.jsp" %>

<%@include file="./slidebar.jsp" %>

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">Item Category</li>
			</ol>
		</div><!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Item Category</h1>
			</div>
		</div><!--/.row-->
		<div id="msgArea"></div>


		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Add New Item Category</div>
					<div class="panel-body">
						<div class="col-md-6">
							<form role="form" id="addCategoryForm" action="./categoryManagementServlet?action=add" method="post">
								<div class="form-group">
									<label>Category Name</label>
									<input class="form-control" name="addCategoryName">
								</div>
								<button type="submit" class="btn btn-primary">Add</button>
								<button type="reset" class="btn btn-default">Cancel</button>
						</form>
						</div>
					</div>
				</div><!-- /.col-->

			<div class="panel panel-default">
				<div class="panel-heading">Item Category Management</div>
				<div class="panel-body">
					<div class="col-md-8">
					<div class="panel-body">
						<table data-toggle="table" id="table-style" data-url="./reteriveCategoryTable" data-row-style="rowStyle" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="id" data-sort-order="desc">
						    <thead>
						    <tr>
						        <th data-field="id" data-align="right" >Item ID</th>
						        <th data-field="name" >Item Name</th>
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
						<div class="panel-heading">Edit Category</div>
						<form id="editForm" role="form"
							action="./categoryManagementServlet?action=edit" method="post">

							<div class="form-group">
								<label>Category name</label> <input class="form-control"
									placeholder="Category Name" name="editCategoryName" id="editCategoryName" />
							</div>


							<input type="hidden" name="editCategoryId" id="editCategoryId" />
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
						<div class="panel-heading">Remove Category</div>
						<form id="removeForm" role="form"
							action="./categoryManagementServlet?action=remove" method="post">

							<div class="form-group">
								<label>Category Name</label> <input class="form-control"
									placeholder="Profile Name" name="removeCategoryName" id="removeCategoryName" readonly/>
							</div>

							<input type="hidden" name="removeCategoryId" id="removeCategoryId" />
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
		        	$("#editCategoryName").val(row.name);
		        	$("#editCategoryId").val(row.id);
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
		        	$("#removeCategoryName").val(row.name);
		        	$("#removeCategoryId").val(row.id);
		        	$(targetmodal).modal({show:true});
		        	return false;

		        }
		    };


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
