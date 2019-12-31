package restaurant.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.dal.RecipesDao;
import restaurant.model.*;

@WebServlet("/findrecipesbynutrition")
public class FindRecipesByNutrition extends HttpServlet {

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
        
        List<Recipes> recipes = new ArrayList<Recipes>();
        List<String> ingredientList = new ArrayList<>();
        // Retrieve and validate recipe.
        // recipeID is retrieved from the URL query string.        
        
        String calories = req.getParameter("calories");
        String fat = req.getParameter("fat");
        String carbohydrates = req.getParameter("carbohydrates");
        String protein = req.getParameter("protein");

        
		if (calories == null || calories.trim().isEmpty() || fat == null || 
				fat.trim().isEmpty() || carbohydrates == null || carbohydrates.trim().isEmpty()|| protein == null || protein.trim().isEmpty()) {
			messages.put("failure", "Please enter valid ingredient names.");
		} else {
			try {
				Integer caloriesInt = Integer.valueOf(calories);
				Integer fatInt = Integer.valueOf(fat);
				Integer carbohydratesInt = Integer.valueOf(carbohydrates);
				Integer proteinInt = Integer.valueOf(protein);
				
				recipes = recipesDao.getRecipesByNutritionInfo(caloriesInt, fatInt, carbohydratesInt, proteinInt);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying Qualifying Results");
			messages.put("previousIngredient", calories + fat + carbohydrates);
		}
        req.setAttribute("recipes", recipes);
        
        req.getRequestDispatcher("/FindRecipesByNutrition.jsp").forward(req, resp);
	}
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Recipes> recipes = new ArrayList<Recipes>();
        List<String> ingredientList = new ArrayList<>();
        // Retrieve and validate recipe.
        // recipeID is retrieved from the URL query string.        
        
        String calories = req.getParameter("calories");
        String fat = req.getParameter("fat");
        String carbohydrates = req.getParameter("carbohydrates");
        String protein = req.getParameter("protein");

        
		if (calories == null || calories.trim().isEmpty() || fat == null || 
				fat.trim().isEmpty() || carbohydrates == null || carbohydrates.trim().isEmpty()|| protein == null || protein.trim().isEmpty()) {
			messages.put("failure", "Please enter valid ingredient names.");
		} else {
			try {
				Integer caloriesInt = Integer.valueOf(calories);
				Integer fatInt = Integer.valueOf(fat);
				Integer carbohydratesInt = Integer.valueOf(carbohydrates);
				Integer proteinInt = Integer.valueOf(protein);
				
				recipes = recipesDao.getRecipesByNutritionInfo(caloriesInt, fatInt, carbohydratesInt, proteinInt);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying Qualifying Results");
			messages.put("previousIngredient", calories + fat + carbohydrates);
		}
        req.setAttribute("recipes", recipes);
        
        req.getRequestDispatcher("/FindRecipesByNutrition.jsp").forward(req, resp);
	}
}
