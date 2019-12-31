<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Category</title>
</head>
<body>
	<form action="findcategories" method="post">
		<h1>Search for a Restaurant Category</h1>
		<p>
			<label for="categoryname">CategoryName</label>
			<input id="categoryname" name="categoryname" value="${fn:escapeXml(param.categoryname)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="categorycreate"><a href="categorycreate">Create Category</a></div>
	<div id="categorydelete"><a href="categorydelete">Delete Category</a></div>
	<div id="categoryupdate"><a href="categoryupdate">Update Category</a></div>
	<br/>
	<h1>Matching Category</h1>
        <table border="1">
            <tr>
                <th>CategoryId</th>
                <th>CategoryName</th>

            </tr>
            <c:forEach items="${categories}" var="categories" >
                <tr>
                    <td><c:out value="${categories.getCategoryId()}" /></td>
                    <td><c:out value="${categories.getCategoryName()}" /></td>

                </tr>
            </c:forEach>
       </table>
</body>
</html>