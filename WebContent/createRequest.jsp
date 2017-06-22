<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,prs.business.Vendor,prs.business.Product" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Purchase Request</title>

<link rel="stylesheet" href="main.css">
</head>


<body>
	<div>
		<h3>Please create your purchase request below.</h3>
		<h4> ${message}</h4>
		
		<form name="action" method="POST" action="CreateRequest" >
		<input type="hidden" name="action" value="CreateRequest">
			<label>Description: </label>
			<input type = "text" name="description" value="${description}"><br><br>
			<label>Justification: </label>
			<input type = "text" name="justification" value="${justification}"><br><br>
			<label>Date Needed: </label> 
			<input type="text" name="dateNeeded" value="${dateNeeded}"><br><br>
			<label>User Name: </label>
			<input type = "text" name="username" value="${username}"><br><br>
			<label>Delivery Mode: </label>
			<input type="text" name="deliveryMode" value="${deliveryMode}"><br><br>
			<label>Document Attached?: </label>
			<input type="checkbox" name="isDocAttached" ><br><br>
			<label>Status: </label>
			<input type="text" name="status" value="${status}"><br><br>
			<label>Total: </label>
			<input type="text" name="total" value="${total}"><br><br>
			<label>Submitted Date: </label>
			<input type="text" name="submittedDate" value="${submittedDate}"><br><br>
			<input type="submit" value="Create Request" class="margin_left">
		</form>
	</div>
</body>
</html>