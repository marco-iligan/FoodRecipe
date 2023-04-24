package com.foodrecipe.service;

import java.util.List;

import com.foodrecipe.model.Ingredient;
import com.foodrecipe.repository.IngredientRepository;

public class IngredientService {
	private final IngredientRepository repo;
	
	public IngredientService() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		repo = new IngredientRepository();
	}
	
	public List<Ingredient> getAll() {
		return repo.getAll();
	}
	
	public int addIngredient(Ingredient request) {
		return repo.create(request);
	}
	
	public int updateIngredient(Ingredient request) {
		return repo.update(request);
	}
	
	public void deleteIngredient(Long id) {
		repo.remove(id);
	}
}
