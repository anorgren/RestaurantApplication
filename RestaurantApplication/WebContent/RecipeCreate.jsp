<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Recipe</title>
</head>
<body>
	<h1>Create Recipes</h1>
	<form action="recipecreate" method="post">
		<p>
			<label for="recipeid">RecipeId</label>
			<input id="recipeid" name="recipeid" value="">
		</p>
		<p>
			<label for="recipename">RecipeName</label>
			<input id="recipename" name="recipename" value="">
		</p>
		<p>
			<label for="description">Description</label>
			<input id="description" name="description" value="">
		</p>
		<p>
			<label for="timetomake">Time To Make (Minutes)</label>
			<input id="timetomake" name="timetomake" value="">
		</p>
		<p>
			<label for="totalingredients">Total Ingredients</label>
			<input id="totalingredients" name="totalingredients" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>