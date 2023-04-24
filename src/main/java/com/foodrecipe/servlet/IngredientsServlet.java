package com.foodrecipe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodrecipe.service.IngredientService;
import com.foodrecipe.model.Ingredient;

/**
 * Servlet implementation class IngredientsServlet
 */
@WebServlet("/ingredients")
public class IngredientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			IngredientService service = new IngredientService();
			List<Ingredient> ingredients = service.getAll();
			HttpSession session = request.getSession();
			session.setAttribute("ingredients", ingredients);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ingredients.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
