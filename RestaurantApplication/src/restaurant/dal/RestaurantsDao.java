package restaurant.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import restaurant.model.Restaurants;


public class RestaurantsDao {
	
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RestaurantsDao instance = null;
	protected RestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RestaurantsDao getInstance() {
		if(instance == null) {
			instance = new RestaurantsDao();
		}
		return instance;
	}



  /**
   * Save the Ingredients instance by storing it in your MySQL instance.
   * This runs a INSERT statement.
   */
  public Restaurants create(Restaurants restaurants) throws SQLException {
    String insertRestaurants = "INSERT INTO Restaurants(RestaurantId,RestaurantName,Latitude,Longitude,Address,City,State,Zip,Price) VALUES(?,?,?,?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertRestaurants);
      insertStmt.setInt(1, restaurants.getRestaurantID());
      insertStmt.setString(2, restaurants.getRestaurantName());
      insertStmt.setFloat(3, restaurants.getLatitude());
      insertStmt.setFloat(4, restaurants.getLongitude());
      insertStmt.setString(5, restaurants.getAddress());
      insertStmt.setString(6, restaurants.getCity());
      insertStmt.setString(7, restaurants.getState());
      insertStmt.setInt(8, restaurants.getZip());
      insertStmt.setString(9, restaurants.getPrice());


      insertStmt.executeUpdate();

      return restaurants;
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
   * Get the Persons record by fetching it from your MySQL instance.
   * This runs a SELECT statement and returns a single Persons instance.
   */

  public Restaurants getRestaurantFromRestaurantId(int restaurantId) throws SQLException {

    String selectRestaurant = "SELECT RestaurantId,RestaurantName,Latitude,Longitude,Address,City,State,Zip,Price FROM Restaurants WHERE RestaurantId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRestaurant);

      selectStmt.setInt(1, restaurantId);

      results = selectStmt.executeQuery();



      if(results.next()) {
        int resultRestaurantId = results.getInt("RestaurantId");
        String restaurantName = results.getString("RestaurantName");
        Float latitude = results.getFloat("Latitude");
        Float longitude = results.getFloat("Longitude");
        String address = results.getString("Address");
        String city = results.getString("City");
        String state = results.getString("State");
        int zip = results.getInt("Zip");
        String price = results.getString("Price");



        Restaurants restaurant = new Restaurants(resultRestaurantId, restaurantName, latitude, longitude, address, city, state, zip, price);
        return restaurant;
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
	 * Get the matching Restaurants records by fetching from your MySQL instance using the user ID.
	 * This runs a SELECT statement and returns a list of matching Reviews.
	 */
	public List<Restaurants> getRestaurantsByUserId(int userId) throws SQLException {
		List<Restaurants> restaurants = new ArrayList<Restaurants>();
		String selectRestaurants =
			"SELECT RestaurantId,RestaurantName,Latitude,Longitude,Address,City,State,Zip,Price FROM Restaurants WHERE Zip = (SELECT UserZip FROM Users WHERE UserId=?);";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			
	
			// While there is more Restaurants record, add it to the list
			while(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String restaurantName = results.getString("RestaurantName");
				Float latitude = results.getFloat("Latitude");
		        Float longitude = results.getFloat("Longitude");
		        String address = results.getString("Address");
		        String city = results.getString("City");
		        String state = results.getString("State");
		        int zip = results.getInt("Zip");
		        String price = results.getString("Price");
	
				
				// Create a Restaurants instance with the field values as specified above
		        Restaurants restaurant = new Restaurants(restaurantId, restaurantName, latitude, longitude, address, city, state, zip, price);
				restaurants.add(restaurant);
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
		return restaurants;
	}
	
	/**
	 * Get the matching Restaurants records by fetching from your MySQL instance using the zipCode.
	 * This runs a SELECT statement and returns a list of Restaurants in that ZipCode.
	 */
	public List<Restaurants> getRestaurantsByZipCode(int zipCode) throws SQLException {
		List<Restaurants> restaurants = new ArrayList<Restaurants>();
		String selectRestaurants =
			"SELECT RestaurantId,RestaurantName,Latitude,Longitude,Address,City,State,Zip,Price FROM Restaurants WHERE Zip =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setInt(1, zipCode);
			results = selectStmt.executeQuery();
			

			// While there is more Restaurants record, add it to the list
			while(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String restaurantName = results.getString("RestaurantName");
				Float latitude = results.getFloat("Latitude");
		        Float longitude = results.getFloat("Longitude");
		        String address = results.getString("Address");
		        String city = results.getString("City");
		        String state = results.getString("State");
		        int resultZip = results.getInt("Zip");
		        String price = results.getString("Price");
	
				
				// Create a Restaurants instance with the field values as specified above
		        Restaurants restaurant = new Restaurants(restaurantId, restaurantName, latitude, longitude, address, city, state, resultZip, price);
				restaurants.add(restaurant);
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
		return restaurants;
	}
	
	
	public List<Restaurants> getRestaurantsByCuisineType(String cuisineType) throws SQLException {
		List<Restaurants> restaurants = new ArrayList<Restaurants>();
		String selectRestaurants = "SELECT RestaurantId,RestaurantName,Latitude,Longitude,Address,City,State,Zip,Price FROM Restaurants WHERE RestaurantId = (SELECT RestaurantId FROM RestaurantCuisineTypes WHERE CuisineTypeId = (SELECT CuisineTypeId FROM CuisineType WHERE TypeDescription LIKE ?));";
								
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setString(1, cuisineType);
			
			results = selectStmt.executeQuery();
		
			// While there is more Restaurants record, add it to the list
			while(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String restaurantName = results.getString("RestaurantName");
				float latitude = results.getFloat("Latitude");
				float longitude = results.getFloat("Longitude");
				String address = results.getString("Address");
				String city = results.getString("City");
				String state = results.getString("State");
				int resultZip = results.getInt("Zip");
				String price = results.getString("Price");
				
				// Create a Recipes instance with the field values as specified above
				Restaurants restaurant = new Restaurants(restaurantId, restaurantName, latitude, longitude, address, city, state, resultZip, price);
				
				// Add recipe to the list
				restaurants.add(restaurant);
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
		return restaurants;
	}
	
	


  /**
   * Delete the Restaurants instance.
   * This runs a DELETE statement.
   */

  public Restaurants delete(Restaurants restaurant) throws SQLException {

    String deleteRestaurant = "DELETE FROM Restaurants WHERE RestaurantId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteRestaurant);
      deleteStmt.setInt(1, restaurant.getRestaurantID());
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