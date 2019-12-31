package restaurant.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Categories;
import restaurant.model.CuisineType;
import restaurant.model.RestaurantCategoryTypes;
import restaurant.model.RestaurantCuisineTypes;
import restaurant.model.RestaurantReviews;
import restaurant.model.Restaurants;

public class RestaurantReviewsDao {
	protected ConnectionManager connectionManager;
	private static RestaurantReviewsDao instance = null;
	
	protected RestaurantReviewsDao getInstance( ) {
		if (instance == null) {
			instance = new RestaurantReviewsDao();
		}
		return instance;
	}
	
	public RestaurantReviews create(RestaurantReviews restaurantReview) throws SQLException {
		String insertRestaurantReview = "INSERT INTO RestaurantReviews(RestaurantId, AverageReview) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStatement = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStatement = connection.prepareStatement(insertRestaurantReview, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1,  restaurantReview.getRestaurant().getRestaurantID());
			insertStatement.setDouble(2, restaurantReview.getAverageReview());
			insertStatement.executeUpdate();
			resultKey = insertStatement.getGeneratedKeys();
			
			
			int rctId = -1;
			if (resultKey.next()) {
				rctId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			restaurantReview.setRestaurantCategoryTypeId(rctId);
			return restaurantReview;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStatement != null) {
				insertStatement.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}

		}
	}
	
	public RestaurantReviews delete(RestaurantReviews restaurantReview) throws SQLException {
		String deleteRCT = "DELETE FROM RestaurantReviews WHERE RestaurantReviewId = ?;";
		Connection connection = null;
		PreparedStatement deleteStatement = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStatement = connection.prepareStatement(deleteRCT);
			deleteStatement.setInt(1, restaurantReview.getRestaurantReviewId());
			deleteStatement.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStatement != null) {
				deleteStatement.close();
			}
		}
	}
	
	public RestaurantReviews getRestaurantReviewFromRestaurantId(int restaurantId) 
			throws SQLException {
		String selectRestaurantReview = "SELECT RestaurantReviewId,RestaurantId,AverageReview FROM RestaurantReviews WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		CategoriesDao categoryDao = CategoriesDao.getInstance();
		
		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectRestaurantReview);
			selectStatement.setInt(1, restaurantId);
			results = selectStatement.executeQuery();
			
			if (results.next()) {
				int restaurantReviewId = results.getInt("RestaurantReviewId");
				int resultRestaurantId = results.getInt("RestaurantId");
				double averageReview = results.getDouble("AverageReview");
				
				Restaurants restaurant = restaurantsDao.getRestaurantFromRestaurantId(resultRestaurantId);
				RestaurantReviews restaurantReview = new RestaurantReviews(restaurantReviewId, restaurant, averageReview);
				return restaurantReview;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStatement != null) {
				selectStatement.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	
}
