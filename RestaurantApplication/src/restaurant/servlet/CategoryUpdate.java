package restaurant.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.dal.CategoriesDao;
import restaurant.model.Categories;


@WebServlet("/categoryupdate")
public class CategoryUpdate extends HttpServlet {
	
	protected CategoriesDao categoryDao;
	
	@Override
	public void init() throws ServletException {
		categoryDao = CategoriesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String categoryid = req.getParameter("categoryid");
        if (categoryid == null || categoryid.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CategoryId.");
        } else {
        	try {
        		Categories category = categoryDao.getCategoryById(Integer.parseInt(categoryid));
        		if(category == null) {
        			messages.put("success", "CategoryId does not exist.");
        		}
        		req.setAttribute("category", category);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CategoryUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String categoryId = req.getParameter("categoryid");
        if (categoryId == null || categoryId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid CategoryId.");
        } else {
        	try {
        		Categories category = categoryDao.getCategoryById(Integer.parseInt(categoryId));
        		if(category == null) {
        			messages.put("success", "CategoryId does not exist. No update to perform.");
        		} else {
        			String newCategoryName = req.getParameter("categoryname");
        			if (newCategoryName == null || newCategoryName.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid CategoryName.");
        	        } else {
        	        	category = categoryDao.updateCategoryName(category, newCategoryName);
        	        	messages.put("success", "Successfully updated " + categoryId);
        	        }
        		}
        		req.setAttribute("category", category);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CategoryUpdate.jsp").forward(req, resp);
    }
}
