package restaurant.servlet;

import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.dal.CategoriesDao;
import restaurant.model.*;

@WebServlet("/findCategories")
public class FindCategories extends HttpServlet{
	protected CategoriesDao categoriesDao;
	
	@Override
	public void init() throws ServletException {
		categoriesDao = CategoriesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		List<Categories> categories = new ArrayList<Categories>();
		
		String categoryName = request.getParameter("categoryname");
		
		if (categoryName == null || categoryName.trim().isEmpty()) {
			messages.put("failure", "Please enter a valid category name.");
		} else {
			try {
				categories = categoriesDao.getCategoryByCategoryName(categoryName);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + categoryName);
			messages.put("previousCategoryName", categoryName);
		}
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/FindCategories.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
		request.setAttribute("messages", messages);
		
		List<Categories> categories = new ArrayList<Categories>();
		
		String categoryName = request.getParameter("categoryname");
		
		if (categoryName == null || categoryName.trim().isEmpty()) {
			messages.put("failure", "Please enter a valid category name.");
		} else {
			try {
				categories = categoriesDao.getCategoryByCategoryName(categoryName);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for " + categoryName);
		}
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/FindCategories.jsp").forward(request, response);
	}
	
}
