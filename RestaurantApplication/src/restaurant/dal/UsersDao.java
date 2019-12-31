package restaurant.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurant.dal.ConnectionManager;

import restaurant.model.*;

public class UsersDao {
	
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	/**
	 * Save the Users instance by storing it in the MySQL instance.
	 * This runs an INSERT statement.
	 */
	public Users create(Users user) throws SQLException {
		String insertUser = "INSERT INTO Users(UserId,UserName,Password,UserCity, UserZip, CaloricGoal,FatGoal,CarbGoal,ProteinGoal) VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);

			insertStmt.setInt(1, user.getUserId());
			insertStmt.setString(2, user.getUserName());
			insertStmt.setString(3, user.getPassword());
			insertStmt.setString(4, user.getCity());
			insertStmt.setInt(5, user.getUserZip());
			insertStmt.setInt(6, user.getCaloricGoal());
			insertStmt.setInt(7, user.getFatGoal());
			insertStmt.setInt(8, user.getCarbGoal());
			insertStmt.setInt(9,  user.getProteinGoal());
			
			insertStmt.executeUpdate();
			

			return user;
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
	 * Get the Users record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Users instance.
	 */
	public Users getUserById(int userId) throws SQLException {
		String selectUser = "SELECT UserId,UserName,Password,UserCity, UserZip, CaloricGoal,FatGoal,CarbGoal,ProteinGoal FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userId);
			
			results = selectStmt.executeQuery();
		
			if(results.next()) {
				int resultUserId = results.getInt("UserId");
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String city = results.getString("UserCity");
				int userZip = results.getInt("UserZip");
				int caloricGoal = results.getInt("CaloricGoal");
				int fatGoal = results.getInt("FatGoal");
				int carbGoal = results.getInt("CarbGoal");
				int proteinGoal = results.getInt("ProteinGoal");
				Users user = new Users(resultUserId, userName, password, city,userZip, caloricGoal, fatGoal, carbGoal, proteinGoal);
				return user;
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
	 * Get the Users record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Users instance.
	 */
	public Users getUserByUserName(int userId) throws SQLException {
		String selectUser = "SELECT UserId,UserName,Password,UserCity,UserZip,CaloricGoal,FatGoal,CarbGoal,ProteinGoal FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userId);
			
			results = selectStmt.executeQuery();
		
			if(results.next()) {
				int resultUserId = results.getInt("UserId");
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String city = results.getString("UserCity");
				int userZip = results.getInt("UserZip");
				int caloricGoal = results.getInt("CaloricGoal");
				int fatGoal = results.getInt("FatGoal");
				int carbGoal = results.getInt("CarbGoal");
				int proteinGoal = results.getInt("ProteinGoal");
				Users user = new Users(resultUserId, userName, password, city, userZip, caloricGoal, fatGoal, carbGoal, proteinGoal);
				return user;
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
	 * Get the Users record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Users instance.
	 */
	public Users getUserByUserName(String userName) throws SQLException {
		String selectUser = "SELECT UserId,UserName,Password,UserCity,UserZip,CaloricGoal,FatGoal,CarbGoal,ProteinGoal FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			
			results = selectStmt.executeQuery();
		
			if(results.next()) {
				int userId = results.getInt("UserId");
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String userCity = results.getString("UserCity");
				int userZip = results.getInt("UserZip");
				int caloricGoal = results.getInt("CaloricGoal");
				int fatGoal = results.getInt("FatGoal");
				int carbGoal = results.getInt("CarbGoal");
				int proteinGoal = results.getInt("ProteinGoal");
				Users user = new Users(userId, resultUserName, password, userCity, userZip, caloricGoal, fatGoal, carbGoal, proteinGoal);
				return user;
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
	
	
	

	public List<Users> getUsersFromCity(String city)
			throws SQLException {
		List<Users> users = new ArrayList<Users>();
		String selectUsers =
			"SELECT UserId,UserName,Password,UserCity,UserZip,CaloricGoal,FatGoal,CarbGoal,ProteinGoal FROM Users WHERE UserCity=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, city);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int userId = results.getInt("UserId");
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String resultCity = results.getString("UserCity");
				int userZip = results.getInt("UserZip");
				int caloricGoal = results.getInt("CaloricGoal");
				int fatGoal = results.getInt("FatGoal");
				int carbGoal = results.getInt("CarbGoal");
				int proteinGoal = results.getInt("ProteinGoal");
				Users user = new Users(userId, resultUserName, password, resultCity, userZip, caloricGoal, fatGoal, carbGoal, proteinGoal);
				users.add(user);
			
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
		return users;
	}


	
	//UpdateUserName (String UserId, String )
		public Users update(Users user, String newUserName) throws SQLException {
			String updateUserName = "UPDATE Users SET UserName=? WHERE UserId=?;";
			Connection connection = null;
			PreparedStatement updateStmt = null;
			try {
				connection = connectionManager.getConnection();
				updateStmt = connection.prepareStatement(updateUserName);
				updateStmt.setString(1, newUserName);
				updateStmt.setInt(2, user.getUserId());
				updateStmt.executeUpdate();
				
				// Update the Users parameter before returning to the caller.
				user.setUserName(newUserName);
				return user;
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(updateStmt != null) {
					updateStmt.close();
				}
			}
		}
	
		/**
		 * Delete the Users instance.
		 * This runs a DELETE statement.
		 */
		public Users delete(Users user) throws SQLException {
			String deleteUser = "DELETE FROM Users WHERE UserName=?;";
			Connection connection = null;
			PreparedStatement deleteStmt = null;
			try {
				connection = connectionManager.getConnection();
				deleteStmt = connection.prepareStatement(deleteUser);
				deleteStmt.setString(1, user.getUserName());
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

	}
