package restaurant.servlet;



import restaurant.dal.*;
import restaurant.model.*;

import java.io.IOException;
import java.sql.SQLException;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {

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
		//Just render the JSP.
		req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		int caloricGoal1 = 0;
		int fatGoal1 = 0;
		int carbGoal1 = 0;
		int proteinGoal1 = 0;
		int userZip1 = 0;
		// Retrieve and validate name.
		String userName = req.getParameter("username");
		if (userName == null || userName.trim().isEmpty()) {
			messages.put("success", "Invalid UserName");
		} else {
			// Create the User.

			String password = req.getParameter("password");
			String city = req.getParameter("city");
			String userZip = req.getParameter("userZip");
			String caloricGoal = req.getParameter("CaloricGoal");
			String fatGoal = req.getParameter("FatGoal");
			String carbGoal = req.getParameter("CarbGoal");
			String proteinGoal = req.getParameter("ProteinGoal");
			try {
				userZip1 = Integer.parseInt(userZip);
				caloricGoal1 = Integer.parseInt(caloricGoal);
				fatGoal1 = Integer.parseInt(fatGoal);
				carbGoal1 = Integer.parseInt(carbGoal);
				proteinGoal1 = Integer.parseInt(proteinGoal);

			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new NumberFormatException();
			}


			try {
				// Exercise: parse the input for StatusLevel.


				Users user = new Users(userName, password, city, userZip1, caloricGoal1, fatGoal1, carbGoal1, proteinGoal1);
				user = usersDao.create(user);
				messages.put("success", "Successfully created " + userName);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}


		req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
}
