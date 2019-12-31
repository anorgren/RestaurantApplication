package restaurant.dal;

import restaurant.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ReviewsDao {

	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}

	/*
	 *  Create a Reviews record in MySQL
	 */
	public Reviews create(Reviews review) throws SQLException {
		String insertReview =
			"INSERT INTO Reviews(RecipeId,UserId,Review,Rating) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, review.getRecipe().getRecipeId());
			insertStmt.setInt(2, review.getUser().getUserId());
			insertStmt.setString(3, review.getReview());
			insertStmt.setInt(3, review.getRating());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			return review;
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

	/**
	 * Get the Reviews record by fetching it from MySQL instance.
	 * This runs a SELECT statement and returns a single Reviews instance.
	 */
	public Reviews getReviewById(int reviewId) throws SQLException {
		String selectReview =
			"SELECT ReviewId,RecipeId,UserId,Review,Rating " +
			"FROM Reviews " +
			"WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			
			// Retrieve the RecipeDao and UsersDao instances
			RecipesDao recipeDao = RecipesDao.getInstance();
			UsersDao userDao = UsersDao.getInstance();
			
			// Obtain each field value
			if(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				int recipeId = results.getInt("RecipeId");
				int userId = results.getInt("UserId");
				String review = results.getString("Review");
				int rating = results.getInt("Rating");
				
				// Create Recipes and Users instances
				Recipes recipe = recipeDao.getRecipeById(recipeId);
				Users user = userDao.getUserById(userId);
				
				
				// Create a Reviews instance with the field values as specified above
				Reviews reviewInstance = new Reviews(resultReviewId, recipe, user, review, rating);
				return reviewInstance;
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
	 * Get the matching Reviews records by fetching from your MySQL instance using the user ID.
	 * This runs a SELECT statement and returns a list of matching Reviews.
	 */
	public List<Reviews> getReviewsByUserId(int userId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT ReviewId,RecipeId,UserId,Review,Rating FROM Reviews WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			
			// Retrieve the RecipeDao and UsersDao instances
			RecipesDao recipeDao = RecipesDao.getInstance();
			UsersDao userDao = UsersDao.getInstance();
			
			
			
			// While there is more Reviews record, add it to the list
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				int recipeId = results.getInt("RecipeId");
				int resultUserId = results.getInt("UserId");
				String review = results.getString("Review");
				int rating = results.getInt("Rating");
				
				// Use the method in UsersDao to fetch the Users object by userName
				Recipes recipe = recipeDao.getRecipeById(recipeId);
				Users user = userDao.getUserById(resultUserId);
				
				// Create a Reviews instance with the field values as specified above
				Reviews reviewInstance = new Reviews(reviewId, recipe, user, review, rating);
				reviews.add(reviewInstance);
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
		return reviews;
	}
	
	
	/**
	 * Delete the Reviews instance.
	 * This runs a DELETE statement.
	 */
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Reviews instance.
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
