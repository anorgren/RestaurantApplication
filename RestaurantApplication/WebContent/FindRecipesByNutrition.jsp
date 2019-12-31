<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Matching Recipes</title>
</head>
<body>
 	<div class="container theme-showcase" role="main">
	<form action="findrecipesbynutrition" method="post">
	<div class="jumbotron">

		<h1>Search for a Recipe by Nutrition Information</h1>
		</div>
		<p>
			<label for="calories">Maximum Calories</label>
			<input id="calories" name="calories" value="${fn:escapeXml(param.calories)}">
			<br />
			<label for="fat">Maximum Grams of Fat</label>
			<input id="fat" name="fat" value="${fn:escapeXml(param.fat)}">
			<br />
			<label for="carbohydrates">Maximum Grams of Carbohydrates</label>
			<input id="carbohydrates" name="carbohydrates" value="${fn:escapeXml(param.carbohydrates)}">
			<br />
			<label for="protein">Minimum Grams of Protein</label>
			<input id="protein" name="protein" value="${fn:escapeXml(param.protein)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/>
			</p>
			
			<div class="alert alert-info" role="alert">
			<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
			</div>
			
	</form>
	<h1>Matching Recipes</h1>
        <table class="table table-striped">
            <tr>
                <th>RecipeId</th>
                <th>Name</th>
                <th>Description</th>
                <th>Time To Make</th>
                <th>Total Ingredients</th>
                <th>Steps</th>
                <th>Ingredients</th>
                <th>Nutrition</th>
            </tr>
            <c:forEach items="${recipes}" var="recipe" >
                <tr>
                    <td><c:out value="${recipe.getRecipeId()}" /></td>
                    <td><c:out value="${recipe.getRecipeName()}" /></td>
                    <td><c:out value="${recipe.getDescription()}" /></td>
                    <td><c:out value="${recipe.getTimeToMake()}" /></td>
                    <td><c:out value="${recipe.getTotalIngredients()}" /></td>
                    <td><a href="recipesteps?recipeid=<c:out value="${recipe.getRecipeId()}"/>">Steps</a></td>
                    <td><a href="recipeingredients?recipeid=<c:out value="${recipe.getRecipeId()}"/>">Ingredients</a></td>
                    <td><a href="recipenutrition?recipeid=<c:out value="${recipe.getRecipeId()}"/>">Nutrition Information</a></td>
                    
                </tr>
            </c:forEach>
       </table>
       
</div>
    
     <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
      
</body>
</html>