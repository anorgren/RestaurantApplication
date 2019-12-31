package restaurant.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Categories;
import restaurant.model.RestaurantCategoryTypes;
import restaurant.model.Restaurants;

public class RestaurantCategoryTypesDao {
	protected ConnectionManager connectionManager;
	private static RestaurantCategoryTypesDao instance = null;
	
	protected RestaurantCategoryTypesDao getInstance( ) {
		if (instance == null) {
			instance = new RestaurantCategoryTypesDao();
		}
		return instance;
	}
	
	public RestaurantCategoryTypes create(RestaurantCategoryTypes restCatTypes) throws SQLException {
		String insertRCT = "INSERT INTO RestaurantCategoryTypes(RestaurantId, CategoryId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStatement = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStatement = connection.prepareStatement(insertRCT, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1,  restCatTypes.getRestaurant().getRestaurantID());
			insertStatement.setInt(2, restCatTypes.getCategory().getCategoryId());
			insertStatement.executeUpdate();
			resultKey = insertStatement.getGeneratedKeys();
			
			int rctId = -1;
			if (resultKey.next()) {
				rctId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			restCatTypes.setRestaurantCategoryTypeId(rctId);
			return restCatTypes;
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
	
	public RestaurantCategoryTypes delete(RestaurantCategoryTypes restCatTypes) throws SQLException {
		String deleteRCT = "DELETE FROM RestaurantCategoryTypes WHERE ResaurantCategoryTypeId = ?;";
		Connection connection = null;
		PreparedStatement deleteStatement = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStatement = connection.prepareStatement(deleteRCT);
			deleteStatement.setInt(1, restCatTypes.getRestaurantCategoryTypeId());
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
	
	public RestaurantCategoryTypes getRestaurantCategoryTypesFromRestaurantCategoryTypeId(int restaurantCategoryTypesId) 
			throws SQLException {
		String selectRCT = "SELECT * FROM RestaurantCategoryTypes WHERE RestaurantCategoryTypeId = ?;";
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		CategoriesDao categoryDao = CategoriesDao.getInstance();
		
		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectRCT);
			selectStatement.setInt(1, restaurantCategoryTypesId);
			results = selectStatement.executeQuery();
			
			if (results.next()) {
				int resultRestaurantCategoryTypeId = results.getInt("RestaurantCategoryTypeId");
				int resultRestaurantId = results.getInt("RestaurantId");
				int resultCategoryId = results.getInt("CategoryId");
				
				Restaurants restaurant = restaurantsDao.getRestaurantFromRestaurantId(resultRestaurantId);
				Categories category = categoryDao.getCategoryById(resultCategoryId);
				RestaurantCategoryTypes resultRCT = 
						new RestaurantCategoryTypes(resultRestaurantCategoryTypeId, restaurant, category);
				return resultRCT;
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
	
	public List<RestaurantCategoryTypes> getRestaurantCategoryTypesFromCategoryId(int categoryId) 
			throws SQLException {
		String selectRCT = "SELECT * FROM RestaurantCategoryTypes WHERE CategoryId = ?;";
		List<RestaurantCategoryTypes> restaurantCategoryTypes = new ArrayList<RestaurantCategoryTypes>();
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		CategoriesDao categoryDao = CategoriesDao.getInstance();
		
		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectRCT);
			selectStatement.setInt(1, categoryId);
			results = selectStatement.executeQuery();
			
			while (results.next()) {
				int resultRestaurantCategoryTypeId = results.getInt("RestaurantCategoryTypeId");
				int resultRestaurantId = results.getInt("RestaurantId");
				int resultCategoryId = results.getInt("CategoryId");
				
				Restaurants restaurant = restaurantsDao.getRestaurantFromRestaurantId(resultRestaurantId);
				Categories category = categoryDao.getCategoryById(resultCategoryId);
				RestaurantCategoryTypes resultRCT = 
						new RestaurantCategoryTypes(resultRestaurantCategoryTypeId, restaurant, category);
				restaurantCategoryTypes.add(resultRCT);
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
		return restaurantCategoryTypes;
	}
	
	public List<RestaurantCategoryTypes> getRestaurantCategoryTypesFromRestaurantId(int restaurantId) 
			throws SQLException {
		String selectRCT = "SELECT * FROM RestaurantCategoryTypes WHERE RestaurantId = ?;";
		List<RestaurantCategoryTypes> restaurantCategoryTypes = new ArrayList<RestaurantCategoryTypes>();
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		CategoriesDao categoryDao = CategoriesDao.getInstance();
		
		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectRCT);
			selectStatement.setInt(1, restaurantId);
			results = selectStatement.executeQuery();
			
			while (results.next()) {
				int resultRestaurantCategoryTypeId = results.getInt("RestaurantCategoryTypeId");
				int resultRestaurantId = results.getInt("RestaurantId");
				int resultCategoryId = results.getInt("CategoryId");
				
				Restaurants restaurant = restaurantsDao.getRestaurantFromRestaurantId(resultRestaurantId);
				Categories category = categoryDao.getCategoryById(resultCategoryId);
				RestaurantCategoryTypes resultRCT = 
						new RestaurantCategoryTypes(resultRestaurantCategoryTypeId, restaurant, category);
				restaurantCategoryTypes.add(resultRCT);
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
		return restaurantCategoryTypes;
	}
}
