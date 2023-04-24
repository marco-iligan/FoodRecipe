package com.foodrecipe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodrecipe.model.Ingredient;
import com.foodrecipe.service.IngredientService;

/**
 * Servlet implementation class AddIngredientServlet
 */
@WebServlet("/add-ingredient")
public class AddIngredientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("newName");
		String description = request.getParameter("newDescription");
		try {
			IngredientService service = new IngredientService();
			Ingredient ingredient = new Ingredient((long)0,name,description);
			service.addIngredient(ingredient);
			response.sendRedirect(request.getContextPath()+"/ingredients");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
