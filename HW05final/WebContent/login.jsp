<jsp:useBean id="bean" class="beans.SessionBean" scope="session" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="style/style.css" />
</head>
<body>
	<div class="box">
		<h1>411 Stock Investments</h1>
		
		<form action="LoginHandlerServlet" method="POST">
			<p class="errorr"><%= bean.getMessage()  %></p>
			<label class="loginReg"> Username: <input type="text" name="userName" class="loginReg"></label>
			<label class="loginReg"> Password: <input type="text" name="password" class="loginReg"></label>

			<input type="submit" value="Login" class="loginReg">
			<a href="registration.jsp" class="button" class="loginReg">Register</a>
			<a href="stockOptions.jsp" class="button" class="loginReg">StockOptions</a>
		</form>

	</div>
</body>
</html>

<% bean.setMessage("");  %>  <!-- scriptlet -->   