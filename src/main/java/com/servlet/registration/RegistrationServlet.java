package com.servlet.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Profile;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection connection;
	PreparedStatement statement_1;
	PreparedStatement statement_2;
	ResultSet resultSet = null;
	RequestDispatcher dispatcher = null;
	
	public void init(ServletConfig config) {
		try {
//			ServletContext context = config.getServletContext();
			Class.forName("com.mysql.cj.jdbc.Driver");
//			connection = DriverManager.getConnection(context.getInitParameter("dbUrl"), context.getInitParameter("dbUser"), 
//					context.getInitParameter("dbPassword"));
			connection = DriverManager.getConnection("jdbc:mysql://localhost/food_recipe_db_jee", "root", "Erish.r.santos03");
			statement_1 = connection.prepareStatement("insert into profile(first_name, mid_name, last_name)"
					+ "values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			statement_2 = connection.prepareStatement("insert into user(username, password, user_profile_id)"
					+ "values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		long profileId = 0;
		
		try {
			statement_1.setString(1, firstName);
			statement_1.setString(2, middleName);
			statement_1.setString(3, lastName);
			int profileCount = statement_1.executeUpdate();
			
			if(profileCount > 0) {
				resultSet = statement_1.getGeneratedKeys();
				if(resultSet.next()) {
					profileId = resultSet.getLong(1);
				}
			}
			
			statement_2.setString(1, username);
			statement_2.setString(2, password);
			statement_2.setLong(3, profileId);
			int userCount = statement_2.executeUpdate();
			
			if(userCount > 0) {
				response.sendRedirect("login.jsp");
//				request.setAttribute("status", "success");
			}
//			else {
//				request.setAttribute("status", "failed");
//			}
//			
		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}finally {
			try {
				if(resultSet != null) resultSet.close();
			} catch(SQLException e) {
				System.out.print(e.getMessage());
			}
		}
		
	}
	
	public void destroy() {
		try {
			connection.close();
			statement_1.close();
			statement_2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
