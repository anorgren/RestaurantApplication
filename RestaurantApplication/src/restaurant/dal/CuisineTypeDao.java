package restaurant.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import restaurant.dal.ConnectionManager;

import restaurant.model.*;

public class CuisineTypeDao {

	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CuisineTypeDao instance = null;
	protected CuisineTypeDao() {
		connectionManager = new ConnectionManager();
	}
	public static CuisineTypeDao getInstance() {
		if(instance == null) {
			instance = new CuisineTypeDao();
		}
		return instance;
	}

	/**
	 * Save the CuisineType instance by storing it in the MySQL instance.
	 * This runs an INSERT statement.
	 */
	public CuisineType create(CuisineType cuisineType) throws SQLException {
		String insertCuisineType = "INSERT INTO CuisineType(TypeDescription) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCuisineType,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, cuisineType.getTypeDescription());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int cuisineTypeId = -1;
			if(resultKey.next()) {
				cuisineTypeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			cuisineType.setCuisineTypeId(cuisineTypeId);
			return cuisineType;
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
	 * Get the CuisineType record by fetching it from MySQL instance.
	 * This runs a SELECT statement and returns a single CuisineType instance.
	 */
	public CuisineType getCuisineTypeFromCuisineTypeId(int cuisineTypeId) throws SQLException {
		String selectCuisineType = "SELECT CuisineTypeId,TypeDescription FROM CuisineType WHERE CuisineTypeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCuisineType);
			selectStmt.setInt(1, cuisineTypeId);
			
			results = selectStmt.executeQuery();
		
			if(results.next()) {
				int resultCuisineTypeId = results.getInt("CuisineTypeId");
				String typeDescription = results.getString("TypeDescription");
				CuisineType cuisineType = new CuisineType(resultCuisineTypeId, typeDescription);
				return cuisineType;
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
	 * Delete the CuisineType instance.
	 * This runs a DELETE statement.
	 */
	public CuisineType delete(CuisineType cuisineType) throws SQLException {
		String deleteCuisineType = "DELETE FROM CuisineType WHERE CuisineTypeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCuisineType);
			deleteStmt.setInt(1, cuisineType.getCuisineTypeId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the CuisineType instance.
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