package restaurant.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Categories;
import restaurant.model.Ingredients;


public class CategoriesDao {
	protected ConnectionManager connectionManager;
	
	private static CategoriesDao instance = null;

	protected CategoriesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static CategoriesDao getInstance() {
		if (instance == null) {
			instance = new CategoriesDao();
		}
		return instance;
	}
	
	public Categories create(Categories category) throws SQLException {
		String insertCategory = "INSERT INTO Categories(CategoryId, CategoryName) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStatement = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStatement = connection.prepareStatement(insertCategory);
			insertStatement.setInt(1, category.getCategoryId());
			insertStatement.setString(2, category.getCategoryName());
			insertStatement.executeUpdate();
			return category;	
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
		}		
	}
	
	public Categories delete(Categories category) throws SQLException {
		String deleteCategory = "DELETE FROM Categories WHERE CategoryId = ?";
		Connection connection = null;
		PreparedStatement deleteStatement = null;
		try {
			connection = connectionManager.getConnection();
			deleteStatement = connection.prepareStatement(deleteCategory);
			deleteStatement.setInt(1, category.getCategoryId());
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
	
	public Categories getCategoryById(int id) throws SQLException {
		String selectCategory = "SELECT * FROM Categories WHERE CategoryId = ?;";
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectCategory);
			selectStatement.setInt(1, id);
			results = selectStatement.executeQuery();
			
			if(results.next()) {
				int resultCategoryId = results.getInt("CategoryId");
				String resultCategoryName = results.getString("CategoryName");
				Categories category = new Categories(resultCategoryId, resultCategoryName);
				return category;
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
	
	public List<Categories> getCategoryByCategoryName(String categoryName) throws SQLException {
		String selectCategory = "SELECT * FROM Categories WHERE CategoryName = ?;";
		List<Categories> categories = new ArrayList<Categories>();
		Connection connection = null;
		PreparedStatement selectStatement = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStatement = connection.prepareStatement(selectCategory);
			selectStatement.setString(1, categoryName);
			results = selectStatement.executeQuery();
			
			while(results.next()) {
				int resultCategoryId = results.getInt("CategoryId");
				String resultCategoryName = results.getString("CategoryName");
				Categories category = new Categories(resultCategoryId, resultCategoryName);
				categories.add(category);
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
		return categories;
	}
	
	public Categories updateCategoryName(Categories category, String newCategoryName) throws SQLException {
		String updateCategory = "UPDATE Categories SET CategoryName = ? WHERE CategoryId = ?;";
		Connection connection = null;
		PreparedStatement updateStatement = null;
		try {
			connection = connectionManager.getConnection();
			updateStatement = connection.prepareStatement(updateCategory);
			updateStatement.setString(1, newCategoryName);
			updateStatement.setInt(2, category.getCategoryId());
			updateStatement.executeUpdate();
			category.setCategoryName(newCategoryName);
			return category; 
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStatement != null) {
				updateStatement.close();
			}
		}
	}
	
}