<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Items Management</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">
<%@ page import="java.util.List" %>
<%@ page import="com.hi5.ff.entity.*" %>
<%@ page import="com.hi5.ff.dao.*" %>
<%
UserDao userDao = new UserDao();
List<User> userList = userDao.getAllUsers();

CategoryDao categoryDao = new CategoryDao();
List<Category> categoryList = categoryDao.getAllCategories();

Boolean actionSuccess = false;
if(request.getAttribute("actionSuccess")!=null){
	actionSuccess = (Boolean) request.getAttribute("actionSuccess");
}

String actionMsg = "";
if(request.getAttribute("actionMsg")!=null){
	actionMsg = (String) request.getAttribute("actionMsg");
}

%>

                                <script>
                                function isNumberKey(evt)
                                {
                                   var charCode = (evt.which) ? evt.which : evt.keyCode;
                                   if (charCode != 46 && charCode > 31 
                                     && (charCode < 48 || charCode > 57))
                                      return false;

                                   return true;
                                }

                            </script>
                                
                                
                                </script>
</head>
<body>
<%@include file="./navbar.jsp" %>

<%@include file="./slidebar.jsp" %>

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">Items</li>
			</ol>
		</div><!--/.row-->

		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Items</h1>
			</div>
		</div><!--/.row-->
		<div id="msgArea"></div>




		<div class="row">
			<div class="col-lg-12">
			<div class="panel panel-default">
					<div class="panel-heading">Add New Item</div>
					<div class="panel-body">
						<div class="col-md-6">
							<form id="addForm" role="form" action="./itemManagementServlet?action=add" method="post">
								<div class="form-group">
									<label>Item name</label>
									<input class="form-control" placeholder="Item Name" name="addItemName" />
								</div>

								<div class="form-group">
									<label>Item Price</label>
									<input class="form-control" placeholder="Item Price" name="addItemPrice" onkeypress="return isNumberKey(event)"/>
								</div>
								

								<div class="form-group">
									<label>Item Remark</label>
									<input class="form-control" placeholder="Item Remark" name="addItemRemark" />
								</div>

								<div class="form-group">
									<label>Category</label>
									<select class="form-control" name="addCategroyId">
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
									<select class="form-control" name="addUserId">
										<%if(userList!=null){
											for(User user:userList){
										%>
										<option value="<%=user.getUserId()%>"><%=user.getUserName() %></option>
										<%	}
										} %>
									</select>
								</div>

								<button type="submit" class="btn btn-primary">Add</button>
								<button type="reset" class="btn btn-default" >Reset</button>
						</form>
						</div>
					</div>
				</div><!-- /.col-->

				<div class="panel panel-default">
					<div class="panel-heading">Items Management</div>
					<div class="panel-body">
						<table data-toggle="table" data-url="./reteriveItemTable"  data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="id" data-sort-order="desc">
						    <thead>
						    <tr>
						        <th data-field="id" data-sortable="true">Item ID</th>
						        <th data-field="name"  data-sortable="true">Item Name</th>
						        <th data-field="price" data-sortable="true">Item Price</th>
						        <th data-field="remark" data-sortable="true">Item Remarks</th>
						        <th data-field="userName" data-sortable="true">Add By</th>
						        <th data-field="date" data-sortable="true">Add On</th>
						        <th data-field="categoryName" data-sortable="true">Item category</th>
						        <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">Action</th>
						     </tr>
						    </thead>
						</table>
					</div>
				</div>
			</div>

  		</div><!--/.row-->


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
									<input class="form-control" placeholder="Item Price" name="editItemPrice" id="editItemPrice" onkeypress="return isNumberKey(event)"/>
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
											for(User user:userList){
										%>
										<option value="<%=user.getUserId()%>"><%=user.getUserName() %></option>
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

		<div id="popupModal2" class="modal fade"
			<table><tr><td></td></tr></table> tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>Title</h3>

					</div>
					<div class="modal-body">
						<div class="panel-heading">Remove Item</div>
						<form id="removeForm" role="form"
							action="./itemManagementServlet?action=remove" method="post">

							<div class="form-group">
								<label>Item Name</label> <input class="form-control"
									placeholder="Item Name" name="removeUserName" id="removeItemName" readonly/>
							</div>

							<div class="form-group">
									<label>Item Price</label>
									<input class="form-control"  name="removeItemPrice" id="removeItemPrice" readonly/>
								</div>

								<div class="form-group">
									<label>Item Remark</label>
									<input class="form-control"  name="removeItemRemark" id="removeItemRemark" readonly/>
								</div>

								<div class="form-group">
									<label>Category</label>
									<input class="form-control"  name="removeCategoryName" id="removeCategoryName" readonly/>
								</div>

								<div class="form-group">
									<label>Added By</label>
									<input class="form-control" name="removeUserName" id="removeUserName" readonly/>
								</div>
							<input type="hidden" name="removeItemId" id="removeItemId" />
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
		        	$("#editItemName").val(row.name);
		        	$("#editItemPrice").val(row.price);
		        	$("#editItemRemark").val(row.remark);
		        	$("#editCategoryId").val(row.categoryId);
		        	$("#editUserId").val(row.userId);
		        	$("#editItemId").val(row.id);
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
		        	$("#removeItemName").val(row.name);
		        	$("#removeItemPrice").val(row.price);
		        	$("#removeItemRemark").val(row.remark);
		        	$("#removeCategoryName").val(row.categoryName);
		        	$("#removeUserName").val(row.userName);
		        	$("#removeItemId").val(row.id);
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