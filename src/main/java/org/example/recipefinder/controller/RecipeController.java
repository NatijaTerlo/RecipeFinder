package org.example.recipefinder.controller;

import org.example.recipefinder.model.Recipe;
import org.example.recipefinder.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getRecipes(@RequestParam List<String> ingredients) {
        if (ingredients.isEmpty()) {
            return new ResponseEntity<>("Ingredients list cannot be empty", HttpStatus.BAD_REQUEST);
        }

        List<Recipe> recipes = recipeService.findRecipesByIngredients(ingredients);
        if (recipes.isEmpty()) {
            return new ResponseEntity<>("No recipes found for the provided ingredients", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        if (recipe == null || recipe.getName() == null || recipe.getName().isEmpty()) {
            return new ResponseEntity<>("Recipe name is required", HttpStatus.BAD_REQUEST);
        }
        Recipe savedRecipe = recipeService.addRecipe(recipe);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }
}
