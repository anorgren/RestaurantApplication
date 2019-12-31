<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a Category</title>
</head>
<body>
	<h1>Update CategoryName</h1>
	<form action="categoryupdate" method="post">
		<p>
			<label for="categoryid">CategoryId</label>
			<input id="categoryid" name="categoryid" value="${fn:escapeXml(param.categoryid)}">
		</p>
		<p>
			<label for="categoryname">New Category Name</label>
			<input id="categoryname" name="categoryname" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
		<br/>
	<div id="categorycreate"><a href="categorycreate">Create Category</a></div>
	<div id="categorydelete"><a href="categorydelete">Delete Category</a></div>
	<div id="findcategroies"><a href="findcategories">Find Category</a></div>
	<br/>
</body>
</html>