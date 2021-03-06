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


@WebServlet("/recipenutrition")
public class RecipeNutrition extends HttpServlet {

protected NutritionInformationDao nutritionDao;
	
	@Override
	public void init() throws ServletException {
		nutritionDao = NutritionInformationDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        Integer recipeIdInt = 0;
        
		// Retrieve and validate Recipes.
        String recipeId = req.getParameter("recipeid");
        if (recipeId == null || recipeId.trim().isEmpty()) {
            messages.put("title", "Invalid RecipeId.");
        } else {
        	
            try {
            	recipeIdInt = Integer.valueOf(recipeId);

    		} catch (NumberFormatException e) {
    			e.printStackTrace();
    			throw new NumberFormatException();
            }
                RecipesDao recipesDao = RecipesDao.getInstance();
                Recipes recipe;
				try {
					recipe = recipesDao.getRecipeById(recipeIdInt);
		        	messages.put("title", "Nutrition Information for " + recipe.getRecipeName());
				} catch (SQLException e) {
					e.printStackTrace();
				}        	
        }
        
        // Retrieve RecipeSteps, and store in the request.
        NutritionInformation nutritionInfo = null;
        try {
        	nutritionInfo = nutritionDao.getNutritionInformationByRecipeId(recipeIdInt);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("recipenutrition", nutritionInfo);
        req.getRequestDispatcher("/RecipeNutrition.jsp").forward(req, resp);
	}
	
	
}