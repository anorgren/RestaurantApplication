package restaurant.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.dal.CategoriesDao;
import restaurant.model.Categories;


@WebServlet("/categorydelete")
public class CategoryDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Category");        
        req.getRequestDispatcher("/CategoryDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        // Retrieve and validate name.
        String categoryId = req.getParameter("categoryid");
        if (categoryId == null || categoryId.trim().isEmpty()) {
            messages.put("title", "Invalid categoryId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the category.
	        try {
		        Categories category = categoryDao.getCategoryById(Integer.parseInt(categoryId));
	        	category = categoryDao.delete(category);
	        	// Update the message.
		        if (category == null) {
		            messages.put("title", "Successfully deleted " + categoryId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + categoryId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CategoryDelete.jsp").forward(req, resp);
    }
}
