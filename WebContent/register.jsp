<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="main.css">
</head>

<!-- Form to Register a First-time User -->

<h3 style="text-align: center;">Please enter user information below.</h3>

<body>
	<p style="text-decoration: bold;">${message}</p>
		<form style="text-align: center;" name="RegisterForm" method="POST" action="Registering">
			<label>Username: </label>
			<input type = "text" name="username" value="${username}"><br><br>
			<label>Password: </label>
			<input type = "text" name="password" value="${password}"><br><br>
			<label>First Name: </label> 
			<input type="text" name="firstName" value="${firstName}"><br><br>
			<label>Last Name: </label>
			<input type = "text" name="lastName" value="${lastName}"><br><br>
			<label>Phone: </label>
			<input type="tel" name="phone" value="${phone}"><br><br>
			<label>Email: </label>
			<input type="email" name="email" value="${email}"><br><br>
			<label>Manager: </label>
			<input type="checkbox" name="manager" ><br>
			<input type="submit" value="Register" class="margin_left">
		</form>
</body>
</html>