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
 * Servlet implementation class UpdateIngredientServlet
 */
@WebServlet("/update-ingredient")
public class UpdateIngredientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long ingId = Long.valueOf(request.getParameter("updateIngredientId"));
		String name = request.getParameter("updateName");
		String description = request.getParameter("updateDescription");
		Ingredient ingredient = new Ingredient(ingId,name,description);
		try {
			IngredientService service = new IngredientService();
			service.updateIngredient(ingredient);
			response.sendRedirect(request.getContextPath()+"/ingredients");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
