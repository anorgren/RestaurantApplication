package restaurant.servlet;

import restaurant.dal.*;
import restaurant.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/categorycreate")
public class CategoryCreate extends HttpServlet {
	protected CategoriesDao categoriesDao;
	
	@Override
	public void init() throws ServletException {
		categoriesDao = CategoriesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/CategoriesCreate.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        
        String categoryName = request.getParameter("categoryname");
        
        if (categoryName == null || categoryName.trim().isEmpty()) {
        	messages.put("success", "Invalid category name");
        } else {
        	String stringCategoryId = request.getParameter("categoryid");
        	Integer categoryId = null;
        	try {
        		categoryId = Integer.valueOf(stringCategoryId);
        	} catch (NumberFormatException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        	try {
        		Categories category = new Categories(categoryId, categoryName);
        		category = categoriesDao.create(category);
        		messages.put("success", "successfully created " + categoryName);
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        }
        request.getRequestDispatcher("/CategoriesCreate.jsp").forward(request, response);	
	}
	

}
