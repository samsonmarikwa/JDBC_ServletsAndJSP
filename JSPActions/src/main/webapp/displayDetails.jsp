<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>
</head>
<body>
	<jsp:useBean id="product" class="com.samsonmarikwa.jsp.Product">
	<jsp:setProperty name="product" property="*" />
	</jsp:useBean>
	
	Product Details
	<br />
	Id: <jsp:getProperty property="id" name="product"/>
	<br />
	Name: <jsp:getProperty property="name" name="product"/>
	<br />
	Description: <jsp:getProperty property="description" name="product"/>
	<br />
	Price: <jsp:getProperty property="price" name="product"/>
	<br/>
	Server Version: <%= application.getServerInfo() %>
	Servlet Version: <%= application.getMajorVersion() %>, <%= application.getMinorVersion() %>
	JSP Version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %>
</body>
</html>