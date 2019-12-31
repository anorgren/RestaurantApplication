package restaurant.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.RestaurantCuisineTypes;
import restaurant.model.Restaurants;
import restaurant.model.CuisineType;

public class RestaurantCuisineTypesDao {

	protected ConnectionManager connectionManager;
	
	private static RestaurantCuisineTypesDao instance = null;

	protected RestaurantCuisineTypesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static RestaurantCuisineTypesDao getInstance() {
		if (instance == null) {
			instance = new RestaurantCuisineTypesDao();
		}
		return instance;
	}
	
	public RestaurantCuisineTypes create(RestaurantCuisineTypes restCuisTypes) throws SQLException {
		String insertRCT = "INSERT INTO RestaurantCuisineTypes(RestaurantId, CuisineTypeId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStatement = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStatement = connection.prepareStatement(insertRCT, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, restCuisTypes.getRestaurant().getRestaurantID());
			insertStatement.setInt(2, restCuisTypes.getCuisineType().getCuisineTypeId());
			insertStatement.executeUpdate();
			
			resultKey = insertStatement.getGeneratedKeys();
			int rctId = -1;
			if (resultKey.next()) {
				rctId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			restCuisTypes.setRestaurantTypeId(rctId);
			return restCuisTypes;
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
	
	public RestaurantCuisineTypes delete(RestaurantCuisineTypes restCuisTypes) throws SQLException {
		String deleteRCT = "DELETE FROM RestaurantCuisineTypes WHERE ResaurantTypeId = ?;";
		Connection connection = null;
		PreparedStatement deleteStatement = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStatement = connection.prepareStatement(deleteRCT);
			deleteStatement.setInt(1,  restCuisTypes.getRestaurantTypeId());
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
	
	public RestaurantCuisineTypes getRestaurantCuisineTypesFromRestaurantCuisineTypesId(int restaurantCuisineTypesId) 
			throws SQLException {
		String selectRCT = " SELECT * FROM RestaurantCuisineTypes WHERE RestaurantCuisineTypeId = ?;";
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		CuisineTypeDao cuisineTypeDao = CuisineTypeDao.getInstance();
		
		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectRCT);
			selectStatement.setInt(1, restaurantCuisineTypesId);
			results = selectStatement.executeQuery();
			
			if (results.next()) {
				int resultRestaurantTypeId = results.getInt("RestaurantTypeId");
				int resultRestaurantId = results.getInt("RestaurantId");
				int resultCuisineTypeId = results.getInt("CuisineTypeId");
				
				Restaurants restaurant = restaurantsDao.getRestaurantFromRestaurantId(resultRestaurantId);
				CuisineType cuisineType = cuisineTypeDao.getCuisineTypeFromCuisineTypeId(resultCuisineTypeId);
				RestaurantCuisineTypes resultRCT = new RestaurantCuisineTypes(resultRestaurantTypeId, restaurant, cuisineType);
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
	
	public List<RestaurantCuisineTypes> getRestaurantCuisineTypesFromRestaurantId(int restaurantId) 
			throws SQLException {
		String selectRestaurant = "SELECT * FROM RestaurantCuisineTypes WHERE RestaurantId = ?;";
		List<RestaurantCuisineTypes> restaurantCuisineTypesList = new ArrayList<RestaurantCuisineTypes>();
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		CuisineTypeDao cuisineTypeDao = CuisineTypeDao.getInstance();
		
		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectRestaurant);
			selectStatement.setInt(1, restaurantId);
			results = selectStatement.executeQuery();
			
			while (results.next()) {
				int resultRestaurantTypeId = results.getInt("RestaurantTypeId");
				int resultRestaurantId = results.getInt("ResultId");
				int resultCuisineTypeId = results.getInt("CuisineTypeId");
				
				Restaurants restaurant = restaurantsDao.getRestaurantFromRestaurantId(resultRestaurantId);
				CuisineType cuisineType = cuisineTypeDao.getCuisineTypeFromCuisineTypeId(resultCuisineTypeId);
				RestaurantCuisineTypes resultRCT = new RestaurantCuisineTypes(resultRestaurantTypeId, restaurant, cuisineType);
				restaurantCuisineTypesList.add(resultRCT);
				return null;
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
		return restaurantCuisineTypesList;
	}
	public List<RestaurantCuisineTypes> getRestaurantCuisineTypesFromCuisineTypeId(int cuisineTypeId) 
			throws SQLException {
		String selectRestaurant = "SELECT * FROM RestaurantCuisineTypes WHERE CuisineTypeId = ?;";
		List<RestaurantCuisineTypes> restaurantCuisineTypesList = new ArrayList<RestaurantCuisineTypes>();
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;
		RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
		CuisineTypeDao cuisineTypeDao = CuisineTypeDao.getInstance();
		
		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectRestaurant);
			selectStatement.setInt(1, cuisineTypeId);
			results = selectStatement.executeQuery();
			
			while (results.next()) {
				int resultRestaurantTypeId = results.getInt("RestaurantTypeId");
				int resultRestaurantId = results.getInt("ResultId");
				int resultCuisineTypeId = results.getInt("CuisineTypeId");
				
				Restaurants restaurant = restaurantsDao.getRestaurantFromRestaurantId(resultRestaurantId);
				CuisineType cuisineType = cuisineTypeDao.getCuisineTypeFromCuisineTypeId(resultCuisineTypeId);
				RestaurantCuisineTypes resultRCT = new RestaurantCuisineTypes(resultRestaurantTypeId, restaurant, cuisineType);
				restaurantCuisineTypesList.add(resultRCT);
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
		return restaurantCuisineTypesList;
	}
}
