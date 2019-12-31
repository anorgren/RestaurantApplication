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
<title>Find a User</title>
</head>
<body>
 	<div class="container theme-showcase" role="main">
	<form action="findusers" method="post">
	<div class="jumbotron">
		<h1>Search for a User by City</h1>
		</div>
		<p>
			<label for="city">City</label>
			<input id="city" name="city" value="${fn:escapeXml(param.city)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/>
		</p>
	
	</form>
	<div id="userCreate"><h3><a href="usercreate">Create User</a></h3></div>
	<div id="findRecipesByIngredients"><a href="findrecipesbyingredients">Find Recipes By Ingredients</a></div>
	<br/>
	<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>
	<h1>Matching Users</h1>
         <table class="table table-striped">
            <thead><tr>
                <th>UserName</th>
                <th>City</th>
                <th>UserZip</th>
                <th>CaloricGoal</th>
                <th>FatGoal</th>
                <th>CarbGoal</th>
                <th>ProteinGoal</th>
                <th>Find Recipes</th>
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