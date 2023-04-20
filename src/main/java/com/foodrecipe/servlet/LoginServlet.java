package com.foodrecipe.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		PrintWriter out = response.getWriter();
		
		try {
			AuthenticationService service = new AuthenticationService();
			AuthenticationResponse authResponse = service.authenticate(authRequest);
			if(authResponse!=null) {
				out.print(authResponse.getUserId());
				request.setAttribute("id", authResponse.getUserId());
				request.setAttribute("name", authResponse.getFirstname());
				request.setAttribute("status", "success");
				
			}else {
				request.setAttribute("status", "failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
