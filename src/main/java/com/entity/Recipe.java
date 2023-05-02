package com.entity;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

	private Long recipeId;
    private String title;
    private String description;
    private Category category;
    private List<String> ingredients;
    private Profile profile;
    
    public Recipe() {
    	
    }

    public Recipe(Long recipeId, String title, String description, Category category, List<String> ingredients, Profile profile) {
        this.recipeId = recipeId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.ingredients = ingredients;
        this.profile = profile;
    }

    public Recipe(String title, String description, Category category, List<String> ingredients, Profile profile) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.ingredients = ingredients;
        this.profile = profile;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
	
}
