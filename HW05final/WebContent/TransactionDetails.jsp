<jsp:useBean id="bean" class="beans.SessionBean" scope="session" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction Details</title>
<link rel="stylesheet" href="style/style.css" type="text/css">
</head>
<body>

	<div class="transaction-page">
		<form action="TransactionHandlerServlet" method="POST">
			<h1>Transaction Details</h1>
			<table class="transaction-details-table">
				<thead>
					<tr>
						<th>Stock Symbol</th>
						<th>Action</th>
						<th>Quantity</th>
						<th>Share Price</th>
						<th>Total Value</th>
					</tr>
				</thead>
				<tbody>
				<%= bean.getTransactionForm() %>
			
					<tr>
						<td colspan="5" class="center">Bank Account Information</td>
					</tr>
					
					<tr>
						<td colspan="2">Account Holder Name</td>
						<td colspan="3"><input type="text" name="accountHolderName" class="accountRout"></td>
					</tr>
					
					<tr>
						<td colspan="2">Routing Number</td>
						<td colspan="3"><input type="text" name="routingNumber" class="accountRout"></td>
					</tr>
				</tbody>
			</table>
		
			<input type="submit" value="Confirm Transaction" class="transaction">
		</form>
		
		<form action="StockHoldingsHandlerServlet" method="POST">
			<input type="submit" value="Cancel" id="cancel" class="transaction">
		</form>
			
		<form action="LogOutHandlerServlet" method="POST">
			<input type="submit" value="Logout" id="logout" class="transaction">
		</form>

	</div>
</body>
</html>
<% bean.setMessage("");  %> 