	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul class="nav menu">
			<li ><a href="Home.jsp"><span class="glyphicon glyphicon-pencil"></span> Home</a></li>
			<li ><a href="DataInput.jsp"><span class="glyphicon glyphicon-pencil"></span> Data Input</a></li>
			<li class="parent ">
				<a href="#">
					<span class="glyphicon glyphicon-list"></span> Admin  <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a class="" href="Profile.jsp">
							<span class="glyphicon glyphicon-share-alt"></span> User Profile
						</a>
					</li>
					<li class="active">
						<a class="" href="User.jsp">
							<span class="glyphicon glyphicon-share-alt"></span> User Account
						</a>
					</li>

				</ul>
			</li>
			<li class="parent ">
				<a href="#">
					<span class="glyphicon glyphicon-list"></span> Management <span data-toggle="collapse" href="#sub-item-2" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span>
				</a>
				<ul class="children collapse" id="sub-item-2">
					<li>
						<a class="" href="Item.jsp">
							<span class="glyphicon glyphicon-share-alt"></span> Item Category
						</a>
					</li>
					<li>
						<a class="" href="Management.jsp">
							<span class="glyphicon glyphicon-share-alt"></span> Records
						</a>
					</li>
					<li>
						<a class="" href="Report.jsp">
							<span class="glyphicon glyphicon-share-alt"></span> Report
						</a>
					</li>
				</ul>
			</li>
		</ul>
	</div><!--/.sidebar-->