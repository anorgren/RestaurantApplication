<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Category</title>
</head>
<body>
	<h1>Create Category</h1>
	<form action="categorycreate" method="post">
		<p>
			<label for="categoryname">CategoryName</label>
			<input id="categoryname" name="categoryname" value="">
		</p>
		<p>
			<label for="categoryId">CategoryId</label>
			<input id="categoryid" name="categoryid" value="">
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
	<div id="findCategories"><a href="findCategories">Find a Category</a></div>
	<div id="categorydelete"><a href="categorydelete">Delete Category</a></div>
	<div id="categoryupdate"><a href="categoryupdate">Update Category</a></div>
	<br/>
</body>
</html>