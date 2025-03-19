package org.example.recipefinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {

    @NotNull(message = "Recipe name cannot be null")
    @NotEmpty(message = "Recipe name cannot be empty")
    private String name;

    @NotNull(message = "Instructions cannot be null")
    @NotEmpty(message = "Instructions cannot be empty")
    private String instructions;

    @NotNull(message = "Ingredients cannot be null")
    @NotEmpty(message = "Ingredients list cannot be empty")
    private List<String> ingredients;
}
