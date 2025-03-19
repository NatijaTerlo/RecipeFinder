package org.example.recipefinder.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Entity class representing an ingredient.
 * This class is used to store information about ingredients in the database.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the ingredient.
     * Must not be null or empty.
     */
    @NotNull(message = "Ingredient name cannot be null")
    @NotEmpty(message = "Ingredient name cannot be empty")
    private String name;
}
