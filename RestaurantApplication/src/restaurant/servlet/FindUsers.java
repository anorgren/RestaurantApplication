package restaurant.servlet;

import restaurant.dal.*;
import restaurant.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findusers")
public class FindUsers extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Users> users = new ArrayList<Users>();
        
        
        String city = req.getParameter("City");
        if (city == null || city.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	
        	try {
            	users = usersDao.getUsersFromCity(city);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + city);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousCity", city);
        }
        req.setAttribute("users", users);
        
        req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Users> users = new ArrayList<Users>();
        
       
        String city = req.getParameter("city");
        if (city == null || city.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Users, and store as a message.
        	try {
            	users = usersDao.getUsersFromCity(city);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + city);
        }
        req.setAttribute("users", users);
        
        req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
    }
}
