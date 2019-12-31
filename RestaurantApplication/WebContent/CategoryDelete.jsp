<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a Category</title>
</head>
<body>
	<h1>${messages.title}</h1>
	<form action="categorydelete" method="post">
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="categoryid">CategoryId</label>
				<input id="categoryid" name="categoryid" value="${fn:escapeXml(param.categoryid)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
		<br/>
	<div id="categorycreate"><a href="categorycreate">Create Category</a></div>
	<div id="findCategories"><a href="findCategories">Find a Category</a></div>
	<div id="categoryupdate"><a href="categoryupdate">Update Category</a></div>
	<br/>
</body>
</html>