package org.example.recipefinder.repository;

import org.example.recipefinder.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r JOIN r.ingredients i WHERE i.name IN :ingredients GROUP BY r HAVING COUNT(DISTINCT i) = :#{#ingredients.size()}")
    List<Recipe> findRecipesByIngredients(@Param("ingredients") List<String> ingredients);
}
