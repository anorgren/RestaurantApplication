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
<title>Find a Restaurant By ZipCode</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
	<form action="findrestaurants" method="post">
	<div class="jumbotron">
		<h1>Search for a Restaurant by ZipCode</h1>
		</div>
		<p>
			<label for="zip">ZipCode</label>
			<input id="zip" name="zip" value="${fn:escapeXml(param.zip)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/><br/>
			</p>
			</form>
			<div id="restaurantcreate"><a href="restaurantcreate">Create Restaurant</a></div>
			<br/>
			<div class="alert alert-info" role="alert">
			<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
			</div>
	<br/>
	<h1>Matching Restaurants</h1>
        <table class="table table-striped">
           <thead><tr>
                <th>RestaurantName</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>ZipCode</th>
                <th>Price</th>

            </tr>
            <c:forEach items="${restaurants}" var="restaurant" >
                <tr>
                    <td><c:out value="${restaurant.getRestaurantName()}" /></td>
                    <td><c:out value="${restaurant.getAddress()}" /></td>
                    <td><c:out value="${restaurant.getCity()}" /></td>
                    <td><c:out value="${restaurant.getState()}" /></td>
                    <td><c:out value="${restaurant.getZip()}" /></td>
                    <td><c:out value="${restaurant.getPrice()}" /></td>
               
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