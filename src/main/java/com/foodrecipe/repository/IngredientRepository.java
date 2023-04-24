package com.foodrecipe.repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.foodrecipe.model.Ingredient;

public class IngredientRepository extends RepositoryBase implements IRepository<Ingredient>{

	public IngredientRepository() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Ingredient> getAll() {
		List<Ingredient> ingredients = new ArrayList<>();
		try {
			open();
			String query = "SELECT * FROM ingredients_tbl";
			prepareCreateStatement(query);
			prepareResultSet();
			
			while(resultSet.next()) {
				ingredients.add(new Ingredient(resultSet.getLong("id"), resultSet.getString("name"),resultSet.getString("description")));
			}
			close();
		}catch(SQLException e) {
			Logger.getLogger(IngredientRepository.class.getName()).log(Level.SEVERE, null, e);
		}
		return ingredients;
	}

	@Override
	public Ingredient findById(Long id) {
		try {
			open();
			String query = "SELECT * FROM ingredients_tbl WHERE id = ?";
			prepareCreateStatement(query);
			List<Object> params = new ArrayList<>();
			params.add(id);
			setParams(params);
			prepareResultSet();
			resultSet.next();
			Ingredient ingredient = new Ingredient(resultSet.getLong("id"), resultSet.getString("name"),resultSet.getString("description"));
			close();
			return ingredient;
		}catch(SQLException e) {
			Logger.getLogger(IngredientRepository.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	@Override
	public int create(Ingredient model) {
		int flag = -1;
		try {
			open();
			String query = "INSERT INTO ingredients_tbl(name,description) values(?,?)";
			prepareCreateStatement(query);
			List<Object> params = new ArrayList<>();
			params.add(model.getName());
			params.add(model.getDescription());
			setParams(params);
			flag = executeStatement();
			close();
			return flag;
			
		}catch(SQLException e) {
			Logger.getLogger(IngredientRepository.class.getName()).log(Level.SEVERE, null, e);
		}
		return flag;
		
	}

	@Override
	public int update(Ingredient model) {
		int flag = -1;
		try {
			open();
			String query = "UPDATE ingredients_tbl SET name = ?, description = ? WHERE id = ?";
			prepareCreateStatement(query);
			List<Object> params = new ArrayList<>();
			params.add(model.getName());
			params.add(model.getDescription());
			params.add(model.getId());
			setParams(params);
			flag = executeStatement();
			close();
			return flag;
		}catch(SQLException e) {
			Logger.getLogger(IngredientRepository.class.getName()).log(Level.SEVERE, null, e);
		}
		return flag;
	}

	@Override
	public void remove(Long id) {
		try {
			open();
			String query = "DELETE FROM ingredients_tbl WHERE id = ?";
			prepareCreateStatement(query);
			List<Object> params = new ArrayList<>();
			params.add(id);
			setParams(params);
			executeStatement();
			close();
		}catch(SQLException e) {
			Logger.getLogger(IngredientRepository.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}

	@Override
	public DefaultTableModel populateTable() {
		String col[] = {"ID","Name","Description"};
		DefaultTableModel tableModel = new DefaultTableModel(col,0);
		List<Ingredient> ingredients = getAll();
		for(Ingredient ingredient:ingredients) {
			Object[] data = {ingredient.getId(),ingredient.getName(),ingredient.getDescription()};
			tableModel.addRow(data);
		}
		return tableModel;
	}

}
