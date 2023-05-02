package com.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Collections;
import java.util.stream.*;

import com.entity.Category;
import com.entity.Profile;
import com.entity.Recipe;

public class RecipeDAO {

	private final String DB_URL = "jdbc:mysql://localhost/food_recipe_db_jee";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "Erish.r.santos03";

	private final String INSERT_RECIPE = "insert into recipe(title, description, category,"
			+ "ingredients, recipe_profile_id) values(?, ?, ?, ?, ?)";
	private final String SELECT_RECIPE_BY_ID = "select * from recipe where recipe_id = ?";
	private final String SELECT_ALL_BY_PROFILE = "select * from recipe where recipe_profile_id = ?";
	private final String UPDATE_RECIPE = "update recipe set title = ?, description = ?, "
			+ "category = ?, ingredients = ? where recipe_id = ?";
	private final String DELETE_RECIPE = "delete from recipe where recipe_id = ?";
	private final String SELECT_PROFILE_BY_ID = "select * from profile where profile_id = ?";
	private final String SEARCH_RECIPE = "select * from recipe where recipe_profile_id = ? "
										+ "and concat_ws(' ', title, ingredients, category) like ?";
	
	protected Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public void insertRecipe(Recipe recipe) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_RECIPE);) {
			statement.setString(1, recipe.getTitle());
			statement.setString(2, recipe.getDescription());
			statement.setString(3, recipe.getCategory().name());
			statement.setString(4, String.join(",", recipe.getIngredients()));
			statement.setLong(5, recipe.getProfile().getProfileId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Recipe selectRecipeById(long recipeId) {
		Recipe recipe = null;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_RECIPE_BY_ID);) {
			statement.setLong(1, recipeId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				Category category = getCategory(resultSet.getString("category"));
				List<String> ingredients = Arrays.asList(resultSet.getString("ingredients").split(","));
				Profile profile = getProfile(resultSet.getLong("recipe_profile_id"));
				recipe = new Recipe(recipeId, title, description, category, ingredients, profile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipe;
	}

	public List<Recipe> selectAllRecipe(long profileId) {
		List<Recipe> recipes = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_PROFILE);) {
			statement.setLong(1, profileId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				long recipeId = resultSet.getLong("recipe_id");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				Category category = getCategory(resultSet.getString("category"));
				String ingredientsString = resultSet.getString("ingredients");
				List<String> ingredients = Arrays.asList(ingredientsString.split(","));
				Profile profile = getProfile(resultSet.getLong("recipe_profile_id"));
				recipes.add(new Recipe(recipeId, title, description, category, ingredients, profile));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipes;
	}

	public boolean updateRecipe(Recipe recipe) throws  SQLException{
		boolean recipeUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE);) {
			statement.setString(1, recipe.getTitle());
			statement.setString(2, recipe.getDescription());
			statement.setString(3, recipe.getCategory().name());
			statement.setString(4, String.join(",", recipe.getIngredients()));
			statement.setLong(5, recipe.getRecipeId());
			
			recipeUpdated = statement.executeUpdate() > 0;
			
		}
		
		return recipeUpdated;

	}
	
	public boolean deleteRecipe(long recipeId) throws SQLException {
		boolean recipeDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE);) {
			statement.setLong(1, recipeId);
			recipeDeleted = statement.executeUpdate() > 0;
			
		}
		
		return recipeDeleted;
	}

	public Category getCategory(String categoryFromDB) {
		switch (categoryFromDB) {
		case "ENTREE":
			return Category.ENTREE;
		case "MAINCOURSE":
			return Category.MAINCOURSE;
		case "DRINK":
			return Category.DRINK;
		case "DESSERT":
			return Category.DESSERT;
		default:
			return null;
		}
	}

	public Profile getProfile(long profileId) throws SQLException, NoSuchElementException{
		Profile profile = null;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_PROFILE_BY_ID);){
					statement.setLong(1, profileId);
					ResultSet resultSet = statement.executeQuery();
					
					if(resultSet.next()) {
						String firstName = resultSet.getString("first_name");
						String middleName = resultSet.getString("mid_name");
						String lastName = resultSet.getString("last_name");
						profile = new Profile(profileId, firstName, middleName, lastName);
					}
				
	}
		return profile;
	}
	
	public List<Recipe> searchRecipe(Long profileId, String keyword){
		List<Recipe> recipes = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(SEARCH_RECIPE);) {
			statement.setLong(1, profileId);
			statement.setString(2, "%" + keyword + "%");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				long recipeId = resultSet.getLong("recipe_id");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				Category category = getCategory(resultSet.getString("category"));
				String ingredientsString = resultSet.getString("ingredients");
				List<String> ingredients = Arrays.asList(ingredientsString.split(","));

				Profile profile = getProfile(resultSet.getLong("recipe_profile_id"));
				recipes.add(new Recipe(recipeId, title, description, category, ingredients, profile));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipes;
	}

}
