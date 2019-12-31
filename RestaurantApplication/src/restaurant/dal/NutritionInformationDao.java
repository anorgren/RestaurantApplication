package restaurant.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import restaurant.model.NutritionInformation;
import restaurant.model.Recipes;


public class NutritionInformationDao {
	protected ConnectionManager connectionManager;
	private static NutritionInformationDao instance = null;

	  protected NutritionInformationDao() {
		  connectionManager = new ConnectionManager();
	  }
	  public static NutritionInformationDao getInstance() {
	    if (instance == null) {
	      instance = new NutritionInformationDao();
	    }
	    return instance;
	  }

	 /**
	 * Save the NutritionInformation instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public NutritionInformation create(NutritionInformation nutritionInformation) throws SQLException {
		String insertNutritionInformation = "INSERT INTO NutritionInformation(RecipeId, Calories, TotalFat, Sugar, Sodium, Protein, SaturatedFat, Carbohydrates) VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNutritionInformation);
			insertStmt.setInt(1, nutritionInformation.getRecipe().getRecipeId());
			insertStmt.setFloat(2, nutritionInformation.getCalories());
			insertStmt.setFloat(3, nutritionInformation.getTotalFat());
			insertStmt.setFloat(4, nutritionInformation.getSugar());
			insertStmt.setFloat(5, nutritionInformation.getSodium());
			insertStmt.setFloat(6, nutritionInformation.getProtein());
			insertStmt.setFloat(7, nutritionInformation.getSaturatedFat());
			insertStmt.setFloat(8, nutritionInformation.getCarbohydrates());
			
			insertStmt.executeUpdate();
		
			return nutritionInformation;
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
	

	
	public NutritionInformation getNutritionInformationByRecipeId(int recipeId) throws SQLException {

		String selectNutritionInformation = "SELECT RecipeId, Calories, TotalFat, Sugar, Sodium, Protein, SaturatedFat, Carbohydrates FROM NutritionInformation WHERE RecipeId=?;";
				
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNutritionInformation);
	
			selectStmt.setInt(1, recipeId);
			
			results = selectStmt.executeQuery();
			
			RecipesDao recipeDao = RecipesDao.getInstance();
			
			
			if(results.next()) {
				
				int resultRecipeId = results.getInt("RecipeId");
				Float calories = results.getFloat("Calories");
				Float totalFat = results.getFloat("TotalFat");
				Float sugar = results.getFloat("Sugar");
				Float sodium = results.getFloat("Sodium");
				Float protein = results.getFloat("Protein");
				Float saturatedFat = results.getFloat("SaturatedFat");
				Float carbohydrates = results.getFloat("Carbohydrates");
				
				Recipes recipe = recipeDao.getRecipeById(resultRecipeId);
				
				NutritionInformation nutritionInformation = new NutritionInformation(recipe, calories, totalFat, sugar, sodium, protein, saturatedFat, carbohydrates);
				return nutritionInformation;
				
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


	public NutritionInformation delete(NutritionInformation nutritionInformation) throws SQLException {

		String deleteNutritionInformation = "DELETE FROM NutritionInformation WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNutritionInformation);
			deleteStmt.setInt(1, nutritionInformation.getRecipe().getRecipeId());
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