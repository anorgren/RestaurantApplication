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


@WebServlet("/recipesteps")
public class ListOfRecipeSteps extends HttpServlet {
	
	protected RecipeStepsDao recipeStepsDao;
	
	@Override
	public void init() throws ServletException {
		recipeStepsDao = RecipeStepsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        int recipeIdInt = 0;
        
		// Retrieve and validate Recipes.
        String recipeId = req.getParameter("recipeid");
        
        
        
        if (recipeId == null || recipeId.trim().isEmpty()) {
            messages.put("title", "Invalid RecipeId.");
        } else {
        	
        	try {
            	recipeIdInt = Integer.parseInt(recipeId);

    		} catch (NumberFormatException e) {
    			e.printStackTrace();
    			throw new NumberFormatException();
            }
        	
        	RecipesDao recipeDao = RecipesDao.getInstance();
        	
        	try {
        		
            	Recipes recipe = recipeDao.getRecipeById(recipeIdInt);
            	String recipeName = recipe.getRecipeName();
            	messages.put("title", "Recipe Steps for " + recipeName);
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        }
        
        
        
        // Retrieve RecipeSteps, and store in the request.
        List<RecipeSteps> recipeSteps = new ArrayList<>();
        try {
        	recipeSteps = recipeStepsDao.getRecipeStepsByRecipeId(recipeIdInt);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("recipeSteps", recipeSteps);
        req.getRequestDispatcher("/RecipeSteps.jsp").forward(req, resp);
	}
}