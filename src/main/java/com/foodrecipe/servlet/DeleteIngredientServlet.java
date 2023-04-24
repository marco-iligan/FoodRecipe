package com.foodrecipe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodrecipe.service.IngredientService;

/**
 * Servlet implementation class DeleteIngredientServlet
 */
@WebServlet("/delete-ingredient")
public class DeleteIngredientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long ingId = Long.valueOf(request.getParameter("id"));
		try {
			IngredientService service = new IngredientService();
			service.deleteIngredient(ingId);
			response.sendRedirect(request.getContextPath()+"/ingredients");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
