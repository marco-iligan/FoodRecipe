package com.foodrecipe.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodrecipe.service.AuthenticationService;
import com.foodrecipe.dto.AuthenticationRequest;
import com.foodrecipe.dto.AuthenticationResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/authenticate")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AuthenticationRequest authRequest = new AuthenticationRequest(username,password);
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		try {
			AuthenticationService service = new AuthenticationService();
			AuthenticationResponse authResponse = service.authenticate(authRequest);
			if(authResponse!=null) {
				session.setAttribute("id", authResponse.getUserId());
				session.setAttribute("name", authResponse.getFirstname());
				session.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("index.jsp");
				
			}else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
