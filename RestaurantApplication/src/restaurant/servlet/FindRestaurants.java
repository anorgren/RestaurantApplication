package restaurant.servlet;

import restaurant.dal.*;
import restaurant.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findrestaurants")
public class FindRestaurants extends HttpServlet {
	protected RestaurantsDao restaurantsDao;
	
	@Override
	public void init() throws ServletException {
	restaurantsDao = RestaurantsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    int zip1 = 0;
    List<Restaurants> restaurants = new ArrayList<Restaurants>();
    
    
    String zipCode = req.getParameter("zip");
    if (zipCode == null || zipCode.trim().isEmpty()) {
        messages.put("success", "Please enter a valid zipcode.");
    } else {
    	
    	try {
        	//zip1 = Integer.valueOf(zipCode);
        	
        	zip1 = Integer.parseInt(zipCode);

		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new NumberFormatException();
        }
    	
    	//RestaurantsDao restaurantDao = RestaurantsDao.getInstance();
    	//Restaurants restaurant;
    	try {
    
        	restaurants = restaurantsDao.getRestaurantsByZipCode(zip1);
      
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
   
    	
    	messages.put("success", "Displaying results for " + zipCode);
    	// Save the previous search term, so it can be used as the default
 
    	messages.put("previousZipCode", zipCode);
    }
    req.setAttribute("restaurants", restaurants);
    
    req.getRequestDispatcher("/FindRestaurants.jsp").forward(req, resp);
    }
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    
    List<Restaurants> restaurants = new ArrayList<Restaurants>();
    
    String zipCode = req.getParameter("zip");

    
	if (zipCode == null || zipCode.trim().isEmpty()) {
		messages.put("failure", "Please enter valid ZipCode.");
	} else {
		try {
			
			Integer zipCodeInt = Integer.valueOf(zipCode);
			
			restaurants = restaurantsDao.getRestaurantsByZipCode(zipCodeInt);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		messages.put("success", "Displaying Qualifying Results");
		messages.put("previousRestaurants", zipCode);
	}
    req.setAttribute("restaurants", restaurants);
    
    req.getRequestDispatcher("/FindRestaurants.jsp").forward(req, resp);
    }
	
}
	

