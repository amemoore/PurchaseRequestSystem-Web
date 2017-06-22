<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList, prs.business.Vendor, prs.business.Product" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vendors</title>
<link rel="stylesheet" href="main.css">
</head>
<body>
	
<!-- Displays All Vendors (by iterating through vendors ArrayList -->
	
		<p>${message}</p>
		<h3>Please pick a Vendor below.</h3>
		
	<table align="center">
		<tr><th>Vendor</th><th>Vendor Id</th></tr>
		<%
			ArrayList<Vendor> allVendors = (ArrayList<prs.business.Vendor>)request.getAttribute("vendors");
 			System.out.println(allVendors.size());
			
			for (Vendor v: allVendors) {
		%>
		<tr><td><%=v.getName()%></td><td><a href="/PRS_Web/CreateRequest?action=SelectVendor&vId=<%=v.getvId()%>"> <%=v.getvId()%> </a> </td></tr>
		<%
			}
		%>
	</table>
</body>
</html>