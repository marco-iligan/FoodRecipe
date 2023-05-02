package com.servlet.Index;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.RecipeDAO;
import com.entity.Category;
import com.entity.Profile;
import com.entity.Recipe;

@WebServlet("/")
public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RecipeDAO recipeDao = new RecipeDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String action = request.getServletPath();

		switch (action) {
		case "/new":
			newForm(request, response);
			break;
		case "/insert":
			try {
				insertNewRecipe(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/edit":
			editForm(request, response);
			break;
		case "/update":
			try {
				updateRecipe(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteThisRecipe(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/search":
			try {
				searchRecipe(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			try {
				listRecipe(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void newForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("recipe_form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertNewRecipe(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		HttpSession session = request.getSession();

		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String categoryString = request.getParameter("category");
		Category category = getCategoryEquivalent(categoryString);
		String ingredientsString = request.getParameter("ingredients");
		String[] ingredientsArray = ingredientsString.split(",");
		List<String> ingredients = new ArrayList<>();
		int x = 0;
		for (String item : ingredientsArray) {
			ingredients.add(item.trim());
			x++;
		}
		
		Profile profile = recipeDao.getProfile(Long.parseLong(session.getAttribute("profileId").toString()));
		Recipe recipe = new Recipe(title, description, category, ingredients, profile);
		recipeDao.insertRecipe(recipe);
		response.sendRedirect("list");
	}

	private void deleteThisRecipe(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		long recipeId = Long.parseLong(request.getParameter("recipeId"));
		try {
			recipeDao.deleteRecipe(recipeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}

	private void editForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long recipeId = Long.parseLong(request.getParameter("recipeId"));
		Recipe existingRecipe = new Recipe();

		try {
			existingRecipe = recipeDao.selectRecipeById(recipeId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("recipe_form.jsp");
			existingRecipe.getIngredients();
			request.setAttribute("recipe", existingRecipe);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateRecipe(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		HttpSession session = request.getSession();
		
		Long recipeId = Long.parseLong(request.getParameter("recipeId"));
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String categoryString = request.getParameter("category");
		Category category = getCategoryEquivalent(categoryString);
		String ingredientsString = request.getParameter("ingredients");
		String[] ingredientsArray = ingredientsString.split(",");
		List<String> ingredients = new ArrayList<>();
		int x = 0;
		for (String item : ingredientsArray) {
			ingredients.add(item.trim());
			System.out.println(ingredients.get(x));
			x++;
		}
		
		Profile profile = recipeDao.getProfile(Long.parseLong(session.getAttribute("profileId").toString()));
		Recipe recipe = new Recipe(recipeId, title, description, category, ingredients, profile);
		recipeDao.updateRecipe(recipe);
		response.sendRedirect("list");
	}

	private void listRecipe(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession();
		Long profileId = Long.parseLong(session.getAttribute("profileId").toString());
		try {
			List<Recipe> recipeList = recipeDao.selectAllRecipe(profileId);
			request.setAttribute("recipeList", recipeList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void searchRecipe(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession();
		Long profileId = Long.parseLong(session.getAttribute("profileId").toString());
		String keyword = request.getParameter("keyword");
		try {
			List<Recipe> recipeList = recipeDao.searchRecipe(profileId, keyword);
			request.setAttribute("recipeList", recipeList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Category getCategoryEquivalent(String category) {
		switch (category) {
		case "Entree":
			return Category.ENTREE;
		case "Main Course":
			return Category.MAINCOURSE;
		case "Drink":
			return Category.DRINK;
		case "Dessert":
			return Category.DESSERT;
		default:
			return null;
		}
	}

}
