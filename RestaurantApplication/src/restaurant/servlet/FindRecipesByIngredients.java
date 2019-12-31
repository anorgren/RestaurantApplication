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

@WebServlet("/findrecipesbyingredients")
public class FindRecipesByIngredients extends HttpServlet {

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
        //List<String> ingredientList = new ArrayList<>();
        // Retrieve and validate recipe.
        // recipeID is retrieved from the URL query string.        
        
        String ingredient1 = req.getParameter("ingredient1");
        String ingredient2 = req.getParameter("ingredient2");
        String ingredient3 = req.getParameter("ingredient3");

        
		if (ingredient1 == null || ingredient1.trim().isEmpty() || ingredient2 == null || 
				ingredient2.trim().isEmpty() || ingredient3 == null || ingredient3.trim().isEmpty()) {
			messages.put("failure", "Please enter valid ingredient names.");
		} else {
			try {
				recipes =recipesDao.getRecipesByIngredients(ingredient1, ingredient2, ingredient3);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + ingredient1 + ", " + ingredient2 + ", " + ingredient3);
			messages.put("previousIngredient", ingredient1 + ingredient2 + ingredient3);
		}
        req.setAttribute("recipes", recipes);
        
        req.getRequestDispatcher("/FindRecipesByIngredients.jsp").forward(req, resp);
	}
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Recipes> recipes = new ArrayList<Recipes>();
        //List<String> ingredientList = new ArrayList<>();
 
       
        String ingredient1 = req.getParameter("ingredient1");
        String ingredient2 = req.getParameter("ingredient2");
        String ingredient3 = req.getParameter("ingredient3");

		if (ingredient1 == null || ingredient1.trim().isEmpty() || ingredient2 == null || 
				ingredient2.trim().isEmpty() || ingredient3 == null || ingredient3.trim().isEmpty()) {
			messages.put("failure", "Please enter valid ingredient names.");
		} else {
			try {
				recipes =recipesDao.getRecipesByIngredients(ingredient1, ingredient2, ingredient3);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + ingredient1 + ", " + ingredient2 + ", " + ingredient3);
			messages.put("previousIngredient", ingredient1 + ingredient2 + ingredient3);
		}
        req.setAttribute("recipes", recipes);
        
        req.getRequestDispatcher("/FindRecipesByIngredients.jsp").forward(req, resp);
    }
	
}