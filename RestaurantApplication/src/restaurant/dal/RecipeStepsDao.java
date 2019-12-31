package restaurant.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import restaurant.dal.ConnectionManager;

import restaurant.model.*;



public class RecipeStepsDao {
	
	protected ConnectionManager connectionManager;
	
	private static RecipeStepsDao instance = null;
	protected RecipeStepsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecipeStepsDao getInstance() {
		if(instance == null) {
			instance = new RecipeStepsDao();
		}
		return instance;
	}
	
	// This is the INSERT operation to create a RecipeSteps record
	public RecipeSteps create(RecipeSteps recipeSteps) throws SQLException{
		String insertRecipeSteps = "INSERT INTO RecipeSteps(RecipeId,StepNumber,Instruction) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecipeSteps,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, recipeSteps.getRecipe().getRecipeId());
			insertStmt.setInt(2, recipeSteps.getStepNumber());
			insertStmt.setString(3, recipeSteps.getInstruction());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int stepId = -1;
			if(resultKey.next()) {
				stepId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recipeSteps.setStepId(stepId);
			return recipeSteps;
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

	// This returns the RecipeSteps record fetched from MySQL using the recipeID
	public RecipeSteps getRecipeStepsById(int stepId) throws SQLException {
		String selectReview =
			"SELECT StepId,RecipeId,StepNumber,Instruction " +
			"FROM RecipeSteps " +
			"WHERE StepId=? ORDER BY StepNumber ASC;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, stepId);
			results = selectStmt.executeQuery();
			
			// Retrieve the Recipes instance
			RecipesDao recipeDao = RecipesDao.getInstance();

			
			// Obtain each field value
			if(results.next()) {
				int resultStepId = results.getInt("StepId");
				int recipeId = results.getInt("RecipeId");
				int stepNumber = results.getInt("StepNumber");
				String instruction = results.getString("Instruction");
				
				// Use the method in RecipesDao to fetch the Recipes object by recipeId
				Recipes recipe = recipeDao.getRecipeById(recipeId);
				
				// Create a RecipeSteps instance with the field values as specified above
				RecipeSteps recipeSteps = new RecipeSteps(resultStepId, recipe, stepNumber, instruction);
				return recipeSteps;
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
	public List<RecipeSteps> getRecipeStepsByRecipeId(int recipeId) throws SQLException {
		List<RecipeSteps> recipeSteps = new ArrayList<RecipeSteps>();
		String selectRecipeSteps =
			"SELECT StepId,RecipeId,StepNumber,Instruction FROM RecipeSteps WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipeSteps);
			selectStmt.setInt(1, recipeId);
			results = selectStmt.executeQuery();
			
			// Retrieve the RecipeDao instance
			RecipesDao recipeDao = RecipesDao.getInstance();
			
			// While there is more RecipeSteps, add it to the list
			while(results.next()) {
				int stepId = results.getInt("StepId");
				int resultRecipeId = results.getInt("RecipeId");
				int stepNumber = results.getInt("StepNumber");
				String instruction = results.getString("Instruction");
				
				// Use the method in RecipesDao to fetch the Recipes object by recipeId
				Recipes recipe = recipeDao.getRecipeById(resultRecipeId);
				
				// Create a Reviews instance with the field values as specified above
				RecipeSteps reviewInstance = new RecipeSteps(stepId, recipe, stepNumber, instruction);
				recipeSteps.add(reviewInstance);
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
		return recipeSteps;
	}
	
	
	/**
	 * Delete the RecipeSteps instance.
	 * This runs a DELETE statement.
	 */
	public RecipeSteps delete(RecipeSteps recipeSteps) throws SQLException {
		String deleteRecipeStep = "DELETE FROM RecipeSteps WHERE StepId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecipeStep);
			deleteStmt.setInt(1, recipeSteps.getStepId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the RecipeSteps instance.
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
