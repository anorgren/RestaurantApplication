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
<title>User Login</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
	<form action="userlogin" method="post">
	<div class="jumbotron">
		<h1>User Login</h1>
		</div>
		<p>
			<label for="username">Username</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
			<p>
			<label for="password">Password</label>
			<input id="password" name="password" value="${fn:escapeXml(param.password)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/><br/>
			</p>
	</form>
	<div id="findRecipesByIngredients"><a href="findrecipesbyingredients">Search Recipes By Ingredients</a></div>
	<br/>
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>
	<h1>User Profile</h1>
        <table class="table table-striped">
           <thead><tr>
                <th>UserName</th>
                <th>City</th>
                <th>User Zip</th>
                <th>Caloric Goal</th>
                <th>Fat Goal</th>
                <th>Carb Goal</th>
                <th>Protein Goal</th>
                <th>Find a Recipe</th>
                <th>Delete User</th>
                <th>Update User</th>
            </tr></thead>
            <c:forEach items="${users}" var="user" >
               <tbody><tr>
                    <td><c:out value="${user.getUserName()}" /></td>
                    <td><c:out value="${user.getCity()}" /></td>
                    <td><c:out value="${user.getUserZip()}" /></td>
                    <td><c:out value="${user.getCaloricGoal()}" /></td>
                    <td><c:out value="${user.getFatGoal()}" /></td>
                    <td><c:out value="${user.getCarbGoal()}" /></td>
                    <td><c:out value="${user.getProteinGoal()}" /></td>
                
               		<td><a href="findrecipesbyingredientsanduser?username=<c:out value="${user.getUserName()}"/>">Find Recipes</a></td>
                    <td><a href="userdelete?username=<c:out value="${user.getUserName()}"/>">Delete</a></td>
                    <td><a href="userupdate?username=<c:out value="${user.getUserName()}"/>">Update</a></td>
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