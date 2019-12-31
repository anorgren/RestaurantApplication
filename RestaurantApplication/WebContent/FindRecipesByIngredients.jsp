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
<title>Find a Recipe</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
	<form action="findrecipesbyingredients" method="post">
	<div class="jumbotron">
		<h1>Search for a Recipe by Ingredients</h1>
		</div>
		<p>
			<label for="ingredient1">Ingredient 1</label>
			<input id="ingredient1" name="ingredient1" value="${fn:escapeXml(param.ingredient1)}">
			<label for="ingredient2">Ingredient 2</label>
			<input id="ingredient2" name="ingredient2" value="${fn:escapeXml(param.ingredient2)}">
			<label for="ingredient3">Ingredient 3</label>
			<input id="ingredient3" name="ingredient3" value="${fn:escapeXml(param.ingredient3)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/><br/>
			</p>
			
			</form>
			<div id="findrecipesbynutrition"><a href="findrecipesbynutrition">Find Recipes By Nutrition</a></div>
			<div id="recipecreate"><a href="recipecreate">Create Recipes</a></div>
			<br/>
			
			<div class="alert alert-info" role="alert">
			<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
			</div>
			<br/>	
	
	<h1>Matching Recipes</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>Recipe Id</th>
                <th>Recipe Name</th>
                <th>Description</th>
                <th>Time to Make (min)</th>
                <th>Total Ingredients</th>
                <th>Steps</th>
                <th>Ingredients</th>
                <th>Nutrition Information</th>
            </tr>
            <c:forEach items="${recipes}" var="recipe" >
               <tbody><tr> 
                    <td><c:out value="${recipe.getRecipeId()}" /></td>
                    <td><c:out value="${recipe.getRecipeName()}" /></td>
                    <td><c:out value="${recipe.getDescription()}" /></td>
                    <td><c:out value="${recipe.getTimeToMake()}" /></td>
                    <td><c:out value="${recipe.getTotalIngredients()}" /></td>
                    <td><a href="recipesteps?recipeid=<c:out value="${recipe.getRecipeId()}"/>">RecipeSteps</a></td>
                    <td><a href="recipeingredients?recipeid=<c:out value="${recipe.getRecipeId()}"/>">Ingredients</a></td>
                    <td><a href="recipenutrition?recipeid=<c:out value="${recipe.getRecipeId()}"/>">Nutrition Info</a></td>
                </tr></tbody>
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