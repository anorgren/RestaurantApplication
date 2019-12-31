package restaurant.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurant.dal.ConnectionManager;

import restaurant.model.*;

public class RecipesDao {
	
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RecipesDao instance = null;
	protected RecipesDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecipesDao getInstance() {
		if(instance == null) {
			instance = new RecipesDao();
		}
		return instance;
	}

	/**
	 * Save the Recipes instance by storing it in the MySQL instance.
	 * This runs an INSERT statement.
	 */
	public Recipes create(Recipes recipe) throws SQLException {
		String insertRecipe = "INSERT INTO Recipes(RecipeId,RecipeName,Description,TimeToMake,TotalIngredients) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecipe);

			insertStmt.setInt(1, recipe.getRecipeId());
			insertStmt.setString(2, recipe.getRecipeName());
			insertStmt.setString(3, recipe.getDescription());
			insertStmt.setInt(4, recipe.getTimeToMake());
			insertStmt.setInt(5, recipe.getTotalIngredients());
			
			insertStmt.executeUpdate();
			
			return recipe;
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
		}
	}

	/**
	 * Get the Recipes record by fetching it from MySQL instance.
	 * This runs a SELECT statement and returns a single Recipes instance.
	 */
	public Recipes getRecipeById(int recipeId) throws SQLException {
		String selectRecipe = "SELECT RecipeId,RecipeName,Description,TimeToMake,TotalIngredients FROM Recipes WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipe);
			selectStmt.setInt(1, recipeId);
			
			results = selectStmt.executeQuery();
		
			if(results.next()) {
				int resultRecipeId = results.getInt("RecipeId");
				String recipeName = results.getString("RecipeName");
				String description = results.getString("Description");
				int timeToMake = results.getInt("TimeToMake");
				int totalIngredients = results.getInt("TotalIngredients");
				Recipes recipe = new Recipes(resultRecipeId,recipeName,description,timeToMake,totalIngredients);
				return recipe;
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
	
	
	/**
	 * Get the Recipes record by fetching it from MySQL instance using a recipeName.
	 * This runs a SELECT statement and returns a single Recipes instance.
	 */
	public Recipes getRecipeByRecipeName(String recipeName) throws SQLException {
		String selectRecipe = "SELECT RecipeId,RecipeName,Description,TimeToMake,TotalIngredients FROM Recipes WHERE RecipeName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipe);
			selectStmt.setString(1, recipeName);
			
			results = selectStmt.executeQuery();
		
			if(results.next()) {
				int recipeId = results.getInt("RecipeId");
				String resultRecipeName = results.getString("RecipeName");
				String description = results.getString("Description");
				int timeToMake = results.getInt("TimeToMake");
				int totalIngredients = results.getInt("TotalIngredients");
				Recipes recipe = new Recipes(recipeId,resultRecipeName,description,timeToMake,totalIngredients);
				return recipe;
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
	
	
	/**
	 * Get the Recipes record by fetching it from MySQL instance using 3 ingredients.
	 * It returns 28 recipes that can be made with the given 3 ingredients, that have at most 3 missing ingredients
	 * This runs a SELECT statement and returns a single Recipes instance.
	 */
	public List<Recipes> getRecipesByIngredients(String ingredient1, String ingredient2, String ingredient3) throws SQLException {
		List<Recipes> recipes = new ArrayList<Recipes>();
		String selectRecipe = "SELECT DISTINCT Recipes.RecipeId, RecipeName, Description, TimeToMake, TotalIngredients FROM Recipes INNER JOIN (SELECT * FROM Ingredients WHERE FoodName LIKE ? OR FoodName LIKE ? OR FoodName LIKE ? GROUP BY RecipeId, IngredientId) AS A ON Recipes.RecipeId = A.RecipeId LIMIT 28;";
								//"INNER JOIN (SELECT * FROM NutritionInformation\n" + //"WHERE Calories BETWEEN 300 AND (SELECT CaloricGoal FROM Users WHERE UserName=?)\n" +
								//"	GROUP By RecipeId) AS NutritionInfo\n" +
								//"	ON A.RecipeId = NutritionInfo.RecipeId\n" +
						
								//"WHERE Quantity < 7\n" +
				
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipe);
			selectStmt.setString(1, "%" + ingredient1 + "%");
			selectStmt.setString(2, "%" + ingredient2 + "%");
			selectStmt.setString(3, "%" + ingredient3 + "%");
			
			results = selectStmt.executeQuery();
		
			// While there is more Recipes record, add it to the list
			while(results.next()) {
				int recipeId = results.getInt("RecipeId");
				String recipeName = results.getString("RecipeName");
				String description = results.getString("Description");
				int timeToMake = results.getInt("TimeToMake");
				int totalIngredients = results.getInt("TotalIngredients");
				
				// Create a Recipes instance with the field values as specified above
				Recipes recipe = new Recipes(recipeId,recipeName,description,timeToMake,totalIngredients);
				
				// Add recipe to the list
				recipes.add(recipe);
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
		return recipes;
	}
	
	
	
	
	/**
	 * Delete the Recipes instance.
	 * This runs a DELETE statement.
	 */
	public Recipes delete(Recipes recipe) throws SQLException {
		String deleteRecipe = "DELETE FROM Recipes WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecipe);
			deleteStmt.setInt(1, recipe.getRecipeId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Users instance.
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
	
	
	public List<Recipes> getRecipesByNutritionInfo(int calories, int fat, int carbs, int protein) throws SQLException {
		List<Recipes> matchingRecipes = new ArrayList<>();
		String selectRecipes = "SELECT RecipeId FROM NutritionInformation WHERE Calories <= ? AND TotalFat <= ? AND Carbohydrates <= ? AND protein >= ? LIMIT 10;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipes);
	
			selectStmt.setInt(1, calories);
			selectStmt.setInt(2, fat);
			selectStmt.setInt(3, carbs);
			selectStmt.setInt(4, protein);
			
			results = selectStmt.executeQuery();
			
			RecipesDao recipeDao = RecipesDao.getInstance();
			
			
			while(results.next()) { 
				int recipeId = results.getInt("RecipeId");
				Recipes recipe = recipeDao.getRecipeById(recipeId);
				matchingRecipes.add(recipe);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
		
		return matchingRecipes;
	}

	
	
	
	

}
