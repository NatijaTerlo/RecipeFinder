package org.example.recipefinder.service;

import org.example.recipefinder.model.Recipe;
import org.example.recipefinder.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findRecipesByIngredients(List<String> ingredientNames) {
        logger.info("Searching for recipes with ingredients: {}", ingredientNames);
        List<Recipe> recipes = recipeRepository.findRecipesByIngredients(ingredientNames);
        logger.info("Found {} recipes", recipes.size());
        return recipes;
    }

    public Recipe addRecipe(Recipe recipe) {
        logger.info("Adding new recipe: {}", recipe.getName());
        Recipe savedRecipe = recipeRepository.save(recipe);
        logger.info("Recipe added successfully with ID: {}", savedRecipe.getId());
        return savedRecipe;
    }
}
