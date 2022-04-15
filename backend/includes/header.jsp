<html>
<head>
<title>Farmer Assistance Portal</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/jquery.validate.js"></script>
<style>
.ui-datepicker {
	font-family: "Trebuchet MS", "Helvetica", "Arial",  "Verdana", "sans-serif";
	font-size: 12px;
}
.ui-datepicker select.ui-datepicker-month, .ui-datepicker select.ui-datepicker-year {
	float:left;
}
</style>
<script>
jQuery('document').ready(function() {
	jQuery("#Form").validate();
});
</script>
</head>
<body id="top">
<div class="wrapper row0">
  <div id="topbar" class="clear"> 
    <nav>
      <ul>
		<% if(session.getAttribute("login_id")!=null) { %>
		<li>Welcome <% out.print(session.getAttribute("login_name")); %></li>
        <li><a href="model/login.jsp?act=logout">Logout</a></li>
		<% } else { %>
		<li><a href="index.jsp">Home</a></li>
        <li><a href="about-us.jsp">About Us</a></li>
        <li><a href="contact-us.jsp">Contact Us</a></li>
		<li><a href="customer-login.jsp">Customer Login</a></li>
		<li><a href="login.jsp?title=Adminstrator">Admin Login</a></li>
		<% } %>
      </ul>
    </nav>
  </div>
</div>
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <div id="logo" class="fl_left">
       <div style="float:left; margin-top:-11px;"><h1 style="font-size:36px;"><a href="index.jsp">Farmer Assistance Portal</a></h1>
      <p style="color:#ffffff">A portal for farming assistance</p>
      </div>
    </div>
    <div class="fl_right">
      <form class="clear" method="post" action="#">
        <fieldset>
          <legend>Search:</legend>
          <input type="text" value="" placeholder="Search Here">
          <button class="fa fa-search" type="submit" title="Search"><em>Search</em></button>
        </fieldset>
      </form>
    </div>
  </header>
</div>
<div class="wrapper row2">
  <div class="rounded">
    <nav id="mainav" class="clear"> 
      <ul class="clear">
        <li class="active"><a href="index.jsp">Home</a></li>
        <!-- Menu System for Super Admin Login -->
        <% if(session.getAttribute("login_level") != null && session.getAttribute("login_level").equals("1")) { %>
          <li><a href="login-home.jsp">Dasboard</a></li>
          <li><a href="#" class="drop" >Add New</a>
            <ul>
              <li><a href="employee.jsp?employee_id=0">Add Experts</a></li>
              <li><a href="type.jsp?type_id=0">Add Product Type</a></li>
              <li><a href="notification.jsp?notification_id=0">Publish Notification</a></li>
              </ul>
            </li>
            <li><a href="#" class="drop" >Reports</a>
              <ul>
                <li><a href="report-employee.jsp?type=2">All Experts</a></li>
			          <li><a href="report-employee.jsp?type=3">All Farmers</a></li>
                <li><a href="report-customer.jsp">All Customers</a></li>
                <li><a href="report-product.jsp?type_id=0">All Product</a></li>
                <li><a href="report-type.jsp?type=2">All Product Types</a></li>
                <li><a href="report-notification.jsp">All Notification</a></li>
                </ul>
              </li>
            <li><a href="change-password.jsp">Change Password</a></li>
            <li><a href="model/login.jsp?act=logout">Logout</a></li>
        <% } %>
      <!-- End of the Super Admin Menu Section -->
      <!-- Menu System for Farmer Login -->
      <% if(session.getAttribute("login_level") != null && session.getAttribute("login_level").equals("3")) { %>
        <li><a href="login-home.jsp">Dasboard</a></li>
        <li><a href="#" class="drop" >Add New</a>
          <ul>
            <li><a href="product.jsp?product_id=0">Add Product</a></li>
            <li><a href="question.jsp?question_id=0">Request Support</a></li>
            </ul>
          </li>
          <li><a href="#" class="drop" >Reports</a>
            <ul>
              <li><a href="report-product.jsp?type_id=0">All Product</a></li>
              <li><a href="report-order.jsp?customer_id=0">All Order</a></li>
              <li><a href="notification-list.jsp">All Notification</a></li>
              <li><a href="report-question.jsp">My Questions</a></li>
              <li><a href="question-list.jsp">Quesion List</a></li>
              </ul>
            </li>
          <li><a href="change-password.jsp">Change Password</a></li>
          <li><a href="model/login.jsp?act=logout">Logout</a></li>
      <% } %>
     <!-- End of the Farmer Menu Section -->
     <!-- Menu System for Expert Login -->
     <% if(session.getAttribute("login_level") != null && session.getAttribute("login_level").equals("2")) { %>
      <li><a href="login-home.jsp">Dasboard</a></li>
      <li><a href="#" class="drop" >My Adminstration</a>
          <ul>
            <li><a href="question-list.jsp">Quesion List</a></li>
            <li><a href="notification-list.jsp">All Notification</a></li>
            </ul>
          </li>
        <li><a href="change-password.jsp">Change Password</a></li>
        <li><a href="model/login.jsp?act=logout">Logout</a></li>
    <% } %>
   <!-- End of the Expert Menu Section -->
		
		<!-- Menu System for Customer Login -->
    <% if(session.getAttribute("login_level") != null && session.getAttribute("login_level").equals("4")) { %>		
    <li><a href="login-home.jsp">Dasboard</a></li>
		<li><a href="#" class="drop" >Choose Items</a>
			<ul>
				<li><a href="list-product.jsp?type_id=1">Flowers</a></li>
				<li><a href="list-product.jsp?type_id=2">Vegetables</a></li>
				<li><a href="list-product.jsp?type_id=3">Grains</a></li>
				<li><a href="list-product.jsp?type_id=4">Organic</a></li>	        
			</ul>
		</li>
		<li><a href="report-order.jsp?customer_id=<%=session.getAttribute("customer_id")%>">My Orders</a></li>
		<li><a href="customer.jsp?customer_id=<%=session.getAttribute("customer_id")%>">My Account</a></li>
		<li><a href="contact-us.jsp">Contact Us</a></li>
        <li><a href="model/customer.jsp?act=logout">Logout</a></li>
		<% } %>
		<!-- End of Employee Menu Section -->
		
		<% if(session.getAttribute("login_level") == null) { %>
      <li><a href="list-product.jsp?type_id=0">All Products</a></li>
      <li><a href="notification-list.jsp">Notifications</a></li>
      <li><a href="question-list.jsp">All Questions</a></li>
      <li><a href="register-page.jsp">Register</a></li>
      <li><a href="login-page.jsp">Login</a></li>
			
	    <li><a href="contact-us.jsp">Contact Us</a></li>

		<% } %>
      </ul>
    </nav>
  </div>
</div>
