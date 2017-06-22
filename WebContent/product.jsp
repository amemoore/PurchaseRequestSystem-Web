<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList, prs.business.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="main.css">
<title>Products</title>
</head>

<!-- Displays Products by Vendor (by iterating through products ArrayList) -->

	<body>
		<p><i>${message}</i></p>
		<h2>Please pick a product below.</h2>
		
		<table align="center">
			<tr><th>Product</th><th>Unit</th><th>Price</th><th>Product Id</th></tr>
			<%
				ArrayList<Product> products = (ArrayList<prs.business.Product>)request.getAttribute("products");
	 			System.out.println(products.size());
				
				for (Product p : products) {
			%>
			
			<tr><td><%=p.getName()%></td><td><%=p.getUnit()%></td><td><%=p.getPrice()%></td>
				<td><a href="/PRS_Web/CreateRequest?action=SelectProduct&pId=<%=p.getProductID()%>"> <%=p.getProductID()%> </a> </td></tr>
			<%
				}
			%>
		</table>
</body>
</html>