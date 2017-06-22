<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="main.css">
<title>Sign In</title>
</head>

<!-- Sign in Button for Username -->

	<body>
		<div class="centerTextBox">
			<p style="text-decoration: bold;">${message}</p>
			<form style="text-decoration: bold;" method="POST" action="SigningIn" name="SigningIn">
					<label>Username: </label>
					<input type="text" name="username" value="${username}">
					<button type="submit">Sign In</button>
			</form>
		</div>
	</body>
</html>