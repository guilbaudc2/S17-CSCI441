<jsp:useBean id="bean" class="beans.SessionBean" scope="session" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stockholdings</title>
<link rel="stylesheet" href="style/style.css" type="text/css">
</head>
<body>

	<div>
		<h1>Current Stock Holdings for <%= bean.getUserName()  %></h1>
		<table>
			<thead>
				<tr>
					<th>Stock Symbol</th>
					<th>Current Price per Share</th>
					<th>Recent Change</th>
					<th>Last Updated</th>
					<th>Owned Shares</th>
					<th>Total Value</th>
					<th>Action</th>
					<th>Quantity</th>
					<th>Transaction</th>
				</tr>
			</thead>
			<tbody>
			<%= bean.getForm() %>
			</tbody>
		</table>
		
		<p class="errorr"><%= bean.getMessage()  %></p>
		
		<form action="LogOutHandlerServlet" method="POST">
			<input type="submit" value="Logout" class="logout">
		</form>
		<div class="mainStockView">
			<div class="stockHeader">
				<h1>Check Stock Prices</h1>
			</div>
			
			<div class="content">
				<label>Ticker Symbol:</label>
				<input class="logReg" id="stockSymbol" type="text" name="username" value="GOOG">
				<button id="checkStock" class="link_button">Check Stock</button><br>
				<br>
			</div>
			<table id="div-table" class="div-table">
				<thead><tr id="table-header" class="div-table-row"></tr></thead>
				<tbody><tr><td colspan="6"><form action='TransactionDetailsServlet'><table class="inner3">
					<tr id="div-table-row" class="div-table-row"></table></form></td></tr></tbody>
				
			</table>
		</div>
	</div>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
	<script src="script/redirect.js" type="text/javascript"></script>
	<script src="js/stockHoldingsAjaxLiveStockPopulation.js"></script> 
	<script src="js/stockOptionsInfoAjax.js"></script> 
	<script src="js/stockOptions.js"></script> 
</body>
</html>
<% bean.setMessage("");  %> 