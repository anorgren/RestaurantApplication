package restaurant.servlet;

import restaurant.dal.*;
import restaurant.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/recipecreate")
public class RecipeCreate extends HttpServlet {
	
	protected RecipesDao recipesDao;
	
	@Override
	public void init() throws ServletException {
		recipesDao = RecipesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/RecipeCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Initialize the recipeId in an integer format
        Integer recipeIdInt = 0;
        Integer timeToMakeInt = 0;
        Integer totalIngredientsInt = 0;

        // Retrieve and validate recipe.
        String recipeId = req.getParameter("recipeid");
        String recipeName = req.getParameter("recipename");
        String description = req.getParameter("description");
        String timeToMake = req.getParameter("timetomake");
        String totalIngredients = req.getParameter("totalingredients");
        
        if (recipeId == null || recipeId.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	
        	// Convert the recipeId into integer
        	try {
				recipeIdInt = Integer.valueOf(recipeId);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new NumberFormatException();
			}
        	
        	// Convert the timeToMake into integer
        	try {
        		timeToMakeInt = Integer.valueOf(timeToMake);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new NumberFormatException();
			}
        	
        	// Convert the totalIngredients into integer
        	try {
        		totalIngredientsInt = Integer.valueOf(totalIngredients);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new NumberFormatException();
			}
      
	        try {
	        	// Create the Recipes instance
	        	Recipes recipe = new Recipes(recipeIdInt, recipeName, description, timeToMakeInt, totalIngredientsInt);
	        	recipe = recipesDao.create(recipe);
	        	messages.put("success", "Successfully created " + recipeId);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RecipeCreate.jsp").forward(req, resp);
    }
}