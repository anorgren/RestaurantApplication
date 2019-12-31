package restaurant.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Ingredients;
import restaurant.model.NutritionInformation;
import restaurant.model.RecipeSteps;
import restaurant.model.Recipes;
import restaurant.model.Reviews;


public class IngredientsDao {
	
	protected ConnectionManager connectionManager;
	private static IngredientsDao instance = null;

	  protected IngredientsDao() {
		  connectionManager = new ConnectionManager();
	  }
	  public static IngredientsDao getInstance() {
	    if (instance == null) {
	      instance = new IngredientsDao();
	    }
	    return instance;
	  }
	  
	  /**
	     * Save the Ingredients instance by storing it in your MySQL instance.
	     * This runs a INSERT statement.
	     */
	    public Ingredients create(Ingredients ingredients) throws SQLException {
	        String insertIngredients = "INSERT INTO Ingredients(FoodName, RecipeId, Quantity) VALUES(?,?,?);";
	        Connection connection = null;
	        PreparedStatement insertStmt = null;
	        ResultSet resultKey = null;
	        try {
	            connection = connectionManager.getConnection();
	            insertStmt = connection.prepareStatement(insertIngredients);
	            insertStmt.setString(1, ingredients.getFoodName());
	            insertStmt.setInt(2, ingredients.getRecipe().getRecipeId());
	            insertStmt.setFloat(3, ingredients.getQuantity());
	        
	        
	            insertStmt.executeUpdate();
	        
	            // Retrieve the auto-generated key and set it, so it can be used by the caller.
				resultKey = insertStmt.getGeneratedKeys();
				int ingredientId = -1;
				if(resultKey.next()) {
					ingredientId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				ingredients.setIngredientId(ingredientId);
	            return ingredients;
	        } catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(insertStmt != null) {
					insertStmt.close();
				}
				if(resultKey != null) {
					resultKey.close();
				}
			}
	    }

	
	
	public Ingredients getIngredientsByIngredientId(int ingredientId) throws SQLException {

		String selectIngredient = "SELECT IngredientId,FoodName,RecipeId,Quantity FROM Ingredients WHERE IngredientId=?;";
				
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectIngredient);
	
			selectStmt.setInt(1,ingredientId);
			
			results = selectStmt.executeQuery();
			
			RecipesDao recipeDao = RecipesDao.getInstance();
			
			
			if(results.next()) {
				
				
				int resultIngredientsId = results.getInt("IngredientId");
				String foodName = results.getString("FoodName");
				int recipeId = results.getInt("RecipeId");
				int quantity = results.getInt("Quantity");
				
				Recipes recipe = recipeDao.getRecipeById(recipeId);
				
				Ingredients ingredient = new Ingredients(resultIngredientsId, foodName, recipe, quantity);
				return ingredient;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	/*
	 * Get the matching RecipeSteps records by fetching from MySQL instance given a recipe ID.
	 * This runs a SELECT statement and returns a list of recipe steps (containing instructions)
	 * for a given recipe.
	 */
	public List<Ingredients> getIngredientsByRecipeId(int recipeId) throws SQLException {
		List<Ingredients> ingredients = new ArrayList<Ingredients>();
		String selectIngredients =
			"SELECT IngredientId,FoodName,RecipeId,Quantity FROM Ingredients WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectIngredients);
			selectStmt.setInt(1, recipeId);
			results = selectStmt.executeQuery();
			
			// Retrieve the RecipeDao instance
			RecipesDao recipeDao = RecipesDao.getInstance();
			
			// While there is more RecipeSteps, add it to the list
			while(results.next()) {
				int ingredientId = results.getInt("IngredientId");
				String foodName = results.getString("FoodName");
				int resultRecipeId = results.getInt("RecipeId");
				int quantity = results.getInt("Quantity");
				
				// Use the method in RecipesDao to fetch the Recipes object by recipeId
				Recipes recipe = recipeDao.getRecipeById(resultRecipeId);
				
				// Create a Reviews instance with the field values as specified above
				Ingredients ingredient = new Ingredients(ingredientId, foodName, recipe, quantity);
				ingredients.add(ingredient);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return ingredients;
	}
	
	
	
	
	
	public Ingredients getIngredientsByFoodName(String foodName) throws SQLException {

		String selectIngredients = "SELECT Ingredient(IngredientsId, FoodName, RecipeId, Quantity) VALUES(?,?,?,?);";
				
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectIngredients);
	
			selectStmt.setString(1,foodName);
			
			results = selectStmt.executeQuery();
			
			RecipesDao recipeDao = RecipesDao.getInstance();
			
			
			if(results.next()) {
				
				
				int ingredientsId = results.getInt("IngredientsId");
				String resultFoodName = results.getString("FoodName");
				//Recipes recipeId = recipeDao.getRecipeById(recipe);
				int recipeId = results.getInt("RecipeId");
				int quantity = results.getInt("Quantity");
				
				Recipes recipe = recipeDao.getRecipeById(recipeId);
				
				Ingredients ingredients = new Ingredients(ingredientsId, resultFoodName, recipe, quantity);
				return ingredients;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	

	

	public Ingredients delete(Ingredients ingredient) throws SQLException {

		String deleteIngredients = "DELETE FROM Ingredients WHERE IngredientId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteIngredients);
			deleteStmt.setInt(1, ingredient.getIngredientId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Restaurants instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	

	
}