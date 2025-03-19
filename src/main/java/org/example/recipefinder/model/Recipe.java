package org.example.recipefinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

/**
 * Entity class representing a recipe.
 * This class is used to store information about recipes in the database.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the recipe.
     * Cannot be null or empty.
     */
    @NotNull(message = "Recipe name cannot be null")
    @NotEmpty(message = "Recipe name cannot be empty")
    private String name;

    /**
     * Instructions for preparing the recipe.
     * Cannot be null or empty.
     */
    @NotNull(message = "Instructions cannot be null")
    @NotEmpty(message = "Instructions cannot be empty")
    private String instructions;

    /**
     * List of ingredients required for the recipe.
     * Cannot be null or empty.
     */
    @ManyToMany
    @JoinTable(
            name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    @NotNull(message = "Ingredients list cannot be null")
    private List<Ingredient> ingredients;

    /**
     * Number of servings the recipe yields.
     * Must be at least 1.
     */
    @Min(value = 1, message = "Servings must be at least 1")
    private int servings;

    /**
     * Dietary preference for the recipe.
     * Can be null if there is no dietary preference.
     */
    @Enumerated(EnumType.STRING)
    private DietaryPreference dietaryPreference; // Optional: Dietary preference (e.g., Vegan)
}

